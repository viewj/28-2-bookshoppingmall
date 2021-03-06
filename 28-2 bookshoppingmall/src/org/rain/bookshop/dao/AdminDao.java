package org.rain.bookshop.dao;

import java.sql.*;

import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.DbUtils;

import java.util.ArrayList;

public class AdminDao {
	
	public static void insertAdmin(Connection conn, Admin admin) {
		PreparedStatement pstmtInsertAdmin = null;
		String sqlInsertAdmin = "INSERT INTO admin(admin_id, admin_pw, admin_name, admin_date) VALUES (?, ?, ?, now())";
		
		try {
			pstmtInsertAdmin = conn.prepareStatement(sqlInsertAdmin);
			pstmtInsertAdmin.setString(1, admin.getAdminId());
			pstmtInsertAdmin.setString(2, admin.getAdminPw());
			pstmtInsertAdmin.setString(3, admin.getAdminName());
			
			int resultInsert = pstmtInsertAdmin.executeUpdate();
			System.out.println("admin 테이블에 삽입된 행 갯수 : " + resultInsert);
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, InsertAdmin");
			e.printStackTrace();
		} finally {
			DbUtils.closePstmt(pstmtInsertAdmin);
		}
	}
	
	public static void deleteAdmin(Connection conn, Admin admin) {
		// 객체참조변수 선언
		PreparedStatement pstmtDeleteAdmin = null;
		// admin테이블의 레코드를 삭제하는 쿼리문
		String sqlDeleteAdmin = "DELETE FROM admin WHERE admin_no=?";
		
		try {
			pstmtDeleteAdmin = conn.prepareStatement(sqlDeleteAdmin);
			// 쿼리문 ?자리에 받아온 admin_no값을 넣는다.
			pstmtDeleteAdmin.setInt(1, admin.getAdminNo());
			// admin테이블에서 수정된 레코드의 수를 리턴 받은 값을 변수에 담는다.
			int resultUpdate = pstmtDeleteAdmin.executeUpdate();
			
			System.out.println("admin 테이블에 삭제된 행 갯수 : " + resultUpdate);
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, deleteAdmin");
			e.printStackTrace();
		} finally {
			DbUtils.closePstmt(pstmtDeleteAdmin);
		}
	}
	
