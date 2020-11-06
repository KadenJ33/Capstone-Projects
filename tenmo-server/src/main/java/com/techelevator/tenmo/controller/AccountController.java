package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.AccountTransfer;
import com.techelevator.tenmo.model.Accounts;

@RestController
@PreAuthorize("isAuthenticated()")



public class AccountController {

	private static final String API_BASE_URL = "http://localhost:8080/";
	private AccountsDAO dao;
	
	public AccountController(AccountsDAO accountsDao) {
		dao = accountsDao;
	}
	
	@RequestMapping(path = "transfer-history/{id}", method = RequestMethod.GET)
	public List<AccountTransfer> transferList(@PathVariable("id") AccountTransfer userId) {
		return dao.getTransferHistory(userId);
	}
	
	
	
}
