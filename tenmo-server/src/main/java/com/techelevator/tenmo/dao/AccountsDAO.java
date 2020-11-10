package com.techelevator.tenmo.dao;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.TransferDTO;
public interface AccountsDAO {
	void transferMoneyTotal(AccountTransfer transfer);
<<<<<<< HEAD
//	void transferMoney(AccountTransfer transfer);
//	
//	void transferHistory(AccountTransfer transfer);
=======

>>>>>>> 367f3b2793eb7ce5b29f0eeca8df03655454490e
	List<AccountTransfer> getTransferHistory(Principal principal);
	List<AccountTransfer> getTransferDetails(Long userId, int transferId);
	BigDecimal getBalance(int userId);
	List<AccountTransfer> getTransferHistoryReceived(Principal principal);
}