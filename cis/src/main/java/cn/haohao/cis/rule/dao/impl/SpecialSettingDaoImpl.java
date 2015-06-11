package cn.haohao.cis.rule.dao.impl;
//j-import-b
import cn.haohao.cis.rule.model.SpecialSetting;
import cn.haohao.cis.rule.dao.ISpecialSettingDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class SpecialSettingDaoImpl extends BaseDaoMybatisImpl<SpecialSetting> implements ISpecialSettingDao {
	public SpecialSettingDaoImpl(){
		this(SpecialSetting.class);
	}
	public SpecialSettingDaoImpl(Class<SpecialSetting> type) {
		super(type);
	}
}