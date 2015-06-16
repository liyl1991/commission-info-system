package cn.haohao.cis.income.dao;
import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.vas.core.dao.BaseDao;
/**
 *	DAO
 *
 */
public interface IVuserIncomeDao extends BaseDao<VuserIncome> {
	VuserIncome getIncomeSum(Integer userId);
}