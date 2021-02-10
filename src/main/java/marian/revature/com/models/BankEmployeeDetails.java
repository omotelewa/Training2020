package marian.revature.com.models;

public class BankEmployeeDetails {
			
		private String bankEmpName;
		private String bankEmpAddress;
		private String BirthDate;
		private String phoneNumber;
		private String accountNumber;
		private double balance;
		public String getBankEmpName() {
			return bankEmpName;
		}
		public void setBankEmpName(String bankEmpName) {
			this.bankEmpName = bankEmpName;
		}
		public String getBankEmpAddress() {
			return bankEmpAddress;
		}
		public void setBankEmpAddress(String bankEmpAddress) {
			this.bankEmpAddress = bankEmpAddress;
		}
		public String getBirthDate() {
			return BirthDate;
		}
		public void setBirthDate(String birthDate) {
			BirthDate = birthDate;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public BankEmployeeDetails(String bankEmpName, String bankEmpAddress, String birthDate, String phoneNumber,
				String accountNumber, double balance) {
			this.bankEmpName = bankEmpName;
			this.bankEmpAddress = bankEmpAddress;
			BirthDate = birthDate;
			this.phoneNumber = phoneNumber;
			this.accountNumber = accountNumber;
			this.balance = balance;
		}
		@Override
		public String toString() {
			return "BankEmployeeDetails [bankEmpName=" + bankEmpName + ", bankEmpAddress=" + bankEmpAddress
					+ ", BirthDate=" + BirthDate + ", phoneNumber=" + phoneNumber + ", accountNumber=" + accountNumber
					+ ", balance=" + balance + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((BirthDate == null) ? 0 : BirthDate.hashCode());
			result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
			long temp;
			temp = Double.doubleToLongBits(balance);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((bankEmpAddress == null) ? 0 : bankEmpAddress.hashCode());
			result = prime * result + ((bankEmpName == null) ? 0 : bankEmpName.hashCode());
			result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
			BankEmployeeDetails other = (BankEmployeeDetails) obj;
			if (BirthDate == null) {
				if (other.BirthDate != null)
					return false;
			} else if (!BirthDate.equals(other.BirthDate))
				return false;
			if (accountNumber == null) {
				if (other.accountNumber != null)
					return false;
			} else if (!accountNumber.equals(other.accountNumber))
				return false;
			
			
			if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
				return false;
			if (bankEmpAddress == null) {
				if (other.bankEmpAddress != null)
					return false;
			} else if (!bankEmpAddress.equals(other.bankEmpAddress))
				return false;
			if (bankEmpName == null) {
				if (other.bankEmpName != null)
					return false;
			} else if (!bankEmpName.equals(other.bankEmpName))
				return false;
			if (phoneNumber == null) {
				if (other.phoneNumber != null)
					return false;
			} else if (!phoneNumber.equals(other.phoneNumber))
				return false;
			return true;
		}
		
		

}
