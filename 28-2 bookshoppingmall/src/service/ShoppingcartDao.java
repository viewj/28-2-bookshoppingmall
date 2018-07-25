package service;

import java.sql.*;
import java.util.ArrayList;

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
		String sqlDeleteShoppingcart = "DELETE FROM shoppingcart WHERE shoppingcart_no=?";
		
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
	
	//ArrayList 리턴 타입. selectAllShoppingcart 메서드 선언. 매개변수로는 클래스 데이터 타입인 conn과 int 데이터 타입인 memberNo를 사용.
	public ArrayList<ShoppingPurchaseList> selectShoppingcart(Connection conn, int memberNo) {
		
		//초기값 지정.
		PreparedStatement pstmtSelectAllShoppingcart = null;
		ResultSet rsSelectAllShoppingcart = null;
		
		ShoppingPurchaseList shoppingcart = null;
		//ArrayList 클래스의 주소값을 할당한 객체 생성.
		ArrayList<ShoppingPurchaseList> arrayListAllShoppingcart = new ArrayList<ShoppingPurchaseList>();
		
		//책의 이름을 얻기 위해 LEFT JOIN 쿼리를 사용
		String sqlSelectAllShoppingcart = "SELECT shoppingcart.shoppingcart_no ,shoppingcart.member_no ,shoppingcart.shoppingcart_amount ,shoppingcart.shoppingcart_price ,LEFT(shoppingcart.shoppingcart_date ,10) AS shoppingcart_date ,book.book_no ,book.book_name ,book.book_point "
			+"FROM shoppingcart LEFT JOIN book ON shoppingcart.book_no = book.book_no WHERE shoppingcart.member_no=? ORDER BY shoppingcart_no DESC";
		
		try {
			pstmtSelectAllShoppingcart = conn.prepareStatement(sqlSelectAllShoppingcart);
			pstmtSelectAllShoppingcart.setInt(1, memberNo);
			
			//resultSet 객체 참조변수인 rsSelectAllShoppingcart에 쿼리실행 결과 리턴값 대입.
			rsSelectAllShoppingcart = pstmtSelectAllShoppingcart.executeQuery();
			
			//*쿼리문의 조건에 맞는 값이 끝날때까지 반복(테이블의 레코더를 가져온다)
			while(rsSelectAllShoppingcart.next()) {
				
				//Shoppingcart 클래스의 새로운 객체 생성.
				shoppingcart = new ShoppingPurchaseList();
				
				//테이블 내에 있는 값을 끄집어  shoppingcart 내부에 저장되어 있는 set메서드에 삽입.
				shoppingcart.setShoppingcartNo(rsSelectAllShoppingcart.getInt("shoppingcart_no"));
				shoppingcart.setMemberNo(rsSelectAllShoppingcart.getInt("member_no"));
				shoppingcart.setBookNo(rsSelectAllShoppingcart.getInt("book_no"));
				shoppingcart.setBookPoint(rsSelectAllShoppingcart.getInt("book_point"));
				shoppingcart.setShoppingcartAmount(rsSelectAllShoppingcart.getInt("shoppingcart_amount"));
				shoppingcart.setShoppingcartPrice(rsSelectAllShoppingcart.getInt("shoppingcart_price"));
				shoppingcart.setBookName(rsSelectAllShoppingcart.getString("book_name"));
				shoppingcart.setShoppingcartDate(rsSelectAllShoppingcart.getString("shoppingcart_date"));
				
				//* 위 반복이 끝나고 비로소 완성된 객체에  인덱스(숫자) 부여.
				arrayListAllShoppingcart.add(shoppingcart);
			}
			
		//예외처리.
		} catch (SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, selectAllShoppingcart close");
			e.printStackTrace();
			
		} finally {
			
			if(pstmtSelectAllShoppingcart != null) {
				try {
					pstmtSelectAllShoppingcart.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, selectAllShoppingcart close");
					e.printStackTrace();
				}
			}
			
			if(rsSelectAllShoppingcart != null) {
				try {
					rsSelectAllShoppingcart.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, selectAllShoppingcart close");
					e.printStackTrace();
				}
			}
		}
		//그렇게 인덱스가 부여된 객체들을 리턴.
		return arrayListAllShoppingcart;
	}
	
	//리턴 타입 int. 즉, 변경된 amount에 따른 price의 총 가격이 리턴된다. 
	//매개변수로는 클래스 데이터 타입인 conn과 update 페이지에서 셋팅된 객체인 shoppingcart를 선언한다.
	public int AmountOfShoppingcart(Connection conn, Shoppingcart shoppingcart) {
		//초기값
		PreparedStatement pstmtAmountOfShoppingcart = null;
		//변경된 amount 값.
		int amount = shoppingcart.getShoppingcartAmount();
		//단가.
		int price = shoppingcart.getShoppingcartPrice();
		//총합.
		int rePrice = price * amount;
		//쿼리문을 변수에 대입.
		String sqlAmountOfShoppingcart = "UPDATE shoppingcart SET shopping_amount=? where shoppingcart_no=?";
		
		try {
			//쿼리문과 db연결에 필요한데이터를 pstmtAmountOfShoppingcart에 대입.
			pstmtAmountOfShoppingcart = conn.prepareStatement(sqlAmountOfShoppingcart);
			
			//물음표에 변경된 수량과 테이블의 위치를 결정지을  SHoppingcartNo을 대입.
			pstmtAmountOfShoppingcart.setInt(1, amount);
			pstmtAmountOfShoppingcart.setInt(2, shoppingcart.getShoppingcartNo());
			//쿼리 실행 및 결과 출력.
			System.out.println("shoppingcart 테이블의 업데이트 결과" + pstmtAmountOfShoppingcart.executeUpdate());
		//예외처리.
		} catch (SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, AmountOfShoppingcart close");
			e.printStackTrace();
		//객체 종료.
		}finally {
			if(pstmtAmountOfShoppingcart != null) {
				try {
					pstmtAmountOfShoppingcart.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, AmountOfShoppingcart close");
					e.printStackTrace();
				}
			}
		}
		//장바구니에 담긴 하나의 종목 총합을 리턴. *(이것들을 리턴 받아서 장바구니 페이지의 하단 총합에 모두 더하면 주문할 총합 실현 가능!)
		return rePrice;
	}
}
