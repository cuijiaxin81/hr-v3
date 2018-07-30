package com.icss.hr.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.sun.corba.se.spi.activation.Server;

/**
 * 员工控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class EmpController {

	@Autowired
	private EmpService service;

	/**
	 * 增加员工
	 * 
	 * @param emp
	 * @param request
	 * @param response
	 */
	@RequestMapping("/emp/add")
	public void addEmp(Emp emp, HttpServletRequest request, HttpServletResponse response) {
		
		service.addEmp(emp);
	}

	/**
	 * 分页查询员工
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emp/query")
	public Map<String, Object> queryEmp(String pageNum, String pageSize, HttpServletRequest request,
			HttpServletResponse response) {

		int pageNumInt = 1;

		try {
			pageNumInt = Integer.parseInt(pageNum);
		} catch (Exception e) {

		}

		int pageSizeInt = 10;

		try {
			pageSizeInt = Integer.parseInt(pageSize);
		} catch (Exception e) {

		}

		Pager pager = new Pager(service.getEmpCount(), pageSizeInt, pageNumInt);
		List<Emp> list = service.queryEmpByPage(pager);

		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 删除员工
	 * 
	 * @param empId
	 */
	@RequestMapping("/emp/delete")
	public void deleteEmp(Integer empId,HttpServletRequest request,
			HttpServletResponse response) {

		service.deleteEmp(empId);

	}

	/**
	 * 返回指定id的员工
	 * 
	 * @param empId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emp/get")
	public Emp queryEmpById(Integer empId,HttpServletRequest request,
			HttpServletResponse response) {

		return service.queryEmpById(empId);
	}

	/**
	 * 修改员工
	 */
	@RequestMapping("/emp/update")
	public void updateEmp(Emp emp, HttpServletRequest request, HttpServletResponse response) {

		service.updateEmp(emp);
	}
	
	/**
	 * 判断登录名是否存在
	 */
	@ResponseBody
	@RequestMapping("/emp/checkLoginName")
	public boolean checkLoginName(String empLoginName,HttpServletRequest request, HttpServletResponse response) {
		
		return service.checkLoginName(empLoginName);		
	}
	
	/**
	 * 登陆验证
	 */
	@ResponseBody
	@RequestMapping("/emp/login")
	public int checkLogin(String empLoginName,String empPwd,HttpServletRequest request, HttpServletResponse response) {
		
		int result = service.checkLogin(empLoginName, empPwd);
		
		//登陆成功后，在session中存储登陆标识
		if (result == 3) {
			HttpSession session = request.getSession();
			session.setAttribute("empLoginName", empLoginName);
		}
				
		return result;		
	}		
	
	/**
	 * 获得当前用户的头像数据
	 */
	@ResponseBody
	@RequestMapping("/emp/getEmpPic")
	public String getEmpPic(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		String empPic = service.queryEmpPic(empLoginName);
		return empPic;
	}
	
	/**
	 * 更新头像
	 */
	@RequestMapping("/emp/updateEmpPic")
	public void updateEmpPic(String imgBase64,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		service.updateEmpPic(empLoginName, imgBase64);
	}
	
	/**
	 * 返回当前用户的密码
	 */
	@ResponseBody
	@RequestMapping("/emp/getEmpPwd")
	public String getEmpPwd(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		String empPwd = service.queryEmpPwd(empLoginName);
		return empPwd;
	}		
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/emp/updateEmpPwd")
	public void updateEmpPwd(String empPwd,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		service.updateEmpPwd(empLoginName, empPwd);		
	}

}