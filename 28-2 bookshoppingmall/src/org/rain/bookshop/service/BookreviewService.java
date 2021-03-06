// 2018.07.25 28기 전재현
package org.rain.bookshop.service;

import java.util.*;
import java.sql.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

public class BookreviewService {
	
	//상세정보에서 bookNo값의 전체 댓글을 가져 오는 메소드입니다.
	public ArrayList<BookreviewList> totalReview(int bookNo) {
		System.out.println(bookNo +"<-bookNo BookreviewService.java");
		
		Connection conn = null;
		
		ArrayList<BookreviewList> bookreviewDetail = null;
		
		try {
			
			conn = DbUtils.connectDB();
			BookreviewDao bookreviewDao = new BookreviewDao();
			
			bookreviewDetail = bookreviewDao.selectBookreview(conn ,bookNo);
			
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
		
		return bookreviewDetail;
	}

	//화면에서 입력한값을 DB에 저장하는 메서드입니다.
	public void insertBookreview(Bookreview bookreview) {
		
		Connection conn = null;
		
		try {
			
			conn = DbUtils.connectDB();
			BookreviewDao bookreviewDao = new BookreviewDao();
			
			bookreviewDao.insertBookreview(conn ,bookreview);
			
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
	
	//댓글 삭제 처리하는 메소드
	public void deleteBookreview(String bookreviewContent) {
		
		Connection conn = null;
		
		try {
			
			conn = DbUtils.connectDB();
			
			//댓글 처리 시작하는 메서드
			BookreviewDao bookreviewDao = new BookreviewDao();
			
			bookreviewDao.deleteBookreview(conn ,bookreviewContent);
			
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
	
}
