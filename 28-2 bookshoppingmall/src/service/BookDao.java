package service;

//필요한 패키지의 class를 import
import java.util.ArrayList;
import java.sql.*;
import service.*;

public class BookDao {
	
	//책을 판매하기 위해 책의 정보를 입력하는 메서드
	public void insertBook(Connection conn, Book book) {
		//쿼리문 작성에 필요한 class를 객체 선언하고 초기값 null 설정
		PreparedStatement pstmtInsertBook = null;
		
		//String타입으로 book테이블의 insert쿼리문 작성 
		String sqlInsertBook = "INSERT INTO book(bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW())";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtInsertBook = conn.prepareStatement(sqlInsertBook);
			
			//insert를 위한 value값에 순서대로 set해준다
			pstmtInsertBook.setInt(1, book.getBookCodeNo());
			pstmtInsertBook.setInt(2, book.getPublisherNo());
			pstmtInsertBook.setString(3, book.getBookName());
			pstmtInsertBook.setString(4, book.getBookAuthor());
			pstmtInsertBook.setInt(5, book.getBookPrice());
			pstmtInsertBook.setInt(6, book.getBookPoint());
			pstmtInsertBook.setInt(7, book.getBookAmount());
			pstmtInsertBook.setString(8, book.getBookOut());
			
			//쿼리 실행
			System.out.println("삽입된 book 레코드의 수 : " + pstmtInsertBook.executeUpdate());
			
		//try과정에서 예외 발생시 catch과정으로 넘어간다
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, insertBook main");
			e.printStackTrace();
		//try과정과 try catch과정 후 finally 과정으로 넘어감
		} finally {
			if(pstmtInsertBook != null) {
				try {
					pstmtInsertBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, insertBook close");
					e.printStackTrace();
				} 
			}
		}
	} 


	public void deleteBook(Connection conn, Book book) {
		PreparedStatement pstmtDeleteBook = null;
		
		String sqlDeleteBook = "DELETE FROM book WHERE book_no=? && bookcode_no=? && publisher_no=?";
		
		try {
			pstmtDeleteBook = conn.prepareStatement(sqlDeleteBook);
			
			pstmtDeleteBook.setInt(1, book.getBookNo());
			pstmtDeleteBook.setInt(2, book.getBookCodeNo());
			pstmtDeleteBook.setInt(3, book.getPublisherNo());
			
			//쿼리 실행
			System.out.println("삭제된 book 레코드의 수 : " + pstmtDeleteBook.executeUpdate());
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, deleteBook main");
			e.printStackTrace();
		} finally {
			if(pstmtDeleteBook != null) {
				try {
					pstmtDeleteBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, deleteBook close");
					e.printStackTrace();
				} 
			}
		}
	}
	
	
	public Book selectForUpdateBook(Connection conn, int bookNo) {
		PreparedStatement pstmtSelectForUpdateBook = null;
		ResultSet rsSelectForUpdateBook = null;
		Book book = null;
		
		String sqlSelectForUpdateBook = "SELECT book_no, bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date FROM book WHERE book_no=?";
		
		try { 
			pstmtSelectForUpdateBook = conn.prepareStatement(sqlSelectForUpdateBook);
			
			pstmtSelectForUpdateBook.setInt(1, bookNo);
			
			rsSelectForUpdateBook = pstmtSelectForUpdateBook.executeQuery();
			
			if(rsSelectForUpdateBook.next()) {
				
				book = new Book();
				
				book.setBookNo(rsSelectForUpdateBook.getInt("book_no"));
				book.setBookCodeNo(rsSelectForUpdateBook.getInt("bookcode_no"));
				book.setPublisherNo(rsSelectForUpdateBook.getInt("publisher_no"));
				book.setBookName(rsSelectForUpdateBook.getString("book_name"));
				book.setBookAuthor(rsSelectForUpdateBook.getString("book_author"));
				book.setBookPrice(rsSelectForUpdateBook.getInt("book_price"));
				book.setBookPoint(rsSelectForUpdateBook.getInt("book_point"));
				book.setBookAmount(rsSelectForUpdateBook.getInt("book_amount"));
				book.setBookOut(rsSelectForUpdateBook.getString("book_out"));
				book.setBookDate(rsSelectForUpdateBook.getString("book_date"));
			}
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, selectForUpdateBook main");
			e.printStackTrace();
		} finally {
			if(rsSelectForUpdateBook != null) {
				try {
					rsSelectForUpdateBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectForUpdateBook close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectForUpdateBook != null) {
				try {
					pstmtSelectForUpdateBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectForUpdateBook close");
					e.printStackTrace();
				}
			}
		}	
		return book;	
	}
	
	
	public void updateBook(Connection conn, Book book) {
		PreparedStatement pstmtUpdateBook = null;	
		
		String sqlUpdateBook = "UPDATE book	SET bookcode_no=? book_name=? book_author=? book_price=? book_point=? book_amount=? book_out=? WHERE book_no=?";
		
		try {
			pstmtUpdateBook = conn.prepareStatement(sqlUpdateBook);
			pstmtUpdateBook.setInt(1, book.getBookCodeNo());
			pstmtUpdateBook.setString(2, book.getBookName());
			pstmtUpdateBook.setString(3, book.getBookAuthor());
			pstmtUpdateBook.setInt(4, book.getBookPrice());
			pstmtUpdateBook.setInt(5, book.getBookPoint());
			pstmtUpdateBook.setInt(6, book.getBookAmount());
			pstmtUpdateBook.setString(7, book.getBookOut());
			pstmtUpdateBook.setInt(8, book.getBookNo());
			
			pstmtUpdateBook.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, updateBook main");
			e.printStackTrace();
		//try과정과 try catch과정 후 finally 과정으로 넘어감
		} finally {
			if(pstmtUpdateBook != null) {
				try {
					pstmtUpdateBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtUpdateBook close");
					e.printStackTrace();
				} 
			}
		}
	}
	
	
	public ArrayList<BookAndPublisher> selectAllBooks(Connection conn, int bookNo) {
		PreparedStatement pstmtSelectAllBooks = null;
		ResultSet rsSelectAllBook = null;
		
		ArrayList<BookAndPublisher> arrayListBookAndPublisher = new ArrayList<BookAndPublisher>(); 
		
		String sqlSelectBook = "SELECT book_no, publisher_name, book_name, book_author, book_price, book_point, book_amount, book_out, book_date FROM book b INNER JOIN publisher p on b.publisher_no = p.publisher_no";
		
		try {			
			pstmtSelectAllBooks = conn.prepareStatement(sqlSelectBook);
			
			rsSelectAllBook = pstmtSelectAllBooks.executeQuery();
			
			while(rsSelectAllBook.next()) {
				Book book = new Book();				
				book.setBookNo(rsSelectAllBook.getInt("book_no"));
				book.setBookName(rsSelectAllBook.getString("book_name"));
				book.setBookAuthor(rsSelectAllBook.getString("book_author"));
				book.setBookPrice(rsSelectAllBook.getInt("book_price"));
				book.setBookPoint(rsSelectAllBook.getInt("book_point"));
				book.setBookAmount(rsSelectAllBook.getInt("book_amount"));
				book.setBookOut(rsSelectAllBook.getString("book_out"));
				book.setBookDate(rsSelectAllBook.getString("book_date"));
				
				Publisher publisher = new Publisher();			
				publisher.setPublisherName(publisher.getString("publisher_name"));
				
				BookAndPublisher bookAndPublisher = new BookAndPublisher;
				BookAndPublisher.setBook();
				BookAndPublisher.setPublisher();
				
				arrayListBookAndPublisher.add(BookAndPublisher);
			}	
		} catch(SQLException e) {
			System.out.println("DB와 관련된 예외가 발생하였습니다, selectAllBooks main");
			e.printStackTrace();
		} finally {
			if(rsSelectAllBook != null) {
				try {
					rsSelectAllBook.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, rsSelectAllBook close");
					e.printStackTrace();
				}
			}
			if(pstmtSelectAllBooks != null) {
				try {
					pstmtSelectAllBooks.close();
				} catch(SQLException e) {
					System.out.println("DB와 관련된 예외가 발생하였습니다, pstmtSelectAllBooks close");
					e.printStackTrace();
				}
			}
		}		
		return arrayListBookAndPublisher;
	}
}