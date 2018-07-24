package service;

import java.util.ArrayList;
import java.sql.*;
import service.*;

public class MemberService {
	// 회원 가입시 사용자로부터 입력받은 정보들을 member 테이블에 insert 하는 메서드로
	// 회원 정보와 관심사 두 테이블이 한번에 만들어지게 하기 위해 트랜잭션 단위로 작업하였다.
	// 매개변수는 회원가입 폼에서 입력받은 정보가 담긴 member 객체와 memberinter 객체.
	// 리턴 데이터는 없다.
	public boolean addMember(Member member, ArrayList<Memberinter> arrayListMemberinter) {
		Connection conn = null;
		boolean resultOfaddMember = false;
		System.out.println("");
		System.out.println("location : MemberService/addMember()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// 자동으로 commit 되지 않게 하여 트랜잭션 처리를 한다.
			conn.setAutoCommit(false);
			
			// memberDao 객체 생성
			MemberinterDao memberinterDao = new MemberinterDao();
			MemberDao memberDao = new MemberDao();
			
			// member 테이블에 한 개의 레코드를 추가하는 메서드 실행
			memberDao.insertMember(conn, member);
			
			// member_no을 가져오기 위함 (밑의 관심 장르 추가하는 부분  + 회원가입 시 바로 세션정보 저장하여 로그인 처리하는 부분 에서 필요)
			memberDao.memberGetVO(conn, member);
		
			// 관심있는 장르가 없다면 insertMemberinter 메서드를 실행하지 않는 것이 좋을 것같다.
			if(arrayListMemberinter.size() != 0) {
				// memberinter 테이블에 한 개 이상의 레코드를 추가하는 메서드
				memberinterDao.insertMemberinter(conn, arrayListMemberinter, member);
			}
			// 예외 없이 여기까지 진행 됐다면 커밋을 통해 실제 DB에 반영
			conn.commit();
			
			resultOfaddMember = true;
		
		} catch (Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqlException) {	
				sqlException.printStackTrace();
			}
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of MemberService/addMember()");
		}
			return resultOfaddMember;
	}
	
	// 로그인 시도 할 때 아이디와 비밀번호가 DB와 일치하는지 비교를 하여 그 결과를 문자열로 리턴하는 메서드
	// 매개변수는 로그인 폼에서 입력받은 정보를 담은 VO
	// 리턴 데이터는 결과를 나타내는 문자열
	public String memberLoginCheck(Member member) {
		Connection conn = null;
		String resultOfLogin = null;
		
		System.out.println("");
		System.out.println("location : MemberService/memberLoginCheck()");
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			MemberDao memberDao = new MemberDao();
			
			// loginCheck 메서드를 통해 로그인 체크
			resultOfLogin = memberDao.loginCheck(conn, member);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of MemberService/memberLoginCheck()");
		}
		return resultOfLogin;
	}
	
	// 입력받은 로그인 정보가 DB와 일치한다면 실행하는 메서드로 세션정보로 사용할 member_no 값을 받아오기 위함
	// 매개변수는 로그인 폼에서 입력받은 정보가 담긴 VO.
	// 리턴데이터는 없다.
	public void memberGetVO(Member member) {
		Connection conn = null;
		
		System.out.println("");
		System.out.println("location : MemberService/memberGetVO()");
		
		try {
			// connectDB 메서드를 통해 DB 연결
			conn = DButil.connectDB();
			
			// memberDao 객체 생성
			MemberDao memberDao = new MemberDao();
			
			// memberGetVO를 통해 세션에 필요한 정보들만 조회 후 VO의 데이터 영역에 대입.
			memberDao.memberGetVO(conn, member);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn 객체 종료 중 예외");
					e.printStackTrace();
				}
			}
			System.out.println("End of MemberService/memberGetVO()");
		}
	}
	public String selectMemberNameForQnaList(int memberNo) {
		Connection conn = DButil.connectDB();
		
		MemberDao memberDao = new MemberDao();
		String memberName = memberDao.selectMemberNameForQnaList(conn, memberNo);
		
		return memberName;
	}
}
