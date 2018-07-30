package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * 测试员工dao
 * @author Administrator
 *
 */
public class TestEmpService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmpService service = context.getBean(EmpService.class);
	
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp("李四","lisi","123456","lisi@163.com","13012345678"
				,Date.valueOf("2005-01-01"),6000.0,dept,job,null,"无");	
		
		service.addEmp(emp);		
	}
		
	
	@Test
	public void testUpdate() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(50);
		
		Job job = new Job();
		job.setJobId(2);
		
		Emp emp = new Emp(51,"张三三","zhangsansan","666666","zhangsan33333@163.com","13066666666"
				,Date.valueOf("2016-06-06"),7666.0,dept,job,null,"擅长云计算");	
		
		service.updateEmp(emp);
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		service.deleteEmp(35);
		
	}
	
	@Test
	public void testQueryById() throws SQLException {
		
		Emp emp = service.queryEmpById(2);
		System.out.println(emp);
	
	}
	
	@Test
	public void testGetCount() throws SQLException {
		
		int count = service.getEmpCount();
		System.out.println(count);
		
	}
	
	@Test
	public void testQueryByPage() throws SQLException {
		
		Pager pager = new Pager(service.getEmpCount(), 7, 2);
		
		List<Emp> list = service.queryEmpByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
		
	}
	
	@Test
	public void testQueryByName() throws SQLException {
		
		String empPwd = service.queryEmpPwd("zhangsan");
		System.out.println(empPwd);
	}
	
	@Test
	public void testQueryEmpPic() throws SQLException {
		
		String empPic = service.queryEmpPic("zhangsan");
		System.out.println(empPic);
		
	}	
	
	@Test
	public void testUpdateEmpPic() throws SQLException {
		
		service.updateEmpPic("jack7", null);
		
	}	
	
	@Test
	public void testUpdateEmpPwd() throws SQLException {
		
		service.updateEmpPwd("jack7", "666999");
		
	}	
	

	@Test
	public void testCheckLogin() throws SQLException {
		
		int result = service.checkLogin("zhangsan", "123456");
		System.out.println(result);
		
	}

}