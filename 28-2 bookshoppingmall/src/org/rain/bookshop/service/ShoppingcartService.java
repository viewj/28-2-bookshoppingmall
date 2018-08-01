package org.rain.bookshop.service;

import java.sql.*;
import java.util.ArrayList;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

public class ShoppingcartService {
	
	public void addShoppingcart(Shoppingcart shoppingcart) {
		
		Connection conn = null;
		
		try {
			//DBtuil클래스를 통해 드라이브 로딩 및 연결 실행
			conn = DbUtils.connectDB();
			
			ShoppingcartDao shoppingcartDao = new ShoppingcartDao();
			
			shoppingcartDao.insertShoppingcart(conn, shoppingcart);
			
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
			System.out.println("End of ShoppingcartService/addShoppingcart()");
		}
	}
	
	//장바구니에 들어있는 값을 삭제하는 메서드입니다.
	public void deleteShoppingcart(int shoppingcartNo) {
		
		Connection conn = null;
		
		try {
			conn = DbUtils.connectDB();
			
			ShoppingcartDao shoppingcartDao = new ShoppingcartDao();
			shoppingcartDao.deleteShoppingcart(conn, shoppingcartNo);
			
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
			System.out.println("End of ShoppingcartService/deleteShoppingcart()");
		}
	}
	
	//회원의 장바구니 목록을 보여주기 위한 메서드입니다.
	public ArrayList<ShoppingPurchaseList> selectShoppingcart(int memberNo){
		
		Connection conn = null;
		ArrayList<ShoppingPurchaseList> arrayListSelectShoppingcart = null;
		
		try {
			
			conn = DbUtils.connectDB();
			ShoppingcartDao shoppingcartDao = new ShoppingcartDao();
			arrayListSelectShoppingcart = shoppingcartDao.selectShoppingcart(conn, memberNo);
			
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
			
			System.out.println("End of ShoppingcartService/selectShoppingcart()");
		}
		
		return arrayListSelectShoppingcart;
	}
}
