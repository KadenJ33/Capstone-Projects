package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.TransferDTO;

public interface AccountsDAO {

	void sendMoney(AccountTransfer transfer);
	
	void transferHistory(AccountTransfer transfer);
	
	List<AccountTransfer> getTransferHistory(Long userId);
	
	List<AccountTransfer> getTransferDetails(Long userId, Long transferId);

	BigDecimal getBalance(int accountId);
}
