package service;

import java.sql.*;
import java.util.ArrayList;

import service.*;

public class PublisherDao {
	
	public ArrayList<Publisher> selectAllPublishers(Connection conn){
		PreparedStatement pstmtSelectAllPublishers = null;
		ResultSet rsSelectAllPublishers = null;
		ArrayList<Publisher> arrayListSelectAllPublishers = new ArrayList<Publisher>();
		Publisher publisher = null;
		
		
		String sqlSelectAllPublishers = "SELECT publisher_no, publisher_name, publisher_website FROM publisher";
		
		try {
			
			pstmtSelectAllPublishers = conn.prepareStatement(sqlSelectAllPublishers);
			
			
			rsSelectAllPublishers = pstmtSelectAllPublishers.executeQuery();
			
			
			while(rsSelectAllPublishers.next()) {
				
				publisher = new Publisher();
			
				publisher.setPublisherNo(rsSelectAllPublishers.getInt("publisher_no"));
				publisher.setPublisherName(rsSelectAllPublishers.getString("publisher_name"));
				publisher.setPublisherWebsite(rsSelectAllPublishers.getString("publisher_website"));
				
				arrayListSelectAllPublishers.add(publisher);
			}
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertPublisher main");
			e.printStackTrace();
		} finally {
			if(pstmtSelectAllPublishers != null) {
				try {
					pstmtSelectAllPublishers.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertPublisher close");
					e.printStackTrace();
				}
			}
			if(rsSelectAllPublishers != null) {
				try {
					rsSelectAllPublishers.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertPublisher close");
					e.printStackTrace();
				}
			}
		}
		return arrayListSelectAllPublishers;
	}

	public void insertPublisher(Connection conn,Publisher publisher) {
		// 객체참조변수 선언
		PreparedStatement pstmtInsertPublisher = null;
		// publisher 테이블에 레코드를 삽입하는 쿼리문
		String sqlInsertPublisher = "INSERT INTO publisher(publisher_name, publisher_website) VALUES (?, ?)";
		
		try {
			pstmtInsertPublisher = conn.prepareStatement(sqlInsertPublisher);
			// 쿼리문 ?자리에 들어갈 값 호출하기
			pstmtInsertPublisher.setString(1, publisher.getPublisherName());
			pstmtInsertPublisher.setString(2, publisher.getPublisherWebsite());
			// 쿼리문 실행 후 그 리턴값을 변수에 담기
			int resultUpdate = pstmtInsertPublisher.executeUpdate();
			
			System.out.println("publisher 테이블에 삽입된 행 갯수 : " + resultUpdate);
		} catch (SQLException e) {
			System.out.println("쿼리문에서 예외가 발생하였습니다, InsertPublisher");
			e.printStackTrace();
		} finally {
			// 객체참조변수가 null이 아닐때 영역이 있는것이기 때문에 객체종료를 할 수 있다.
			if (pstmtInsertPublisher != null) {
				try {
					pstmtInsertPublisher.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtInsertPublisher 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
	
	public void deletePublisher(Connection conn, Publisher publisher) {
		// 객체 참조 변수 선언
		PreparedStatement pstmtDeletePublisher = null;
		// publisher 테이블에 레코드를 삭제하는 쿼리문 
		String sqlDeletePublisher = "DELETE FROM publisher WHERE publisher_no=?";
		// publisherNo가 잘 받아졌는지 확인
		System.out.println("memberNo, memberList.jsp => MemberDao.java " + publisher.getPublisherNo());
		try {
			pstmtDeletePublisher = conn.prepareStatement(sqlDeletePublisher);
			// Delete쿼리문에 있는 ?자리에 들어갈 publisherNo값 호출
			pstmtDeletePublisher.setInt(1, publisher.getPublisherNo());
			// 쿼리문 실행 후에 테이블에서 변하는 레코드의 숫자를 리턴받아서 변수에 담아준다.
			int resultUpdate = pstmtDeletePublisher.executeUpdate();
			
			System.out.println("publisher 테이블에 삭제된 행 갯수 : " + resultUpdate);
		} catch (SQLException e) {
			System.out.println("쿼리문에서 예외가 발생하였습니다, DeletePublisher");
			e.printStackTrace();
		} finally {
			if (pstmtDeletePublisher != null) {
				try {
					pstmtDeletePublisher.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtDeletePublisher 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
}
