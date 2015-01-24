package com.easyway.java.basic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;

/**
 * <pre>
 *  title: 数据同步类 java实现高性能的数据同步 [复制链接]
 *  
 *  小小程序员
 *  
 *  电梯直达 跳转到指定楼层 1# 发表于 2010-11-25 18:10 | 只看该作者 回帖奖励
 *  最近在做一个银行的生产数据脱敏系统，今天写代码时遇到了一个“瓶颈”，脱敏系统需要将生产环境上Infoxmix里的数据原封不动的Copy到另一台
 *  Oracle数据库服务器上，然后对Copy后的数据作些漂白处理。为了将人为干预的因素降到最低，在系统设计时采用Java代码对数据作Copy,思路 如图：
 *  
 * 首
 *  先在代码与生产库间建立一个Connection，将读取到的数据放在ResultSet对象，然后再与开发库建立一个Connection。从
 *  ResultSet取出数据后通过TestConnection插入到开发库，以此来实现Copy。代码写完后运行程序，速度太慢了，一秒钟只能Copy
 *  一千条数据，生产库上有上亿条数据，按照这个速度同步完要到猴年马月呀,用PreparedStatement批处理速度也没有提交多少。我想能不能用多
 *  线程处理，多个人干活总比一个人干活速度要快。
 *  假设生产库有1万条数据，我开5个线程，每个线程分2000条数据，同时向开发库里插数据，Oracle支持高并发这样的话速度至少会提高好多倍，按照这
 *  个思路重新进行了编码，批处理设置为1万条一提交，统计插入数量的变量使用
 *  java.util.concurrent.atomic.AtomicLong，程序一运行，传输速度飞快CPU利用率在70%~90%，现在一秒钟可
 *  以拷贝50万条记录，没过几分钟上亿条数据一条不落地全部Copy到目标库。
 *      String queryStr = "SELECT * FROM xx";   
 *     ResultSet coreRs = PreparedStatement.executeQuery(queryStr);  
 * 
 * String queryStr = "SELECT * FROM xx";ResultSet coreRs = PreparedStatement.executeQuery(queryStr);
 * 实习生问如果xx表里有上千万条记录，你全部查询出来放到ResultSet, 那内存不溢出了么？Java在设计的时候已经考虑到这个问题了，并没有查询出所有的数据，而是只查询了一部分数据放到ResultSet,数据“用完”它 会自动查询下一批数据，你可以用setFetchSize(int rows)方法设置一个建议值给ResultSet,告诉它每次从数据库Fetch多少条数据。但我不赞成，因为JDBC驱动会根据实际情况自动调整 Fetch的数量。另外性能也与网线的带宽有直接的关系。 # F- e2 q2 G, b3 n+ D! }1 M
 * 相关代码 
 *  Description: 该类用于将生产核心库数据同步到开发库
 * </pre>
 * 
 * @author Tank Zhang
 */

public class CoreDataSyncImpl implements CoreDataSync {

	private List<String> coreTBNames; // 要同步的核心库表名

	private Logger log = Logger.getLogger(getClass());

	private AtomicLong currentSynCount = new AtomicLong(0L); // 当前已同步的条数

	private int syncThreadNum; // 同步的线程数

