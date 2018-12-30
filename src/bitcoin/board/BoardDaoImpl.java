package bitcoin.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bitcoin.JDBC.DBConnect;

public class BoardDaoImpl implements BoardDao {
	private DBConnect db;
	
	public BoardDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public void insert(Board b) {
		Connection conn = db.getConnection();
		String sql = "insert into board1 values(board1_seq.nextval, ?, sysdate, sysdate, 0, 0, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getCu_id());
			pstmt.setString(3, b.getBoard_write());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disConn();
	}

	@Override
	public void update(Board b) {
		Connection conn = db.getConnection();
		String sql = "update board1 set board_update=sysdate, board_title=?, board_write=? where board_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getBoard_write());
			pstmt.setInt(3, b.getBoard_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disConn();
	}

	@Override
	public Board select(int board_num) {
		ResultSet rs;
		Connection conn = db.getConnection();
		String sql = "select * from board1 where board_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Board(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//현재 메서드가 종료하기 전에 꼭 실행해야하는 코드 작성
			db.disConn();
		}
		return null;
	}

	@Override
	public void delete(int num) {
		Connection conn = db.getConnection();
		String sql = "delete board1 where board_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disConn();
	}

	@Override
	public ArrayList<Board> selectAll() {
		ResultSet rs;
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = db.getConnection();
		String sql = "select * from board1";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//현재 메서드가 종료하기 전에 꼭 실행해야하는 코드 작성
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board> selectByWriter(String writer) {
		ResultSet rs;
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = db.getConnection();
		String sql = "select * from board1 where cu_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//현재 메서드가 종료하기 전에 꼭 실행해야하는 코드 작성
			db.disConn();
		}
		return list;
	}
}
