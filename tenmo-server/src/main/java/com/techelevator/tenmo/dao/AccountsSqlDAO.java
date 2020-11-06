package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;

@Component
public class AccountsSqlDAO implements AccountsDAO {

	private JdbcTemplate jdbcTemplate;
	
	public AccountsSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
<<<<<<< HEAD
	
	public void transferMoney(Accounts user) {
		String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		jdbcTemplate.update(results, user.getBalance(), user.getUserId(), user.getAccountId());
	}
	
=======
	@Override
	public void transferMoney(Accounts user) {
		String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		jdbcTemplate.update(results, user.getBalance(), user.getUserId());
	}
	@Override
>>>>>>> 87e6279f65f9a6ab8d40a2ed847ae29a159def4f
	public AccountTransfer transferHistory(AccountTransfer transfer) {
		String sql = "INSERT INTO transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount), " +
				"VALUES(?, ?, ?, ?, ?) ";
		transfer.setTransferId(getNextTransferId());
		jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
		return transfer;
	}
	@Override
	public List<AccountTransfer> getTransferHistory(AccountTransfer userId) {
		List<AccountTransfer> transferList = new ArrayList<>();
		String sql = "SELECT transfer_id, account_from, account_to, amount FROM transfers " + 
					 "JOIN users ON transfers.account_from = users.user_id WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) {
			AccountTransfer theTransfers = new AccountTransfer();
			theTransfers = mapToRowTransfer(results);
			transferList.add(theTransfers);
		}
		return transferList;
	}
	
	private AccountTransfer mapToRowTransfer(SqlRowSet rowSet) {
		AccountTransfer theTransferHistory = new AccountTransfer();
		theTransferHistory.setTransferId(rowSet.getInt("transfer_id"));
		theTransferHistory.setTransferTypeId(rowSet.getInt("transfer_type_id"));
		theTransferHistory.setTransferStatusId(rowSet.getInt("transfer_status_id"));
		theTransferHistory.setAccountFrom(rowSet.getInt("account_from"));
		theTransferHistory.setAccountTo(rowSet.getInt("account_to"));
		theTransferHistory.setAmount(rowSet.getBigDecimal("amount"));
		return theTransferHistory;
	}
	
	private void mapToRowAccounts(SqlRowSet rowSet) {
		Accounts theTransfer = new Accounts();
		theTransfer.setBalance(rowSet.getBigDecimal("balance"));
		theTransfer.setUserId(rowSet.getInt("user_id"));
//		theTransfer.setAccountId(rowSet.getInt("account_id"));
	}
	
	private int getNextTransferId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_transfer_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new transfer");
		}
	}
	
	
	
}
