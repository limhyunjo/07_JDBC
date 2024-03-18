package edu.kh.bookList.controller;

import java.io.IOException;

import edu.kh.bookList.model.service.BookService;
import edu.kh.bookList.model.service.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booklist/delete")
public class DeletServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			// 서비스 객체 생성
			BookService service = new BookServiceImpl();
			
			// 제출된 파라미터 얻어오기
			int bookNo = Integer.parseInt(req.getParameter("bookNo")); // 삭제할 부서 코드
			
			
			// 서비스 메서드 호출 후 결과 반환 받기
			int result = service.deleteBook(bookNo);
			
			// 서비스 결과에 따라서
			// Session에 "삭제 성공", "삭제 실패" 메시지를 속성을 추가
			
			String message =  null;
			HttpSession session = req.getSession();
			
			if(result > 0)	message = "삭제 성공";
			else		    message = "삭제 실패";
			
			session.setAttribute("message", message);
			
			// 전체 부서 조회를 재요청
			resp.sendRedirect("/booklist/selectAll");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	
	
}
