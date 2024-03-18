package edu.kh.bookList.model.dto;

//DTO 객체 하나가 DEPARTMENT4 테이블의 한 행 데이터를 저장하는 용도
public class Book {
	
	private int bookNo; // 도서 번호
	private String bookType; // 도서 종류
	private String bookName; // 책 이름
	private String bookAuthor; // 저자
	private String bookPublisher; // 출판사
	
	
	// 기본 생성자
	public Book() {}

	
	// 매개 변수 생성자
	public Book(int bookNo, String bookType, String bookName, String bookAuthor, String bookPublisher) {
		super();
		this.bookNo = bookNo;
		this.bookType = bookType;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
     }


	// Getter / Setter
	public int getBookNo() {
		return bookNo;
	}


	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}


	public String getBookType() {
		return bookType;
	}


	public void setBookType(String bookType) {
		this.bookType = bookType;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBookAuthor() {
		return bookAuthor;
	}


	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}


	public String getBookPublisher() {
		return bookPublisher;
	}


	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}


	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookType=" + bookType + ", bookName=" + bookName + ", bookAuthor="
				+ bookAuthor + ", bookPublisher=" + bookPublisher + "]";
	} 

	
	
	
} 

