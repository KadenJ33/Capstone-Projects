package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;

public interface AccountsDAO {

	void transferMoney(Accounts user);
	
	AccountTransfer transferHistory(AccountTransfer transfer);
	
	List<AccountTransfer> getTransferHistory(AccountTransfer userId);
}
