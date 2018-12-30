package bitcoin.coin;

import java.util.ArrayList;

import bitcoin.JDBC.DBConnect;

public class CoinServiceImpl implements CoinService {
	private CoinDao dao;
	
	public CoinServiceImpl (CoinDao coinDao) {
		this.dao = coinDao;
	}

	@Override
	public void insertCoin(Coin coin) {
		dao.insert(coin);
	}

	@Override
	public ArrayList<Coin> getList(String cu_id) {
		return dao.selectAll(cu_id);
	}

	@Override
	public void remove(int sellCoin_Num) {
		dao.removeSell(sellCoin_Num);
	}
}
