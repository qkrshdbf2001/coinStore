package bitcoin.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bitcoin.JDBC.DBConnect;

public class CommentDaoImpl implements CommentDao{

	private DBConnect db;
	
	public CommentDaoImpl() {
		db = DBConnect.getInstance();
	}
	
	public void cmInsert(Comment cm) {
		Connection conn = db.getConnection();
		System.out.println(cm);
		String sql = "insert into comment1 values(comment1_seq.nextval, ?, ?, sysdate, ?)";
		PreparedStatement pstmt;	
		
		try {
			System.out.println(cm);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cm.getCu_id());
			pstmt.setInt(2, cm.getBoard_num());
			pstmt.setString(3, cm.getCm_write());
			System.out.println("전송");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public ArrayList<Comment> selectAll(int num) {
		ResultSet rs;
		Connection conn = db.getConnection();
		ArrayList<Comment> list = new ArrayList<Comment>();
		String sql = "select * from comment1 where board_num=?";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		
		System.out.println(list.isEmpty());
		
		System.out.println("댓글이 없습니다.");
		
		return list;
	}

	@Override
	public String selectCm_id(int Cm_Num) {
		ResultSet rs;
		Connection conn = db.getConnection();
		String cu_id = null;
		String sql = "select cu_id from Comment1 where Cm_Num=?";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Cm_Num);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cu_id=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		
		return cu_id;
	}

	@Override
	public void updateComment(Comment cm1) {
		Connection conn = db.getConnection();
		String sql = "update comment1 set cm_write=? where cm_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cm1.getCm_write());
			pstmt.setInt(2, cm1.getCm_num());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

	@Override
	public void delComment(Comment cm2) {
		Connection conn = db.getConnection();
		String sql = "delete Comment1 where cm_num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cm2.getCm_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
	}

}
