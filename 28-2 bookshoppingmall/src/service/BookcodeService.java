package service;

import java.sql.*;
import service.*;


public class BookcodeService {
	public void addBookcode(Bookcode bookcode) {
		Connection conn = DButil.connectDB();
		BookcodeDao bookcodeDao = new BookcodeDao();
		
		bookcodeDao.insertBookcode(conn, bookcode);
		
		System.out.println("확인");
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
