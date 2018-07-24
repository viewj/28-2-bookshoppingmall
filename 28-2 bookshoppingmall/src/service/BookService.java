//2018.07.23 28기 전재현
package service;

import service.*;
import java.sql.*;
import java.util.*;

public class BookService {
	
	//책 등록화면에서 입력한 값을 처리하는 메서드입니다.
	public boolean addBook(Book book) {
		
		Connection conn = null;
		boolean resultOfAddBook = false;
		
		try {
			// Dbutil클래스를 통해 드라이브 로딩및 연결 
			conn = DButil.connectDB();
			
			// 자동으로 commit이 되지 안도록 하기 위해 트랜잭션 처리 실행 합니다.
			conn.setAutoCommit(false);
			
			// 쿼리실행을 위해 Dao클래스 타입의 참조변수 생성
			BookDao bookDao = new BookDao();
			
			//Dao클래스에 있는 메서드 실행.
			bookDao.insertBook(conn ,book);
			
			//예외 처리가 안날시 commit을 통해 DB실제 방영
			conn.commit();
			
			resultOfAddBook = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
			
		}finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resultOfAddBook;
	}
	
	//수정 하기 위해 등록 되어있는 책의 정보값을 가져오기위한 메서드입니다. 
	public BookInformation updateBookSelect(int bookNo) {
		
		Connection conn = null;
		BookInformation bookUpdate = new BookInformation();
		
		// DButil클래스를 통해 드라이브 로딩및 연결 
		conn = DButil.connectDB();
		
		// 쿼리실행을 위해 Dao클래스 타입의 참조변수 생성
		BookDao bookDao = new BookDao();
		
		//Dao클래스에 있는 메서드 실행 후 리턴값을 변수에 대입 했습니다.
		bookUpdate = bookDao.selectForUpdateBook(conn ,bookNo);
			
		return bookUpdate;
	}
	
	//화면에서 수정한 값을 가져와서 DB에 들어있는 값을 변경 메서드입니다.
	public boolean updateBook(Book book) {
		
		Connection conn = null;
		boolean resultOfAddBook = false;
		
		try {
			// Dbutil클래스를 통해 드라이브 로딩및 연결 
			conn = DButil.connectDB();
			
			// 자동으로 commit이 되지 안도록 하기 위해 트랜잭션 처리 실행 합니다.
			conn.setAutoCommit(false);
			
			// 쿼리실행을 위해 Dao클래스 타입의 참조변수 생성
			BookDao bookDao = new BookDao();
			
			//Dao클래스에 있는 메서드 실행.
			bookDao.updateBook(conn ,book);
			
			//예외 처리가 안날시 commit을 통해 DB실제 방영
			conn.commit();
			
			resultOfAddBook = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
			
		}finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resultOfAddBook;
	}
	
	//DB에 저장 되어있는 값을 삭제처리 하는 메서드입니다.
	public boolean deleteBook(int bookNo) {
		
		Connection conn = null;
		boolean resultOfAddBook = false;
		
		try {
			// Dbutil클래스를 통해 드라이브 로딩및 연결 
			conn = DButil.connectDB();
			
			// 자동으로 commit이 되지 안도록 하기 위해 트랜잭션 처리 실행 합니다.
			conn.setAutoCommit(false);
			
			// 쿼리실행을 위해 Dao클래스 타입의 참조변수 생성
			BookDao bookDao = new BookDao();
			
			//Dao클래스에 있는 메서드 실행.
			bookDao.deleteBook(conn ,bookNo);
			
			//예외 처리가 안날시 commit을 통해 DB실제 방영
			conn.commit();
			
			resultOfAddBook = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
			
		}finally {
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return resultOfAddBook;
	}
	
	//책의 정보를 얻기 위한 메서드입니다.
	public ArrayList<BookInformation> selectBook() {
		
		Connection conn = null;
		ArrayList<BookInformation> booktotalList = new ArrayList<BookInformation>();
		
		// DButil클래스를 통해 드라이브 로딩및 연결 
		conn = DButil.connectDB();
		
		// 쿼리실행을 위해 Dao클래스 타입의 참조변수 생성
		BookDao bookDao = new BookDao();
		
		//Dao클래스에 있는 메서드 실행 후 리턴값을 변수에 대입 했습니다.
		booktotalList = bookDao.selectAllBooks(conn);
			
		return booktotalList;
	}
}
