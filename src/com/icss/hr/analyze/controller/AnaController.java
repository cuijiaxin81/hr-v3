package com.icss.hr.analyze.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.dto.DeptSal;
import com.icss.hr.analyze.service.AnaService;

/**
 * ���ݷ���������
 * @author Administrator
 *
 */
@Controller
public class AnaController {
	
	@Autowired
	private AnaService service;
	
	/**
	 * ��������ͳ��
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ana/queryDeptEmpCount")
	public List<DeptEmpCount> queryDeptEmpCount(HttpServletRequest request,HttpServletResponse response) {
		
		return service.queryEmpCount();		
	}
	
	/**
	 * ְ��ƽ������
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ana/queryJobAvgSal")
	public List<Map<String, Object>> queryJobAvgSal(HttpServletRequest request,HttpServletResponse response) {
		
		return service.queryJobAvgSal();	
	}
	
	/**
	 * ���������߹���
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ana/queryDeptSal")
	public List<DeptSal> queryDeptSal(HttpServletRequest request,HttpServletResponse response) {
		
		return service.queryDeptSal();	
	}

}