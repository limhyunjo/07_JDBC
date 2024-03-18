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

@WebServlet("/booklist/update")
public class UpdateServlet extends HttpServlet{

	    
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		 try {
	  			
	  			// 서비스 객체 생성
	  			BookService service = new BookServiceImpl();
	  			
	  			// 전달 받은 파라미터를 모두 얻어와 객체에 저장
	  			
	  			int bookNo = Integer.parseInt(req.getParameter("bookNo"));
	  			String bookType = req.getParameter("bookType");
				String bookName	= req.getParameter("bookName");
				String bookAuthor = req.getParameter("bookAuthor");
				String bookpublisher = req.getParameter("bookPublisher");
				
				
	  			Book book = new Book(bookNo, bookType, bookName, bookAuthor, bookpublisher);
	  			
	  			int result = service.updateBook(book);
				
	  				
	  			// 수정 결과에 따라 message 지정
	  			String message = null;
	  			if(result > 0)	message = "도서 수정됨";
	  			else		   message = "도서 수정 실패";
	  			
	  			// message를 Session에 속성으로 추가
	  			req.getSession().setAttribute("message", message);
	  			
	  			// 전체 부서 조회 재요청
	  			resp.sendRedirect("/booklist/selectAll");
	  			
	  			
	  		}catch (Exception e) {
	  			e.printStackTrace();
	  		}
	  	
	  	}
	  		 
		 
		
	}
	  	

