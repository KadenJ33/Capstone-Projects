package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.TransferDTO;

@Component
public class AccountsSqlDAO implements AccountsDAO {
	private UserDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	public AccountsSqlDAO(JdbcTemplate jdbcTemplate, UserDAO dao) {
		this.dao = dao;
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public void transferMoneyTotal(AccountTransfer transfer) {
		transferMoney(transfer);
		transferHistory(transfer);
	}

	public void transferMoney(AccountTransfer transfer) {
		String sql = "UPDATE accounts SET balance = balance - ? WHERE user_id = ?";
		jdbcTemplate.update(sql, transfer.getAmount(), transfer.getAccountFrom());
		
		String sql2 = "UPDATE accounts SET balance = balance + ? WHERE user_id = ?";
		jdbcTemplate.update(sql2, transfer.getAmount(), transfer.getAccountTo());
	}
	
	public void transferHistory(AccountTransfer transfer) {
		String sql = "INSERT INTO transfers(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
				"VALUES(DEFAULT, ?, ?, ?, ?, ?) ";
//		transfer.setTransferId(getNextTransferId());
		jdbcTemplate.update(sql, transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
	}


	@Override
	public BigDecimal getBalance(int userId) {
		BigDecimal balance = null;
		String sql = "SELECT balance FROM accounts WHERE account_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
		if (result.next()) {
		balance = result.getBigDecimal(1);
		}
		return balance;
	}
	
//	@Override
//	public List<AccountTransfer> getTransferHistory(Principal principal) {
//		List<AccountTransfer> transferList = new ArrayList<>();
//		String sql = "SELECT transfer_id, account_from, account_to, amount FROM transfers " + 
//					 "JOIN users ON transfers.account_from = users.user_id WHERE users.username = ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
//		while(results.next()) {
//			AccountTransfer theTransfers = new AccountTransfer();
//			theTransfers.setTransferId(results.getInt("transfer_id"));
//			theTransfers.setAccountFrom(results.getInt("account_from"));
<<<<<<< HEAD
//			theTransfers.setAccountTo(results.getInt("account_to"));
=======
	//		theTransfers.setAccountTo(results.getInt("account_to"));
>>>>>>> 47bad9a61fe3af776671e1fbbb28589a68a1770e
//			theTransfers.setAmount(results.getBigDecimal("amount"));
//			transferList.add(theTransfers);
//		}
//		return transferList;
<<<<<<< HEAD
//	}
	
	@Override
	public List<AccountTransfer> getTransferHistory(AccountTransfer history) {
		List<AccountTransfer> transferList2 = new ArrayList<>();
		String sql = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " +
				"JOIN accounts ON accounts.account_id = transfers.account_to " +
				"JOIN users ON users.user_id = accounts.user_id " +
				"WHERE transfers.account_from = ? ";
		
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, history.getAccountFrom());
		while(results.next()) {
			AccountTransfer theHistory = mapToRowTransferHistory(results);
			transferList2.add(theHistory);
=======
//		
//	}
	
	public List<AccountTransfer> getTransferHistory(Principal principal) {
		List<AccountTransfer> transferList = new ArrayList<>();
		
		String sql = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " + 
				"JOIN accounts ON accounts.account_id = transfers.account_from " + 
				"LEFT OUTER JOIN users ON users.user_id = accounts.user_id " + 
				"WHERE transfers.account_to = ?";
		int userId = dao.findIdByUsername(principal.getName());
		//String sql = "SELECT transfer_id, account_from, account_to, amount FROM transfers " + 
		//			 "JOIN users ON transfers.account_from = users.user_id WHERE users.username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) {
			AccountTransfer theTransfers = new AccountTransfer();
			theTransfers.setTransferId(results.getInt("transfer_id"));
			theTransfers.setOtherUser(results.getString("username"));
			theTransfers.setAmount(results.getBigDecimal("amount"));
			transferList.add(theTransfers);
		}
		return transferList;
	}
	@Override
	public List<AccountTransfer> getTransferHistoryReceived(Principal principal) {
		List<AccountTransfer> transferList = new ArrayList<>();
		String sql = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " + 
				"JOIN accounts ON accounts.account_id = transfers.account_to " + 
				"LEFT OUTER JOIN users ON users.user_id = accounts.user_id " + 
				"WHERE transfers.account_from = ?";
		
		int userId = dao.findIdByUsername(principal.getName());
		//String sql = "SELECT t.transfer_id, t.account_from, t.account_to, t.amount, u.username FROM transfers t " + 
		//			 "JOIN users u ON transfers.account_to = users.user_id WHERE users.username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) {
			AccountTransfer theTransfers = new AccountTransfer();
			theTransfers.setTransferId(results.getInt("transfer_id"));
			theTransfers.setOtherUser(results.getString("username"));
			//theTransfers.setAccountFrom(results.getInt("account_from"));
			//theTransfers.setAccountTo(results.getInt("account_to"));
			theTransfers.setAmount(results.getBigDecimal("amount"));
			transferList.add(theTransfers);
>>>>>>> 47bad9a61fe3af776671e1fbbb28589a68a1770e
		}
		
		String sql2 = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " +
				"JOIN accounts ON accounts.account_id = transfers.account_from " +
				"JOIN users ON users.user_id = accounts.user_id " +
				"WHERE transfers.account_to = ? ";
		
		SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql, history.getAccountTo());
		while(results2.next()) {
			AccountTransfer theHistory = mapToRowTransferHistory(results2);
			transferList2.add(theHistory);
		}
			return transferList2;
	}
	
<<<<<<< HEAD
//	@Override
//	public List<AccountTransfer> getTransferHistory(AccountTransfer history) {
//		List<AccountTransfer> transferList = new ArrayList<>();
//		String sql = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " +
//				"JOIN accounts ON accounts.account_id = transfers.account_to " +
//				"JOIN users ON users.user_id = accounts.user_id " +
//				"WHERE transfers.account_from = ? ";
//		
//		
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, history.getAccountFrom());
//		while(results.next()) {
//			AccountTransfer theHistory = mapToRowTransferHistory(results);
//			transferList.add(theHistory);
//			}
//		return transferList;
//		}
//	
//	@Override
//	public List<AccountTransfer> getTransferHistory2(AccountTransfer history2) {
//		List<AccountTransfer> transferList2 = new ArrayList<>();
//	String sql2 = "SELECT transfers.transfer_id, users.username, transfers.amount FROM transfers " +
//			"JOIN accounts ON accounts.account_id = transfers.account_from " +
//			"JOIN users ON users.user_id = accounts.user_id " +
//			"WHERE transfers.account_to = ? ";
//	
//	SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, history2.getAccountTo());
//	while(results2.next()) {
//		AccountTransfer theHistory = mapToRowTransferHistory(results2);
//		transferList2.add(theHistory);
//	}
//		return transferList2;
//}
=======
	
