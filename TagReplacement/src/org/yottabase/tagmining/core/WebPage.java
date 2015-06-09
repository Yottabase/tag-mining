package org.yottabase.tagmining.core;

public class WebPage {
	
	private String trecID;
	
	private String pageHtml;

	public WebPage() {
		super();
	}
	
	public WebPage(String trecID, String pageHtml) {
		this.trecID = trecID;
		this.pageHtml = pageHtml;
	}

	public String getTrecID() {
		return trecID;
	}

	public void setTrecID(String trecID) {
		this.trecID = trecID;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

}
