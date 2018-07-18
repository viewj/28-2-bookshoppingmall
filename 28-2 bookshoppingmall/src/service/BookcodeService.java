package service;

import java.sql.*;
import service.*;


public class BookcodeService {
	public void addBookcode(Bookcode bookcode) {
		Connection conn = null;
		try {
			conn = DButil.connectDB();
			BookcodeDao bookcodeDao = new BookcodeDao();
			bookcodeDao.insertBookcode(conn, bookcode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
