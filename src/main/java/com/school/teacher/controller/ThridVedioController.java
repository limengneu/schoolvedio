/**
 * @文件名称：ThridVedioController.java
 * @类路径：com.school.teacher.controller
 * @版权:Copyright (c)2012
 * @公司名称：杭州商友全球网信息技术有限公司
 * @作者：limeng
 * @时间：Aug 15, 20129:13:27 PM
 */
package com.school.teacher.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.biz.thrid.ThridVedioService;

/**
 * @描述：
 * @部门：研发部
 * @作者：limeng
 * @创建时间：Aug 15, 20129:13:27 PM
 */
@Controller
@RequestMapping("/vedio/")
public class ThridVedioController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ThridVedioController.class);
	
	@Autowired
	private ThridVedioService thridVedioService;
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView upload(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("home");
		String result= thridVedioService.uploadVedio("home");
		logger.error("this is a test");		
		mav.addObject("uploadResult", result);

		return mav;
	}
	
	@RequestMapping(value = "sucess", method = RequestMethod.GET)
	public ModelAndView sucess(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("home");
		Map requestParams = request.getParameterMap();
		mav.addObject("uploadResult", requestParams);
		mav.addObject("result", "sucess");
		return mav;
	}
	
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public ModelAndView fail(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("home");
		Map requestParams = request.getParameterMap();
		mav.addObject("uploadResult", requestParams);
		mav.addObject("result", "fail");
		return mav;
	}

}
