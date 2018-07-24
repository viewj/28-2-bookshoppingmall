//2018-07-23 서연문
package service;

import java.sql.*;
import java.util.ArrayList;

import service.*;

public class MemberinterDao {
		public Memberinter selectForUpdateMemberinter(Connection conn, int memberinterno) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtSelectForUpdateMemberinter = null;
		ResultSet rsSelectForUpdateMemberinter = null;
		//Book클래스 데이터 타입으로 book객체 생성
		Memberinter memberinter = null;
		
		//book테이블의 SELECT쿼리문 작성
		String sqlSelectForUpdateMemberinter = "SELECT memberinter_no, member_no, bookcode_no FROM memberinter WHERE memberinter_no=?";
		
		try { 
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtSelectForUpdateMemberinter = conn.prepareStatement(sqlSelectForUpdateMemberinter);
			
			//SELECT를 위한 value값에 set해준다
			pstmtSelectForUpdateMemberinter.setInt(1, memberinterno);
			
			//쿼리 실행하고 리턴값 rs객체에 대입
			rsSelectForUpdateMemberinter = pstmtSelectForUpdateMemberinter.executeQuery();
			
			//다음 값이 있다면
			if(rsSelectForUpdateMemberinter.next()) {
				//생성자를 통해 Book클래스를 생성하고  주소값을 book객체에 대입
				memberinter = new Memberinter();
				//book객체에 대입된 주소값을 따라가 SELECT한 정보를 set해줌
				memberinter.setMemberinterNo(rsSelectForUpdateMemberinter.getInt("memberinter_no"));
				memberinter.setMemberNo(rsSelectForUpdateMemberinter.getInt("member_no"));
				memberinter.setBookcodeNo(rsSelectForUpdateMemberinter.getInt("bookcode_no"));
			}
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, selectForUpdateMemberinter main");
			e.printStackTrace();
		} finally {
			if(rsSelectForUpdateMemberinter != null) {
				try {
					rsSelectForUpdateMemberinter.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectForUpdateMemeberinter close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectForUpdateMemberinter != null) {
				try {
					pstmtSelectForUpdateMemberinter.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectForUpdateBook close");
					e.printStackTrace();
				}
			}
		}	
		return memberinter;	
	}
	
	
	//책의 정보를 수정해주기 위한 메서드
	public void updateMemberinter(Connection conn, Memberinter memberinter) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtUpdateMemberinter = null;	
		
		//book테이블의 UPDATE쿼리문 작성
		String sqlUpdateMemberinter = "UPDATE memberinter SET member_no=? bookcode_no=? WHERE memberinter_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtUpdateMemberinter = conn.prepareStatement(sqlUpdateMemberinter);
			
			//각 ?값에 순서대로 대입
			pstmtUpdateMemberinter.setInt(1, memberinter.getMemberNo());
			pstmtUpdateMemberinter.setInt(2, memberinter.getBookcodeNo());
			pstmtUpdateMemberinter.setInt(3, memberinter.getMemberinterNo());

			//쿼리 실행
			pstmtUpdateMemberinter.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, updateMemberinter main");
			e.printStackTrace();
		//try과정과 try catch과정 후 finally 과정으로 넘어감
		} finally {
			if(pstmtUpdateMemberinter != null) {
				try {
					pstmtUpdateMemberinter.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtUpdateMemberinter close");
					e.printStackTrace();
				} 
			}
		}
	}
	
	
	public void deleteMemberinter(Connection conn, Memberinter memberinter) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtDeleteMemberinter = null;
		
		//book테이블의 DELETE쿼리문 작성
		String sqlDeleteMemberinter = "DELETE FROM memberinter WHERE memberinter_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtDeleteMemberinter = conn.prepareStatement(sqlDeleteMemberinter);
			
			//DELETE를 위한 value값에 set해준다
			pstmtDeleteMemberinter.setInt(1, memberinter.getMemberinterNo());
			
			//쿼리 실행
			System.out.println("삭제된 MemberInter 레코드의 수 : " + pstmtDeleteMemberinter.executeUpdate());
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deleteMemberinter main");
			e.printStackTrace();
		} finally {
			if(pstmtDeleteMemberinter != null) {
				try {
					pstmtDeleteMemberinter.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, deleteMemberinter close");
					e.printStackTrace();
				} 
			}
		}
	}
	
	
	public void insertMemberinter(Connection conn, ArrayList<Memberinter> arrayListMemberinter, Member member) {
		PreparedStatement pstmtInsertMemberinter = null;
		
		//book테이블의 INSERT쿼리문 작성 
		String sqlInsertMemberinter = "INSERT INTO memberinter(member_no, bookcode_no) VALUES(?, ?)";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtInsertMemberinter = conn.prepareStatement(sqlInsertMemberinter);
			
			int memberNo = member.getMemberNo();
			
			//INSERT를 위한 value값에 순서대로 set해준다
			for(int i = 0; i < arrayListMemberinter.size(); i++) {
				pstmtInsertMemberinter.setInt(1, memberNo);
				pstmtInsertMemberinter.setInt(2, arrayListMemberinter.get(i).getBookcodeNo());
				System.out.println("삽입된 memberinter 레코드의 수 : " +pstmtInsertMemberinter.executeUpdate());
			}
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertMemberinter main");
			e.printStackTrace();
		//try과정과 try catch과정 후 finally 과정으로 넘어감
		} finally {
			if(pstmtInsertMemberinter != null) {
				try {
					pstmtInsertMemberinter.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertMemberinter close");
					e.printStackTrace();
				} 
			}
		}	
	}
}