package service;

import java.sql.*;
import service.*;

public class BookcodeDao {
	public void insertBookcode(Connection conn, Bookcode bookcode) {
		PreparedStatement pstmtInsertBookcode = null;
		
		String sqlInsertBookcode = "INSERT INTO bookcode VALUES(?)";
		
		try {
			pstmtInsertBookcode = conn.prepareStatement(sqlInsertBookcode);
			
			pstmtInsertBookcode.setString(1, bookcode.getBookcodeName());
			
			
			System.out.println("삽입된 bookcode 레코드의 수 : " + pstmtInsertBookcode.executeUpdate());
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, connectDB");
			e.printStackTrace();
		}
	} 
}
