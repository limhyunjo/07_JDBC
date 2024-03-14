package edu.kh.dept.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/department/search")
public class SearchServlet extends HttpServlet{

 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
 
	 
	 
	try {
		 // 파라미터 얻어오기
		String keyword = req.getParameter("keyword"); // 검색어
		
		// Service 객체 생성
		DepartmentService service = new DepartmentServiceImpl();
		
		List<Department> deptList = service.search();			
		
		  
		   
		//selectALL 참고 
		// 리스트에 있는것을 하나씩 꺼내서 
		
	    // 조회 결과가 없을 경우
		if(deptList.isEmpty()) {
			
		// Session 객체 생성 후 바로 값 추가 
		 req.getSession().setAttribute("message", "해당 부서가 존재하지 않습니다");
		 resp.sendRedirect("/"); // 전체 조회로 redirect
		
		// 부서코드 검색 시 성공하면 만들어진 화면으로 이동 
		// 실패하면 해당 부서 존재하지 않는다고 뜨기
		
		}else { // 조회 결과가 있을 경우
		req.setAttribute("deptList", deptList); 
		//forward할 JSP 경로
		String path = "/WEB-INF/views/search.jsp";
		req.getRequestDispatcher(path).forward(req, resp); // jsp로 forward

		}
			
		
		
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	 
 }
}
