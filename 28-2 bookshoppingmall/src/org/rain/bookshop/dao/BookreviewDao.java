//2018.07.25 28기 전재현
package org.rain.bookshop.dao;

import java.sql.*;
import java.util.*;
import org.rain.bookshop.dto.*;

public class BookreviewDao {

	//상세 페이지에서 해당 페이지의 댓글 리스트를 출력하기 위한 메서드입니다.
	public ArrayList<BookreviewList> selectBookreview(Connection conn ,int bookNo) {
		
		ResultSet rsSelectBookreview = null;
		PreparedStatement pstmtSelectBookreview = null;
		BookreviewList bookreviewList = null;
		
		//Dao클래스에 들어있는 클래스타의 변수를설정
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
				member.setMemberId(rsSelectBookreview.getString("member_id"));
				
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
	
	//DB에 입력한 댓글을 저장하는 메서드입니다.
	public void insertBookreview(Connection conn ,Bookreview bookrivew) {
		
		PreparedStatement pstmtSelectBookreview = null;
		
		//insertQuery
		String insertQuery = "INSERT INTO bookreview(book_no ,member_no ,bookreview_content) VALUES(? ,? ,?)";
		
		try {
			pstmtSelectBookreview = conn.prepareStatement(insertQuery);
			pstmtSelectBookreview.setInt(1, bookrivew.getBookNo());
			pstmtSelectBookreview.setInt(2, bookrivew.getMemberNo());
			pstmtSelectBookreview.setString(3, bookrivew.getBookreviewContent());
			
			pstmtSelectBookreview.executeUpdate();
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if(pstmtSelectBookreview != null) {
				
				try {
					pstmtSelectBookreview.close();
					
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectBookreview close");
					e.printStackTrace();
					
				}
			}
		}
	}
	
	//DB에 저장되어 있는 값을 삭제 처리하는 메소드입니다.
	public void deleteBookreview(Connection conn ,String bookreviewContent) {
		
		PreparedStatement pstmtdeleteBookreview = null;
		
		String deleteBookreview = "DELETE FROM bookreview WHERE bookreview_content = ?";
		
		try {
			pstmtdeleteBookreview = conn.prepareStatement(deleteBookreview);
			pstmtdeleteBookreview.setString(1, bookreviewContent);
			
			pstmtdeleteBookreview.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if(pstmtdeleteBookreview != null) {
				
				try {
					pstmtdeleteBookreview.close();
					
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtdeleteBookreview close");
					e.printStackTrace();
					
				}
			}
		}
	}
	
	
}
