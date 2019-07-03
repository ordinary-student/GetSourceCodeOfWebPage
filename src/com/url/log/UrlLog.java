package com.url.log;

/**
 * 网址解析日志记录类
 * 
 * @author ordinary-student
 *
 */
public class UrlLog
{
	private String time;
	private String url;

	public UrlLog()
	{
	}

	public UrlLog(String time, String url)
	{
		this.time = time;
		this.url = url;
	}

	@Override
	public String toString()
	{
		return time + "---" + url;
	}

	/**
	 * @return time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * @param time
	 *            要设置的 time
	 */
	public void setTime(String time)
	{
		this.time = time;
	}

	/**
	 * @return url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            要设置的 url
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}
}
