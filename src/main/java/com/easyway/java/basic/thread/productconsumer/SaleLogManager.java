package com.easyway.java.basic.thread.productconsumer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 日活统计表
 * 
 * 
 */
class SaleDailyLogModel {

	private static final long serialVersionUID = 264163675682797995L;

	public String getKey() {
		return this.actId + "_" + this.inviteCode + "_" + this.itemId;
	}

	public void addLog(SaleDailyLogModel log) {
		this.pageClickCount = this.pageClickCount + log.getPageClickCount();
		this.downloadCount = this.downloadCount + log.getDownloadCount();
		this.inviterPage = this.inviterPage + log.getInviterPage();
		this.inviteePage = this.inviteePage + log.getInviteePage();
		this.detailPage = this.detailPage + log.getDetailPage();
		this.shareClick = this.shareClick + log.getShareClick();
		this.sharePage = this.sharePage + log.getSharePage();
		this.download = this.download + log.getDownload();
		this.inviterBuy = this.inviterBuy + log.getInviterBuy();
		this.inviteeBuy = this.inviteeBuy + log.getInviteeBuy();
	}

	public void clearLog() {
		this.pageClickCount = 0;
		this.downloadCount = 0;
		this.inviterPage = 0;
		this.inviteePage = 0;
		this.detailPage = 0;
		this.shareClick = 0;
		this.sharePage = 0;
		this.download = 0;
		this.inviterBuy = 0;
		this.inviteeBuy = 0;
		this.timestamp = System.currentTimeMillis();
	}

	private Long id;

	private Long actId; // 活动id

	private String inviteCode = "";

	private Long itemId;

	private Integer pageClickCount = 0; // 页面点击数量

	private Integer downloadCount = 0; // 下载数量

	private Date date; // 日期

	private long timestamp = System.currentTimeMillis();

	private int inviterPage;

	private int inviteePage;

	private int detailPage;

	private int shareClick;

	private int sharePage;

	private int download;

	private int inviterBuy;

	private int inviteeBuy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActId() {
		return actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	public Integer getPageClickCount() {
		return pageClickCount;
	}

	public void setPageClickCount(Integer pageClickCount) {
		this.pageClickCount = pageClickCount;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getInviterPage() {
		return inviterPage;
	}

	public void setInviterPage(int inviterPage) {
		this.inviterPage = inviterPage;
	}

	public int getInviteePage() {
		return inviteePage;
	}

	public void setInviteePage(int inviteePage) {
		this.inviteePage = inviteePage;
	}

	public int getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(int detailPage) {
		this.detailPage = detailPage;
	}

	public int getShareClick() {
		return shareClick;
	}

	public void setShareClick(int shareClick) {
		this.shareClick = shareClick;
	}

	public int getSharePage() {
		return sharePage;
	}

	public void setSharePage(int sharePage) {
		this.sharePage = sharePage;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getInviterBuy() {
		return inviterBuy;
	}

	public void setInviterBuy(int inviterBuy) {
		this.inviterBuy = inviterBuy;
	}

	public int getInviteeBuy() {
		return inviteeBuy;
	}

	public void setInviteeBuy(int inviteeBuy) {
		this.inviteeBuy = inviteeBuy;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
/**
 * 背景：在活动运营时，通常我们会统计一下数据，比如 页面访问次数，按钮点击次数等等数据，当访问用户过多，高并发情况下，这些数据实时入库必然给数据库造成巨大的压力。所以，对高并发情况的下的数据统计，可采取延时入库的方案。
这里举个例子：
1.定义 实体类
 * @author longgangbai
 *
 */
public class SaleLogManager {

	protected SaleLogManager saleLogManager;

	private Map<String, SaleDailyLogModel> dailyLogMap = new ConcurrentHashMap<String, SaleDailyLogModel>();

	public void saveSaleDailyLog(SaleDailyLogModel saleDailyLogModel) {  
	    if(dailyLogMap.get(saleDailyLogModel.getKey()) == null) {  
	        dailyLogMap.put(saleDailyLogModel.getKey(),saleDailyLogModel);  
	    }  
	    SaleDailyLogModel dailyLog = dailyLogMap.get(saleDailyLogModel.getKey());  
	    synchronized (dailyLog) {  
	        dailyLog.addLog(saleDailyLogModel);//if也可设置一个固定的时间段入库  
	        if(System.currentTimeMillis() - dailyLog.getTimestamp() > ((int) (Math.random() * 60000 + 30000))) {  
	            try {  
	                saleLogManager.saveSaleDailyLog(dailyLog);//入库  
	                dailyLog.clearLog();  
	            } catch (Exception e) {  
	                e.printStackTrace();
	            }  
	        }  
	    }  
	      
	}
}