	public static String loginCheck(Connection conn, Admin admin) {
		// 객체참조변수 선언
		PreparedStatement pstmtAdminLogin = null;
		ResultSet rsAdminLogin = null;
		
		// admin테이블 전체 레코드에서 특정 레코드를 찾는 쿼리문
		String sqlAdminLogin = "SELECT admin_pw FROM admin where admin_id=?";
		
		// 쿼리문에서 나온 행의 pw값을 담을 변수와 리턴해줄 값을 담을 변수 선언
		String pw = null;
		String result = null;
		
		try {
			pstmtAdminLogin = conn.prepareStatement(sqlAdminLogin);
			pstmtAdminLogin.setString(1, admin.getAdminId());
			rsAdminLogin = pstmtAdminLogin.executeQuery();
			
			// 아이디와 비밀번호 둘다 pstmtAdminLogin 레코드는 하나이기 때문에 while이 아닌 if를 사용한다.
			if (rsAdminLogin.next()) {
				pw = rsAdminLogin.getString("admin_pw");
				
				if(admin.getAdminPw().equals(pw)) {
					result="로그인성공";
				} else {
					result="비밀번호불일치";
				}
			} else {
				result = "아이디불일치";
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, loginCheck");
			e.printStackTrace();
		} finally {
			DbUtils.closeObject(rsAdminLogin, pstmtAdminLogin);
		}
		return result;
	}
	
	public static void adminGetVO(Connection conn, Admin admin) {
		// 객체참조변수 선언
		PreparedStatement pstmtAdminGetVO = null;
		ResultSet rsAdminGetVO = null;
		
		// admin_id값을 이용하여 레코드의 일부분을 가져올 수 있는 쿼리문
		String sqlAdminGetVO = "SELECT admin_no, admin_name FROM admin WHERE admin_id=?";
		
		try {
			pstmtAdminGetVO = conn.prepareStatement(sqlAdminGetVO);
			pstmtAdminGetVO.setString(1, admin.getAdminId());
			rsAdminGetVO = pstmtAdminGetVO.executeQuery();
			
			if (rsAdminGetVO.next()) {
				admin.setAdminNo(rsAdminGetVO.getInt("admin_no"));
				admin.setAdminName(rsAdminGetVO.getString("admin_name"));
				System.out.println("rsAdminGetVO에서 받은 admin_no값 : " + admin.getAdminNo());
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, adminGetVO");
			e.printStackTrace();
		} finally {
			DbUtils.closeObject(rsAdminGetVO, pstmtAdminGetVO);
		}
	}
	
	public static ArrayList<Admin> selectAllAdmins(Connection conn) {
		// 리턴해줄 배열 선언
		ArrayList<Admin> arrayListAdmin = new ArrayList<Admin>();
		
		// 객체 참조 변수 선언
		PreparedStatement pstmtSelectAllAdmins = null;
		ResultSet rsSelectAllAdmins = null;
		
		// 전체 레코드를 나열하는 쿼리문
		String sqlSelectAllAdmins = "SELECT admin_no, admin_id, admin_name, admin_date FROM admin";
		
		try {
			pstmtSelectAllAdmins = conn.prepareStatement(sqlSelectAllAdmins);
			rsSelectAllAdmins = pstmtSelectAllAdmins.executeQuery();
			
			while(rsSelectAllAdmins.next()) {
				// 한 레코드를 담을 멤버클래스 선언
				Admin admin = new Admin();
				
				admin.setAdminNo(rsSelectAllAdmins.getInt("admin_no"));
				admin.setAdminId(rsSelectAllAdmins.getString("admin_id"));
				admin.setAdminName(rsSelectAllAdmins.getString("admin_name"));
				admin.setAdminDate(rsSelectAllAdmins.getString("admin_date"));
				
				// 한 레코드씩 정보를 배열에 저장
				arrayListAdmin.add(admin);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, selectAllAdmins");
			e.printStackTrace();
		} finally {
			DbUtils.closeObject(rsSelectAllAdmins,pstmtSelectAllAdmins);
		}
		return arrayListAdmin;
	}
	
	public static Admin SelectAdmin(Connection conn, int adminNo) {
		// 객체참조변수 선언
		PreparedStatement pstmtSelectAdmin = null;
		ResultSet rsSelectAdmin = null;
		
		// 리턴해줄 멤버클래스 선언
		Admin returnAdmin = null;
		
		// 원하는 부분의 레코드전체를 가져오는 쿼리문
		String sqlSelectAdmin = "SELECT admin_no, admin_id, admin_pw, admin_name, admin_date FROM admin WHERE admin_no=?";
		
		try {
			pstmtSelectAdmin = conn.prepareStatement(sqlSelectAdmin);
			pstmtSelectAdmin.setInt(1, adminNo);
			rsSelectAdmin = pstmtSelectAdmin.executeQuery();
			
			// member_no는 primary key이기 때문에 하나밖에 존재하지않는다. 그렇기 때문에 while이 아닌 if를 사용한다.
			if(rsSelectAdmin.next()) {
				returnAdmin = new Admin();
				
				// 쿼리문에서 나온 값을 member클래스 데이터타입으로 저장한다.
				returnAdmin.setAdminNo((rsSelectAdmin.getInt("admin_no")));
				returnAdmin.setAdminId((rsSelectAdmin.getString("admin_id")));
				returnAdmin.setAdminPw((rsSelectAdmin.getString("admin_pw")));
				returnAdmin.setAdminName((rsSelectAdmin.getString("admin_name")));
				returnAdmin.setAdminDate((rsSelectAdmin.getString("admin_date")));
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectAdmin");
			e.printStackTrace();
		} finally {
			DbUtils.closeObject(rsSelectAdmin, pstmtSelectAdmin);
		}
		return returnAdmin;
	}
	
	public static Admin SelectAdminNo(Connection conn, String adminId) {
		// 객체참조변수 선언
		PreparedStatement pstmtSelectAdmin = null;
		ResultSet rsSelectAdmin = null;
		// 리턴해줄 멤버클래스 선언
		Admin returnAdmin = null;
		// 원하는 부분의 레코드전체를 가져오는 쿼리문
		String sqlSelectAdmin = "SELECT admin_no, admin_id, admin_pw, admin_name, admin_date FROM admin WHERE admin_id=?";
		
		try {
			pstmtSelectAdmin = conn.prepareStatement(sqlSelectAdmin);
			pstmtSelectAdmin.setString(1, adminId);
			rsSelectAdmin = pstmtSelectAdmin.executeQuery();
			
			// member_no는 primary key이기 때문에 하나밖에 존재하지않는다. 그렇기 때문에 while이 아닌 if를 사용한다.
			if(rsSelectAdmin.next()) {
				returnAdmin = new Admin();
				// 쿼리문에서 나온 값을 member클래스 데이터타입으로 저장한다.
				returnAdmin.setAdminNo((rsSelectAdmin.getInt("admin_no")));
				returnAdmin.setAdminId((rsSelectAdmin.getString("admin_id")));
				returnAdmin.setAdminPw((rsSelectAdmin.getString("admin_pw")));
				returnAdmin.setAdminName((rsSelectAdmin.getString("admin_name")));
				returnAdmin.setAdminDate((rsSelectAdmin.getString("admin_date")));
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectAdmin");
			e.printStackTrace();
		}
		return returnAdmin;
	}
}
