package edu.kh.bookList.model.dao;

//지정된 위치의 static을 모두 가져와 사용
import static edu.kh.bookList.common.JDBCTemplate.*;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.bookList.model.dto.Book;





public class BookDAOImpl implements BookDAO{

	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop; // Map<String, String> 형태, 파일 입출력 쉬움
	
	
	// 기본 생성자
	// - 객체 생성 시 sql.xml 파일 내용을 읽어와 prop에 저장
	public BookDAOImpl() {
		
		try {
			prop = new Properties();
			String path = BookDAOImpl.class.getResource("/edu/kh/bookList/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 도서 전체 조회
	@Override
	public List<Book> selectAll(Connection conn) throws SQLException {
		
		// 결과 저장용 변수 선언 / 객체 생성
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			// SQL 작성
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			// ResultSet 한 행 씩 접근해서 컬럼 값을 얻어온 후
			//List에 옮겨 담기
			while(rs.next()) {
				
				int bookId     = rs.getInt("BOOK_NO");
				String bookType  = rs.getString("BOOK_TYPE");
				String bookName = rs.getString("BOOK_NAME");
				String bookAuthor = rs.getString("BOOK_AUTHOR");
				String bookPublisher = rs.getString("BOOK_PUBLISHER");
				
				Book book = new Book(bookId, bookType, bookName, bookAuthor, bookPublisher);
				
				bookList.add(book);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return bookList;
	}

	// 도서 추가
	@Override
	public int insertBook(Connection conn, Book book) throws SQLException {
		

		// 1. 결과 저장용 변수 선언 / 객체 생성
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("insertBook");
			
			// 3. PreparedStatement 객체 생성 + SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			
			// 4. ?에 알맞은 값 대입
			pstmt.setInt(1, book.getBookNo());
			pstmt.setString(2, book.getBookType());
			pstmt.setString(3, book.getBookName());
			pstmt.setString(4, book.getBookAuthor());
			pstmt.setString(5, book.getBookPublisher());
			
			// 5. SQL(INSERT) 수행 후 결과(삽입 성공한 행의 개수) 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			// 6. 사용한 JDBC 객체 자원 반환 (단, 커넥션 제외)
			close(pstmt);
		}
		
		return result;
	}

	// 도서 수정
	@Override
	public int updateBook(Connection conn, Book book) throws SQLException {
		
	int result = 0; // 결과 저장용 변수 선언
		
		try { //  try 내부에서 DB랑 연결 후 나붕에 JDBC 자원들 반환
			
			//sql 얻어오기 key 값이 updateBook인 value 얻어오기
			String sql = prop.getProperty("updateBook");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book.getBookType());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getBookAuthor());
			pstmt.setString(4, book.getBookPublisher());
			pstmt.setInt(5, book.getBookNo());
			
			result = pstmt.executeUpdate();  // 실행
			
		}finally { // JDBC 객체 자원 무조건 반환 실행 하는 구문
			close(pstmt);
		}
		
		return result;
	}

	// 부서 삭제
	@Override
	public int deleteBook(Connection conn, int bookNo) throws SQLException {
		
         int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNo);
			
			result = pstmt.executeUpdate(); // DML 수행
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

   // 도서 한 행 선택
	@Override
	public Book selectOne(Connection conn, int bookNo) throws SQLException {
		  // 결과 저장용 변수 선언
		  Book book = null; 
			
		  try {
		 
			//SQL 얻어오기
			String sql = prop.getProperty("selectOne");
			  
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			
			//SQL(SELECT) 수행 후 결과 (ResultSet) 반환 받기
			rs = pstmt.executeQuery();
			
			// PK를 조건으로 삼은 SELECT문은
			// 조회 성공 시 1행만 조회됨! --> while 대신 if문으로 1회만 접근
			
			if(rs.next()) {
			
			book = new Book(
					bookNo, rs.getString("BOOK_TYPE"),
					rs.getString("BOOK_NAME"),
					rs.getString("BOOK_AUTHOR"),
					rs.getString("BOOK_PUBLISHER")
					
						);
				
			}
			
			
		  }finally {
			// 사용한 JDBC 객체 자원 반환 (커넥션 제외)
			 close(rs);
			 close(pstmt);
			
		  }
		  
			return book;// 조회 실패 시 null, 성공 시 null 아님
		}

		
		
	}

	



