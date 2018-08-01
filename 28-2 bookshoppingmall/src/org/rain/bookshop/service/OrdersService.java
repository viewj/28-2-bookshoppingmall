//2018-07-25 서연문
package org.rain.bookshop.service;

import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

import java.sql.*;
import java.util.*;

public class OrdersService {
	
	//주문 등록 메서드
	public boolean addOrders(Orders orders) {
		
		Connection conn = null;
		boolean resltOfAddOrders = false;
		
		try {
			conn = DbUtils.connectDB();
			
			conn.setAutoCommit(false);
			
			OrdersDao ordersDao = new OrdersDao();
			
			ordersDao.insertOrders(conn, orders);
			
			conn.commit();
			
			resltOfAddOrders = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlExceptio) {
				sqlExceptio.printStackTrace();
			}
		} finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resltOfAddOrders;
	}
	
	//주문내역 삭제 메서드
	public boolean deleteOrders(int ordersNo) {
	
		Connection conn = null;
		boolean resltOfAddOrders = false;
		
		try {	
			conn = DbUtils.connectDB();
			
			conn.setAutoCommit(false);
			
			OrdersDao ordesrDao = new OrdersDao();
			
			ordesrDao.deleteOrders(conn, ordersNo);
			
			conn.commit();
		
			resltOfAddOrders = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlExceptio) {
				sqlExceptio.printStackTrace();
			}
		} finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resltOfAddOrders;
	}
	
	//주문 내역 수정하기 위한 메서드
	public OrdersAndBookAndMember UpdateOrdersSelect(int orderNo) {
		
		Connection conn = null;
		OrdersAndBookAndMember ordersUpdate = new OrdersAndBookAndMember();
		
		conn = DbUtils.connectDB();
		
		OrdersDao ordesrDao = new OrdersDao();
		
		ordersUpdate = ordesrDao.SelectForUpdateMemberOrders(conn, orderNo);
		
		return ordersUpdate;		
	}
	
	//주문 내역 상세보기 & 주문 내역 수정하기 위해 등록된 값을 가져오는 메서드
	public boolean updateOrders(OrdersAndBookAndMember ordersAndBookAndMemberForUpdateSelect) {
		
		Connection conn = null;
		boolean resltOfAddOrders = false;
		
		try {
			conn = DbUtils.connectDB();
			
			conn.setAutoCommit(false);
			
			OrdersDao ordersDao = new OrdersDao();
			
			ordersDao.updateOrders(conn, ordersAndBookAndMemberForUpdateSelect);
			
			conn.commit();
			
			resltOfAddOrders = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlExceptio) {
				sqlExceptio.printStackTrace();
			}
		} finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resltOfAddOrders;
	}
}	
	

