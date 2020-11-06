package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;

public interface AccountsDAO {
	
	void transferMoney(Accounts user);
	
	AccountTransfer transferHistory(AccountTransfer transfer);
	
	List<AccountTransfer> getTransferHistory(Long userId);
	
	List<AccountTransfer> getTransferDetails(Long userId, Long transferId);

	BigDecimal getBalance(Long accountId);
}
