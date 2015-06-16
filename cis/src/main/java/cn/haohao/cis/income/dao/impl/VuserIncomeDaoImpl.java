package cn.haohao.cis.income.dao.impl;
//j-import-b
import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.cis.income.dao.IVuserIncomeDao;

import org.springframework.stereotype.Repository;

import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VuserIncomeDaoImpl extends BaseDaoMybatisImpl<VuserIncome> implements IVuserIncomeDao {
	public VuserIncomeDaoImpl(){
		this(VuserIncome.class);
	}
	public VuserIncomeDaoImpl(Class<VuserIncome> type) {
		super(type);
	}
	@Override
	public VuserIncome getIncomeSum(Integer userId) {
		return this.sqlSessionTemplate.selectOne(this.getStatementName() + ".getIncomeSum",userId);
	}
}