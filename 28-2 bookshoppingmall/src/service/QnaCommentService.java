package service;

import java.sql.Connection;
import java.sql.SQLException;

public class QnaCommentService {
	
	public boolean addQnaComment(Qna qna) {
		
		Connection conn = null; 
		boolean resultOfAddQna = false;
		
		try {
			
			//DButil클래스를통해 드라이브 로딩 및 연결
			conn = DButil.connectDB();
			
			//자동으로 commit 되지 않게 하기 위해 트랜젝션 처리 설정
			conn.setAutoCommit(false);
			
			QnaCommentDao qnaCommentDao = new QnaCommentDao();
			
			//qnaDao클래스에 있는 메서드 실행
			qnaCommentDao.insertQnaComment(conn, qna);
			
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
}
