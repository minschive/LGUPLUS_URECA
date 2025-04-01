package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Statement 사용
public class Test1 {

	public static void main(String[] args) throws Exception {
		// MySQL 에 접근하기 위해 필요한 3가지
		String url = "jdbc:mysql://localhost:3306/madang";
		String user = "root";
		String pwd = "minschive";
		
		// Driver 테스트 (DriverManager 에 자동 등록)
//		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		// JDBC 드라이버 객체를 DriverManager 에 등록 단계 필요 <= 자동으로 처리
		
		// Connection
		Connection con = DriverManager.getConnection(url, user, pwd);
		
		// Statement (sql 전달 객체)
		Statement stmt = con.createStatement();
		
		// ResultSet(select query 의 결과)
		ResultSet rs = null;
		
		// insert
		// dup 확인
//		{
//			String insertSql = "insert into customer values ( 6, '손흥민', '영국 토트넘', '010-6666-6666' ); ";
//			int ret = stmt.executeUpdate(insertSql);
//			System.out.println(ret);
//		}
		
		// update
//		{
//			String updateSql = "update customer set address = '대한민국 서울' where custid = 6; ";
//			int ret = stmt.executeUpdate(updateSql);
//			System.out.println(ret);
//		}
		
		// delete
//		{
//			String deleteSql = "delete from customer where custid = 6; ";
//			int ret = stmt.executeUpdate(deleteSql);
//			System.out.println(ret);
//		}
		
		// select list (복수 건)
		{ 
//			String selectSql = "select * from customer; ";
//			String selectSql = "select custid, name, address, phone from customer; ";
			String selectSql = "select custid, name cust_name, address cust_address, phone cust_phone from customer; ";
			rs = stmt.executeQuery(selectSql);
			while(rs.next()) { // row 이동
				// 해당 row 에서 필요한 column 획득 <- rs.getInt(), rs.getString() (괄호 안에 인덱스도 사용가능하지만, 일반적으로 컬럼명을 사용)
//				System.out.println(rs.getInt("custid") + " | " + rs.getString("name") + " | " + rs.getString("address") + " | " + rs.getString("phone"));
				System.out.println(rs.getInt("custid") + " | " + rs.getString("cust_name") + " | " + rs.getString("cust_address") + " | " + rs.getString("cust_phone")); // alias
			}
		}
		
		// select one (단 건)
		{ 
			String selectSql = "select * from customer where custid = 4; ";
//			String selectSql = "select custid, name, address, phone from customer where custid = 4; ";
//			String selectSql = "select custid, name cust_name, address cust_address, phone cust_phone from customer where custid = 4; ";
			rs = stmt.executeQuery(selectSql);
			if(rs.next()) { // 1건에 대해 있고 없고
				// 해당 row 에서 필요한 column 획득 <- rs.getInt(), rs.getString() (괄호 안에 인덱스도 사용가능하지만, 일반적으로 컬럼명을 사용)
				System.out.println(rs.getInt("custid") + " | " + rs.getString("name") + " | " + rs.getString("address") + " | " + rs.getString("phone"));
//				System.out.println(rs.getInt("custid") + " | " + rs.getString("cust_name") + " | " + rs.getString("cust_address") + " | " + rs.getString("cust_phone")); // alias
			}
		}
		
		if(rs == null) rs.close();
		stmt.close();
		con.close();
	}

}
