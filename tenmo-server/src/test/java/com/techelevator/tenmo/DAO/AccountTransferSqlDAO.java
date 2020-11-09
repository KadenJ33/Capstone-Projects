package com.techelevator.tenmo.DAO;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.tenmo.dao.UserSqlDAO;

@SpringBootTest
public class AccountTransferSqlDAO {
	
	private static SingleConnectionDataSource dataSource;
	private AccountTransferSqlDAO dao;
	private UserSqlDAO userDao;
	private JdbcTemplate jdbc;
	
	@BeforeClass
	public static void setUpDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String deleteTables = "TRUNCATE TABLE accounts, transfers, users";
		jdbc.update(deleteTables);
		
		String testUser = "INSERT INTO users(user_id, username, password_hash) VALUES(?, ?, ?)";
		jdbc.update(testUser, 1, "testUser1", "password");
		jdbc.update(testUser, 2, "testUser2", "password");
		
		String testAccount = "INSERT INTO accounts(account_id, user_id, balance) VALUES(?, ?, ?)";
		jdbc.update(testAccount, 1, 1, 1000);
		jdbc.update(testAccount, 2, 2, 1000);
		
		String testTransfer = "INSERT INTO transfers(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount VALUES(DEFAULT, ?, ?, ?, ?, ?)";
		jdbc.update(testTransfer, 2, 2, 1, 2, 100);
		jdbc.update(testTransfer, 2, 2, 2, 1, 100);
		jdbc.update(testTransfer, 2, 2, 3, 1, 100);
		jdbc.update(testTransfer, 2, 2, 1, 2, 100);
		
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public BigDecimal getBalance_test() {
		
		return null;
	}
	
	@Test
	public void transferMoney() {
		
	}
	
	@Test
	public void transferHistory() {
		
	}
	
	@Test
	public void getTransferHistory() {
		
	}
	
}
