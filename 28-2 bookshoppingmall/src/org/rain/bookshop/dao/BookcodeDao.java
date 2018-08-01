package org.rain.bookshop.dao;

import java.util.ArrayList;
import java.sql.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;

public class BookcodeDao {
	// bookcode 테이블의 모든 레코드를 조회하는 메서드
	// 매개변수로는 conn 객체를 입력받는다.(쿼리 준비하는 메서드를 이용해야 하기 때문)
	// 리턴 데이터 타입으로는 북코드객체를 담는 배열리스트. 조회된 모든 레코드를 담기 위함이다.
	public static ArrayList<Bookcode> selectAllBookcodes(Connection conn){
		PreparedStatement pstmtSelectAllBookcodes = null;
		ResultSet rsSelectAllBookcodes = null;
		ArrayList<Bookcode> arrayListSelectAllBookcodes = new ArrayList<Bookcode>();
		Bookcode bookcode = null;
		
		// bookcode 테이블의 모든 레코드를 조회하는 쿼리작성
		String sqlSelectAllBookCodes = "SELECT bookcode_no, bookcode_name FROM bookcode";
		
		try {
			// 위에서 준비한 쿼리 준비
			pstmtSelectAllBookcodes = conn.prepareStatement(sqlSelectAllBookCodes);
			
			// 쿼리 실행 후 리턴된 ResultSet 객체의 참조값을  객체참조변수에 대입
			rsSelectAllBookcodes = pstmtSelectAllBookcodes.executeQuery();
			
			// 모든 레코드를 ArrayList에 담기위한 while문
			while(rsSelectAllBookcodes.next()) {
				// bookcode 객체를 생성하고 그 객체 내부 데이터 영역에 조회된 각각의 값을 대입한다.
				bookcode = new Bookcode();
			
				bookcode.setBookcodeNo(rsSelectAllBookcodes.getInt("bookcode_no"));
				bookcode.setBookcodeName(rsSelectAllBookcodes.getString("bookcode_name"));
				
				// 그렇게 완성된 bookcode VO를 add메서드를 통해 리스트에 추가한다. 
				arrayListSelectAllBookcodes.add(bookcode);
			}
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertBookcode main");
			e.printStackTrace();
		} finally {
			DbUtils.closeObject(rsSelectAllBookcodes, pstmtSelectAllBookcodes);
		}
		return arrayListSelectAllBookcodes;
	}
	
	//void 리턴 타입. 매서드 insertBookcode를 선언하고 
	//매개변수로는 Connection 클래스 데이터 타입인 conn과 String 데이터 타입인 bookcodeName을 선언.
	public static void insertBookcode(Connection conn, String BookcodeName) {
		//초기값 지정.
		PreparedStatement pstmtInsertBookcode = null;
		//insert 쿼리문이 들어갈 sqlInsertBookcode를 선언 및 쿼리문 대입.
		String sqlInsertBookcode = "INSERT INTO bookcode(bookcode_name) VALUES(?)";
		
		try {
			//PreparedStatement로 생성된 객체에 쿼리 실행을 위한 데이터와 쿼리문을 삽입.
			pstmtInsertBookcode = conn.prepareStatement(sqlInsertBookcode);
			//? 안의 값을 bookcode 클래스를 따라가 나온 bookcodeName값을 가져와 대입.
			pstmtInsertBookcode.setString(1, BookcodeName);
			
			//update하며 리턴된 int 타입을 출력. 즉, bookcode 테이블 내에 삽입된 레코드의 개수가 나온다
			System.out.println("삽입된 bookcode 레코드의 수 : " + pstmtInsertBookcode.executeUpdate());
			
		//SQLException 오류가 났을시 아래와 같은 텍스와 오류위치 출력.
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertBookcode main");
			e.printStackTrace();
		//객체 종료
		} finally {
			DbUtils.closePstmt(pstmtInsertBookcode);
		}
	} 
	//void 리턴 타입. deleteBookcode메서드를 선언, 매개변수는 클래스 데이터 타입인 변수 conn과 int 데이터 타입인 변수 bookcodeNo을 선언한다.
	public static void deleteBookcode(Connection conn, int bookcodeNo) {
		//초기값.
		PreparedStatement pstmtdeleteBookcode = null;
		
		//쿼리문을 String data type 변수인 sqldeldeteBookcode에 대입.
		String sqldeleteBookcode = "DELETE FROM bookcode where bookcode_no=?";
		
		try {
			//쿼리 실행을 위한 데이터들을 pstmtdeldeteBookcode객체에 삽입.
			pstmtdeleteBookcode = conn.prepareStatement(sqldeleteBookcode);
			//?를 int 변수인 bookcodeNo 전환.
			pstmtdeleteBookcode.setInt(1, bookcodeNo);
			
			//쿼리 실행및, 삭제된 레코드 수 출력.
			System.out.println("삭제된 bookcode 레코드의 수 :" + pstmtdeleteBookcode.executeUpdate());
			
		//예외 발생시*
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deleteBookcode main");
			e.printStackTrace();
		//객체 종료
		} finally {
			DbUtils.closePstmt(pstmtdeleteBookcode);
		}
	}
}