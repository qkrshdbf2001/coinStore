package bitcoin.custom;

import java.util.ArrayList;

public interface CustomService {
	void join(Custom ct);

	boolean login(Custom ct);

	Custom getMyInfo(String id);

	Custom getSelectN(String name);
	
	void editMyInfo(Custom m);

	void out(String id);
	
	ArrayList<Custom> getList();

	//���α��� ����
	void updateSUID(Custom ct);
	void updateSUPW(Custom ct);
	void updateSUPH(Custom ct);
	
	//���α��� ����
	void deleteSUID(String cu_id);

	//���� �߰��մϴ�.
	void walletAdd(Custom ct);

	//������ �ִ� ���� �����ɴϴ�.
	int getWallet(String cu_id);

	//������
	void subWallet(Custom ct);
}
