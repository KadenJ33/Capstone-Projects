package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.TransferDTO;

public interface AccountsDAO {
	
	void transferMoneyTotal(AccountTransfer transfer);
	
	List<AccountTransfer> getTransferHistory(AccountTransfer history);
	
//	List<AccountTransfer> getTransferHistory2(AccountTransfer history2);
	
//	List<AccountTransfer> getTransferHistory(Principal principal);
	
	List<AccountTransfer> getTransferDetails(Long userId, Long transferId);

	BigDecimal getBalance(int accountId);
}
