//2018.07.25 28기 전재현
package service;

import java.sql.*;
import java.util.*;

public class BookreviewDao {

	//상세 페이지에서 해당 페이지의 댓글 리스트를 출력하기 위한 메서드입니다.
	public ArrayList<BookreviewList> selectBookreview(Connection conn ,int bookNo) {
		
		ResultSet rsSelectBookreview = null;
		PreparedStatement pstmtSelectBookreview = null;
		BookreviewList bookreviewList = null;
		Book book = null;
		Member member = null;
		Bookreview bookreview = null;
		ArrayList<BookreviewList> bookreviewDetail = new ArrayList<BookreviewList>();
		
		//댓글 리스트를 출력하기 위한 LEFT JOIN문 입니다.
		String selectQuery = "SELECT book.book_name ,member.member_id ,bookreview.bookreview_no ,bookreview.book_no ,bookreview.member_no ,bookreview.bookreview_content "
				+"FROM bookreview LEFT JOIN book ON bookreview.book_no = book.book_no LEFT JOIN member ON bookreview.member_no = member.member_no ORDER BY bookreview.bookreview_no DESC";
		
		try {
			pstmtSelectBookreview = conn.prepareStatement(selectQuery);
			
			rsSelectBookreview = pstmtSelectBookreview.executeQuery();
					
			while(rsSelectBookreview.next()) {
				book = new Book();
				book.setBookName(rsSelectBookreview.getString("book_name"));
				
				member = new Member();
				member.setMemberName(rsSelectBookreview.getString("member_name"));
				
				bookreview = new Bookreview();
				bookreview.setBookNo(rsSelectBookreview.getInt("book_no"));
				bookreview.setBookreviewContent(rsSelectBookreview.getString("bookreview_content"));
				bookreview.setBookreviewNo(rsSelectBookreview.getInt("bookreview_no"));
				bookreview.setMemberNo(rsSelectBookreview.getInt("member_no"));
				
				bookreviewList = new BookreviewList();	
				bookreviewList.setBook(book);
				bookreviewList.setMember(member);
				bookreviewList.setBookreview(bookreview);
				
				bookreviewDetail.add(bookreviewList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if(rsSelectBookreview != null) {
				
				try {
					rsSelectBookreview.close();
					
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectBookreview close");
					e.printStackTrace();
					
				}
			}
			
			if(pstmtSelectBookreview != null) {
				
				try {
					pstmtSelectBookreview.close();
					
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectBookreview close");
					e.printStackTrace();
					
				}
			}
		}
		return bookreviewDetail;
	}
	
}
