package service;
import java.sql.*;

public class DButil {

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
}
