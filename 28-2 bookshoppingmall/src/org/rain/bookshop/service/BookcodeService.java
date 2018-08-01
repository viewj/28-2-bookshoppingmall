package org.rain.bookshop.service;

import java.util.ArrayList;
import java.sql.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

public class BookcodeService {
	// 화면에서 체크한 bookcode 항목들을 삭제하는 메서드
	public static void deleteBookcode(int bookcodeNo) {
		Connection conn = null;
		try {
			conn = DbUtils.connectDB();
		
			BookcodeDao.deleteBookcode(conn, bookcodeNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn);
		}
	}
	
	//화면에서 입력한 값을 추가하는 메서드입니다.
	public static void addBookcode(String bookcodeName) {
		Connection conn = null;
		try {
			conn = DbUtils.connectDB();
			
			BookcodeDao.insertBookcode(conn, bookcodeName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn);
		}
	}
	
	// Bookcode 테이블의 모든 레코드를 조회하여 리스트로 리턴받는 메서드
	// 매개변수는 없다.
	// 리턴 데이터 타입으로는 북코드객체를 담는 배열리스트. 조회된 모든 레코드를 담기 위함이다.
	public static ArrayList<Bookcode> getAllBookcodes() {
		Connection conn = null;
		ArrayList<Bookcode> arrayListOfAllBookcodes = null;
		try {
			// DB 연결
			conn = DbUtils.connectDB();
			
			// Bookcode 테이블의 모든 레코드를 조회하여 리스트를 리턴하는 메서드 호출
			arrayListOfAllBookcodes = BookcodeDao.selectAllBookcodes(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn);
		}
		return arrayListOfAllBookcodes;
	}
}
