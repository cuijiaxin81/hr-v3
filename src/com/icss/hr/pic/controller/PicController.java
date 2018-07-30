package com.icss.hr.pic.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.hr.common.Pager;
import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;


/**
 * ͼ�������
 * 
 * @author Administrator
 *
 */
@Controller
public class PicController {

	@Autowired
	private PicService service;

	/**
	 * �ϴ�ͼƬ
	 * 
	 * @param picInfo
	 * @param picData
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/pic/add")
	public void add(String picInfo, @RequestParam("picData") MultipartFile picData, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// ��õ�¼��
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		// ��װpojo����
		Pic pic = new Pic(picData.getOriginalFilename(), picInfo, picData.getSize(), empLoginName, picData.getBytes(),
				new Date());

		service.addPic(pic);
	}

	/**
	 * ����ͼƬ�б�
	 */
	@ResponseBody
	@RequestMapping("/pic/query")
	public Map<String, Object> query(String pageNum, String pageSize, HttpServletRequest request,
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
				
		Pager pager = new Pager(service.getPicCount(), pageSizeInt, pageNumInt);
		List<Pic> list = service.queryPicByPage(pager);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);
		
		return map;
	}
	
	/**
	 * ����id����ͼƬ����������
	 */
	@ResponseBody
	@RequestMapping("/pic/get")
	public byte[] get(Integer picId,HttpServletRequest request,
			HttpServletResponse response) {
		
		return service.queryPicById(picId).getPicData();		
	}
	
	/**
	 * ����ָ��idͼƬ
	 * @throws IOException 
	 */
	@RequestMapping("/pic/download")
	public void download(Integer picId,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		//�������
		Pic pic = service.queryPicById(picId);
		
		//�����ļ���ת��
		String filename = new String(pic.getPicName().getBytes("utf-8"),"iso-8859-1");
		
		//���ñ�ͷ��֪ͨ������Ը�����ʽ��������
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		//�����
		OutputStream out = response.getOutputStream();
		
		//�����Ӧ���ͻ���
		FileCopyUtils.copy(pic.getPicData(), out);		
	}
	
	/**
	 * ɾ��ͼƬ
	 */
	@RequestMapping("/pic/delete")
	public void delete(Integer picId,HttpServletRequest request,
			HttpServletResponse response) {
		
		service.deletePic(picId);
								
	}
	

}