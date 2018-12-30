package bitcoin.board;

import java.util.ArrayList;

public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	
	public BoardServiceImpl() {
		
	}
	
	public BoardServiceImpl(BoardDao boardDao) {
		this.dao = boardDao;
	}

	@Override
	public void writeBoard(Board b) {
		dao.insert(b);
	}

	@Override
	public Board getBoard(int board_num) {
		return dao.select(board_num);
	}

	@Override
	public void editBoard(Board b) {
		dao.update(b);
	}

	@Override
	public void delBoard(int num) {
		dao.delete(num);		
	}

	@Override
	public ArrayList<Board> getAll() {
		return dao.selectAll();
	}

	@Override
	public ArrayList<Board> getByWriter(String writer) {
		return dao.selectByWriter(writer);
	}
}
