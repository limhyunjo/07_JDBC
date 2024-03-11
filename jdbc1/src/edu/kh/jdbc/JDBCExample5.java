package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
 public static void main(String[] args) {
	

	  Connection conn = null;// Db 연결 정보를 가지고 있는 객체 (어느 계정)
	  Statement stmt = null;// SQL 수행 후 결과 반환 받는 객체
	  ResultSet rs = null;// SELECT 결과를 저장하는 객체
		
	  try {
		
	  Class.forName("oracle.jdbc.driver.OracleDriver"); 
		 
		/* 2-2) DB 연결 정보를 이용해 Connection 생성*/
		String type     = "jdbc:oracle:thin:@"; // 드라이버 종류
		String host     = "localhost";  // DB 서버 컴퓨터의 IP 주소
		String port     = ":1521";  // DB 서버 컴퓨터에 DB 프로그램 연결 번호
		String dbName   = ":xe";    // DB 이름
		String userName = "KH_LHJ"; // 사용자 계정
		String pw       = "KH1234"; // 계정 비밀번호
		
		conn 
		= DriverManager.getConnection(type + host + port + dbName, userName, pw);
		//-> 커넥션 만듦
		
		/* 만들어진 커넥션으로 SQL 수행 시 자동 커밋 비활성화! */
		conn.setAutoCommit(false);
		
		
		/* 3. SQL 작성 */
		Scanner sc = new Scanner(System.in);
		
		System.out.print("부서코드 입력 : ");
		String deptCode = sc.next();
		
		System.out.print("부서명 입력 : ");
		String deptTitle = sc.next();
		
		System.out.print("지역코드 입력 : ");
		String locationId = sc.next();
		
		String sql 
		= String.format("INSERT INTO DEPARTMENT4 VALUES('%s', '%s', '%s')",
				deptCode, deptTitle, locationId);
		
	//String.format printf와 비슷하지만 출력이 아니라 문자열로 만듦
		
		stmt = conn.createStatement(); // Statement객체 생성
		int result = stmt.executeUpdate(sql);//SQL 수행 후 결과 반환 받기
		//-> DML 수행 결과는 결과 행의 수 ( 숫자로 반환됨)
		
		/* 6. 수행 결과에 따라 트랜잭션 제어 처리 */
	
		if(result>0) {
			System.out.println("삽입 성공");
			conn.commit();
		}else {
			System.out.println("삽입 실패");
			conn.rollback();
		}
		
		
	  }catch(Exception e) {
		  e.printStackTrace();
	  }finally {
	  try {
			 /* 7. JDBC 자원 반환 */ 
		    if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }  
	  }
	  
	 }
	}
