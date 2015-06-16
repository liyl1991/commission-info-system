package cn.haohao.cis.rule.dao.impl;
//j-import-b
import cn.haohao.cis.rule.model.VspecialSetting;
import cn.haohao.cis.rule.dao.IVspecialSettingDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VspecialSettingDaoImpl extends BaseDaoMybatisImpl<VspecialSetting> implements IVspecialSettingDao {
	public VspecialSettingDaoImpl(){
		this(VspecialSetting.class);
	}
	public VspecialSettingDaoImpl(Class<VspecialSetting> type) {
		super(type);
	}
}