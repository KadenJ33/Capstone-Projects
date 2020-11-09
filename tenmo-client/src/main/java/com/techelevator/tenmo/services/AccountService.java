package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.security.Principal;
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
	
<<<<<<< HEAD
//	public AccountTransfer[] transferList2(AccountTransfer history2) throws AccountServiceException {
//		AccountTransfer[] transferHistory2 = null;
//		try {
//			transferHistory2 = restTemplate.exchange(BASE_URL + "accounts/transfer/history/recieve", HttpMethod.GET, makeAccountTransferHistoryEntity2(history2), AccountTransfer[].class, history2).getBody();
//		} catch (RestClientResponseException ex) {
//			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
//		}
//		return transferHistory2;
//	}
=======
	public AccountTransfer[] transferList() throws AccountServiceException {
		AccountTransfer[] transferHistory = null;
		try {
			transferHistory = restTemplate.exchange(BASE_URL + "accounts/transfer/history/sent", HttpMethod.GET, makeAuthEntity(), AccountTransfer[].class).getBody();
		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferHistory;
	}
	public AccountTransfer[] transferList2() throws AccountServiceException {
		AccountTransfer[] transferHistory = null;
		try {
			transferHistory = restTemplate.exchange(BASE_URL + "accounts/transfer/history/received", HttpMethod.GET, makeAuthEntity(), AccountTransfer[].class).getBody();
		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferHistory;
	}
>>>>>>> 47bad9a61fe3af776671e1fbbb28589a68a1770e
		
	public AccountTransfer[] transferDetails(int transferId) throws AccountServiceException {
		AccountTransfer[] transferDetails = null;
		try {
			transferDetails = restTemplate.exchange(BASE_URL + "accounts/transfer/history/details/" + transferId, HttpMethod.GET, makeAuthEntity(), AccountTransfer[].class).getBody();
			
		}catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferDetails;
	}
	
	public void transferMoney(AccountTransfer theTransfer, AuthenticatedUser currentUser) throws AccountServiceException {
		try {
			restTemplate.exchange(BASE_URL + "accounts/transfer", HttpMethod.PUT, makeAccountTransferEntity(theTransfer), AccountTransfer.class, theTransfer);
		} catch (RestClientResponseException ex) {
			 new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
	}
	
	public AccountTransfer[] getTransferHistory(AccountTransfer history, AuthenticatedUser currentUser) throws AccountServiceException {
		AccountTransfer[] transferHistory = null;
		try {
			transferHistory = restTemplate.exchange(BASE_URL + "accounts/transfer/history", HttpMethod.GET, makeAccountTransferHistoryEntity(history), AccountTransfer[].class, history).getBody();
		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferHistory;
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
	
	private HttpEntity<AccountTransfer> makeAccountTransferHistoryEntity(AccountTransfer history) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<AccountTransfer> entity = new HttpEntity<>(history, headers);
	    return entity;
	}
	
	private HttpEntity<AccountTransfer> makeAccountTransferHistoryEntity2(AccountTransfer history2) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<AccountTransfer> entity = new HttpEntity<>(history2, headers);
	    return entity;
	}
}
