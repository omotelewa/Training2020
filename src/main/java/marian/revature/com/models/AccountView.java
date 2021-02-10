package marian.revature.com.models;

public class AccountView{
	
	
	public AccountView(int AccountView_ID, int USER_ID, double BALANCE, int AccountView_TYPE) {
		super();
	}
	
	public AccountView(int AccountView_ID, int AccountView_TYPE,double BALANCE) {
		super();
	
		this.acctid=AccountView_ID;
		this.acctype = AccountView_TYPE;
		this.balance = BALANCE;
	
	}
	
	
	
	private int acctype;
	private double balance;
	
	private int acctid;
	
	@Override
	public String toString() {
		if(acctype == 1) {return "AccountView: SAVINGS "+ "|BALANCE: " + balance;}
		if(acctype == 0) {return "AccountView: CHECKING "+ "|BALANCE: " + balance;}
		return Integer.toString(acctype);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountView other = (AccountView) obj;
		if (acctype != other.acctype)
			return false;
		return true;
	
	}
	
	public int getAcctype() {
		return acctype;
	}
	
	public void setAcctype(int acctype) {
		this.acctype = acctype;
	}
	
}

