package service;

import java.sql.*;

public class AdminService {
	
	public boolean addAdmin(Admin admin) {
		Connection conn = null;
		boolean resultOfaddAdmin = false;
		System.out.println("");
		System.out.println("location : adminService/addadmin()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// 자동으로 commit 되지 않게 하여 트랜잭션 처리를 한다.
			conn.setAutoCommit(false);
			
			// adminDao 객체 생성
			AdminDao adminDao = new AdminDao();
					
			// admin 테이블에 한 개의 레코드를 추가하는 메서드 실행
			adminDao.insertAdmin(conn, admin);
			
			// 예외 없이 여기까지 진행 됐다면 커밋을 통해 실제 DB에 반영
			conn.commit();
			
			resultOfaddAdmin = true;
		
		} catch (Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			}
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of AdminService/addAdmin()");
		}
			return resultOfaddAdmin;
	}
	
	// 로그인 시도 할 때 아이디와 비밀번호가 DB와 일치하는지 비교를 하여 그 결과를 문자열로 리턴하는 메서드
	// 매개변수는 로그인 폼에서 입력받은 정보를 담은 VO
	// 리턴 데이터는 결과를 나타내는 문자열
	public String adminLoginCheck(Admin admin) {
		Connection conn = null;
		String resultOfLogin = null;
		
		System.out.println("");
		System.out.println("location : AdminService/adminLoginCheck()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			AdminDao adminDao = new AdminDao();
			
			// loginCheck 메서드를 통해 로그인 체크
			resultOfLogin = adminDao.loginCheck(conn, admin);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of AdminService/adminLoginCheck()");
		}
		return resultOfLogin;
	}
	
	// 입력받은 로그인 정보가 DB와 일치한다면 실행하는 메서드로 세션정보로 사용할 member_no 값을 받아오기 위함
	// 매개변수는 로그인 폼에서 입력받은 정보가 담긴 VO.
	// 리턴데이터는 없다.
	public void adminGetVO(Admin admin) {
		Connection conn = null;
		
		System.out.println("");
		System.out.println("location : adminService/adminGetVO()");
		
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			AdminDao adminDao = new AdminDao();
			
			// memberGetVO를 통해 세션에 필요한 정보들만 조회 후 VO의 데이터 영역에 대입.
			adminDao.adminGetVO(conn, admin);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of AdminService/adminGetVO()");
		}
	}
}
