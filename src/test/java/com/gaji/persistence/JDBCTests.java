package com.gaji.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","book_ek","book_ek")) {
			log.info(conn);
		} catch (Exception e) {
			// TODO: handle exception
			fail(e.getMessage());
		}
	}
}