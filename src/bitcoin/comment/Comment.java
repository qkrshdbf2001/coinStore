package bitcoin.comment;

import java.sql.Date;

public class Comment {
	private int cm_num;
	private String cu_id;
	private int board_num;
	private Date cm_date;
	private String cm_write;
	
	public Comment() {
		
	}
	
	public Comment(int cm_num, String cu_id, int board_num, Date cm_date, String cm_write) {
		this.cm_num = cm_num;
		this.cu_id = cu_id;
		this.board_num = board_num;
		this.cm_date = cm_date;
		this.cm_write = cm_write;
	}

	public int getCm_num() {
		return cm_num;
	}

	public void setCm_num(int cm_num) {
		this.cm_num = cm_num;
	}

	public String getCu_id() {
		return cu_id;
	}

	public void setCu_id(String cu_id) {
		this.cu_id = cu_id;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public Date getCm_date() {
		return cm_date;
	}

	public void setCm_date(Date cm_date) {
		this.cm_date = cm_date;
	}

	public String getCm_write() {
		return cm_write;
	}

	public void setCm_write(String cm_write) {
		this.cm_write = cm_write;
	}

	@Override
	public String toString() {
		return "Comment [cm_num=" + cm_num + ", cu_id=" + cu_id + ", board_num=" + board_num + ", cm_date=" + cm_date
				+ ", cm_write=" + cm_write + "]";
	}
}
