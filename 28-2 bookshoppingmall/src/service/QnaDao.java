// 2018.07.23 정규룡, 전재현 짝코딩
package service;

import java.sql.*;
import java.util.ArrayList;

public class QnaDao {
	
	// Qna테이블에 정보 입력하는 메소드
	public void insertQna(Connection conn, Qna qna) {
		// 객체참조변수 선언
		PreparedStatement pstmtInsertQna = null;
		String sqlInsertQna = "INSERT INTO qna(member_no, qna_title, qna_content, qna_date) VALUES (?, ?, ?, now())";
		try {
			pstmtInsertQna = conn.prepareStatement(sqlInsertQna);
			
			// 쿼리문의 ?자리에 들어갈 값들을 넣어준다.
			pstmtInsertQna.setInt(1, qna.getMember_no());
			pstmtInsertQna.setString(2, qna.getQna_title());
			pstmtInsertQna.setString(3, qna.getQna_content());
			
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
	
	// qna테이블을 수정하기 위한 정보출력
	public Qna selectForUpdateQna(Connection conn, int qnaNo) {
		// 객체참조변수 선언
		PreparedStatement pstmtSelectForUpdateQna = null;
		ResultSet rsSelectForUpdateQna = null; 
		Qna qna = new Qna();
		String sqlSelectForUpdateQna = "SELECT qna_no, member_no, qna_title, qna_content, qna_date FROM qna WHERE qna_no=?";
		
		try {
			pstmtSelectForUpdateQna = conn.prepareStatement(sqlSelectForUpdateQna);
			// 쿼리문의 ?자리에 들어갈 값들을 넣어준다.
			pstmtSelectForUpdateQna.setInt(1, qnaNo);
			
			// 실행 결과값을 변수에 담는다.
			rsSelectForUpdateQna = pstmtSelectForUpdateQna.executeQuery();
			
			// 해당 하는 컬럼의 값들을 넣어준다.
			if (rsSelectForUpdateQna.next()) {
				qna.setMember_no(rsSelectForUpdateQna.getInt("member_no"));
				qna.setQna_title(rsSelectForUpdateQna.getString("qna_title"));
				qna.setQna_content(rsSelectForUpdateQna.getString("qna_content"));
				qna.setQna_date(rsSelectForUpdateQna.getString("qna_date"));
				qna.setQna_no(qnaNo);
			}
			
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, pstmtSelectForUpdateQna");
			e.printStackTrace();
		} finally {
			if (rsSelectForUpdateQna != null) {
				try {
					rsSelectForUpdateQna.close();
				} catch(SQLException sqlException) {
					System.out.println("rsSelectForUpdateQna 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
			
			if (pstmtSelectForUpdateQna != null) {
				try {
					pstmtSelectForUpdateQna.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtSelectForUpdateQna 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
		
		return qna;
		
	}
	
	// qna테이블을 수정하는 메소드
	public void updateQna(Connection conn, Qna qna) {
		//객체참조변수 선언
		PreparedStatement pstmtUpdateQna = null;
		String sqlUpdateQna = "UPDATE qna SET qna_title=?, qna_content=?,qna_date=now() WHERE qna_no=?";
		
		try {
			pstmtUpdateQna = conn.prepareStatement(sqlUpdateQna);
			// 쿼리문의 ?자리에 해당하는 값들을 넣어준다.
			pstmtUpdateQna.setString(1, qna.getQna_title());
			pstmtUpdateQna.setString(2, qna.getQna_content());
			pstmtUpdateQna.setInt(3, qna.getQna_no());
			
			// 쿼리문이 제대로 실행되면 1, 아니면 0을 리턴하고 변수에 담는다.
			int resultUpdate = pstmtUpdateQna.executeUpdate();
			System.out.println("qna 테이블에 수정된 행 갯수 : " + resultUpdate);
			
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, pstmtUpdateQna");
			e.printStackTrace();
		} finally {
			if (pstmtUpdateQna != null) {
				try {
					pstmtUpdateQna.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtUpdateQna 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
	
	// qna테이블의 한 행을 삭제하는 메소드
	public void deleteQna(Connection conn, Qna qna) {
		// 객체참조변수 선언
		PreparedStatement pstmtDeleteQna = null;
		String sqlDeleteQna = "DELETE FROM qna WHERE qna_no=?";
		
		try {
			pstmtDeleteQna = conn.prepareStatement(sqlDeleteQna);
			// 쿼리문의 ?자리에 해당하는 값을 넣어준다.
			pstmtDeleteQna.setInt(1, qna.getQna_no());
			
			// 쿼리문이 제대로 실행되면 1, 아니면 0을 리턴하고 변수에 담는다.
			int resultDelete = pstmtDeleteQna.executeUpdate();
			System.out.println("qna 테이블에 삭제된 행 갯수 : " + resultDelete);
			
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, pstmtDeleteQna");
			e.printStackTrace();
		} finally {
			if (pstmtDeleteQna != null) {
				try {
					pstmtDeleteQna.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtDeleteQna 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
	
	// qna테이블 전체 행 리스트를 가져오는 메소드
	public ArrayList<Qna> selectAllQnas(Connection conn) {
		// 객체참조변수 선언
		PreparedStatement pstmtSelectAllQnas = null;
		ResultSet rsSelectAllQnas = null;
		String sqlSelectAllQnas = "SELECT qna_no, member_no, qna_title, qna_content, qna_date FROM qna";
		ArrayList<Qna> ArrayListQna = null;
		Qna qna = null;
		
		try {
			pstmtSelectAllQnas = conn.prepareStatement(sqlSelectAllQnas);
			
			rsSelectAllQnas = pstmtSelectAllQnas.executeQuery();
			
			// qna클래스 타입으로 선언한 ArrayListQna 리스트에 담기위한 객체생성
			ArrayListQna = new ArrayList<Qna>();
			while(rsSelectAllQnas.next()) {
				qna = new Qna();
				qna.setQna_no(rsSelectAllQnas.getInt("qna_no"));
				qna.setMember_no(rsSelectAllQnas.getInt("member_no"));
				qna.setQna_title(rsSelectAllQnas.getString("qna_title"));
				qna.setQna_content(rsSelectAllQnas.getString("qna_content"));
				qna.setQna_date(rsSelectAllQnas.getString("qna_date"));
				ArrayListQna.add(qna);
			}
			
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, pstmtSelectAllQnas");
			e.printStackTrace();
		} finally {
			if (rsSelectAllQnas != null) {
				try {
					rsSelectAllQnas.close();
				} catch(SQLException sqlException) {
					System.out.println("rsSelectAllQnas 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
			
			if (pstmtSelectAllQnas != null) {
				try {
					pstmtSelectAllQnas.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtSelectAllQnas 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
		
		return ArrayListQna;
		
	}
	
	public Qna selectUserQnaList(Connection conn, int qnaNo) {
		PreparedStatement pstmtSelectUserQnaList = null;
		ResultSet rsSelectUserQnaList = null;
		Qna qna = new Qna();
		String sqlSelectUserQnaList = "SELECT qna_content, qna_title, qna_date, member_no FROM qna where qna_no=?";
		
		try {
			pstmtSelectUserQnaList = conn.prepareStatement(sqlSelectUserQnaList);
		
			pstmtSelectUserQnaList.setInt(1, qnaNo);
			
			rsSelectUserQnaList = pstmtSelectUserQnaList.executeQuery();
			
			if(rsSelectUserQnaList.next()) {
				qna.setMember_no(rsSelectUserQnaList.getInt("member_no"));
				qna.setQna_title(rsSelectUserQnaList.getString("qna_title"));
				qna.setQna_date(rsSelectUserQnaList.getString("qna_date"));
				qna.setQna_content(rsSelectUserQnaList.getString("qna_content"));
				qna.setQna_no(qnaNo);
			}
		} catch (SQLException e) {
			System.out.println("pstmtSelectAllQnas 객체 종료 중 예외 발생");
			e.printStackTrace();
		}
		return qna;
		
	}
	
	public ArrayList<QnaForAdmin> selectAllQnasForAdmin(Connection conn) {
		
		PreparedStatement pstmtSelectAllQnas = null;
		ResultSet rsSelectAllQnas = null;
		
		String sqlSelectAllQnasForAdmin = "SELECT qna.qna_no, member.member_id, qna.qna_title, qna.qna_content, qna.qna_date FROM qna " + 
											"JOIN member ON qna.member_no = member.member_no;";
		ArrayList<QnaForAdmin> ArrayListQna = null;
		QnaForAdmin qnaForAdmin = null;
		
		try {
			pstmtSelectAllQnas = conn.prepareStatement(sqlSelectAllQnasForAdmin);
			
			rsSelectAllQnas = pstmtSelectAllQnas.executeQuery();
			
			// qna클래스 타입으로 선언한 ArrayListQna 리스트에 담기위한 객체생성
			ArrayListQna = new ArrayList<QnaForAdmin>();
			while(rsSelectAllQnas.next()) {
				qnaForAdmin = new QnaForAdmin();
				qnaForAdmin.setQna_no(rsSelectAllQnas.getInt("qna_no"));
				qnaForAdmin.setMember_id(rsSelectAllQnas.getString("member_id"));
				qnaForAdmin.setQna_title(rsSelectAllQnas.getString("qna_title"));
				qnaForAdmin.setQna_content(rsSelectAllQnas.getString("qna_content"));
				qnaForAdmin.setQna_date(rsSelectAllQnas.getString("qna_date"));
				ArrayListQna.add(qnaForAdmin);
			}
			
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, selectAllQnasForAdmin");
			e.printStackTrace();
		} finally {
			if (rsSelectAllQnas != null) {
				try {
					rsSelectAllQnas.close();
				} catch(SQLException sqlException) {
					System.out.println("rsSelectAllQnas 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
			
			if (pstmtSelectAllQnas != null) {
				try {
					pstmtSelectAllQnas.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtSelectAllQnas 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
		
		return ArrayListQna;
		
	}
	
}

