package service;

import java.util.ArrayList;
import java.sql.*;
import service.*;


public class BookcodeService {
	
	//화면에서 입력한 값을 추가하는 메서드입니다.
	public void addBookcode(String bookcodeName) {
		Connection conn = null;
		try {
			conn = DButil.connectDB();
			BookcodeDao bookcodeDao = new BookcodeDao();
			
			bookcodeDao.insertBookcode(conn, bookcodeName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Bookcode 테이블의 모든 레코드를 조회하여 리스트로 리턴받는 메서드
	// 매개변수는 없다.
	// 리턴 데이터 타입으로는 북코드객체를 담는 배열리스트. 조회된 모든 레코드를 담기 위함이다.
	public ArrayList<Bookcode> getAllBookcodes() {
		Connection conn = null;
		ArrayList<Bookcode> arrayListOfAllBookcodes = null;
		try {
			// DB 연결
			conn = DButil.connectDB();
			
			// Bookcode 테이블의 모든 레코드를 조회하여 리스트를 리턴하는 메서드 호출
			BookcodeDao bookcodeDao = new BookcodeDao();
			arrayListOfAllBookcodes = bookcodeDao.selectAllBookcodes(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arrayListOfAllBookcodes;
	}
}
