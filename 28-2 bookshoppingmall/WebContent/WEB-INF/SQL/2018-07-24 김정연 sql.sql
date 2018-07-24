-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 bookshop의 구조를 덤프합니다. admin
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_no` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(50) NOT NULL,
  `admin_pw` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_date` datetime NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.admin: ~0 rows (대략적)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. book
CREATE TABLE IF NOT EXISTS `book` (
  `book_no` int(10) NOT NULL AUTO_INCREMENT,
  `bookcode_no` int(10) NOT NULL,
  `publisher_no` int(10) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `book_author` varchar(50) NOT NULL,
  `book_price` int(10) NOT NULL,
  `book_point` int(10) NOT NULL,
  `book_amount` int(10) NOT NULL,
  `book_out` varchar(50) NOT NULL,
  `book_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`),
  KEY `FK_book_bookcode` (`bookcode_no`),
  KEY `FK_book_publisher` (`publisher_no`),
  CONSTRAINT `FK_book_bookcode` FOREIGN KEY (`bookcode_no`) REFERENCES `bookcode` (`bookcode_no`),
  CONSTRAINT `FK_book_publisher` FOREIGN KEY (`publisher_no`) REFERENCES `publisher` (`publisher_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.book: ~0 rows (대략적)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookcode
CREATE TABLE IF NOT EXISTS `bookcode` (
  `bookcode_no` int(10) NOT NULL AUTO_INCREMENT,
  `bookcode_name` varchar(50) NOT NULL,
  PRIMARY KEY (`bookcode_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookcode: ~3 rows (대략적)
/*!40000 ALTER TABLE `bookcode` DISABLE KEYS */;
INSERT INTO `bookcode` (`bookcode_no`, `bookcode_name`) VALUES
	(1, '소설'),
	(2, '시'),
	(3, '자서전');
/*!40000 ALTER TABLE `bookcode` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookintro
CREATE TABLE IF NOT EXISTS `bookintro` (
  `bookintro_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `bookintro_content` text NOT NULL,
  `bookintro_writer` varchar(50) NOT NULL,
  PRIMARY KEY (`bookintro_no`),
  KEY `FK__book` (`book_no`),
  CONSTRAINT `FK__book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookintro: ~0 rows (대략적)
/*!40000 ALTER TABLE `bookintro` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookintro` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookreview
CREATE TABLE IF NOT EXISTS `bookreview` (
  `bookreview_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `bookreview_content` text NOT NULL,
  PRIMARY KEY (`bookreview_no`),
  KEY `FK_bookreview_book` (`book_no`),
  KEY `FK_bookreview_member` (`member_no`),
  CONSTRAINT `FK_bookreview_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_bookreview_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookreview: ~0 rows (대략적)
/*!40000 ALTER TABLE `bookreview` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookreview` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. member
CREATE TABLE IF NOT EXISTS `member` (
  `member_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `member_pw` varchar(50) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `member_addr` varchar(50) NOT NULL,
  `member_point` int(11) NOT NULL DEFAULT '0',
  `member_date` datetime NOT NULL,
  PRIMARY KEY (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.member: ~2 rows (대략적)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_no`, `member_id`, `member_pw`, `member_name`, `member_addr`, `member_point`, `member_date`) VALUES
	(1, 'jms0611', 'whalstjd4674', '조민성', '군산시 해망동', 0, '2018-07-23 16:03:26'),
	(2, 'rok762', 'qwer1234*', '마룬5', '보스턴', 0, '2018-07-24 09:14:42');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. memberinterest
CREATE TABLE IF NOT EXISTS `memberinterest` (
  `memberint_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_no` int(10) NOT NULL,
  `bookcode_no` int(10) NOT NULL,
  PRIMARY KEY (`memberint_no`),
  KEY `FK__member` (`member_no`),
  KEY `FK_memberinterest_bookcode` (`bookcode_no`),
  CONSTRAINT `FK_memberinterest_bookcode` FOREIGN KEY (`bookcode_no`) REFERENCES `bookcode` (`bookcode_no`),
  CONSTRAINT `FK__member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.memberinterest: ~0 rows (대략적)
/*!40000 ALTER TABLE `memberinterest` DISABLE KEYS */;
/*!40000 ALTER TABLE `memberinterest` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orders_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `orders_price` int(10) NOT NULL,
  `orders_amount` int(10) NOT NULL,
  `orders_date` datetime NOT NULL,
  `orders_addr` varchar(50) NOT NULL,
  `orders_static` varchar(50) NOT NULL,
  PRIMARY KEY (`orders_no`),
  KEY `FK_others_book` (`book_no`),
  KEY `FK_others_member` (`member_no`),
  CONSTRAINT `FK_others_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_others_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.orders: ~0 rows (대략적)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. publisher
CREATE TABLE IF NOT EXISTS `publisher` (
  `publisher_no` int(10) NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(50) NOT NULL,
  `publisher_website` varchar(50) NOT NULL,
  PRIMARY KEY (`publisher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.publisher: ~1 rows (대략적)
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` (`publisher_no`, `publisher_name`, `publisher_website`) VALUES
	(1, '한빛출판사', 'www.hanbit.com');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. qna
CREATE TABLE IF NOT EXISTS `qna` (
  `qna_no` int(10) NOT NULL AUTO_INCREMENT,
  `qna_content` text,
  `member_no` int(10) DEFAULT NULL,
  `qna_title` varchar(50) DEFAULT NULL,
  `qna_date` datetime DEFAULT NULL,
  PRIMARY KEY (`qna_no`),
  KEY `FK_q&a_member` (`member_no`),
  CONSTRAINT `FK_q&a_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.qna: ~0 rows (대략적)
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. qna_comment
CREATE TABLE IF NOT EXISTS `qna_comment` (
  `qna_comment_no` int(10) NOT NULL AUTO_INCREMENT,
  `qna_no` int(10) NOT NULL,
  `admin_no` int(10) NOT NULL,
  `qna_comment_content` text NOT NULL,
  `qna_comment_date` datetime NOT NULL,
  PRIMARY KEY (`qna_comment_no`),
  KEY `FK_q&a_connect_admin` (`admin_no`),
  KEY `FK_qna_comment_qna` (`qna_no`),
  CONSTRAINT `FK_q&a_connect_admin` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`),
  CONSTRAINT `FK_qna_comment_qna` FOREIGN KEY (`qna_no`) REFERENCES `qna` (`qna_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.qna_comment: ~0 rows (대략적)
/*!40000 ALTER TABLE `qna_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_comment` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. shoppingcart
CREATE TABLE IF NOT EXISTS `shoppingcart` (
  `shoppingcart_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `shoppingcart_amount` int(10) NOT NULL,
  `shoppingcart_price` int(10) NOT NULL,
  `shoppingcart_date` datetime NOT NULL,
  PRIMARY KEY (`shoppingcart_no`),
  KEY `FK_shoppingkart_book` (`book_no`),
  KEY `FK_shoppingkart_member` (`member_no`),
  CONSTRAINT `FK_shoppingkart_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_shoppingkart_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.shoppingcart: ~0 rows (대략적)
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
