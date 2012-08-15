/**
 * @文件名称：ThridVedioServiceImpl.java
 * @类路径：com.school.biz.thrid.impl
 * @版权:Copyright (c)2012
 * @公司名称：杭州商友全球网信息技术有限公司
 * @作者：limeng
 * @时间：Aug 15, 20126:57:02 PM
 */
package com.school.biz.thrid.impl;

import java.net.HttpURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.biz.thrid.ThridVedioService;
import com.school.biz.thrid.util.VedioUploadConstans;
import com.school.common.utils.DigestUtils;

/**
 * @描述：
 * @部门：研发部
 * @作者：limeng
 * @创建时间：Aug 15, 20126:57:02 PM
 */
public class ThridVedioServiceImpl implements ThridVedioService {
	
	private static final Logger logger=LoggerFactory.getLogger(ThridVedioServiceImpl.class);

	private String appkey;

	private String secret;

	private String css;
	
	private String ourl;
	
	private String rurl;

	private String vedioPublic;
	
	private String uploadUrl;
	
	private HttpClient httpClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.biz.thrid.ThridVedioService#uploadVedio()
	 */
	public String uploadVedio(String user) {
		
		String urlParam=buildUrlPrama(user);
		
		GetMethod getmethod = new GetMethod(uploadUrl+"?"+urlParam);
		return httpRequest(getmethod);
	}

	
	private String httpRequest(HttpMethod method) {
		int responseCode = -1;
		try {
			httpClient.executeMethod(method);
			responseCode = method.getStatusCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				logger.error("http client error the responseCode is :"
						+ responseCode);
			}
			return method.getResponseBodyAsString();
		} catch (Exception ioe) {
			logger.error("http exception", ioe);
		} finally {
			method.releaseConnection();
		}
		return  null;

	}
	/**
	 * @方法功能说明：
	 * @修改者名字: limeng
	 * @修改时间：Aug 15, 20128:48:46 PM
	 * @参数：@param sid
	 * @return:void
	 */
	private String buildUrlPrama(String sid) {
		String sign=buildSign(getTsString());
		StringBuilder param=new StringBuilder();
		param.append(VedioUploadConstans.VEDIO_APPKEY);param.append("=");param.append(appkey);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_SECRET);param.append("=");param.append(secret);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_CSS);param.append("=");param.append(css);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_OURL);param.append("=");param.append(ourl);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_RURL);param.append("=");param.append(rurl);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_PUBLIC);param.append("=");param.append(vedioPublic);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_SID);param.append("=");param.append(sid);
		param.append("&");
		param.append(VedioUploadConstans.VEDIO_SIGN);param.append("=");param.append(sign);
		return param.toString();
	}

	private String buildSign(String ts) {

		String req = DigestUtils.Md5Encrypt("");

		String secendDigest = req + "#" + appkey + "#" + secret + "#" + ts;

		return DigestUtils.Md5Encrypt(secendDigest);

	}

	private String getTsString() {
		DateTime time = DateTime.now();
		String ts = time.getMillis() + "";
		return ts.substring(0, 10);
	}

	/**
	 * @param appkey
	 *            the appkey to set
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	/**
	 * @param secret
	 *            the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @param css
	 *            the css to set
	 */
	public void setCss(String css) {
		this.css = css;
	}

	/**
	 * @param ourl
	 *            the ourl to set
	 */
	public void setOurl(String ourl) {
		this.ourl = ourl;
	}

	/**
	 * @param rurl
	 *            the rurl to set
	 */
	public void setRurl(String rurl) {
		this.rurl = rurl;
	}

	/**
	 * @param vedioPublic
	 *            the vedioPublic to set
	 */
	public void setVedioPublic(String vedioPublic) {
		this.vedioPublic = vedioPublic;
	}

	/**
	 * @param uploadUrl the uploadUrl to set
	 */
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	/**
	 * @return the httpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * @param httpClient the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	

}