	@Override
	public void syncData(int businessType) throws Exception {

		for (String tmpTBName : coreTBNames) {
			log.info("开始同步核心库" + tmpTBName + "表数据");
			// 获得核心库连接
			Connection coreConnection = ConnectionHelper.getConnectionHelper()
					.getConnection();
			Statement coreStmt = coreConnection.createStatement();
			// 为每个线程分配结果集
			ResultSet coreRs = coreStmt.executeQuery("SELECT count(*) FROM "
					+ tmpTBName);
			coreRs.next();
			// 总共处理的数量
			long totalNum = coreRs.getLong(1);
			// 每个线程处理的数量
			long ownerRecordNum = (long) Math.ceil((totalNum / syncThreadNum));
			log.info("共需要同步的数据量：" + totalNum);
			log.info("同步线程数量：" + syncThreadNum);
			log.info("每个线程可处理的数量：" + ownerRecordNum);

			// 开启五个线程向目标库同步数据
			for (int i = 0; i < syncThreadNum; i++) {
				StringBuilder sqlBuilder = new StringBuilder();
				// 拼装后SQL示例
				// Select * From dms_core_ds Where id between 1 And 657398
				// Select * From dms_core_ds Where id between 657399 And 1314796

				// Select * From dms_core_ds Where id between 1314797 And
				// 1972194

				// Select * From dms_core_ds Where id between 1972195 And
				// 2629592

				// Select * From dms_core_ds Where id between 2629593 And
				// 3286990
				// ..
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between ")
						.append(i * ownerRecordNum + 1).append(" And ")
						.append((i * ownerRecordNum + ownerRecordNum));
				Thread workThread = new Thread(

				new WorkerHandler(sqlBuilder.toString(), businessType,
						tmpTBName));
				workThread.setName("SyncThread-" + i);
				workThread.start();
			}
			while (currentSynCount.get() < totalNum)
				;
			// 休眠一会儿让数据库有机会commit剩余的批处理(只针对JUnit单元测试,因为单元测试完成后会关闭虚拟器，使线程里的代码没有机会作提交操作);

			// Thread.sleep(1000 * 3);
			log.info("核心库" + tmpTBName + "表数据同步完成，共同步了" + currentSynCount.get()
					+ "条数据");
		}
	}// end for loop

	public void setCoreTBNames(List<String> coreTBNames) {
		this.coreTBNames = coreTBNames;
	}

	public void setSyncThreadNum(int syncThreadNum) {

		this.syncThreadNum = syncThreadNum;
	}

	// 数据同步线程

	final class WorkerHandler implements Runnable {
		ResultSet coreRs;
		String queryStr;

		int businessType;
		String targetTBName;

		public WorkerHandler(String queryStr, int businessType,
				String targetTBName) {

			this.queryStr = queryStr;

			this.businessType = businessType;

			this.targetTBName = targetTBName;
		}

		public void run() {

			try {

				// 开始同步
				launchSyncData();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
		}

		// 同步数据方法

		void launchSyncData() throws Exception {

			// 获得核心库连接
			Connection coreConnection = ConnectionHelper.getConnectionHelper()
					.getConnection();
			Statement coreStmt = coreConnection.createStatement();

			// 获得目标库连接
			Connection targetConn = ConnectionHelper.getConnectionHelper()
					.getConnection();
			targetConn.setAutoCommit(false);// 设置手动提交
			PreparedStatement targetPstmt = targetConn
					.prepareStatement("INSERT INTO " + targetTBName
							+ " VALUES (?,?,?,?,?)");
			ResultSet coreRs = coreStmt.executeQuery(queryStr);
			log.info(Thread.currentThread().getName() + "'s Query SQL::"
					+ queryStr);

			int batchCounter = 0; // 累加的批处理数量

			while (coreRs.next()) {
				targetPstmt.setString(1, coreRs.getString(2));
				targetPstmt.setString(2, coreRs.getString(3));
				targetPstmt.setString(3, coreRs.getString(4));
				targetPstmt.setString(4, coreRs.getString(5));
				targetPstmt.setString(5, coreRs.getString(6));
				targetPstmt.addBatch();
				batchCounter++;
				currentSynCount.incrementAndGet();// 递增

				if (batchCounter % 10000 == 0) { // 1万条数据一提交
					targetPstmt.executeBatch();
					targetPstmt.clearBatch();
					targetConn.commit();
				}
			}

			// 提交剩余的批处理
			targetPstmt.executeBatch();
			targetPstmt.clearBatch();
			targetConn.commit();

			// 释放连接
			// connectionFactory.release(targetConn, targetPstmt,coreRs);
		}
	}
}