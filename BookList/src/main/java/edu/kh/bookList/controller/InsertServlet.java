package edu.kh.bookList.controller;

import java.io.IOException;

import edu.kh.bookList.model.dto.Book;
import edu.kh.bookList.model.service.BookService;
import edu.kh.bookList.model.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booklist/insert")
public class InsertServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// JSP로 요청 위임해서 부서 추가 화면 보여주기
		String path = "/WEB-INF/views/insert.jsp";	
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
		
			BookService service = new BookServiceImpl();
			

			int bookNo		= Integer.parseInt(req.getParameter("bookNo")); 
			String bookType  = req.getParameter("bookType"); 
			String bookName = req.getParameter("bookName"); 
			String bookAuthor = req.getParameter("bookAuthor");
			String bookPublisher = req.getParameter("bookPublisher");
			
			
			

			// 부서 추가 서비스 호출 후 결과 반환 받기
			Book book = new Book(bookNo, bookType, bookName, bookAuthor, bookPublisher);
			
			int result = service.insertBook(book);
			
			
			String message = null; // 응답 화면에서 alert()로 출력할 내용
			
			HttpSession session = req.getSession();
			
			if(result > 0) 	message = "도서 추가 성공";
			else			message = "도서 추가 실패";
			
			session.setAttribute("message", message);
			
			// 모든 부서 조회 페이지 재요청
			resp.sendRedirect("/booklist/selectAll");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
	}
	
	
	
}
