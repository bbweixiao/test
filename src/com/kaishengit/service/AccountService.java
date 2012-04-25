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
		
		//1.减去账号中的金额
		//?钱不够
		if(money < account.getMoney()) {
			account.setMoney(account.getMoney() - money);
			dao.update(account);
			//2.插入操作记录
			Recode recode = new Recode();
			recode.setAccount_id(account.getId());
			recode.setCreatetime(DateUtil.getNow());
			recode.setOutmoney(money);
			recode.setBalance(account.getMoney());
			
			RecodeDao recodeDao = new RecodeDao();
			recodeDao.save(recode);
		} else {
			throw new ServiceException("余额不足");
		}
		
		
	}

	public void save(Account account, float money) {
		
		if(money <= 0) {
			throw new ServiceException("金额不能为负数或0");
		} else {
			account.setMoney(account.getMoney() + money);
			dao.update(account);

			//2.插入操作记录
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
			throw new ServiceException("同一账号不能转账");
		} else {
			//1.通过id找Account
			Account inAccount = dao.findById(id);
			if(inAccount == null) {
				throw new ServiceException("账号不存在，请核实");
			} else {
				if(money > account.getMoney()) {
					throw new ServiceException("余额不足");
				} else {
					//2.转账
					account.setMoney(account.getMoney() - money);
					dao.update(account);
					
					inAccount.setMoney(inAccount.getMoney() + money);
					dao.update(inAccount);
					
					//3.记录
					Recode outRecode = new Recode();
					outRecode.setAccount_id(account.getId());
					outRecode.setCreatetime(DateUtil.getNow());
					outRecode.setOutmoney(money);
					outRecode.setNote("向" + inAccount.getId() + "转出" + money + "元");
					outRecode.setBalance(account.getMoney());
					
					RecodeDao recodeDao = new RecodeDao();
					recodeDao.save(outRecode);
					
					
					Recode inRecode = new Recode();
					inRecode.setAccount_id(inAccount.getId());
					inRecode.setCreatetime(DateUtil.getNow());
					inRecode.setInmoney(money);
					inRecode.setNote("由" + account.getId() + "转入" + money + "元");
					inRecode.setBalance(inAccount.getMoney());
					
					recodeDao.save(inRecode);
					
				}
			}
		}
		
		
		
		
	}

}
