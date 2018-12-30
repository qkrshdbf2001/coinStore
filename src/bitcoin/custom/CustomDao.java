package bitcoin.custom;

import java.util.ArrayList;

public interface CustomDao {
	void insertCustom(Custom ct);
	
	void updateCustom(Custom ct);
	
	void deleteCustom(String id);
	
	Custom select(String id);
	
	//�̸����� �˻�
	Custom selectN(String name);
	
	ArrayList<Custom> selectAll();

	//���α��� ����
	void updateSUID(Custom ct);
	void updateSUPW(Custom ct);
	void updateSUPH(Custom ct);
	
	//���α��� ����
	void deleteSU(String cu_id);

	//�� �߰�
	void walletUpdate(Custom ct);

	//�� ��������
	int getWallet(String cu_id);
}
