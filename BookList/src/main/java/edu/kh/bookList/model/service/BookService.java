package edu.kh.bookList.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.bookList.model.dto.Book;

public interface BookService {

	/** 도서 전체 조회
	 * @return bookList 도서 목록
	 * @throws SQLException
	 */
	List<Book> selectAll() throws SQLException;

	/** 도서 추가
	 * @param book
	 * @return result(삽입된 행의 개수)
	 * @throws SQLException
	 */
	int insertBook(Book book) throws SQLException;

	/** 도서 수정
	 * @param book
	 * @return result
	 * @throws SQLException
	 */
	int updateBook(Book book)throws SQLException;



	/** 도서 삭제
	 * @param bookNo
	 * @return result
	 * @throws SQLException
	 */
	int deleteBook(int bookNo)throws SQLException;

}
