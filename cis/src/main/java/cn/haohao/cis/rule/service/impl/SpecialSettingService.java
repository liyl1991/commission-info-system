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
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class SpecialSettingService implements ISpecialSettingService {
	
	@Autowired
	private ISpecialSettingDao specialSettingDao;
	
	public void batchDeleteSpecialSetting(List<SpecialSetting> specialSettingList) {
		specialSettingDao.batchDelete(specialSettingList);
	}

	public SpecialSetting createSpecialSetting(SpecialSetting specialSetting) {
		specialSettingDao.create(specialSetting);
		return specialSetting;
	}

	public void deleteSpecialSetting(SpecialSettingQueryObj specialSettingQueryObj) {
		specialSettingDao.delete(specialSettingQueryObj);
	}

	public SpecialSetting getSpecialSettingById(Integer specialSettingId) {
		return specialSettingDao.getById(specialSettingId);
	}

	public SpecialSetting modifySpecialSetting(SpecialSetting specialSetting) {
		specialSettingDao.update(specialSetting);
		return specialSetting;
	}

	public Page<SpecialSetting> pageQuerySpecialSetting(SpecialSettingQueryObj specialSettingqueryObj) {
		return specialSettingDao.pageCountByArgs(specialSettingqueryObj);
	}
	
	public List<SpecialSetting> querySpecialSetting(SpecialSettingQueryObj specialSettingqueryObj) {
		return specialSettingDao.queryByArgs(specialSettingqueryObj);
	}
	
	public void updateDynamic(SpecialSettingUpdateObj updateObj){
		specialSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(SpecialSettingQueryObj queryObj){
		return specialSettingDao.countByArgs(queryObj);
	}
}