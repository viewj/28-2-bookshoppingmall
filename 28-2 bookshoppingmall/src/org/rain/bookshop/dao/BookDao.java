//2018-07-18 서연문
package org.rain.bookshop.dao;

//필요한 패키지의 class를 import
import java.util.ArrayList;
import java.sql.*;
import org.rain.bookshop.dto.*;

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
	public void deleteBook(Connection conn, int bookNo) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtDeleteBook = null;
		
		//book테이블의 DELETE쿼리문 작성
		String sqlDeleteBook = "DELETE FROM book WHERE book_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtDeleteBook = conn.prepareStatement(sqlDeleteBook);
			
			//DELETE를 위한 value값에 set해준다
			pstmtDeleteBook.setInt(1, bookNo);
			
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
	public BookInformation selectForUpdateBook(Connection conn, int bookNo) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtSelectForUpdateBook = null;
		ResultSet rsSelectForUpdateBook = null;
		//Book클래스 데이터 타입으로 book객체 생성
		BookInformation bookUpdate = null;
		
		//book테이블에 들어있는 bookcode_no값 과 publisher_no값을 해당테이블의 name값으로 가져오기 위한 JOIN문입니다.
		String sqlSelectForUpdateBook = "SELECT book.book_no ,book.book_name ,book.book_author ,book.book_price ,book.book_point ,book.book_amount ,book.book_out ,bookcode.bookcode_name ,publisher.publisher_name "
					+"FROM book left JOIN bookcode ON book.bookcode_no = bookcode.bookcode_no left JOIN publisher ON book.publisher_no = publisher.publisher_no WHERE book.book_no=?";
		
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
				bookUpdate = new BookInformation();
				//book객체에 대입된 주소값을 따라가 SELECT한 정보를 set해줌
				bookUpdate.setBookNo(rsSelectForUpdateBook.getInt("book_no"));
				bookUpdate.setBookPrice(rsSelectForUpdateBook.getInt("book_price"));
				bookUpdate.setBookPoint(rsSelectForUpdateBook.getInt("book_point"));
				bookUpdate.setBookAmount(rsSelectForUpdateBook.getInt("book_amount"));
				bookUpdate.setBookCodeName(rsSelectForUpdateBook.getString("bookcode_name"));
				bookUpdate.setPublisherName(rsSelectForUpdateBook.getString("publisher_name"));
				bookUpdate.setBookName(rsSelectForUpdateBook.getString("book_name"));
				bookUpdate.setBookAuthor(rsSelectForUpdateBook.getString("book_author"));
				bookUpdate.setBookOut(rsSelectForUpdateBook.getString("book_out"));
				
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
		return bookUpdate;	
	}
	
	
	//책의 정보를 수정해주기 위한 메서드
	public void updateBook(Connection conn, Book book) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtUpdateBook = null;	
		
		//book테이블의 UPDATE쿼리문 작성
		String sqlUpdateBook = "UPDATE book SET bookcode_no=? ,publisher_no=? ,book_name=? ,book_author=? ,book_price=? ,book_point=? ,book_amount=? ,book_out=? WHERE book_no=?";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtUpdateBook = conn.prepareStatement(sqlUpdateBook);
			
			//각 ?값에 순서대로 대입
			pstmtUpdateBook.setInt(1, book.getBookCodeNo());
			pstmtUpdateBook.setInt(2, book.getPublisherNo());
			pstmtUpdateBook.setString(3, book.getBookName());
			pstmtUpdateBook.setString(4, book.getBookAuthor());
			pstmtUpdateBook.setInt(5, book.getBookPrice());
			pstmtUpdateBook.setInt(6, book.getBookPoint());
			pstmtUpdateBook.setInt(7, book.getBookAmount());
			pstmtUpdateBook.setString(8, book.getBookOut());
			pstmtUpdateBook.setInt(9, book.getBookNo());
			
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
	public ArrayList<BookInformation> selectAllBooks(Connection conn) {
		//객체 선언하고 초기값 null 설정
		PreparedStatement pstmtSelectAllBooks = null;
		ResultSet rsSelectAllBook = null;
		
		//리스트를 리턴해줄 배열을 객체 선언
		ArrayList<BookInformation> arrayListBookAndPublisher = new ArrayList<BookInformation>(); 
		
		//book테이블의 SELECT쿼리문 작성
		String sqlSelectBook = "SELECT book.book_no ,book.book_name ,book.book_author ,book.book_price ,book.book_point ,book.book_amount ,book.book_out ,left(book.book_date ,10) as book_date ,bookcode.bookcode_name ,publisher.publisher_name "
					+"FROM book left JOIN bookcode ON book.bookcode_no = bookcode.bookcode_no left JOIN publisher ON book.publisher_no = publisher.publisher_no ORDER BY book_no DESC";
		
		try {
			//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
			pstmtSelectAllBooks = conn.prepareStatement(sqlSelectBook);
			
			//쿼리 실행 및 rs객체에 리턴값 대입
			rsSelectAllBook = pstmtSelectAllBooks.executeQuery();
			
			//리턴값이 있다면
			while(rsSelectAllBook.next()) {
				//생성자를 통해 Book클래스를 생성하고  주소값을 Book클래스 데이터 타입으로 생성된 book객체에 대입
				BookInformation bookInformation = new BookInformation();
				//book객체에 대입된 주소값을 찾아가 set
				bookInformation.setBookNo(rsSelectAllBook.getInt("book_no"));
				bookInformation.setBookPrice(rsSelectAllBook.getInt("book_price"));
				bookInformation.setBookPoint(rsSelectAllBook.getInt("book_point"));
				bookInformation.setBookAmount(rsSelectAllBook.getInt("book_amount"));
				bookInformation.setBookOut(rsSelectAllBook.getString("book_out"));
				bookInformation.setBookName(rsSelectAllBook.getString("book_name"));
				bookInformation.setBookAuthor(rsSelectAllBook.getString("book_author"));
				bookInformation.setBookDate(rsSelectAllBook.getString("book_date"));
				bookInformation.setBookCodeName(rsSelectAllBook.getString("bookcode_name"));
				bookInformation.setPublisherName(rsSelectAllBook.getString("publisher_name"));
				
				//배열에 저장
				arrayListBookAndPublisher.add(bookInformation);
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
	
	//책 상세보기에 정보를 출력하기 위한 메서드입니다.
	public BookDetail selectBookDetail(Connection conn ,int bookNo) {
		
			BookDetail bookDetail = null;
			
			PreparedStatement pstmtSelectBookDetail = null;
			ResultSet rsSelectBookDetail = null;
			
			//상세정보를 얻기 위한 LEFT JOIN쿼리문
			String selectBookJoinQuery = "SELECT book.book_no , book.book_name ,book.book_author ,book.book_price ,book.book_point ,book.book_amount ,book.book_out ,bookcode.bookcode_name ,publisher.publisher_name ,publisher.publisher_website ,bookintro.bookintro_no ,bookintro.bookintro_content ,bookintro.bookintro_writer "
						+"FROM book LEFT JOIN bookcode ON book.bookcode_no = bookcode.bookcode_no LEFT JOIN publisher ON book.publisher_no = publisher.publisher_no LEFT JOIN bookintro ON book.book_no = bookintro.book_no WHERE book.book_no=?";
			try {
				//conn클래스를 통해 메서드를 작성한 쿼리문으로 호출하고 객체에 대입
				pstmtSelectBookDetail = conn.prepareStatement(selectBookJoinQuery);
				
				//쿼리문에 있는 ?값 설정입니다.
				pstmtSelectBookDetail.setInt(1, bookNo);
				
				rsSelectBookDetail = pstmtSelectBookDetail.executeQuery();
				
				if(rsSelectBookDetail.next()) {
					bookDetail = new BookDetail();
					
					bookDetail.setBookNo(rsSelectBookDetail.getInt("book_no"));
					bookDetail.setBookPrice(rsSelectBookDetail.getInt("book_price"));
					bookDetail.setBookPoint(rsSelectBookDetail.getInt("book_point"));
					bookDetail.setBookAmount(rsSelectBookDetail.getInt("book_amount"));	
					bookDetail.setBookintroNo(rsSelectBookDetail.getInt("bookintro_no"));
					bookDetail.setBookName(rsSelectBookDetail.getString("book_Name"));
					bookDetail.setBookOut(rsSelectBookDetail.getString("book_out"));
					bookDetail.setBookAuthor(rsSelectBookDetail.getString("book_author"));
					bookDetail.setBookcodeName(rsSelectBookDetail.getString("bookcode_name"));
					bookDetail.setPublisherName(rsSelectBookDetail.getString("publisher_name"));
					bookDetail.setPublisherWebsite(rsSelectBookDetail.getString("publisher_website"));
					bookDetail.setBookintroContent(rsSelectBookDetail.getString("bookintro_content"));
					bookDetail.setBookintroWriter(rsSelectBookDetail.getString("bookintro_writer"));
				}
				
			}catch(SQLException e) {
				System.out.println("DB와 관련된 예외가 발생하였습니다, selectBookDetail main");
				e.printStackTrace();
				
			}finally {
				
				if(rsSelectBookDetail != null) {
					
					try {
						rsSelectBookDetail.close();
					} catch(SQLException e) {
						System.out.println("rsSelectBookDetail close");
						e.printStackTrace();
					}
				}
				
				if(pstmtSelectBookDetail != null) {
					try {
						pstmtSelectBookDetail.close();
					} catch(SQLException e) {
						System.out.println("pstmtSelectAllBooks close");
						e.printStackTrace();
					}
				}
			}	
		return bookDetail;
	}
	
	public String selectBookName(Connection conn, int bookNo) {
		PreparedStatement pstmtSelectBookName = null;
		ResultSet rsSelectBookName = null;
		String bookName = null;
		String sqlSelectBookName = "SELECT book_name FROM book WHERE book_no=?";
		try {
			pstmtSelectBookName = conn.prepareStatement(sqlSelectBookName);
		
			pstmtSelectBookName.setInt(1, bookNo);
			rsSelectBookName = pstmtSelectBookName.executeQuery();
			
			if(rsSelectBookName.next()) {
				bookName = rsSelectBookName.getString("book_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookName;
		
	}
}










