//2018.07.23 28기 정규룡 ,전재현(짝코딩)
package service;

import java.sql.*;
import java.util.ArrayList;

public class QnaService {

	public boolean addQna(Qna qna) {
		
		Connection conn = null; 
		boolean resultOfAddQna = false;
		
		try {
			
			//DButil클래스를통해 드라이브 로딩 및 연결
			conn = DButil.connectDB();
			
			//자동으로 commit 되지 않게 하기 위해 트랜젝션 처리 설정
			conn.setAutoCommit(false);
			
			QnaDao qnaDao = new QnaDao();
			
			//qnaDao클래스에 있는 메서드 실행
			qnaDao.insertQna(conn, qna);
			
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
				System.out.println("End of QnaService/addQna()");
			}
		}
		
		return resultOfAddQna;
	}
	
	public Qna selectForUpdateQna(Qna qna) {
		
		Connection conn = null;
				
		try {
			conn = DButil.connectDB();
			
			QnaDao qnaDao = new QnaDao();
			
			qnaDao.insertQna(conn, qna);
			conn.commit();
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
				System.out.println("End of QnaService/selectForUpdateQna()");
			}
		}
		return qna;
		
	}
	
	public void updateQna(Qna qna) {
		
		Connection conn = null;
		
		try {
			conn = DButil.connectDB();
			
			QnaDao qnaDao = new QnaDao();
			
			qnaDao.updateQna(conn, qna);
			conn.commit();
			
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
				System.out.println("End of QnaService/updateQna()");
			}
		}
		
	}
	
	public void deleteQna(Qna qna) {
		
		Connection conn = null;
		
		try {
			conn = DButil.connectDB();
			
			QnaDao qnaDao = new QnaDao();
			
			qnaDao.deleteQna(conn, qna);
			conn.commit();
			
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
				System.out.println("End of QnaService/deleteQna()");
			}
		}
	}

	public ArrayList<Qna> selectAllQnas() {
		
		Connection conn = null;
		ArrayList<Qna> arrayListQna = new ArrayList<Qna>();
		
		try {
			conn = DButil.connectDB();
			
			QnaDao qnaDao = new QnaDao();
			arrayListQna = qnaDao.selectAllQnas(conn);
			
			
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
				System.out.println("End of QnaService/deleteQna()");
			}
		}
		return arrayListQna;
	}
}


