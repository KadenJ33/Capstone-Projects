package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")



public class AccountController {

	private static final String API_BASE_URL = "http://localhost:8080/";
	private AccountsDAO dao;
	UserDAO userDao;
	public AccountController(AccountsDAO accountsDao, UserDAO userDao) {
		dao = accountsDao;
		this.userDao = userDao;
	}
	
	@RequestMapping(path = "/accounts/transfer", method = RequestMethod.PUT)
	public void transferMoney(@RequestBody AccountTransfer transfer) {
		
		dao.transferMoney(transfer);
		dao.transferHistory(transfer);
	}
	
	@RequestMapping(path = "transfer-history", method = RequestMethod.GET)
	public List<AccountTransfer> transferList(Principal principal) {
		
		User user = userDao.findByUsername(principal.getName());
		
		return dao.getTransferHistory(user.getId());
	}
	
	@RequestMapping(path = "transfer-history/{transferId}", method = RequestMethod.GET)
	public List<AccountTransfer> detailsList( @PathVariable Long transferId, Principal principal) {
		
		User user = userDao.findByUsername(principal.getName());
		
		return dao.getTransferDetails(user.getId(), transferId);
	}
	
	
	
}
