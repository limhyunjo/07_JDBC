package edu.kh.dept.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;

public interface DepartmentDAO {


		/** 부서 전체 조회
		 * @param conn
		 * @return deptList
		 * @throws SQLException
		 */
		List<Department> selectAll(Connection conn) throws SQLException;

		
		/** 부서 추가
		 * @param conn
		 * @param dept
		 * @return result
		 * @throws SQLException
		 */
		int insertDepartment(Connection conn, Department dept) throws SQLException;


		/** 부서 제거
		 * @param conn
		 * @param deptId
		 * @return
		 * @throws SQLException
		 */
		int deleteDepartment(Connection conn, String deptId) throws SQLException;


		/** 부서 1행 조회
		 * @param conn
		 * @param deptId
		 * @return dept
		 */
		Department selectOne(Connection conn, String deptId) throws SQLException;


		/** 부서 수정하기
		 * @param conn
		 * @param dept
		 * @return result
		 * @throws SQLException 
		 */
		int updateDepartment(Connection conn, Department dept) throws SQLException;



		/**
		 * @param conn
		 * @param key
		 * @return deptList
		 * @throws SQLException
		 */
		List<Department> searchDepartment(Connection conn, String key) throws SQLException;

		
		
		
	}
