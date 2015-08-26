package cn.haohao.cis.rule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.rule.dao.IIncomeSettingDao;
import cn.haohao.cis.rule.dao.ISpecialSettingDao;
import cn.haohao.cis.rule.service.IIncomeSettingService;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.model.SpecialSetting;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingUpdateObj;
import cn.haohao.cis.utils.BaseUtils;

@Service
public class IncomeSettingService implements IIncomeSettingService {
	
	@Autowired
	private IIncomeSettingDao incomeSettingDao;
	@Autowired
	private ISpecialSettingDao specialSettingDao;
	
	public void batchDeleteIncomeSetting(List<IncomeSetting> incomeSettingList) {
		incomeSettingDao.batchDelete(incomeSettingList);
	}

	public IncomeSetting createIncomeSetting(IncomeSetting incomeSetting) {
		incomeSettingDao.create(incomeSetting);
		return incomeSetting;
	}

	public void deleteIncomeSetting(IncomeSettingQueryObj incomeSettingQueryObj) {
		incomeSettingDao.delete(incomeSettingQueryObj);
	}

	public IncomeSetting getIncomeSettingById(Integer incomeSettingId) {
		return incomeSettingDao.getById(incomeSettingId);
	}

	public IncomeSetting modifyIncomeSetting(IncomeSetting incomeSetting) {
		incomeSettingDao.update(incomeSetting);
		return incomeSetting;
	}

	public Page<IncomeSetting> pageQueryIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj) {
		return incomeSettingDao.pageCountByArgs(incomeSettingqueryObj);
	}
	
	public List<IncomeSetting> queryIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj) {
		return incomeSettingDao.queryByArgs(incomeSettingqueryObj);
	}
	
	public void updateDynamic(IncomeSettingUpdateObj updateObj){
		incomeSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(IncomeSettingQueryObj queryObj){
		return incomeSettingDao.countByArgs(queryObj);
	}

	@Override
	public void updateDynamic(IncomeSettingUpdateObj updateObj, IncomeSetting newSetting) {
		this.incomeSettingDao.updateDynamic(updateObj);
		this.incomeSettingDao.create(newSetting);
	}

	@Override
	public IncomeSetting getIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj) {
		List<IncomeSetting> list = incomeSettingDao.queryByArgs(incomeSettingqueryObj);
		return (list != null && list.size() != 0)?list.get(0):null;
	}

	@Override
	public IncomeSetting createIncomeSetting(IncomeSetting incomeSetting,Integer userId) {
		Integer settingId = this.incomeSettingDao.getSequence();
		incomeSetting.setSettingId(settingId);
		this.incomeSettingDao.create(incomeSetting);
		SpecialSetting specialSetting = new SpecialSetting();
		specialSetting.setSettingId(settingId);
		specialSetting.setUserId(userId);
		specialSetting.setType(1);
		this.specialSettingDao.create(specialSetting);
		return incomeSetting;
	}

	@Override
	public void updateAndCreate(IncomeSetting incomeSetting, Integer userId) {
		IncomeSettingUpdateObj updateObj = new IncomeSettingUpdateObj();
		updateObj.setSettingId(incomeSetting.getSettingId());
		updateObj.getNewUpdAttObj().setStatus(2);
		updateObj.getNewUpdAttObj().setEndDate(BaseUtils.getFirstDayOnCurrentMonth());
		this.incomeSettingDao.updateDynamic(updateObj);
		/*SpecialSettingQueryObj specialSettingQueryObj = new SpecialSettingQueryObj();
		specialSettingQueryObj.setSettingId(incomeSetting.getSettingId());
		specialSettingQueryObj.setUserId(userId);
		specialSettingQueryObj.setType(1);
		this.specialSettingDao.delete(specialSettingQueryObj);*/
		
		Integer settingId = this.incomeSettingDao.getSequence();
		incomeSetting.setSettingId(settingId);
		incomeSetting.setUsingDate(BaseUtils.getFirstDayOnCurrentMonth());
		this.incomeSettingDao.create(incomeSetting);
		SpecialSetting newSpecialSetting = new SpecialSetting();
		newSpecialSetting.setSettingId(settingId);
		newSpecialSetting.setUserId(userId);
		newSpecialSetting.setType(1);
		this.specialSettingDao.create(newSpecialSetting);
	}

	@Override
	public void deleteSpecialIncomeSetting(IncomeSetting incomeSetting, Integer userId) {
		SpecialSetting delObj = new SpecialSetting();
		delObj.setUserId(userId);
		delObj.setSettingId(incomeSetting.getSettingId());
		delObj.setType(1);
		this.specialSettingDao.delete(delObj);
		
		this.incomeSettingDao.delete(incomeSetting);
	}

	@Override
	public void updateAndDelete(IncomeSetting incomeSetting, Integer userId) {
		IncomeSettingUpdateObj updateObj = new IncomeSettingUpdateObj();
		updateObj.setSettingId(incomeSetting.getSettingId());
		updateObj.getNewUpdAttObj().setEndDate(BaseUtils.getFirstDayOnCurrentMonth());
		updateObj.getNewUpdAttObj().setStatus(2);
		this.incomeSettingDao.updateDynamic(updateObj);
		
		/*SpecialSetting delObj = new SpecialSetting();
		delObj.setUserId(userId);
		delObj.setSettingId(incomeSetting.getSettingId());
		delObj.setType(1);
		this.specialSettingDao.delete(delObj);*/
		
	}

	@Override
	public void updateDynamic(IncomeSettingUpdateObj updateObj1,IncomeSettingUpdateObj updateObj2) {
		this.incomeSettingDao.updateDynamic(updateObj1);
		this.incomeSettingDao.updateDynamic(updateObj2);
		IncomeSettingQueryObj delQueryObj = new IncomeSettingQueryObj();
		delQueryObj.setUsingEndDateEq(true);
		this.incomeSettingDao.delete(delQueryObj);
	}

}