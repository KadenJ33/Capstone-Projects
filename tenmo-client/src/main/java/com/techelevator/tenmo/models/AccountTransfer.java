package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class AccountTransfer {

	private String otherUser;
	

	private int transferId;
	private int transferTypeId;
	private int transferStatusId;
	private int accountFrom;
	private int accountTo;
	private BigDecimal amount;
	private String otherUser;
	
//	public AccountTransfer(int transferId, int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
//		this.transferId = transferId;
//		this.transferTypeId = transferTypeId;
//		this.transferStatusId = transferStatusId;
//		this.accountFrom = accountFrom;
//		this.accountTo = accountTo;
//		this.amount = amount;
//	}
	
//	public AccountTransfer() {
//		
//	}
<<<<<<< HEAD
	
	public String getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}
=======
	public String getOtherUser() {
		return otherUser;
	}
>>>>>>> 47bad9a61fe3af776671e1fbbb28589a68a1770e

	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}
	
	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(int transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public int getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "accountFrom: " + accountFrom + "accountTo" + accountTo + "amount" + amount;
	}

}
