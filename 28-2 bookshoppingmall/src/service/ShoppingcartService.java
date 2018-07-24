package service;

import java.sql.*;
import java.util.ArrayList;

import service.*;

public class ShoppingcartService {
	public String addShoppingcart(Shoppingcart shoppingcart) {
		Connection conn = null;
		String bookOut = null;
		try {
			conn = DButil.connectDB();
			
			ShoppingcartDao shoppingcartDao = new ShoppingcartDao();
			
			shoppingcartDao.insertShoppingcart(conn, shoppingcart);
			
			BookDao bookDao = new BookDao();
			
	
			
			
			
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
		return bookOut;
	}
	
	public void deleteShoppingcart(int shoppingcartNo) {
		Connection conn = null;
		try {
			conn = DButil.connectDB();
			
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
	
	public ArrayList<Shoppingcart> selectShoppingcart(int memberNo){
		Connection conn = null;
		ArrayList<Shoppingcart> arrayListSelectShoppingcart = null;
		try {
			conn = DButil.connectDB();
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
