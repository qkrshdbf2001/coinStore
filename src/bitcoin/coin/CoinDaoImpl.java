package bitcoin.coin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bitcoin.JDBC.DBConnect;
import bitcoin.custom.Custom;

public class CoinDaoImpl implements CoinDao {
private DBConnect db;
	
	public CoinDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public void insert(Coin coin) {
		Connection conn = db.getConnection();
		String sql = "insert into coin1 values(coin1_seq.nextval,?,?,?)";
		PreparedStatement pstmt;
		
		try {
			System.out.println(coin);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coin.getCu_id());
			pstmt.setString(2, coin.getCoin_name());
			pstmt.setFloat(3, coin.getCoin_wallet());
			System.out.println("Àü¼Û");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	public ArrayList<Coin> selectAll(String cu_id) {
		ResultSet rs;
		ArrayList<Coin> list = new ArrayList<Coin>();
		Connection conn = db.getConnection();
		String sql = "select * from coin1 where cu_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cu_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Coin(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getFloat(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public void removeSell(int sellCoin_Num) {
		Connection conn = db.getConnection();
		String sql = "delete coin1 where coin_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sellCoin_Num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}
}
