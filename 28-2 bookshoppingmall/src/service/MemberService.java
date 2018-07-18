package service;

import java.sql.*;
import service.*;

public class MemberService {
	public String memberLoginCheck(Member member) {
		Connection conn = null;
		String resultOfLogin = null;
				
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			MemberDao memberDao = new MemberDao();
			
			// loginCheck 메서드를 통해 로그인 체크
			resultOfLogin = memberDao.loginCheck(conn, member);
		} catch (Exception e){
			System.out.println("MemberService - memberLoginCheck()에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("MemberService - memberLoginCheck() - finally - conn.close에서 예외 발생");
					e.printStackTrace();
				}
			}
		}
		return resultOfLogin;
	}
	
	public void memberGetVO(Member member) {
		Connection conn = null;
		
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			MemberDao memberDao = new MemberDao();
			
			// memberGetVO를 통해 세션에 필요한 정보들만 조회 후 VO의 데이터 영역에 대입.
			memberDao.memberGetVO(conn, member);
		} catch (Exception e){
			System.out.println("MemberService - memberLoginCheck()에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("MemberService - memberLoginCheck() - finally - conn.close에서 예외 발생");
					e.printStackTrace();
				}
			}
		}
	}
}
