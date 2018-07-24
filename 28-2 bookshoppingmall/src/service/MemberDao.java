package service;

import java.sql.*;
import java.util.ArrayList;
import service.*;

public class MemberDao {
	
	public void insertMember(Connection conn, Member member) {
		// 객체참조변수 선언
		PreparedStatement pstmtInsertMember = null;
		
		// member 테이블에 레코드를 생성하는 쿼리문 
		String sqlInsertMember = "INSERT INTO member(member_id,member_pw, member_name, member_addr, member_date) VALUES (?, ?, ?, ?, now())";
		
		try {
			pstmtInsertMember = conn.prepareStatement(sqlInsertMember);
			// 레코드에 넣을 값을 설정(?자리에 순서대로 들어감)
			pstmtInsertMember.setString(1, member.getMemberId());
			pstmtInsertMember.setString(2, member.getMemberPw());
			pstmtInsertMember.setString(3, member.getMemberName());
			pstmtInsertMember.setString(4, member.getMemberAddr());
			
			// 쿼리문이 실행될 경우 변화가 있는 레코드의 숫자를 리턴하는데 그 값을 변수에 담는다.
			int resultUpdate = pstmtInsertMember.executeUpdate();
			// 변화가 있는 레코드의 숫자 확인
			System.out.println("member 테이블에 추가된 행 갯수 : " + resultUpdate);
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, InsertMember");
			e.printStackTrace();
		} finally {
			if (pstmtInsertMember != null) {
				try {
					pstmtInsertMember.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtInsertMember 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
	
	public void deleteMember(Connection conn, Member member) {
		// 객체참조변수 선언
		PreparedStatement pstmtDeleteMember = null;
		// member테이블의 레코드를 삭제하는 쿼리문
		String sqlDeleteMember = "DELETE FROM member WHERE member_no=?";
		
		try {
			pstmtDeleteMember = conn.prepareStatement(sqlDeleteMember);
			// 쿼리문 ?자리에 받아온 memberNo값을 넣는다.
			pstmtDeleteMember.setInt(1, member.getMemberNo());
			// member테이블에서 수정된 레코드의 수를 리턴 받은 값을 변수에 담는다.
			int resultUpdate = pstmtDeleteMember.executeUpdate();
			
			System.out.println("member 테이블에 삭제된 행 갯수 : " + resultUpdate);
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, deleteMember");
			e.printStackTrace();
		} finally {
			if (pstmtDeleteMember != null) {
				try {
					pstmtDeleteMember.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtDeleteMember 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
	}
	
	public String loginCheck(Connection conn, Member member) {
		// 객체참조변수 선언
		PreparedStatement pstmtMemberLogin = null;
		ResultSet rsMemberLogin = null;
		
		// member테이블 전체 레코드에서 특정 레코드를 찾는 쿼리문
		String sqlMemberLogin = "SELECT member_pw FROM member where member_id=?";
		
		// 쿼리문에서 나온 행의 pw값을 담을 변수와 리턴해줄 값을 담을 변수 선언
		String pw = null;
		String result = null;
		
		try {
			pstmtMemberLogin = conn.prepareStatement(sqlMemberLogin);
			pstmtMemberLogin.setString(1, member.getMemberId());
			rsMemberLogin = pstmtMemberLogin.executeQuery();
			// 아이디와 비밀번호 둘다 일치하는 레코드는 하나이기 때문에 while이 아닌 if를 사용한다.
			if (rsMemberLogin.next()) {
				pw = rsMemberLogin.getString("member_pw");
				
				if(member.getMemberPw().equals(pw)) {
					result="로그인성공";
				} else {
					result="비밀번호불일치";
				}
			} else {
				result = "아이디불일치";
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, pstmtMemberLogin");
			e.printStackTrace();
		} finally {
			if (rsMemberLogin != null) {
				try {
					rsMemberLogin.close();
				} catch(SQLException sqlException) {
					System.out.println("ResultSet 객체 종료 중 예외 발생, pstmtMemberLogin");
					sqlException.printStackTrace();
				}
			}
			if (pstmtMemberLogin != null) {
				try {
					pstmtMemberLogin.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtMemberLogin 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public void memberGetVO(Connection conn, Member member) {
		System.out.println("");
		System.out.println("location : MemberDao/memberGetVO");
		
		// 객체참조변수 선언
		PreparedStatement pstmtMemberGetVO = null;
		ResultSet rsMemberGetVO = null;
		
		// member_id값을 이용하여 레코드의 일부분을 가져올 수 있는 쿼리문
		String sqlMemberGetVO = "SELECT member_no FROM member WHERE member_id=?";
		
		try {
			pstmtMemberGetVO = conn.prepareStatement(sqlMemberGetVO);
			pstmtMemberGetVO.setString(1, member.getMemberId());
			rsMemberGetVO = pstmtMemberGetVO.executeQuery();
			
			if (rsMemberGetVO.next()) {
				member.setMemberNo(rsMemberGetVO.getInt("member_no"));
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다.");
			e.printStackTrace();
		} finally {
			if (rsMemberGetVO != null) {
				try {
					rsMemberGetVO.close();
				} catch(SQLException sqlException) {
					System.out.println("ResultSet 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
			if (pstmtMemberGetVO != null) {
				try {
					pstmtMemberGetVO.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtMemberGetVO 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
			
			System.out.println("");
			System.out.println("End of MemberDao/memberGetVO");
		}
	}
	
	public ArrayList<Member> selectAllMembers(Connection conn) {
		// 리턴해줄 배열 선언
		ArrayList<Member> arrayListMember = new ArrayList<Member>();
		// 객체 참조 변수 선언
		PreparedStatement pstmtSelectAllMembers = null;
		ResultSet rsSelectAllMembers = null;
		// 전체 레코드를 나열하는 쿼리문
		String sqlSelectAllMembers = "SELECT member_no, member_id, member_name, member_date FROM member";
		
		try {
			pstmtSelectAllMembers = conn.prepareStatement(sqlSelectAllMembers);
			rsSelectAllMembers = pstmtSelectAllMembers.executeQuery();
			
			while(rsSelectAllMembers.next()) {
				// 한 레코드를 담을 멤버클래스 선언
				Member member = new Member();
				member.setMemberNo(rsSelectAllMembers.getInt("member_no"));
				member.setMemberId(rsSelectAllMembers.getString("member_id"));
				member.setMemberName(rsSelectAllMembers.getString("member_name"));
				member.setMemberDate(rsSelectAllMembers.getString("member_date"));
				// 한 레코드씩 정보를 배열에 저장
				arrayListMember.add(member);
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, selectAllMembers");
			e.printStackTrace();
		} finally {
			if (rsSelectAllMembers != null) {
				try {
					rsSelectAllMembers.close();
				} catch(SQLException sqlException) {
					System.out.println("ResultSet 객체 종료 중 예외 발생, rsSelectAllMembers");
					sqlException.printStackTrace();
				}
			}
			if (pstmtSelectAllMembers != null) {
				try {
					pstmtSelectAllMembers.close();
				} catch(SQLException sqlException) {
					System.out.println("pstmtSelectAllMembers 객체 종료 중 예외 발생");
					sqlException.printStackTrace();
				}
			}
		}
		
		return arrayListMember;
	}
	
	public Member SelectMember(Connection conn, int memberNo) {
		// 객체참조변수 선언
		PreparedStatement pstmtSelectMember = null;
		ResultSet rsSelectMember = null;
		// 리턴해줄 멤버클래스 선언
		Member returnMember = null;
		// 원하는 부분의 레코드전체를 가져오는 쿼리문
		String sqlSelectMember = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date WHERE member_no=?";
		
		try {
			pstmtSelectMember = conn.prepareStatement(sqlSelectMember);
			pstmtSelectMember.setInt(1, memberNo);
			rsSelectMember = pstmtSelectMember.executeQuery();
			
			// member_no는 primary key이기 때문에 하나밖에 존재하지않는다. 그렇기 때문에 while이 아닌 if를 사용한다.
			if(rsSelectMember.next()) {
				returnMember = new Member();
				// 쿼리문에서 나온 값을 member클래스 데이터타입으로 저장한다.
				returnMember.setMemberNo((rsSelectMember.getInt("member_no")));
				returnMember.setMemberId((rsSelectMember.getString("member_id")));
				returnMember.setMemberPw((rsSelectMember.getString("member_pw")));
				returnMember.setMemberName((rsSelectMember.getString("member_name")));
				returnMember.setMemberAddr((rsSelectMember.getString("member_addr")));
				returnMember.setMemberPoint((rsSelectMember.getString("member_point")));
				returnMember.setMemberDate((rsSelectMember.getString("member_date")));
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectMember");
			e.printStackTrace();
		}
		
		return returnMember;
		
	}
	
	public String selectMemberNameForQnaList(Connection conn, int memberNo) {
		PreparedStatement pstmtSelectMemberNameForQnaList = null;
		ResultSet rsSelectMemberNameForQnaList = null;
		String selectMemberNo = null;
		String sqlSelectMemberNameForQnaList = "SELECT member_name FROM member WHERE member_no=?";
		try {
			pstmtSelectMemberNameForQnaList = conn.prepareStatement(sqlSelectMemberNameForQnaList);
		
			pstmtSelectMemberNameForQnaList.setInt(1, memberNo);
			rsSelectMemberNameForQnaList = pstmtSelectMemberNameForQnaList.executeQuery();
		
			if(rsSelectMemberNameForQnaList.next()) {
				selectMemberNo = rsSelectMemberNameForQnaList.getString("member_name");
			}
		} catch (SQLException e) {
			System.out.println("DB에서 예외가 발생하였습니다, SelectMember");
			e.printStackTrace();
		}
		return selectMemberNo;
		
	}
}






