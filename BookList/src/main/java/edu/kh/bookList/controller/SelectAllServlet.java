package edu.kh.bookList.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import edu.kh.bookList.common.JDBCTemplate;
import edu.kh.bookList.model.dto.Book;
import edu.kh.bookList.model.service.BookService;
import edu.kh.bookList.model.service.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/booklist/selectAll")
public class SelectAllServlet extends HttpServlet {

  // Get 방식 요청 처리
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
	try {
		// Service 객체 생성
	BookService service = new BookServiceImpl();
	
	// 모든 도서 조회 Service 호출 후 결과 반환 받기
	
	List<Book> bookList = service.selectAll();			
	
	// DB 조회 결과를 request scope에 세팅하여
	// JSP로 요청 위임(forward)하기
	
	req.setAttribute("bookList", bookList);
	String path = "/WEB-INF/views/selectAll.jsp";
	req.getRequestDispatcher(path).forward(req, resp);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
}
