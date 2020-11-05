package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Accounts {

	private int amountId;
	private int userId;
	private BigDecimal balance;
	
	public Accounts(int amountId, int userId, BigDecimal balance) {
		this.amountId = amountId;
		this.balance = balance;
		this.userId = userId;
	}

	public int getAmountId() {
		return amountId;
	}

	public void setAmountId(int amountId) {
		this.amountId = amountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
