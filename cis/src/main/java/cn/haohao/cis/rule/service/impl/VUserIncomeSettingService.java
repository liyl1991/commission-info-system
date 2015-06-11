package cn.haohao.cis.rule.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.rule.dao.IVUserIncomeSettingDao;
import cn.haohao.cis.rule.service.IVUserIncomeSettingService;
import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.VUserIncomeSettingUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class VUserIncomeSettingService implements IVUserIncomeSettingService {
	
	@Autowired
	private IVUserIncomeSettingDao vUserIncomeSettingDao;
	
	public void batchDeleteVUserIncomeSetting(List<VUserIncomeSetting> vUserIncomeSettingList) {
		vUserIncomeSettingDao.batchDelete(vUserIncomeSettingList);
	}

	public VUserIncomeSetting createVUserIncomeSetting(VUserIncomeSetting vUserIncomeSetting) {
		vUserIncomeSettingDao.create(vUserIncomeSetting);
		return vUserIncomeSetting;
	}

	public void deleteVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj) {
		vUserIncomeSettingDao.delete(vUserIncomeSettingQueryObj);
	}

	public VUserIncomeSetting getVUserIncomeSettingById(Long vUserIncomeSettingId) {
		return vUserIncomeSettingDao.getById(vUserIncomeSettingId);
	}

	public VUserIncomeSetting modifyVUserIncomeSetting(VUserIncomeSetting vUserIncomeSetting) {
		vUserIncomeSettingDao.update(vUserIncomeSetting);
		return vUserIncomeSetting;
	}

	public Page<VUserIncomeSetting> pageQueryVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingqueryObj) {
		return vUserIncomeSettingDao.pageCountByArgs(vUserIncomeSettingqueryObj);
	}
	
	public List<VUserIncomeSetting> queryVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingqueryObj) {
		return vUserIncomeSettingDao.queryByArgs(vUserIncomeSettingqueryObj);
	}
	
	public void updateDynamic(VUserIncomeSettingUpdateObj updateObj){
		vUserIncomeSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(VUserIncomeSettingQueryObj queryObj){
		return vUserIncomeSettingDao.countByArgs(queryObj);
	}

	@Override
	public Float getDownlineSpecialMax(VUserIncomeSettingQueryObj queryObj) {
		return this.vUserIncomeSettingDao.getDownlineSpecialMax(queryObj);
	}

	@Override
	public List<VUserIncomeSetting> queryUserSpecial(VUserIncomeSettingQueryObj queryObj) {
		return this.vUserIncomeSettingDao.queryUserSpecial(queryObj);
	}
}