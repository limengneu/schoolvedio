/**
 * @文件名称：VedioServiceTest.java
 * @类路径：com.school.biz.service
 * @版权:Copyright (c)2012
 * @公司名称：杭州商友全球网信息技术有限公司
 * @作者：limeng
 * @时间：Aug 17, 201210:56:19 AM
 */
package com.school.biz.service;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @描述：
 * @部门：研发部
 * @作者：limeng
 * @创建时间：Aug 17, 201210:56:19 AM
 */
public class VedioServiceTest {

	@Test
	public void test() {
        String src="http://player.56.com/v_71259119.swf";
		System.out.println(StringUtils.substringAfter(src, "v_"));
	}
	
	
	@Test
	public void testURLdecode() throws Exception {
        String src=" http://player.56.com/v_71259119.swf %E6%88%91%E7%9A%84%E8%A7%86%E9%A2%91 test %E6%B5%8B%E8%AF%95";
		System.out.println(URLDecoder.decode(src, "UTF-8"));
	}

}
