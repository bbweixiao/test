package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kaishengit.entity.Account;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class AccountDao {

	private DBHelp<Account> db = new DBHelp<Account>();

	public Account findByUserNameAndPassword(String id, String password) {
		String sql = "SELECT id,username,`password`,money,`enable` FROM t_account WHERE id = ? AND `password` = ?";
		return db.executeQueryForObject(sql, new AccountRowMapper(), id,password);
	}
	
	
	private class AccountRowMapper implements RowMapper<Account> {

		public Account mapperRow(ResultSet rs) throws SQLException {
			Account a = new Account();
			a.setEnable(rs.getBoolean("enable"));
			a.setId(rs.getString("id"));
			a.setMoney(rs.getFloat("money"));
			a.setPassword(rs.getString("password"));
			a.setUsername(rs.getString("username"));
			return a;
		}
		
	}


	public void update(Account account) {
		String sql = "update t_account set username = ?,password=?,enable=?,money=? where id = ?";
		db.executeSQL(sql, account.getUsername(),account.getPassword(),account.isEnable(),account.getMoney(),account.getId());
	}


	public Account findById(String id) {
		String sql = "SELECT id,username,`password`,money,`enable` FROM t_account WHERE id = ?";
		return db.executeQueryForObject(sql, new AccountRowMapper(), id);
	}
}
