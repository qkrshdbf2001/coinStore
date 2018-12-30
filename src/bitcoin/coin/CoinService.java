package bitcoin.coin;

import java.util.ArrayList;

public interface CoinService {

	void insertCoin(Coin coin);

	ArrayList<Coin> getList(String cu_id);

	void remove(int sellCoin_Num);
}
