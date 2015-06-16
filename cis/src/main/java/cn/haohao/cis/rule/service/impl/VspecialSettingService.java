package cn.haohao.cis.rule.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.dao.IVspecialSettingDao;
import cn.haohao.cis.rule.service.IVspecialSettingService;
import cn.haohao.cis.rule.model.VspecialSetting;
import cn.haohao.cis.rule.vo.VspecialSettingQueryObj;
import cn.haohao.cis.rule.vo.VspecialSettingUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class VspecialSettingService implements IVspecialSettingService {
	
	@Autowired
	private IVspecialSettingDao vspecialSettingDao;
	
	public void batchDeleteVspecialSetting(List<VspecialSetting> vspecialSettingList) {
		vspecialSettingDao.batchDelete(vspecialSettingList);
	}

	public VspecialSetting createVspecialSetting(VspecialSetting vspecialSetting) {
		vspecialSettingDao.create(vspecialSetting);
		return vspecialSetting;
	}

	public void deleteVspecialSetting(VspecialSettingQueryObj vspecialSettingQueryObj) {
		vspecialSettingDao.delete(vspecialSettingQueryObj);
	}

	public VspecialSetting getVspecialSettingById(Long vspecialSettingId) {
		return vspecialSettingDao.getById(vspecialSettingId);
	}

	public VspecialSetting modifyVspecialSetting(VspecialSetting vspecialSetting) {
		vspecialSettingDao.update(vspecialSetting);
		return vspecialSetting;
	}

	public Page<VspecialSetting> pageQueryVspecialSetting(VspecialSettingQueryObj vspecialSettingqueryObj) {
		return vspecialSettingDao.pageCountByArgs(vspecialSettingqueryObj);
	}
	
	public List<VspecialSetting> queryVspecialSetting(VspecialSettingQueryObj vspecialSettingqueryObj) {
		return vspecialSettingDao.queryByArgs(vspecialSettingqueryObj);
	}
	
	public void updateDynamic(VspecialSettingUpdateObj updateObj){
		vspecialSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(VspecialSettingQueryObj queryObj){
		return vspecialSettingDao.countByArgs(queryObj);
	}
}