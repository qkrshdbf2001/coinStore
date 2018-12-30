package bitcoin.coin;

import java.util.ArrayList;

public interface CoinDao {

	void insert(Coin coin);

	ArrayList<Coin> selectAll(String cu_id);

	void removeSell(int sellCoin_Num);

}
