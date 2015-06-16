package cn.haohao.cis.income.dao.impl;
//j-import-b
import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.dao.IUserIncomeDao;

import org.springframework.stereotype.Repository;

import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class UserIncomeDaoImpl extends BaseDaoMybatisImpl<UserIncome> implements IUserIncomeDao {
	public UserIncomeDaoImpl(){
		this(UserIncome.class);
	}
	public UserIncomeDaoImpl(Class<UserIncome> type) {
		super(type);
	}
}