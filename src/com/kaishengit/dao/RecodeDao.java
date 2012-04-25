package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Recode;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class RecodeDao {

	private DBHelp<Recode> db = new DBHelp<Recode>();
	
	public void save(Recode recode) {
		String sql = "INSERT INTO t_recode(account_id,createtime,inmoney,outmoney,note,balance) VALUES(?,?,?,?,?,?)";
		db.executeSQL(sql, recode.getAccount_id(),recode.getCreatetime(),recode.getInmoney(),recode.getOutmoney(),recode.getNote(),recode.getBalance());
	}

	public List<Recode> findAllByAccountId(String id) {
		String sql = "SELECT id,account_id,createtime,inmoney,outmoney,note,balance FROM t_recode where account_id = ? order by createtime desc";
		return db.executeQueryForList(sql, new RecodeRowMapper(), id);
	}
	
	private class RecodeRowMapper implements RowMapper<Recode> {

		public Recode mapperRow(ResultSet rs) throws SQLException {
			Recode r = new Recode();
			r.setAccount_id(rs.getString("account_id"));
			r.setBalance(rs.getFloat("balance"));
			r.setCreatetime(rs.getString("createtime"));
			r.setId(rs.getInt("id"));
			r.setInmoney(rs.getFloat("inmoney"));
			r.setNote(rs.getString("note"));
			r.setOutmoney(rs.getFloat("outmoney"));
			return r;
		}
		
	}
	
}
