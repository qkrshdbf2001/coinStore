package bitcoin.custom;

import java.util.ArrayList;

public class CustomServiceImpl implements CustomService {

	private CustomDao dao;

	public CustomServiceImpl(CustomDao dao) {
		this.dao = dao;
	}

	@Override
	public void join(Custom ct) {
		// TODO Auto-generated method stub
		dao.insertCustom(ct);
	}

	@Override
	public boolean login(Custom ct) {
		// TODO Auto-generated method stub
		Custom ct2 = dao.select(ct.getCu_id());
		if (ct2 == null) {
			System.out.println("없는 아이디");
		} else {
			if (ct2.getCu_passwd().equals(ct.getCu_passwd())) {
				return true;
			} else {
				System.out.println("패스워드 불일치");
			}
		}
		return false;
	}

	@Override
	public Custom getMyInfo(String id) {
		// TODO Auto-generated method stub
		return dao.select(id);
	}
	
	@Override
	public Custom getSelectN(String name) {
		// TODO Auto-generated method stub
		return dao.selectN(name);
	}

	@Override
	public void editMyInfo(Custom ct) {
		// TODO Auto-generated method stub
		dao.updateCustom(ct);
	}

	@Override
	public void out(String id) {
		// TODO Auto-generated method stub
		dao.deleteCustom(id);
	}

	@Override
	public ArrayList<Custom> getList() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public void updateSUID(Custom ct) {
		dao.updateSUID(ct);
		
	}

	@Override
	public void updateSUPW(Custom ct) {
		dao.updateSUPW(ct);		
	}

	@Override
	public void updateSUPH(Custom ct) {
		dao.updateSUPH(ct);
	}

	@Override
	public void deleteSUID(String cu_id) {
		dao.deleteSU(cu_id);
	}

	@Override
	public void walletAdd(Custom ct) {
		dao.walletUpdate(ct);
	}

	@Override
	public int getWallet(String cu_id) {
		return dao.getWallet(cu_id);
	}

	@Override
	public void subWallet(Custom ct) {
		dao.walletUpdate(ct);
	}
}
