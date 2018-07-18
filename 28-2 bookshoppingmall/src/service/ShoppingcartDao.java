package service;

import java.sql.*;
import service.*;

public class ShoppingcartDao {
	//void 리턴 타입. insertShoppingcart 메서드 선언. 매개변수는  클래스 데이터 타입으로 conn과 shoppinigCart를 선언했다.
	public void insertShoppingcart(Connection conn, Shoppingcart shoppingcart) {
		//초기값.
		PreparedStatement pstmtInsertShoppingcart = null;
		
		//String 변수인 sqlInsertShoppingCartdp 쿼리 실행에 필요한 쿼리문을 대입.
		String sqlInsertShoppingCart = "INSERT INTO shoppingcart(book_no, member_no, shoppingcart_amount, shoppingcart_price, shoppingcart_date) VALUES(?,?,?,?,now())";
		
		try {
			//pstmtInsertShoppingcart 객체 참조 변수에 쿼리문과 db연결 데이터를 특정 메소드를 이용해 삽입.
			pstmtInsertShoppingcart = conn.prepareStatement(sqlInsertShoppingCart);
			
			//쿼리문의 ? 값 변환.
			pstmtInsertShoppingcart.setInt(1, shoppingcart.getBookNo());
			pstmtInsertShoppingcart.setInt(2, shoppingcart.getMemberNo());
			pstmtInsertShoppingcart.setInt(3, shoppingcart.getShoppingcartAmount());
			pstmtInsertShoppingcart.setInt(4, shoppingcart.getShoppingcartPrice());
			//쿼리 실행 및 결과 출력.(int)
			System.out.println("shoppingcart 테이블에 입력된 레코드의 개수" + 	pstmtInsertShoppingcart.executeUpdate());
		//예외 처리.
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertShoppingcart main");
			e.printStackTrace();
		//객체 종료.
		} finally {
			if(pstmtInsertShoppingcart != null) {
				try {
					pstmtInsertShoppingcart.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertShoppingcart close");
					e.printStackTrace();
				}
			}
		}
	}
	//void리턴 타입. deleteShoppingcart 메서드 선언. 매개변수는 클래스 데이터 타입인 conn과 int 데이터 타입인 shoppingcartNo을 선언.
	public void deleteShoppingcart(Connection conn, int shoppingcartNo) {
		//초기값 지정.
		PreparedStatement pstmtDeleteShoppingcart = null;
		//쿼리 실행에 필요한 쿼리문을 String 변수인 sqlDeleteShoppingcart에 대입.
		String sqlDeleteShoppingcart = "DELETE FORM shoppingcart where shoppingcart_no=?";
		
		try {
			//객체 참조변수 pstmtDeleteShoppingcart에 쿼리문과 db연결에 필요한 데이터를 특정 메서드를 이용해 삽입.
			pstmtDeleteShoppingcart = conn.prepareStatement(sqlDeleteShoppingcart);
			pstmtDeleteShoppingcart.setInt(1, shoppingcartNo);
			//쿼리 실행 및 결과 출력.
			System.out.println("shoppingcart 테이블의 삭제된 레코드 수" + pstmtDeleteShoppingcart.executeUpdate());
		//예외 처리.
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deleteShoppingcart main");
			e.printStackTrace();
		//객체 종료.
		} finally {
			if(pstmtDeleteShoppingcart != null) {
				try {
					pstmtDeleteShoppingcart.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, deleteShoppingcart close");
					e.printStackTrace();
				}
			}
		}
			
	}
}






















