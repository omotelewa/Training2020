package marian.revature.com.models;

import java.util.Date;

public class TransactionSummary {

	private AccountDetails details ;
	private Date date; 
	private double currentBalance;
	private double withdrawBalance;
	private double depositBalance;
	public AccountDetails getDetails() {
		return details;
	}
	public void setDetails(AccountDetails details) {
		this.details = details;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public double getWithdrawBalance() {
		return withdrawBalance;
	}
	public void setWithdrawBalance(double withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}
	public double getDepositBalance() {
		return depositBalance;
	}
	public void setDepositBalance(double depositBalance) {
		this.depositBalance = depositBalance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(currentBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(depositBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		temp = Double.doubleToLongBits(withdrawBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionSummary other = (TransactionSummary) obj;
		if (Double.doubleToLongBits(currentBalance) != Double.doubleToLongBits(other.currentBalance))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(depositBalance) != Double.doubleToLongBits(other.depositBalance))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (Double.doubleToLongBits(withdrawBalance) != Double.doubleToLongBits(other.withdrawBalance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransactionSummary [details=" + details + ", date=" + date + ", currentBalance=" + currentBalance
				+ ", withdrawBalance=" + withdrawBalance + ", depositBalance=" + depositBalance + "]";
	}
	public TransactionSummary(AccountDetails details, Date date, double withdrawBalance, double depositBalance) {
		this.details = details;
		this.date = date;
		this.withdrawBalance = withdrawBalance;
		this.depositBalance = depositBalance;
	}
	
	
	
	
}
