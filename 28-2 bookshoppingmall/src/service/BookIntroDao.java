package service;
import java.sql.*;
import service.*;
import java.util.*;
public class BookIntroDao {
	
	public void insertBookIntro(Connection conn, BookIntro bookIntro) {
		PreparedStatement pstmtInsertBookIntro = null;
		String sqlInsertBookIntro = "INSERT INTO bookintro(book_no, bookintro_content, bookintro_writer) VALUES (?,?,?)";
		
		try {
			pstmtInsertBookIntro = conn.prepareStatement(sqlInsertBookIntro);
			pstmtInsertBookIntro.setInt(1, bookIntro.getBookNo());
			pstmtInsertBookIntro.setString(2, bookIntro.getBookintroContent());
			pstmtInsertBookIntro.setString(3, bookIntro.getBookintroWriter());
			
			pstmtInsertBookIntro.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
