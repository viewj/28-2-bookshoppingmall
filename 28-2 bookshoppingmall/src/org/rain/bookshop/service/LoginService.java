package org.rain.bookshop.service;

import java.sql.*;

import org.rain.bookshop.dao.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;

public class LoginService {
	
	// 입력받은 로그인 정보가 DB와 일치한다면 실행하는 메서드로 세션정보로 사용할 member_no 값을 받아오기 위함
	// 매개변수는 로그인 폼에서 입력받은 정보가 담긴 VO.
	// 리턴데이터는 없다.
	public static void adminGetVO(Admin admin) {
		Connection conn = null;
		
		LocationUtils.printStartLoaction("LoginService - adminGetVO()");
		
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DbUtils.connectDB();
			
			// memberGetVO를 통해 세션에 필요한 정보들만 조회 후 VO의 데이터 영역에 대입.
			AdminDao.adminGetVO(conn, admin);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn);
			LocationUtils.printEndLoaction("LoginService - adminGetVO()");
		}
	}
	
	// 로그인 시도 할 때 아이디와 비밀번호가 DB와 일치하는지 비교를 하여 그 결과를 문자열로 리턴하는 메서드
	// 매개변수는 로그인 폼에서 입력받은 정보를 담은 VO
	// 리턴 데이터는 결과를 나타내는 문자열
	public static String adminLoginCheck(Admin admin) {
		Connection conn = null;
		String resultOfLogin = null;
		
		LocationUtils.printStartLoaction("LoginService - adminLoginCheck()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DbUtils.connectDB();
			
			// loginCheck 메서드를 통해 로그인 체크
			resultOfLogin = AdminDao.loginCheck(conn, admin);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn);
			LocationUtils.printEndLoaction("LoginService - adminLoginCheck()");
		}
		return resultOfLogin;
	}
}
