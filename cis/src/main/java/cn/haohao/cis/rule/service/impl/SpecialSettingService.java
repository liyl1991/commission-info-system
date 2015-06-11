package cn.haohao.cis.rule.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.dao.ISpecialSettingDao;
import cn.haohao.cis.rule.service.ISpecialSettingService;
import cn.haohao.cis.rule.model.SpecialSetting;
import cn.haohao.cis.rule.vo.SpecialSettingQueryObj;
import cn.haohao.cis.rule.vo.SpecialSettingUpdateObj;
import cn.haohao.vas.core.exception.BusinessException;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class SpecialSettingService implements ISpecialSettingService {
	
	@Autowired
	private ISpecialSettingDao sepcialSettingDao;
	
	public void batchDeleteSepcialSetting(List<SpecialSetting> sepcialSettingList) {
		sepcialSettingDao.batchDelete(sepcialSettingList);
	}

	public SpecialSetting createSepcialSetting(SpecialSetting sepcialSetting) {
		sepcialSettingDao.create(sepcialSetting);
		return sepcialSetting;
	}

	public void deleteSepcialSetting(SpecialSettingQueryObj sepcialSettingQueryObj) {
		sepcialSettingDao.delete(sepcialSettingQueryObj);
	}

	public SpecialSetting getSepcialSettingById(Long sepcialSettingId) {
		return sepcialSettingDao.getById(sepcialSettingId);
	}

	public SpecialSetting modifySepcialSetting(SpecialSetting sepcialSetting) {
		sepcialSettingDao.update(sepcialSetting);
		return sepcialSetting;
	}

	public Page<SpecialSetting> pageQuerySepcialSetting(SpecialSettingQueryObj sepcialSettingqueryObj) {
		return sepcialSettingDao.pageCountByArgs(sepcialSettingqueryObj);
	}
	
	public List<SpecialSetting> querySepcialSetting(SpecialSettingQueryObj sepcialSettingqueryObj) {
		return sepcialSettingDao.queryByArgs(sepcialSettingqueryObj);
	}
	
	public void updateDynamic(SpecialSettingUpdateObj updateObj){
		sepcialSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(SpecialSettingQueryObj queryObj){
		return sepcialSettingDao.countByArgs(queryObj);
	}
}