package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AccountTransfer;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.User;
import com.techelevator.view.ConsoleService;


public class AccountService {
	
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	public RestTemplate restTemplate = new RestTemplate();
	
	public AccountService(String url) {
		BASE_URL = url;
	}
	
	public BigDecimal viewCurrentBalance() throws AccountServiceException {
		BigDecimal account = null;
		try {
			account = restTemplate.exchange(BASE_URL + "balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return account;
	}
	public AccountTransfer transferList(AccountTransfer accountId) throws AccountServiceException {
		AccountTransfer transferList = null;
		try {
			transferList = restTemplate.exchange(BASE_URL + "transfer-history/" + accountId, HttpMethod.GET, makeAuthEntity(), AccountTransfer.class).getBody();
			
		}catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferList;
	}
	
	public boolean transferMoney(AccountTransfer transfer, AuthenticatedUser currentUser) throws AccountServiceException {
		try {
			restTemplate.exchange(BASE_URL + "accounts/transfer", HttpMethod.PUT, makeAccountTransferEntity(transfer), AccountTransfer.class, transfer);
			return true;
		} catch (RestClientResponseException ex) {
			 new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
			 return false;
		}
	}

	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
	
	private HttpEntity<AccountTransfer> makeAccountTransferEntity(AccountTransfer transfer) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<AccountTransfer> entity = new HttpEntity<>(transfer, headers);
	    return entity;
	  }
	
//	public void transferHistory(AccountTransfer transfer) throws AccountServiceException {
//	try {
//		restTemplate.exchange(BASE_URL + "account/transfer/history", HttpMethod.POST, makeAuthEntity(AUTH_TOKEN), AccountTransfer.class).getBody();
//	} catch (RestClientResponseException ex) {
//		throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
//	}
//}
	
}
