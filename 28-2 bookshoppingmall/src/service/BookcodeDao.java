package service;

import java.sql.*;
import service.*;

public class BookcodeDao {
	//void 리턴 타입. 매서드 insertBookcode를 선언하고 
	//매개변수로는 Connection 클래스 데이터 타입인 conn과 Bookcode 클래스 데이터 타입인 bookcode를 선언.
	public void insertBookcode(Connection conn, Bookcode bookcode) {
		//초기값 지정.
		PreparedStatement pstmtInsertBookcode = null;
		//insert 쿼리문이 들어갈 sqlInsertBookcode를 선언 및 쿼리문 대입.
		String sqlInsertBookcode = "INSERT INTO bookcode VALUES(?)";
		
		try {
			//PreparedStatement로 생성된 객체에 쿼리 실행을 위한 데이터와 쿼리문을 삽입.
			pstmtInsertBookcode = conn.prepareStatement(sqlInsertBookcode);
			//? 안의 값을 bookcode 클래스를 따라가 나온 bookcodeName값을 가져와 대입.
			pstmtInsertBookcode.setString(1, bookcode.getBookcodeName());
			//update하며 리턴된 int 타입을 출력. 즉, bookcode 테이블 내에 삽입된 레코드의 개수가 나온다
			System.out.println("삽입된 bookcode 레코드의 수 : " + pstmtInsertBookcode.executeUpdate());
			
		//SQLException 오류가 났을시 아래와 같은 텍스와 오류위치 출력.
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertBookcode main");
			e.printStackTrace();
		//객체 종료
		} finally {
			if(pstmtInsertBookcode != null) {
				try {
					pstmtInsertBookcode.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertBookcode close");
					e.printStackTrace();
				}
			}
		}
	} 
	//void 리턴 타입. deleteBookcode메서드를 선언, 매개변수는 클래스 데이터 타입인 변수 conn과 int 데이터 타입인 변수 bookcodeNo을 선언한다.
	public void deldeteBookcode(Connection conn, int bookcodeNo) {
		//초기값.
		PreparedStatement pstmtdeldeteBookcode = null;
		
		//쿼리문을 String data type 변수인 sqldeldeteBookcode에 대입.
		String sqldeldeteBookcode = "DELETE FORM bookcode where bookcode_no=?";
		
		try {
			//쿼리 실행을 위한 데이터들을 pstmtdeldeteBookcode객체에 삽입.
			pstmtdeldeteBookcode = conn.prepareStatement(sqldeldeteBookcode);
			//?를 int 변수인 bookcodeNo 전환.
			pstmtdeldeteBookcode.setInt(1, bookcodeNo);
			
			//쿼리 실행및, 삭제된 레코드 수 출력.
			System.out.println("삭제된 bookcode 레코드의 수 :" + pstmtdeldeteBookcode.executeLargeUpdate());
			
		//예외 발생시*
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deldeteBookcode main");
			e.printStackTrace();
		//객체 종료
		} finally {
			if(pstmtdeldeteBookcode != null) {
				try {
					pstmtdeldeteBookcode.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, deldeteBookcode close");
					e.printStackTrace();
				}
			}
		}
		
	}
}
