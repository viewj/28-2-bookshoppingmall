//2018-07-23 서연문
package service;

import java.sql.*;
import service.*;

public class MemberinterDao {

	public void insertMemberinter(Connection conn, Memberinter memberinter) {
		PreparedStatement pstmtInsertMemberinter = null;
		
		//book테이블의 INSERT쿼리문 작성 
		String sqlInsertMemberinter = "INSERT INTO memberinter(member_no, bookcode_no) VALUES(?, ?)";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtInsertMemberinter = conn.prepareStatement(sqlInsertMemberinter);
			
			//INSERT를 위한 value값에 순서대로 set해준다
			pstmtInsertMemberinter.setInt(1, memberinter.getMemberNo());
			pstmtInsertMemberinter.setInt(2, memberinter.getBookcodeNo());
			
			System.out.println("삽입된 memberinter 레코드의 수 : " +pstmtInsertMemberinter.executeUpdate());
			
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