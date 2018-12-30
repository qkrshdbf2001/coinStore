package bitcoin.coin;

public class Coin {
	private int coin_num;
	private String cu_id;
	private String coin_name;
	private float coin_wallet;
	
	public Coin() {
		
	}
	
	public Coin(int coin_num, String cu_id, String coin_name, float coin_wallet) {
		this.coin_num = coin_num;
		this.cu_id = cu_id;
		this.coin_name = coin_name;
		this.coin_wallet = coin_wallet;
	}

	public int getCoin_num() {
		return coin_num;
	}



	public void setCoin_num(int coin_num) {
		this.coin_num = coin_num;
	}



	public String getCu_id() {
		return cu_id;
	}



	public void setCu_id(String cu_id) {
		this.cu_id = cu_id;
	}



	public String getCoin_name() {
		return coin_name;
	}



	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
	}



	public float getCoin_wallet() {
		return coin_wallet;
	}



	public void setCoin_wallet(float coin_wallet) {
		this.coin_wallet = coin_wallet;
	}



	@Override
	public String toString() {
		return "Coin [coin_num=" + coin_num + ", cu_id=" + cu_id + ", coin_name=" + coin_name + ", coin_wallet="
				+ coin_wallet + "]";
	}
}
