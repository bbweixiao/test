package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.RecodeDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.Recode;

public class RecodeService {

	private RecodeDao dao = new RecodeDao();
	
	public List<Recode> findRecodeByAccount(Account a) {
		return dao.findAllByAccountId(a.getId());
	}

}
