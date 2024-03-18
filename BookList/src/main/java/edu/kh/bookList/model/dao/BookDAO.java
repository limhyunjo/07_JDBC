package edu.kh.bookList.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.bookList.model.dto.Book;

public interface BookDAO {

	/** 도서 전체 조회
	 * @param conn
	 * @return bookList
	 * @throws SQLException
	 */
	List<Book> selectAll(Connection conn) throws SQLException;

	/** 도서 추가
	 * @param conn
	 * @param book
	 * @return result(행 개수)
	 * @throws SQLException
	 */
	int insertBook(Connection conn, Book book) throws SQLException;

	/** 도서 수정
	 * @param conn
	 * @param book
	 * @return result
	 * @throws SQLException
	 */
	int updateBook(Connection conn, Book book) throws SQLException;

	/** 도서 삭제
	 * @param conn
	 * @param bookNo
	 * @return result
	 * @throws SQLException
	 */
	int deleteBook(Connection conn, int bookNo)throws SQLException;



	

}
