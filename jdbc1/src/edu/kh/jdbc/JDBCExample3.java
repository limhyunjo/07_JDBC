package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		// 입력 받은 최소 급여 보다 많이 받고(이상)
		// 입력 받은 최고 급여보단 적게 받는(이하)
		// 사원의  사번, 이름, 급여를 급여 내림차순 조회
		
		// [실행화면]
		// 최소 급여 : 1000000
		// 최대 급여 : 3000000
		
		// (사번) / (이름) / (급여)
		// (사번) / (이름) / (급여)
		// (사번) / (이름) / (급여)
		 Connection conn = null; 
		 Statement stmt = null;
		 ResultSet rs = null;
		
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
	    
		Scanner sc = new Scanner(System.in);
		System.out.println("최소 급여 입력 : ");
		int input1 = sc.nextInt();
		System.out.println("최대 급여 입력 : ");
		int input2 = sc.nextInt();
		System.out.println("-------------------------------");
		
		String sql= "SELECT EMP_ID, EMP_NAME, SALARY "
				+ "FROM EMPLOYEE \r\n" 
				+ "WHERE SALARY BETWEEN " + input1 + " AND " + input2
		        + " ORDER BY SALARY DESC";
		
		stmt = conn.createStatement();
		rs= stmt.executeQuery(sql);
		
		while(rs.next()) {
			 // rs.next(): 다음 행으로 이동해서 
		     //            행이 있으면 true, 없으면 false
			 
			 String empId = rs.getString("EMP_ID");
			 String empName = rs.getString("EMP_NAME");
			 int salary = rs.getInt("SALARY");
			 
			 System.out.printf("%s / %s / %d\n",
						empId, empName, salary);
			}
			
		
		
		 }catch(Exception e) {
			   //SQLException, ClassNotFoundException 한 번에 처리
				e.printStackTrace();
			  }finally {
				  try {
					 /* 7. 사용 완료된 JDBC 객체 반환하기( 역순으로 ! )*/ 
				    if(rs !=null) rs.close();
				    if(stmt !=null) stmt.close();
				    if(conn !=null) conn.close();
					  
				  }catch(Exception e) {
					  e.printStackTrace();
				  }
			  }
				 
			}
			}