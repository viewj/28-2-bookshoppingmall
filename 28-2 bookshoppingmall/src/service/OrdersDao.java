package service;

import java.util.ArrayList;
import java.sql.*;
import service.*;

public class OrdersDao {

	public void insertOrders(Connection conn, Orders orders) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtInsertOrders = null;
		
		//book테이블의 INSERT쿼리문 작성 
		String sqlInsertOrders = "INSERT INTO orders(book_no, member_no, order_price, orders_amount, orders_date, orders_addr) VALUES(?, ?, ?, ?, NOW(), ?)";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtInsertOrders = conn.prepareStatement(sqlInsertOrders);
			
			//INSERT를 위한 value값에 순서대로 set해준다
			pstmtInsertOrders.setInt(1, orders.getBookNo());
			pstmtInsertOrders.setInt(2, orders.getMemberNo());
			pstmtInsertOrders.setInt(3, orders.getOrdersPrice());
			pstmtInsertOrders.setInt(4, orders.getOrdersAmount());
			pstmtInsertOrders.setString(5, orders.getOrdersAddr());
			
			//쿼리 실행
			System.out.println("삽입된 Order 레코드의 수 : " + pstmtInsertOrders.executeUpdate());
			
		//try과정에서 예외 발생시 catch과정으로 넘어간다
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertOrders main");
			e.printStackTrace();
		//try과정과 try catch과정 후 finally 과정으로 넘어감
		} finally {
			if(pstmtInsertOrders != null) {
				try {
					pstmtInsertOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertOrders close");
					e.printStackTrace();
				} 
			}
		}
	}

	
	public void deleteOrders(Connection conn, int ordersNo) {
		PreparedStatement pstmtDeleteOrders = null;
		
		String sqlDeleteOrders = "DELETE FROM orders WHERE orders_no=?";
		
		try {
			pstmtDeleteOrders = conn.prepareStatement(sqlDeleteOrders);
			
			pstmtDeleteOrders.setInt(1, ordersNo);
			
			System.out.println("삭제된 orders 레코드의 수 : " + pstmtDeleteOrders.executeUpdate());
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deleteOrders main");
			e.printStackTrace();
		} finally {
			if(pstmtDeleteOrders != null) {
				try {
					pstmtDeleteOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, deleteOrders close");
					e.printStackTrace();
				} 
			}
		}
	}
	
	//해당 member_no을 가진 member의 주문리스트를 보기 위한 메서드 
	public ArrayList<OrdersAndBookAndMember> SelectMemberOrders(Connection conn, int memberNo) {
		PreparedStatement pstmtSelectMemberOrders = null;
		ResultSet rsSelectMemberOrders = null;
		
		ArrayList<OrdersAndBookAndMember> arrayListOrdersAndBook = new ArrayList<OrdersAndBookAndMember>();
		
		Orders orders = null;
		Book book = null;
		Member member = null;
		OrdersAndBookAndMember ordersAndBookAndMemberSelect = null;
		
		String sqlSelectOrders = "SELECT o.orders_no, b.book_name, m.member_name, o.orders_addr, o.orders_amount, o.orders_price, o.orders_date"
				+ "FROM orders o INNER JOIN book b ON o.book_no = b.book_no INNER JOIN member m ON o.member_no = m.member_no WHERE member_no=?";
		
		try {
			pstmtSelectMemberOrders = conn.prepareStatement(sqlSelectOrders);
			
			pstmtSelectMemberOrders.setInt(1, memberNo);
			
			rsSelectMemberOrders = pstmtSelectMemberOrders.executeQuery();

			while(rsSelectMemberOrders.next()) {
				orders = new Orders();
				orders.setOrdersNo(rsSelectMemberOrders.getInt("orders_no"));
				orders.setOrdersAddr(rsSelectMemberOrders.getString("orders_addr"));
				orders.setOrdersAmount(rsSelectMemberOrders.getInt("orders_amount"));
				orders.setOrdersPrice(rsSelectMemberOrders.getInt("orders_price"));
				orders.setOrderDate(rsSelectMemberOrders.getString("orders_date"));
				
				book = new Book();
				book.setBookName(rsSelectMemberOrders.getString("book_name"));
				
				member = new Member();
				member.setMemberName(rsSelectMemberOrders.getString("member_name"));
				
				ordersAndBookAndMemberSelect = new OrdersAndBookAndMember();
				ordersAndBookAndMemberSelect.setOrders(orders);
				ordersAndBookAndMemberSelect.setBook(book);
				ordersAndBookAndMemberSelect.setMember(member);
				
				arrayListOrdersAndBook.add(ordersAndBookAndMemberSelect);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectForMemberOrders");
			e.printStackTrace();
		} finally {
			if(rsSelectMemberOrders != null) {
				try {
					rsSelectMemberOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectMemberOrders close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectMemberOrders != null) {
				try {
					pstmtSelectMemberOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectMemberOrders close");
					e.printStackTrace();
				}
			}
		}			
		return arrayListOrdersAndBook;		
	}
	
	
	
	//member가 주문한 orderList에서 orderNo값을 기준으로 update하기 위해 기존 정보를 가져오는 메서드
	public OrdersAndBookAndMember SelectForUpdateMemberOrders(Connection conn, int orderNo) {
		PreparedStatement pstmtSelectForUpdateMemberOrders = null;
		ResultSet rsSelectForUpdateMemberOrders = null;
		
		Orders orders = null;
		Book book = null;
		Member member = null;
		OrdersAndBookAndMember ordersAndBookAndMemberForUpdateSelect = null;
		
		String sqlSelectOrders = "SELECT o.orders_no, b.book_name, m.member_name, o.orders_addr, o.orders_amount, o.orders_price, o.orders_date"
				+ "FROM orders o INNER JOIN book b ON o.book_no = b.book_no INNER JOIN member m ON o.member_no = m.member_no WHERE order_no=?";
		
		try {
			pstmtSelectForUpdateMemberOrders = conn.prepareStatement(sqlSelectOrders);
			
			pstmtSelectForUpdateMemberOrders.setInt(1, orderNo);
			
			rsSelectForUpdateMemberOrders = pstmtSelectForUpdateMemberOrders.executeQuery();

			if(rsSelectForUpdateMemberOrders.next()) {
				orders = new Orders();
				orders.setOrdersNo(rsSelectForUpdateMemberOrders.getInt("orders_no"));
				orders.setOrdersAddr(rsSelectForUpdateMemberOrders.getString("orders_addr"));
				orders.setOrdersAmount(rsSelectForUpdateMemberOrders.getInt("orders_amount"));
				orders.setOrdersPrice(rsSelectForUpdateMemberOrders.getInt("orders_price"));
				orders.setOrderDate(rsSelectForUpdateMemberOrders.getString("orders_date"));
				
				book = new Book();
				book.setBookName(rsSelectForUpdateMemberOrders.getString("book_name"));
				
				member = new Member();
				member.setMemberName(rsSelectForUpdateMemberOrders.getString("member_name"));
				
				ordersAndBookAndMemberForUpdateSelect = new OrdersAndBookAndMember();
				ordersAndBookAndMemberForUpdateSelect.setOrders(orders);
				ordersAndBookAndMemberForUpdateSelect.setBook(book);
				ordersAndBookAndMemberForUpdateSelect.setMember(member);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectForMemberOrders");
			e.printStackTrace();
		} finally {
			if(rsSelectForUpdateMemberOrders != null) {
				try {
					rsSelectForUpdateMemberOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectMemberOrders close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectForUpdateMemberOrders != null) {
				try {
					pstmtSelectForUpdateMemberOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectMemberOrders close");
					e.printStackTrace();
				}
			}
		}			
		return ordersAndBookAndMemberForUpdateSelect;		
	}
	
	
	public void updateOrders(Connection conn, OrdersAndBookAndMember ordersAndBookAndMemberForUpdateSelect) {

		PreparedStatement pstmtUpdateOrders = null;	
		
		Orders orders = new Orders();
		Book book = new Book();
		Member member = new Member();
		
		orders = ordersAndBookAndMemberForUpdateSelect.getOrders();
		book = ordersAndBookAndMemberForUpdateSelect.getBook();
		member = ordersAndBookAndMemberForUpdateSelect.getMember();
		
		String sqlUpdateOrders = "UPDATE orders o INNER JOIN book b ON o.book_no = b.book_no INNER JOIN member m  ON o.member_no = m.member_no"
				+ "SET orders_no=?, book_name=?, member_name=?, orders_addr=?, orders_amount=?, orders_price=?, orders_date=now() WHERE orders_no=?";
		
		try {
			pstmtUpdateOrders = conn.prepareStatement(sqlUpdateOrders);			

			pstmtUpdateOrders.setInt(1, orders.getOrdersNo());
			pstmtUpdateOrders.setString(2, book.getBookName());
			pstmtUpdateOrders.setString(3, member.getMemberName());
			pstmtUpdateOrders.setString(4, orders.getOrdersAddr());
			pstmtUpdateOrders.setInt(5, orders.getOrdersAmount());
			pstmtUpdateOrders.setInt(6, orders.getOrdersPrice());
			pstmtUpdateOrders.setInt(7, orders.getOrdersNo());
			
			pstmtUpdateOrders.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, updateOrders main");
			e.printStackTrace();

		} finally {
			if(pstmtUpdateOrders != null) {
				try {
					pstmtUpdateOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtUpdateOrders close");
					e.printStackTrace();
				} 
			}
		}
	}	
	
	
	//관리자가 모든 주문리스트를 보기 위한 메서드 
	public ArrayList<OrdersAndBookAndMember> SelectAllOrders(Connection conn) {
		PreparedStatement pstmtSelectAllOrders = null;
		ResultSet rsSelectAllOrders = null;
		
		ArrayList<OrdersAndBookAndMember> arrayListAllOrders = new ArrayList<OrdersAndBookAndMember>();
		
		Orders orders = null;
		Book book = null;
		Member member = null;
		OrdersAndBookAndMember ordersAndBookAndMemberAllSelect = null;
		
		String sqlSelectOrders = "SELECT o.orders_no, b.book_name, m.member_name, o.orders_addr, o.orders_amount, o.orders_price, o.orders_date"
				+ "FROM orders o INNER JOIN book b ON o.book_no = b.book_no INNER JOIN member m ON o.member_no = m.member_no";
		
		try {
			pstmtSelectAllOrders = conn.prepareStatement(sqlSelectOrders);
			
			rsSelectAllOrders = pstmtSelectAllOrders.executeQuery();

			while(rsSelectAllOrders.next()) {
				orders = new Orders();
				orders.setOrdersNo(rsSelectAllOrders.getInt("orders_no"));
				orders.setOrdersAddr(rsSelectAllOrders.getString("orders_addr"));
				orders.setOrdersAmount(rsSelectAllOrders.getInt("orders_amount"));
				orders.setOrdersPrice(rsSelectAllOrders.getInt("orders_price"));
				orders.setOrderDate(rsSelectAllOrders.getString("orders_date"));
				
				book = new Book();
				book.setBookName(rsSelectAllOrders.getString("book_name"));
				
				member = new Member();
				member.setMemberName(rsSelectAllOrders.getString("member_name"));
				
				ordersAndBookAndMemberAllSelect = new OrdersAndBookAndMember();
				ordersAndBookAndMemberAllSelect.setOrders(orders);
				ordersAndBookAndMemberAllSelect.setBook(book);
				ordersAndBookAndMemberAllSelect.setMember(member);
				
				arrayListAllOrders.add(ordersAndBookAndMemberAllSelect);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectAllOrders");
			e.printStackTrace();
		} finally {
			if(rsSelectAllOrders != null) {
				try {
					rsSelectAllOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectAllOrders close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectAllOrders != null) {
				try {
					pstmtSelectAllOrders.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectAllOrders close");
					e.printStackTrace();
				}
			}
		}			
		return arrayListAllOrders;		
	}
	
}