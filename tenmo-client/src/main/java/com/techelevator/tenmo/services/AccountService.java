package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AccountTransfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.view.ConsoleService;

public class AccountService {
	
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	public RestTemplate restTemplate = new RestTemplate();
	
	public AccountService(String url) {
		BASE_URL = url;
	}
	
	public BigDecimal viewCurrentBalance(int accountId) throws AccountServiceException {
		BigDecimal account = null;
		try {
			account = restTemplate.exchange(BASE_URL + "balance/{accountId}", HttpMethod.GET, makeAuthEntity(AUTH_TOKEN), BigDecimal.class, accountId).getBody();
		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return account;
	}
	public AccountTransfer transferList(AccountTransfer accountId) throws AccountServiceException {
		AccountTransfer transferList = null;
		try {
			transferList = restTemplate.exchange(BASE_URL + "transfer-history/" + accountId, HttpMethod.GET, makeAuthEntity(AUTH_TOKEN), AccountTransfer.class).getBody();
			
		}catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		}
		return transferList;
	}

	private HttpEntity makeAuthEntity(String AUTH_TOKEN) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
	
}
