/**
 * @文件名称：ThridVedioController.java
 * @类路径：com.school.teacher.controller
 * @版权:Copyright (c)2012
 * @公司名称：杭州商友全球网信息技术有限公司
 * @作者：limeng
 * @时间：Aug 15, 20129:13:27 PM
 */
package com.school.teacher.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.school.biz.model.Vedio;
import com.school.biz.service.VedioService;
import com.school.biz.thrid.ThridVedioService;
import com.school.biz.thrid.model.SuccessVedio;
import com.school.biz.thrid.util.VedioUploadConstans;

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
	
	@Autowired
	private VedioService vedioService;
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public ModelAndView upload(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("/vedio/upload");
		String result= thridVedioService.uploadVedio("home");
		logger.error("this is a test");
		mav.addObject("uploadResult", result);
		return mav;
	}
	
	@RequestMapping(value = "sucess", method = RequestMethod.GET)
	public ModelAndView sucess(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("/vedio/sucess");
		String queryString=request.getQueryString();
		Vedio vedio=buildVeido(queryString);
		vedioService.saveVedio(vedio);
		mav.addObject("successVedio", vedio);
		return mav;
	}
	
	/**
	 * @方法功能说明：
	 * @修改者名字: limeng
	 * @修改时间：Aug 16, 20126:31:52 PM
	 * @参数：@param queryString
	 * @参数：@return
	 * @return:Vedio
	 */
	private Vedio buildVeido(String queryString) {
		Vedio vedio=new Vedio();
		String[] obejctStr=queryString.split("&");
		Map<String,String> obejctMap=new HashMap<String,String>();
		for(int index=0;index<obejctStr.length;index++){
		String[] restStr=obejctStr[index].split("=");
		obejctMap.put(restStr[0], restStr[1]);
		}
		vedio.setAttach(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_ATTACH));
		vedio.setAuthor(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_SID));
		vedio.setImage(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_COVER));
		vedio.setResult(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_RESULT));
		vedio.setPath(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_PLAYER));
		vedio.setTitle(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_SUBJECT));
		vedio.setVid(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_VID));
		vedio.setVpublic(obejctMap.get(VedioUploadConstans.RESULT_VEDIO_COOP_PUBLIC));
		return vedio;
	}

	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public ModelAndView fail(HttpServletRequest request, ModelAndView mav){
		mav.setViewName("/vedio/fail");
		String  requestParams = request.getQueryString();
		Vedio vedio= new Vedio();
		vedio.setStatus("f");
		vedioService.saveVedio(vedio);
		mav.addObject("queryString", requestParams);
		return mav;
	}

}
