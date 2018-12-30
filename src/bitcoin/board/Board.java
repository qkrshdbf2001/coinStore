package bitcoin.board;

import java.sql.Date;

public class Board {
	private int board_num;
	private String board_title;
	private Date board_date;
	private Date board_update;
	private int board_count;
	private int board_del;
	private String cu_id;
	private String board_write;
	
	public Board() {
		
	}

	public Board(int board_num, String board_title, Date board_date, Date board_update, int board_count, int board_del,
			String cu_id, String board_write) {
		super();
		this.board_num = board_num;
		this.board_title = board_title;
		this.board_date = board_date;
		this.board_update = board_update;
		this.board_count = board_count;
		this.board_del = board_del;
		this.cu_id = cu_id;
		this.board_write = board_write;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public Date getBoard_update() {
		return board_update;
	}

	public void setBoard_update(Date board_update) {
		this.board_update = board_update;
	}

	public int getBoard_count() {
		return board_count;
	}

	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}

	public int getBoard_del() {
		return board_del;
	}

	public void setBoard_del(int board_del) {
		this.board_del = board_del;
	}

	public String getCu_id() {
		return cu_id;
	}

	public void setCu_id(String cu_id) {
		this.cu_id = cu_id;
	}

	public String getBoard_write() {
		return board_write;
	}

	public void setBoard_write(String board_write) {
		this.board_write = board_write;
	}

	@Override
	public String toString() {
		return "Board [board_num=" + board_num + ", board_title=" + board_title + ", board_date=" + board_date
				+ ", board_update=" + board_update + ", board_count=" + board_count + ", board_del=" + board_del
				+ ", cu_id=" + cu_id + ", board_write=" + board_write + "]";
	}
}
