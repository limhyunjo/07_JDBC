package edu.kh.todoList.model.dao;

//지정된 위치의 static을 모두 가져와 사용
import static edu.kh.todoList.common.JDBCTemplate.*;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todoList.model.dto.Todo;

//DAO (Data Access Object) : DB에 접근하는 객체(SQL수행,결과 반환 받기)
// DB에 접근 가능한 유일한 객체

public class TodoDAOImpl implements TodoDAO{

	//JDBC 객체 참조 변수 선언 / java.sql
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Properties 객체 참조 변수 선언 (Map/ SQL 구문 읽어옴)
	private Properties prop;
	
	// 기본 생성자로 객체 생성 시
	// Properties 객체 생성 + xml 파일 내용 읽어오기
	public TodoDAOImpl() {
		
	  try { // 나중에 반환
	  prop = new Properties(); // Properties객체 생성
	  String path  // 경로 읽어옴
	  =TodoDAOImpl.class.getResource("/edu/kh/todoList/sql/sql.xml").getPath();
	  
	  prop.loadFromXML(new FileInputStream(path));
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	}

	// 할 일 목록 조회
	@Override
	public List<Todo> selectAll(Connection conn) throws SQLException {
		
	 // DAO오면 일단 결과 저장용 변수 만들기
		
	 // 결과 저장용 변수 선언/ 객체 생성
	 List<Todo> todoList = new ArrayList<Todo>();
		
	 try {
	  String sql = prop.getProperty("selectAll");
	  //selectAll이라는 sql을 읽어올 것이다
		 
	  stmt = conn.createStatement();
		
	  // SQL 수행 후 결과 반환 받기
	  rs = stmt.executeQuery(sql);
	  
	  // 조회 결과 한 행씩 접근
	  while(rs.next()) {
		int    todoNo 		= rs.getInt("TODO_NO");
		String todoTitile = rs.getString("TODO_TITLE");
		String complete 	= rs.getString("COMPLETE");
		String regDate 		= rs.getString("REG_DATE");
		
		//Todo 객체를 생성해서 값 세팅 후 todoList에 추가
		
		Todo todo = new Todo(todoNo, todoTitile, complete, regDate);
		todoList.add(todo);
		
	  }

		 
	 }finally{
		close(rs);
		close(stmt);
	 }
		
		return todoList;
	}

	// 완료된 할 일 개수
	@Override
	public int getCompleteCount(Connection conn) throws SQLException {
		
		int completeCount = 0;
		
		try {
			String sql = prop.getProperty("getCompleteCount");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			//-> GROUP BY가 없는 SELECT의 COUNT 그룹 함수의 결과는 1행
			//-> if문을 이용해서 조회 결과 행 접근(효율이 좋음)
			if(rs.next()) {
				completeCount = rs.getInt(1); // 1번 컬럼 값
			}
			
			
		}finally {
			close(rs);
			close(stmt);
		}

		
		
		return completeCount;
	}
	
	// 할 일 추가
	@Override
	public int addTodo(Connection conn, String todoTitle, String todoContent) throws SQLException {
       int result = 0; // dao에서는 결과 저장용 변수 선언 먼저 하자
       
       //sql에서 작성한거 가져와서 작성
   	try {
		String sql = prop.getProperty("addTodo");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, todoTitle);
		pstmt.setString(2, todoContent);
		
		result = pstmt.executeUpdate();
		
	  }finally {
		close(pstmt);
		
	 }
   	return result;
	}

	@Override
	public Todo selectTodo(Connection conn, int todoNo) throws SQLException {
	
	 // 결과 저장용 변수 선언/ 객체 생성;
	 Todo todo = null;
	 
	 try {
	  
	  String sql = prop.getProperty("selectTodo");
	  //- > sql.xml 파일에 해당 구문 적으러 가기
		
	  // 작성하고 돌아온 후
	  pstmt = conn.prepareStatement(sql);
	  pstmt.setInt(1, todoNo);
	  
	  rs = pstmt.executeQuery(); //SQL수행, 결과 반환
	  
	  if(rs.next()) { // 조회 결과가 있어도 1행 밖에 없음
		              // == if 사용이 효율적
		  todo = new Todo(); //todo객체를 하나 만듦
	    todo.setTodoNo			(rs.getInt("TODO_NO"));
		todo.setTodoTitle		(rs.getString("TODO_TITLE"));
		todo.setTodoContent	(rs.getString("TODO_CONTENT"));
		todo.setComplete		(rs.getString("COMPLETE"));
		todo.setRegDate			(rs.getString("REG_DATE"));
	  
	  }
		 
	 }finally {
	  close(rs);
	  close(pstmt);
		 
		 
	 }
		return todo;
	}
	
	@Override
	public int changeComplete(Connection conn, int todoNo, String complete) throws SQLException {
	 
	  int result = 0;
	  
	  try {
		 String sql = prop.getProperty("changeComplete");
		
		 pstmt = conn.prepareStatement(sql);
		 
		  pstmt.setString(1, complete);
		  pstmt.setInt(2, todoNo);
		  
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//할 일 수정
	@Override
	public int updateTodo(Connection conn, Todo todo) throws SQLException {
		
	  int result = 0;
	  
	  try {
		  
		String sql = prop.getProperty("updateTodo");
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, todo.getTodoTitle());
		pstmt.setString(2, todo.getTodoContent());
		pstmt.setInt   (3, todo.getTodoNo());
		
		result = pstmt.executeUpdate();
		  
	  }finally {
		close(pstmt); 
		 
	  }
	  
		return result;
	}

	// 할 일 삭제
	@Override
	public int deleteTodo(Connection conn, int todoNo) throws SQLException {
		
	 // 그냥 delete dml 실행하고 끝냄 
     // 위의 update 구문 긁어와서 수정
		  int result = 0;
		  
		  try {
			  
			String sql = prop.getProperty("deleteTodo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, todoNo);
			
			
			result = pstmt.executeUpdate();
			  
		  }finally {
			close(pstmt); 
			 
		  }
		  
			return result;	
	 
	}
}
