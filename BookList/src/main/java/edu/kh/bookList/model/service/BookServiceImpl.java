package edu.kh.bookList.model.service;

//지정된 위치의 static을 모두 가져와 사용
import static edu.kh.bookList.common.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.bookList.model.dao.BookDAO;
import edu.kh.bookList.model.dao.BookDAOImpl;
import edu.kh.bookList.model.dto.Book;







//Service : 비즈니스 로직 처리(실제 업무)
public class BookServiceImpl implements BookService{

	// 

	   private BookDAO dao = null;
	  
		public BookServiceImpl() {
			dao = new BookDAOImpl();
		}

		//도서 전체 조회
		@Override
		public List<Book> selectAll() throws SQLException {
		// 커넥션-> DAO 메서드 호출 -> 커넥션 닫기 -> 결과 반환
			
			
			Connection conn = getConnection();
			
			//DAO 메서드 호출
			List<Book> bookList = dao.selectAll(conn);
			
			close(conn);
			
			return bookList;
		}

		// 도서 추가
		@Override
		public int insertBook(Book book) throws SQLException {
			
			int result = 0; 
			
			Connection conn = getConnection();
			
			
			try {
			
				result = dao.insertBook(conn, book);
				
				
				if(result > 0)	commit(conn);
				else			rollback(conn);
		
			} catch(SQLException e) {			
				
				e.printStackTrace();
				
				rollback(conn);
			
					
				
			} finally { 

				close(conn);
			}

			return result;
		}

		// 도서 수정
		@Override
		public int updateBook(Book book) throws SQLException {
			
			
			Connection conn = getConnection();
			
			int result = dao.updateBook(conn, book);
			
			if(result > 0)	commit(conn);
			else			rollback(conn);
			
			close(conn); 
			
			return result;
			
		}

		

	

        // 도서 삭제
		@Override
		public int deleteBook(int bookNo) throws SQLException {
			Connection conn = getConnection();
			
			int result = dao.deleteBook(conn, bookNo);
			
			if(result > 0)	commit(conn);
			else			rollback(conn);
			
			close(conn);
			
			return result;
		}


		@Override
		public int selectBook(int bookNo) throws SQLException {
			Connection conn = getConnection();
			
			// 2. DAO 메서드 호출 후 결과 반환 받기
			Book book = dao.selectOne(conn,bookNo);
			
			// 3. 커넥션 반환
			close(conn);
			
			// 4. 결과 반환
			return bookNo;
		}

		
}
