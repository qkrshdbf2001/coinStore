package bitcoin.custom;

import java.sql.Date;

public class Custom {
	private String cu_id;
	private String cu_passwd;
	private String cu_name;
	private String cu_phone;
	private Date join_date;
	private int wallet;
	
	public Custom() {
		
	}
	
	public Custom(String cu_id, String cu_passwd, String cu_name, String cu_phone, Date join_date, int Wallet) {
		this.cu_id = cu_id;
		this.cu_passwd = cu_passwd;
		this.cu_name = cu_name;
		this.cu_phone = cu_phone;
		this.join_date = join_date;
		this.wallet = Wallet;
	}
	
	public String getCu_id() {
		return cu_id;
	}
	public void setCu_id(String cu_id) {
		this.cu_id = cu_id;
	}
	public String getCu_passwd() {
		return cu_passwd;
	}
	public void setCu_passwd(String cu_passwd) {
		this.cu_passwd = cu_passwd;
	}
	public String getCu_name() {
		return cu_name;
	}
	public void setCu_name(String cu_name) {
		this.cu_name = cu_name;
	}
	public String getCu_phone() {
		return cu_phone;
	}
	public void setCu_phone(String cu_phone) {
		this.cu_phone = cu_phone;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int Wallet) {
		this.wallet = Wallet;
	}

	@Override
	public String toString() {
		return "Custom [cu_id=" + cu_id + ", cu_passwd=" + cu_passwd + ", cu_name=" + cu_name + ", cu_phone=" + cu_phone
				+ ", join_date=" + join_date + ", Wallet=" + wallet + "]";
	}
}
