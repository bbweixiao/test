package com.kaishengit.service;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.dao.RecodeDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.Recode;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.DateUtil;

public class AccountService {

	private AccountDao dao = new AccountDao();

	public Account login(String username, String password) {
		return dao.findByUserNameAndPassword(username,password);
	}

	public void getMoney(Account account, float money) {
		
		//1.��ȥ�˺��еĽ��
		//?Ǯ����
		if(money < account.getMoney()) {
			account.setMoney(account.getMoney() - money);
			dao.update(account);
			//2.���������¼
			Recode recode = new Recode();
			recode.setAccount_id(account.getId());
			recode.setCreatetime(DateUtil.getNow());
			recode.setOutmoney(money);
			recode.setBalance(account.getMoney());
			
			RecodeDao recodeDao = new RecodeDao();
			recodeDao.save(recode);
		} else {
			throw new ServiceException("����");
		}
		
		
	}

	public void save(Account account, float money) {
		
		if(money <= 0) {
			throw new ServiceException("����Ϊ������0");
		} else {
			account.setMoney(account.getMoney() + money);
			dao.update(account);

			//2.���������¼
			Recode recode = new Recode();
			recode.setAccount_id(account.getId());
			recode.setCreatetime(DateUtil.getNow());
			recode.setInmoney(money);
			recode.setBalance(account.getMoney());
			
			RecodeDao recodeDao = new RecodeDao();
			recodeDao.save(recode);
		}
		
		
	}

	public void tranMoney(Account account, String id, Float money) {
		
		if(account.getId().equals(id)) {
			throw new ServiceException("ͬһ�˺Ų���ת��");
		} else {
			//1.ͨ��id��Account
			Account inAccount = dao.findById(id);
			if(inAccount == null) {
				throw new ServiceException("�˺Ų����ڣ����ʵ");
			} else {
				if(money > account.getMoney()) {
					throw new ServiceException("����");
				} else {
					//2.ת��
					account.setMoney(account.getMoney() - money);
					dao.update(account);
					
					inAccount.setMoney(inAccount.getMoney() + money);
					dao.update(inAccount);
					
					//3.��¼
					Recode outRecode = new Recode();
					outRecode.setAccount_id(account.getId());
					outRecode.setCreatetime(DateUtil.getNow());
					outRecode.setOutmoney(money);
					outRecode.setNote("��" + inAccount.getId() + "ת��" + money + "Ԫ");
					outRecode.setBalance(account.getMoney());
					
					RecodeDao recodeDao = new RecodeDao();
					recodeDao.save(outRecode);
					
					
					Recode inRecode = new Recode();
					inRecode.setAccount_id(inAccount.getId());
					inRecode.setCreatetime(DateUtil.getNow());
					inRecode.setInmoney(money);
					inRecode.setNote("��" + account.getId() + "ת��" + money + "Ԫ");
					inRecode.setBalance(inAccount.getMoney());
					
					recodeDao.save(inRecode);
					
				}
			}
		}
		
		
		
		
	}

}
