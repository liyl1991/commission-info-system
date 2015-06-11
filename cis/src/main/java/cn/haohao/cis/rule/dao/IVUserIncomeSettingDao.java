package cn.haohao.cis.rule.dao;

import java.util.List;

import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.vas.core.dao.BaseDao;
/**
 *	DAO
 *
 */
public interface IVUserIncomeSettingDao extends BaseDao<VUserIncomeSetting> {
	Float getDownlineSpecialMax(VUserIncomeSettingQueryObj queryObj);
	
	List<VUserIncomeSetting> queryUserSpecial(VUserIncomeSettingQueryObj queryObj);
}