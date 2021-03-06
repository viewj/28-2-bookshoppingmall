package org.rain.bookshop.service;

import java.sql.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

import java.util.ArrayList;

public class PublisherService {
	
	//츌판사 정보를 입력하기위한 메서드
	public void addPublisher(Publisher publisher) {
		Connection conn = null;
		try {
			conn = DbUtils.connectDB();
			
			PublisherDao publisherDao = new PublisherDao();
			publisherDao.insertPublisher(conn, publisher);
			
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
	
	// Publisher 테이블의 모든 레코드를 조회하여 리스트로 리턴받는 메서드
	// 매개변수는 없다.
	// 리턴 데이터 타입으로는 퍼블리셔 객체를 담는 배열리스트. 조회된 모든 레코드를 담기 위함이다.
	public ArrayList<Publisher> getAllPublishers() {
		Connection conn = null;
		ArrayList<Publisher> arrayListOfAllPublishers = null;
		try {
			// DB 연결
			conn = DbUtils.connectDB();
			
			// Bookcode 테이블의 모든 레코드를 조회하여 리스트를 리턴하는 메서드 호출
			PublisherDao publisherDao = new PublisherDao();
			arrayListOfAllPublishers = publisherDao.selectAllPublishers(conn);
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
		return arrayListOfAllPublishers;
	}
}
