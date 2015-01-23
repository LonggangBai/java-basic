package com.easyway.java.basic.threadlocal;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  Spring中Singleton模式的线程安全
 * spring中的单例 
 * 2011-01-06 14:00 
 * spring中管理的bean实例默认情况下是单例的[sigleton类型],就还有prototype类型 
 * 按其作用域来讲有sigleton,prototype,request,session,global session。 
 * 
 * spring中的单例与设计模式里面的单例略有不同，设计模式的单例是在整个应用中只有一个实例，而spring中的单例是在一个IoC容器中就只有一个实例。 
 * 但spring中的单例也不会影响应用的并发访问，【不会出现各个线程之间的等待问题，或是死锁问题】因为大多数时候客户端都在访问我们应用中的业务对象，而这些业务对象并 
 * 
 * 没有做线程的并发限制，只是在这个时候我们不应该在业务对象中设置那些容易造成出错的成员变量，在并发访问时候这些成员变量将会是并发线程中的共享对象，那么这个时候 
 * 
 * 就会出现意外情况。 
 * 
 * 那么我们的Eic-server的所有的业务对象中的成员变量如，在Dao中的xxxDao,或controller中的xxxService，都会被多个线程共享，那么这些对象不会出现同步问题吗，比如会造 
 * 
 * 成数据库的插入，更新异常？ 
 * 还有我们的实体bean，从客户端传递到后台的controller-->service-->Dao,这一个流程中，他们这些对象都是单例的，那么这些单例的对象在处理我们的传递到后台的实体bean不 
 * 
 * 会出问题吗？ 
 * 答：[实体bean不是单例的]，并没有交给spring来管理，每次我们都手动的New出来的【如EMakeType et = new EMakeType();】，所以即使是那些处理我们提交数据的业务处理类 
 * 
 * 是被多线程共享的，但是他们处理的数据并不是共享的，数据时每一个线程都有自己的一份，所以在数据这个方面是不会出现线程同步方面的问题的。但是那些的在Dao中的 
 * 
 * xxxDao,或controller中的xxxService，这些对象都是单例那么就会出现线程同步的问题。但是话又说回来了，这些对象虽然会被多个进程并发访问，可我们访问的是他们里面的方 
 * 
 * 法，这些类里面通常不会含有成员变量，那个Dao里面的ibatisDao是框架里面封装好的，已经被测试，不会出现线程同步问题了。所以出问题的地方就是我们自己系统里面的业务 
 * 
 * 对象，所以我们一定要注意这些业务对象里面千万不能要独立成员变量，否则会出错。 
 * 
 * 所以我们在应用中的业务对象如下例子； 
 * controller中的成员变量List和paperService： 
 * public class TestPaperController extends BaseController { 
 * private static final int List = 0; 
 * @Autowired 
 * @Qualifier("papersService") 
 * private TestPaperService papersService ; 
 * public Page queryPaper(int pageSize, int page,TestPaper paper) throws EicException{ 
 *   RowSelection localRowSelection = getRowSelection(pageSize, page); 
 *   List<TestPaper> paperList = papersService.queryPaper(paper,localRowSelection); 
 *   Page localPage = new Page(page, localRowSelection.getTotalRows(), 
 *     paperList); 
 *   return localPage; 
 *    
 * } 
 * 
 * 
 * service里面的成员变量ibatisEntityDao： 
 * 
 * @SuppressWarnings("unchecked") 
 * @Service("papersService") 
 * @Transactional(rollbackFor = { Exception.class }) 
 * public class TestPaperServiceImpl implements TestPaperService { 
 * @Autowired 
 * @Qualifier("ibatisEntityDao") 
 * private IbatisEntityDao ibatisEntityDao; 
 * private static final String NAMESPACE_TESTPAPER = "com.its.exam.testpaper.model.TestPaper"; 
 * private static final String BO_NAME[] = { "试卷仓库" }; 
 * private static final String BO_NAME2[] = { "试卷配置试题" }; 
 * private static final String BO_NAME1[] = { "试卷试题类型" }; 
 * private static final String NAMESPACE_TESTQUESTION="com.its.exam.testpaper.model.TestQuestion"; 
 * public List<TestPaper> queryPaper(TestPaper paper,RowSelection paramRowSelection) throws EicException{ 
 *   try { 
 * 
 *    return (List<TestPaper>) ibatisEntityDao.queryForListWithPage( 
 *     NAMESPACE_TESTPAPER, "queryPaper", paper,paramRowSelection); 
 *   } catch (Exception exception) { 
 *    exception.printStackTrace(); 
 *    throw new EicException(exception, "eic", "0001", BO_NAME); 
 *   } 
 * 
 * } 
 * 
 * 由上面可以看出，虽然我们这个应用里面含有成员变量，但是并不会出现线程同步方面的问题，因为，controller里面的成员变量private TestPaperService papersService ;之 
 * 
 * 所以会成为成员变量，我们的目的是注入，将其实例化进而访问里面的方法，private static final int List = 0;是final的不会被改变。 
 * service里面的private IbatisEntityDao ibatisEntityDao;是框架本身的线程同步问题已解决【其解决方案很有可能就是使用ThreadLocal，见下面】。 
 * 
 * 
 * 
 * 这下面的bean 一个是通过BeanFactory getBean得到，一个是业务对象testPaper.getClass()，得到，通过不同客户端的浏览器访问，可得到下面结论， 
 * springIoC容器管理的bean就是单例，因为不同的访问均得到相同的对象【在应用开启的状态下，不重新启动应用下，即在同一次的应用运行中】 
 * 
 * -------------------------spring 中的sigleton ,这才是真正的整个应用下面就一个实例:class 
 * 
 * com.its.exam.testpaper.service.impl.TestPaperServiceImpl$$EnhancerByCGLIB$$584b889d 
 * -------------------------spring 中的sigleton ,这才是真正的整个应用下面就一个实例:class 
 * 
 * com.its.exam.testpaper.service.impl.TestPaperServiceImpl$$EnhancerByCGLIB$$584b889d 
 * -------------------------spring 中的sigleton ,这才是真正的整个应用下面就一个实例:class 
 * 
 * com.its.exam.testpaper.service.impl.TestPaperServiceImpl$$EnhancerByCGLIB$$584b889d 
 * -------------------------spring 中的sigleton ,这才是真正的整个应用下面就一个实例:class 
 * 
 * com.its.exam.testpaper.service.impl.TestPaperServiceImpl$$EnhancerByCGLIB$$584b889d 
 * 
 * Spring框架对单例的支持是采用单例注册表的方式进行实现的，详见“Spring设计模式——单例模式”这篇文章。 
 * 
 * 至于spring如何实现那些个有状态bean[如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder]的线程安全，如下原理：详见“ThreadLocal百度百科”，还可以参考网上这篇文章：“浅谈Spring声明式事务管理ThreadLocal和JDKProxy”。 
 * 
 * 当使用ThreadLocal维护变量[仅仅是变量,因为线程同步的问题就是成员变量的互斥访问出问题]时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都 
 * 
 * 可以独立地改变自己的副本，而不会影响其它线程所对应的副本。 
 * 原理概念：为每一个使用该变量的线程都提供一个变量值的副本，是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。从线程的角度看，就好像每一个线 
 * 
 * 程都完全拥有该变量。【每个线程其实是改变的是自己线程的副本，而不是真正要改变的变量，所以效果就是每个线程都有自己的，“这其实就将共享变相为人人有份!”】 
 * 虽然使用ThreadLocal会带来更多的内存开销，但这点开销是微不足道的。因为保存在ThreadLocal中的对象，通常都是比较小的对象。 
 * 基本概念为每一个使用该变量的线程都提供一个变量值的副本，是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。从线程的角度看，就好像每一个线程都完全拥有该变量。 
 * 
 * 它主要由四个方法组成initialValue()，get()，set(T)，remove()，其中值得注意的是initialValue()，该方法是一个protected的方法，显然是为了子类重写而特意实现的。该方法返回当前线程在该线程局部变量的初始值，这个方法是一个延迟调用方法，在一个线程第1次调用get()或者set(Object)时才执行，并且仅执行1次。ThreadLocal中的确实实现直接返回一个null： 举例ThreadLocal的原理 
 * 
 * ThreadLocal是如何做到为每一个线程维护变量的副本的呢？其实实现的思路很简单，在ThreadLocal类中有一个Map，用于存储每一个线程的变量的副本。比如下面的示例实现：
 * 
 * 
 * 使用方法　　ThreadLocal 的使用 

　　使用方法一： 

　　Hibernate的文档时看到了关于使ThreadLocal管理多线程访问的部分。具体代码如下 

　　1. public static final ThreadLocal session = new ThreadLocal(); 

　　2. public static Session currentSession() { 

　　3. Session s = (Session)session.get(); 

　　4. //open a new session,if this session has none 

　　5. if(s == null){ 

　　6. s = sessionFactory.openSession(); 

　　7. session.set(s); 

　　8. } 

　　return s; 

　　9. } 

　　我们逐行分析 

　　1。 初始化一个ThreadLocal对象，ThreadLocal有三个成员方法 get()、set()、initialvalue()。 

　　如果不初始化initialvalue，则initialvalue返回null。 

　　3。session的get根据当前线程返回其对应的线程内部变量，也就是我们需要的net.sf.hibernate.Session（相当于对应每个数据库连接）.多线程情况下共享数据库链接是不安全的。ThreadLocal保证了每个线程都有自己的s（数据库连接）。 

　　5。如果是该线程初次访问，自然，s（数据库连接）会是null，接着创建一个Session，具体就是行6。 

　　6。创建一个数据库连接实例 s 

　　7。保存该数据库连接s到ThreadLocal中。 

　　8。如果当前线程已经访问过数据库了，则从session中get()就可以获取该线程上次获取过的连接实例。 

 * 　　使用方法二 

　　当要给线程初始化一个特殊值时，需要自己实现ThreadLocal的子类并重写该方法，通常使用一个内部匿名类对ThreadLocal进行子类化，EasyDBO中创建jdbc连接上下文就是这样做的： 



 * </pre>
 * 
 * @author Administrator
 * 
 */
public class JDBCContext {

	private static Logger logger = LoggerFactory.getLogger(JDBCContext.class);

	private DataSource ds;

	protected Connection connection;

	private boolean isValid = true;

	private static ThreadLocal jdbcContext;

	private JDBCContext(DataSource ds) {

		this.ds = ds;

		// createConnection();

	}

	public static JDBCContext getJdbcContext(javax.sql.DataSource ds)

	{

		if (jdbcContext == null)
			jdbcContext = new JDBCContextThreadLocal(ds);

		JDBCContext context = (JDBCContext) jdbcContext.get();

		if (context == null) {

			context = new JDBCContext(ds);

		}

		return context;

	}

	private static class JDBCContextThreadLocal extends ThreadLocal {

		public javax.sql.DataSource ds;

		public JDBCContextThreadLocal(javax.sql.DataSource ds)

		{

			this.ds = ds;

		}

		protected synchronized Object initialValue() {

			return new JDBCContext(ds);

		}

	}

}
