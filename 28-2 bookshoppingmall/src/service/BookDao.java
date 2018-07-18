//2018-07-18 서연문
package service;

//필요한 패키지의 class를 import
import java.util.ArrayList;
import java.sql.*;
import service.*;

public class BookDao {
	
	//책을 판매하기 위해 책의 정보를 입력하는 메서드
	public void insertBook(Connection conn, Book book) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtInsertBook = null;
		
		//book테이블의 INSERT쿼리문 작성 
		String sqlInsertBook = "INSERT INTO book(bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW())";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtInsertBook = conn.prepareStatement(sqlInsertBook);
			
			//INSERT를 위한 value값에 순서대로 set해준다
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


	//책의 정보를 삭제하기 위한 메서드
	public void deleteBook(Connection conn, Book book) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtDeleteBook = null;
		
		//book테이블의 DELETE쿼리문 작성
		String sqlDeleteBook = "DELETE FROM book WHERE book_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtDeleteBook = conn.prepareStatement(sqlDeleteBook);
			
			//DELETE를 위한 value값에 set해준다
			pstmtDeleteBook.setInt(1, book.getBookNo());
			
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
	
	
	//책의 정보 수정을 위해 기존 정보를 SELECT하기 위한 메서드
	public Book selectForUpdateBook(Connection conn, int bookNo) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtSelectForUpdateBook = null;
		ResultSet rsSelectForUpdateBook = null;
		//Book클래스 데이터 타입으로 book객체 생성
		Book book = null;
		
		//book테이블의 SELECT쿼리문 작성
		String sqlSelectForUpdateBook = "SELECT book_no, bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date FROM book WHERE book_no=?";
		
		try { 
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtSelectForUpdateBook = conn.prepareStatement(sqlSelectForUpdateBook);
			
			//SELECT를 위한 value값에 set해준다
			pstmtSelectForUpdateBook.setInt(1, bookNo);
			
			//쿼리 실행하고 리턴값 rs객체에 대입
			rsSelectForUpdateBook = pstmtSelectForUpdateBook.executeQuery();
			
			//다음 값이 있다면
			if(rsSelectForUpdateBook.next()) {
				//생성자를 통해 Book클래스를 생성하고  주소값을 book객체에 대입
				book = new Book();
				//book객체에 대입된 주소값을 따라가 SELECT한 정보를 set해줌
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
	
	
	//책의 정보를 수정해주기 위한 메서드
	public void updateBook(Connection conn, Book book) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtUpdateBook = null;	
		
		//book테이블의 UPDATE쿼리문 작성
		String sqlUpdateBook = "UPDATE book	SET bookcode_no=? book_name=? book_author=? book_price=? book_point=? book_amount=? book_out=? WHERE book_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtUpdateBook = conn.prepareStatement(sqlUpdateBook);
			
			//각 ?값에 순서대로 대입
			pstmtUpdateBook.setInt(1, book.getBookCodeNo());
			pstmtUpdateBook.setString(2, book.getBookName());
			pstmtUpdateBook.setString(3, book.getBookAuthor());
			pstmtUpdateBook.setInt(4, book.getBookPrice());
			pstmtUpdateBook.setInt(5, book.getBookPoint());
			pstmtUpdateBook.setInt(6, book.getBookAmount());
			pstmtUpdateBook.setString(7, book.getBookOut());
			pstmtUpdateBook.setInt(8, book.getBookNo());
			
			//쿼리 실행
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
	
	
	//책의 리스트를 출력하기 위한 SELECTE 메서드
	public ArrayList<BookAndPublisher> selectAllBooks(Connection conn, int bookNo) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtSelectAllBooks = null;
		ResultSet rsSelectAllBook = null;
		
		//리스트를 리턴해줄 배열을 객체 선언
		ArrayList<BookAndPublisher> arrayListBookAndPublisher = new ArrayList<BookAndPublisher>(); 
		
		//book테이블의 SELECT쿼리문 작성
		String sqlSelectBook = "SELECT book_no, publisher_name, book_name, book_author, book_price, book_point, book_amount, book_out, book_date FROM book b INNER JOIN publisher p on b.publisher_no = p.publisher_no";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtSelectAllBooks = conn.prepareStatement(sqlSelectBook);
			
			//쿼리 실행 및 rs객체에 리턴값 대입
			rsSelectAllBook = pstmtSelectAllBooks.executeQuery();
			
			//리턴값이 있다면
			while(rsSelectAllBook.next()) {
				//생성자를 통해 Book클래스를 생성하고  주소값을 Book클래스 데이터 타입으로 생성된 book객체에 대입
				Book book = new Book();
				//book객체에 대입된 주소값을 찾아가 set
				book.setBookNo(rsSelectAllBook.getInt("book_no"));
				book.setBookName(rsSelectAllBook.getString("book_name"));
				book.setBookAuthor(rsSelectAllBook.getString("book_author"));
				book.setBookPrice(rsSelectAllBook.getInt("book_price"));
				book.setBookPoint(rsSelectAllBook.getInt("book_point"));
				book.setBookAmount(rsSelectAllBook.getInt("book_amount"));
				book.setBookOut(rsSelectAllBook.getString("book_out"));
				book.setBookDate(rsSelectAllBook.getString("book_date"));
				
				//생성자를 통해 Publisher클래스를 생성하고 주소값을 Publisher클래스 데이터 타입으로 생성된 publisher객체에 대입
				Publisher publisher = new Publisher();			
				//publisher객체에 대입된 주소값을 찾아가 set
				publisher.setPublisherName(rsSelectAllBook.getString("publisher_name"));
				
				//위와 마찬가지로 BookAndPublisher클래스 객체 생성 대입
				BookAndPublisher bookAndPublisher = new BookAndPublisher();
				//대입된 주소값을 찾아가 set
				bookAndPublisher.setBook(book);
				bookAndPublisher.setPublisher(publisher);
				
				//배열에 저장
				arrayListBookAndPublisher.add(bookAndPublisher);
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