>>>>>>> 47bad9a61fe3af776671e1fbbb28589a68a1770e
	
	@Override
	public List<AccountTransfer> getTransferDetails(Long userId, int transferId) {
		List<AccountTransfer> transferDetails = new ArrayList<>();
		String sql = "SELECT * FROM transfers  JOIN users ON transfers.account_from = users.user_id WHERE user_id = ? AND transfer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, transferId);
		while(results.next()) {
			AccountTransfer theDetails = mapToRowTransferDetails(results);
			transferDetails.add(theDetails);
		}
		return transferDetails;
	}
	
	
	public AccountTransfer mapToRowTransferDetails(SqlRowSet rowSet) {
		AccountTransfer theTransferHistory = new AccountTransfer();
		theTransferHistory.setTransferId(rowSet.getInt("transfer_id"));
		theTransferHistory.setTransferTypeId(rowSet.getInt("transfer_type_id"));
		theTransferHistory.setTransferStatusId(rowSet.getInt("transfer_status_id"));
		theTransferHistory.setAccountFrom(rowSet.getInt("account_from"));
		theTransferHistory.setAccountTo(rowSet.getInt("account_to"));
		theTransferHistory.setAmount(rowSet.getBigDecimal("amount"));
		return theTransferHistory;
	}
	
	public AccountTransfer mapToRowTransferHistory(SqlRowSet rowSet) {
		AccountTransfer theTransferHistory = new AccountTransfer();
		theTransferHistory.setTransferId(rowSet.getInt("transfer_id"));
		theTransferHistory.setOtherUser(rowSet.getString("username"));
		theTransferHistory.setAmount(rowSet.getBigDecimal("amount"));
		return null;
	}
	
//	private void mapToRowTransfer(SqlRowSet rowSet) {
//		TransferDTO transfer = new TransferDTO();
//		transfer.setAmount(rowSet.getBigDecimal("amount"));
//		transfer.setAccountTo(rowSet.getInt("accountTo"));
//	}
//	
//	private void mapToRowAccounts(SqlRowSet rowSet) {
//		Accounts user = new Accounts();
//		user.setBalance(rowSet.getBigDecimal("amount"));
//		user.setUserId(rowSet.getInt("userId"));
//	}
//	
//	private int getNextTransferId() {
//		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_transfer_id')");
//		if(nextIdResult.next()) {
//			return nextIdResult.getInt(1);
//		} else {
//			throw new RuntimeException("Something went wrong while getting an id for the new transfer");
//		}
//	}
	
	
	
}
