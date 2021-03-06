package org.rain.bookshop.service;

import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

import java.sql.*;
import java.util.ArrayList;

public class QnaCommentService {
	
	public boolean addQnaComment(QnaComment qnaComment) {
		
		Connection conn = null; 
		boolean resultOfAddQna = false;
		
		try {
			
			//DButil클래스를통해 드라이브 로딩 및 연결
			conn = DbUtils.connectDB();
			
			//자동으로 commit 되지 않게 하기 위해 트랜젝션 처리 설정
			conn.setAutoCommit(false);
			
			QnaCommentDao qnaCommentDao = new QnaCommentDao();
			
			//qnaCommentDao클래스에 있는 메서드 실행
			qnaCommentDao.insertQnaComment(conn, qnaComment);
			
			// 예외 없이 실행이 되었으면 커밋을 통해 my-sql에 반영
			conn.commit();
			
			resultOfAddQna = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e2) {
						System.out.println("conn 객체 종료 중 예외");
						e2.printStackTrace();
					}
				}
				System.out.println("End of QnaService/addQnaComment()");
			}
		}
		
		return resultOfAddQna;
	}
	
	public ArrayList<QnaComment> selectAllQnaComments(int qnaNo) {
		
		Connection conn = null;
		ArrayList<QnaComment> arrayListQnaComments = new ArrayList<QnaComment>();
		
		try {
			conn = DbUtils.connectDB();
			
			QnaCommentDao qnaCommentDao = new QnaCommentDao();
			arrayListQnaComments = qnaCommentDao.selectQnaCommentList(conn, qnaNo);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e2) {
						System.out.println("conn 객체 종료 중 예외");
						e2.printStackTrace();
					}
				}
				System.out.println("End of QnaService/selectAllQnas()");
			}
		}
		return  arrayListQnaComments;
	}
}
