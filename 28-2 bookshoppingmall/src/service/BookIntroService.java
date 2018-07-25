package service;

import java.sql.*;
import java.util.*;
import service.*;

public class BookIntroService {
	public void insertBookIntro(BookIntro bookIntro) {
		Connection conn = null;
		
		try {
			conn = DButil.connectDB();
			
			BookIntroDao bookIntroDao = new BookIntroDao();
			
			bookIntroDao.insertBookIntro(conn, bookIntro);
			
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
