package bitcoin.comment;

import java.util.ArrayList;

public class CommentServiceImpl implements CommentService{
	private CommentDao dao;
	
	public CommentServiceImpl(CommentDao commentDao) {
		this.dao = commentDao;
	}

	@Override
	public void cmWrite(Comment cm) {
		dao.cmInsert(cm);
	}

	@Override
	public ArrayList<Comment> cmPrintAll(int num) {
		return dao.selectAll(num);
	}

	@Override
	public String selectCm_id(int Cm_Num) {
		return dao.selectCm_id(Cm_Num);
	}

	@Override
	public void updateComment(Comment cm1) {
		dao.updateComment(cm1);
	}

	@Override
	public void delComment(Comment cm2) {
		dao.delComment(cm2);
	}
}
