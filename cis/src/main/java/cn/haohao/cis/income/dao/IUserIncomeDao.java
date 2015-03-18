package cn.haohao.cis.income.dao;
import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.vas.core.dao.BaseDao;
/**
 *	DAO
 *
 */
public interface IUserIncomeDao extends BaseDao<UserIncome> {
	
	public UserIncome getIncomeSum(Integer userId);
	
}