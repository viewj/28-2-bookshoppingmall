package org.rain.bookshop.service;

import java.sql.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

public class AdminService {
	
	public boolean addAdmin(Admin admin) {
		Connection conn = null;
		boolean resultOfaddAdmin = false;
		System.out.println("");
		System.out.println("location : adminService/addadmin()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DbUtils.connectDB();
			
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
	
	

	public Admin selectAdmin(int adminNo) {
		
		Connection conn = null;
		Admin admin = null;
		try {
			conn = DbUtils.connectDB();
			
			AdminDao adminDao = new AdminDao();
			
			admin = adminDao.SelectAdmin(conn, adminNo);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e2) {
						System.out.println("conn 객체 종료 중 예외");
						e2.printStackTrace();
					}
				}
				System.out.println("");
				System.out.println("End of AdminService/selectAdmin()");
			}
		}
		return admin;
	}
	
	public int selectAdminNo(String adminId) {
		
		Connection conn = null;
		Admin admin = null;
		int adminNo = 0;
		try {
			conn = DbUtils.connectDB();
			
			AdminDao adminDao = new AdminDao();
			
			admin = adminDao.SelectAdminNo(conn, adminId);
			adminNo = admin.getAdminNo();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e2) {
						System.out.println("conn 객체 종료 중 예외");
						e2.printStackTrace();
					}
				}
				System.out.println("");
				System.out.println("End of AdminService/selectAdminNo()");
			}
		}
		return adminNo;
		
	}
}
