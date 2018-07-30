package com.icss.hr.job.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * ְ�������
 * 
 * @author Administrator
 *
 */
@Controller
public class JobController {

	@Autowired
	private JobService service;

	/**
	 * ����ְ��
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/add")
	public void addJob(Job job, HttpServletRequest request, HttpServletResponse response) {
		service.addJob(job);
	}

	/**
	 * ��ѯְ��
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/job/query")
	public List<Job> queryJob(HttpServletRequest request, HttpServletResponse response) {
		return service.queryJob();
	}

	/**
	 * ɾ��ְ��
	 * 
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/delete")
	public void deleteJob(Integer jobId, HttpServletRequest request, HttpServletResponse response) {
		service.deleteJob(jobId);
	}

	/**
	 * ͨ��id��ѯְ��
	 * 
	 * @param jobId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/job/get")
	public Job getJob(Integer jobId, HttpServletRequest request, HttpServletResponse response) {
		return service.queryJobById(jobId);
	}

	/**
	 * �޸�ְ��
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/update")
	public void updateJob(Job job, HttpServletRequest request, HttpServletResponse response) {
		service.updateJob(job);
	}
	
}
