package bitcoin.board;

import java.util.ArrayList;

public interface BoardService {

	void writeBoard(Board b);

	Board getBoard(int board_num);

	void editBoard(Board b);

	void delBoard(int num);

	ArrayList<Board> getAll();

	ArrayList<Board> getByWriter(String writer);

}
