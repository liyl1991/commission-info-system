package cn.haohao.cis.rule.dao.impl;
//j-import-b
import java.util.List;

import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.cis.rule.dao.IVUserIncomeSettingDao;

import org.springframework.stereotype.Repository;

import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VUserIncomeSettingDaoImpl extends BaseDaoMybatisImpl<VUserIncomeSetting> implements IVUserIncomeSettingDao {
	public VUserIncomeSettingDaoImpl(){
		this(VUserIncomeSetting.class);
	}
	public VUserIncomeSettingDaoImpl(Class<VUserIncomeSetting> type) {
		super(type);
	}
	@Override
	public Float getDownlineSpecialMax(VUserIncomeSettingQueryObj queryObj) {
		return this.sqlSessionTemplate.selectOne(this.getStatementName() + ".getDownlineSpecialMax", queryObj);
	}
	@Override
	public List<VUserIncomeSetting> queryUserSpecial(VUserIncomeSettingQueryObj queryObj) {
		return this.sqlSessionTemplate.selectList(this.getStatementName() + ".queryUserSpecial", queryObj);
	}
}