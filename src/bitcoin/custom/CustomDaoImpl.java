package bitcoin.custom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bitcoin.JDBC.DBConnect;
import bitcoin.custom.Custom;

public class CustomDaoImpl implements CustomDao {

	private DBConnect db;
	
	public CustomDaoImpl() {
		db = DBConnect.getInstance();
	}
	
	//회원가입
	public void insertCustom(Custom ct) {
		Connection conn = db.getConnection();
		System.out.println("데이터베이스 연결");
		String sql = "insert into Custom1 values(?,?,?,?,sysdate,0)";
		System.out.println("삽입 sql 작성");
		PreparedStatement pstmt;	
		
		try {
			System.out.println(ct);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCu_id());
			pstmt.setString(2, ct.getCu_passwd());
			pstmt.setString(3, ct.getCu_name());
			pstmt.setString(4, ct.getCu_phone());
			System.out.println("전송");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}
	
	//회원검색
	public Custom select(String cu_id) {
		ResultSet rs;
		Connection conn = db.getConnection();
		String sql = "select * from Custom1 where CU_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cu_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Custom(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disConn();
		return null;
	}
	
	@Override
	public Custom selectN(String name) {
		ResultSet rs;
		Connection conn = db.getConnection();
		String sql = "select * from Custom1 where CU_name=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Custom(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disConn();
		return null;
	}

	//시간 추가
	public void addTimeCustom(Custom ct) {
		Connection conn = db.getConnection();
		String sql = "update Custom1 set wallet=? where CU_ID=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ct.getWallet());
			pstmt.setString(2, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}
	
	//회원정보 수정
	public void updateCustom(Custom ct) {
		Connection conn = db.getConnection();
		String sql = "update Custom1 set CU_PASSWD=? where CU_ID=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCu_passwd());
			pstmt.setString(2, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	//회원 삭제
	public void deleteCustom(String cu_id) {
		Connection conn = db.getConnection();
		String sql = "delete Custom1 where cu_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cu_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	//전체 결과 조회
	public ArrayList<Custom> selectAll() {
		ResultSet rs;
		ArrayList<Custom> list = new ArrayList<Custom>();
		Connection conn = db.getConnection();
		String sql = "select * from Custom1";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Custom(rs.getString(1), "********", rs.getString(3), rs.getString(4), rs.getDate(5),rs.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public void updateSUID(Custom ct) {
		Connection conn = db.getConnection();
		String sql = "update Custom1 set CU_name=? where CU_id=?";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCu_name());
			pstmt.setString(2, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public void updateSUPW(Custom ct) {
		Connection conn = db.getConnection();
		String sql = "update Custom1 set CU_passwd='000000' where CU_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public void updateSUPH(Custom ct) {
		Connection conn = db.getConnection();
		String sql = "update Custom1 set cu_phone=? where CU_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCu_phone());
			pstmt.setString(2, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public void deleteSU(String cu_id) {
		Connection conn = db.getConnection();
		String sql = "delete Custom1 where cu_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cu_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public void walletUpdate(Custom ct) {
		Connection conn = db.getConnection();
		
		String sql = "update Custom1 set wallet=? where cu_id=?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ct.getWallet());
			pstmt.setString(2, ct.getCu_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public int getWallet(String cu_id) {
		ResultSet rs;
		Connection conn = db.getConnection();
		int wallet = 0;
		String sql = "select wallet from Custom1 where cu_id=?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cu_id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				wallet=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disConn();
		
		return wallet;
	}
}
