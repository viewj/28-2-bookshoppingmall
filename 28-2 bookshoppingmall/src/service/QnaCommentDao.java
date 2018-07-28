package service;

import java.sql.*;
import java.util.ArrayList;

import service.*;

public class QnaCommentDao {
	
	// Qna_comment테이블에 정보 입력하는 메소드
		public void insertQnaComment(Connection conn, QnaComment qnaComment) {
			// 객체참조변수 선언
			PreparedStatement pstmtInsertQna = null;
			String sqlInsertQna = "INSERT INTO qna_comment(qna_no, admin_no, qna_comment_content, qna_comment_date) VALUES (?, ?, ?, now())";
			try {
				pstmtInsertQna = conn.prepareStatement(sqlInsertQna);
				
				// 쿼리문의 ?자리에 들어갈 값들을 넣어준다.
				pstmtInsertQna.setInt(1, qnaComment.getQnaNo());
				pstmtInsertQna.setInt(2, qnaComment.getAdminNo());
				pstmtInsertQna.setString(3, qnaComment.getQnaCommentContent());
				
				// 쿼리문이 실행이 되면 수정된 행의 수가 리턴된다.
				int resultInsert = pstmtInsertQna.executeUpdate();
				System.out.println("qna 테이블에 추가된 행 갯수 : " + resultInsert);
				
			// 예외 발생 시 콘솔창에 해당 문자열 출력
			} catch (SQLException e) {
				System.out.println("DB에서 예외가 발생하였습니다, pstmtInsertQna");
				e.printStackTrace();
			} finally {
				if (pstmtInsertQna != null) {
					try {
						pstmtInsertQna.close();
					} catch(SQLException sqlException) {
						System.out.println("pstmtInsertQna 객체 종료 중 예외 발생");
						sqlException.printStackTrace();
					}
				}
			}
		}
	
	//QnaComment 클래스를 리턴 데이터 타입 지정. selectForUpdateQnaComment 메서드 선언. 매개변수로는 db연결을 위한 conn과 int 데이터 타입인 adminNo(session)과 qnaNo(외래키)을 선언.
	public QnaComment selectForUpdateQnaComment(Connection conn, int sessionAdminNo, int qnaNo) {
		//초기값 지정
		PreparedStatement pstmtSelectForUpdateQnaComment = null;
		ResultSet rsSelectForUpdateQnaComment = null;
		//QnaComment 클래스에 대한 객체 생성.
		QnaComment qnaComment = new QnaComment();
		//쿼리문 변수에 대입.
		String sqlSelectForUpdateQnaComment = "SELECT qna_comment_no, qna_no, admin_no, qna_comment_content FROM qna_comment where qna_no=? and admin_no=?";
		
		try {
			//PreparedStatement 객체 참조 변수에 db 실행을 위한 데이터 삽입.
			pstmtSelectForUpdateQnaComment = conn.prepareStatement(sqlSelectForUpdateQnaComment);
			//쿼리문의 ?에 값 입력.
			pstmtSelectForUpdateQnaComment.setInt(1, qnaNo);
			pstmtSelectForUpdateQnaComment.setInt(2, sessionAdminNo);
			//쿼리 실행 정보를 ResultSet 객체참조 변수에 대입.
			rsSelectForUpdateQnaComment = pstmtSelectForUpdateQnaComment.executeQuery();
			//테이블 내에 생성된 데이터 값 추출.
			if(rsSelectForUpdateQnaComment.next()) {
				//추출된 데이터 값을 qnaComment 클래스 내부의 메서드에 셋팅.
				qnaComment.setQnaCommentNo(rsSelectForUpdateQnaComment.getInt("qna_comment_no"));
				qnaComment.setQnaNo(rsSelectForUpdateQnaComment.getInt("qna_no"));
				qnaComment.setAdminNo(rsSelectForUpdateQnaComment.getInt("admin_no"));
				qnaComment.setQnaCommentContent(rsSelectForUpdateQnaComment.getString("qna_comment_content"));
			}
		//예외처리
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rsSelectForUpdateQnaComment != null) {
				try {
					rsSelectForUpdateQnaComment.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, selectForUpdateQnaComment close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectForUpdateQnaComment != null) {
				try {
					pstmtSelectForUpdateQnaComment.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, selectForUpdateQnaComment close");
					e.printStackTrace();
				}
			}
		}
		//셋팅된 데이터가 있는 qnaComment 클래스 리턴.
		return qnaComment;
		
	}
	//리턴 데이터 타입은 void. updateQnaComment 메서드 선언 후 매개변수로는 conn과 QnaComment 클래스 데이터 타입인 qnaComment를 선언.
	public void updateQnaComment(Connection conn, QnaComment qnaComment) {
		//초기값.
		PreparedStatement pstmtUpdateQnaComment = null;
		//String 데이터 타입에 쿼리문 대입.
		String sqlUpdateQnaComment = "UPDATE qna_comment SET qna_comment_content=?, qna_comment_date=now() where qna_comment_no=?";
		
		try {
			//db 작업을 위한 데이터 들을 PreparedStatement 객체참조변수에 대입.
			pstmtUpdateQnaComment = conn.prepareStatement(sqlUpdateQnaComment);
			//쿼리문의 ?값에 해당 값 삽입.
			pstmtUpdateQnaComment.setString(1, qnaComment.getQnaCommentContent());
			pstmtUpdateQnaComment.setInt(2, qnaComment.getQnaCommentNo());
			//쿼리 실행.
			pstmtUpdateQnaComment.executeUpdate();
		//예외처리.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmtUpdateQnaComment != null) {
				try {
					pstmtUpdateQnaComment.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, updateQnaComment close");
					e.printStackTrace();
				}
			}
		}
	}
	
	public  ArrayList<QnaComment> selectQnaCommentList(Connection conn, int qnaNo) {
		PreparedStatement pstmtSelectQnaCommentList = null;
		ResultSet rsSelectQnaCommentList = null;
		ArrayList<QnaComment> qnaCommentList = new ArrayList<QnaComment>();
		QnaComment qnaComment = null;
		
		String sqlSelectUserQnaList = "SELECT qna_no, admin_no, qna_comment_content, qna_comment_date FROM qna_comment where qna_no=? ORDER BY qna_comment_no DESC";
		
		try {
			pstmtSelectQnaCommentList = conn.prepareStatement(sqlSelectUserQnaList);
		
			pstmtSelectQnaCommentList.setInt(1, qnaNo);
			
			rsSelectQnaCommentList = pstmtSelectQnaCommentList.executeQuery();
			
			while(rsSelectQnaCommentList.next()) {
				qnaComment = new QnaComment();
				qnaComment.setQnaNo(rsSelectQnaCommentList.getInt("qna_no"));
				qnaComment.setAdminNo(rsSelectQnaCommentList.getInt("admin_no"));
				qnaComment.setQnaCommentContent(rsSelectQnaCommentList.getString("qna_comment_content"));
				qnaComment.setQnaCommentDate(rsSelectQnaCommentList.getString("qna_comment_date"));
				qnaCommentList.add(qnaComment);
			}
			
		} catch (SQLException e) {
			System.out.println("pstmtSelectQnaCommentList 객체 종료 중 예외 발생");
			e.printStackTrace();
		}
		return qnaCommentList;
		
	}
}
