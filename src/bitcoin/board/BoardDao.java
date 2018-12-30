package bitcoin.board;

import java.util.ArrayList;

public interface BoardDao {

	void insert(Board b);

	void update(Board b);

	Board select(int board_num);

	void delete(int num);

	ArrayList<Board> selectAll();

	ArrayList<Board> selectByWriter(String writer);
}
