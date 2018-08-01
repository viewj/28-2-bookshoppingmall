package org.rain.bookshop.service;

import java.sql.*;
import java.util.*;
import org.rain.bookshop.dto.*;
import org.rain.bookshop.utils.*;
import org.rain.bookshop.dao.*;

public class BookIntroService {
	public void insertBookIntro(BookIntro bookIntro) {
		Connection conn = null;
		
		try {
			conn = DbUtils.connectDB();
			
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
