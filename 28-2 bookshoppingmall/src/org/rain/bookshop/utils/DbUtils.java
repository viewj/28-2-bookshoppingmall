package org.rain.bookshop.utils;
import java.sql.*;

public class DbUtils {
	
	// db 연결후 conn 객체 리턴
	public static Connection connectDB() {
		Connection conn = null;
		System.out.println("");
		System.out.println("location : DButil/connectDB");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String jdbcDriver = "jdbc:mysql://localhost:3306/bookshop?useUnicode=true&characterEncoding=euckr";
			String dbUser = "root";
			String dbPw = "java0000";
			
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPw);
			 
		} catch (ClassNotFoundException e) {
			System.out.println("DB Driver Class를 찾지 못하였습니다. 커넥터를 확인해주세요!");
		} catch (SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다");
			e.printStackTrace();
		} finally {
			System.out.println("");
			System.out.println("End of DButil/connectDB");
		}
		return conn;			 
	}
	
	// conn 객체 종료
	public static void closeConn(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// pstmt 객체 종료
	public static void closePstmt(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// rs 객체 종료
	public static void closeRs(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// conn, pstmt, rs 객체 종료
	public static void closeObject(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// conn, pstmt 객체 종료
	public static void closeObject(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// rs, pstmt 객체 종료
	public static void closeObject(ResultSet rs, PreparedStatement pstmt) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
