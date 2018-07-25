// 2018.07.25 28기 전재현
package service;

import java.util.*;
import java.sql.*;

public class BookreviewService {
	
	//상세정보에서 bookNo값의 전체 댓글을 가져 오는 메소드입니다.
	public ArrayList<BookreviewList> totalReview(int bookNo) {
		
		Connection conn = null;
		
		ArrayList<BookreviewList> bookreviewDetail = null;
		
		try {
			
			conn = DButil.connectDB();
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

}
