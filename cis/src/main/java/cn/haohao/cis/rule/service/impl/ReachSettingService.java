package cn.haohao.cis.rule.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.rule.dao.IReachSettingDao;
import cn.haohao.cis.rule.service.IReachSettingService;
import cn.haohao.cis.rule.model.ReachSetting;
import cn.haohao.cis.rule.vo.ReachSettingQueryObj;
import cn.haohao.cis.rule.vo.ReachSettingUpdateObj;
import cn.haohao.cis.utils.BaseUtils;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class ReachSettingService implements IReachSettingService {
	
	@Autowired
	private IReachSettingDao reachSettingDao;
	
	public void batchDeleteReachSetting(List<ReachSetting> reachSettingList) {
		reachSettingDao.batchDelete(reachSettingList);
	}

	public ReachSetting createReachSetting(ReachSetting reachSetting) {
		reachSettingDao.create(reachSetting);
		return reachSetting;
	}

	public void deleteReachSetting(ReachSettingQueryObj reachSettingQueryObj) {
		reachSettingDao.delete(reachSettingQueryObj);
	}

	public ReachSetting getReachSettingById(Integer reachSettingId) {
		return reachSettingDao.getById(reachSettingId);
	}

	public ReachSetting modifyReachSetting(ReachSetting reachSetting) {
		reachSettingDao.update(reachSetting);
		return reachSetting;
	}

	public Page<ReachSetting> pageQueryReachSetting(ReachSettingQueryObj reachSettingqueryObj) {
		return reachSettingDao.pageCountByArgs(reachSettingqueryObj);
	}
	
	public List<ReachSetting> queryReachSetting(ReachSettingQueryObj reachSettingqueryObj) {
		return reachSettingDao.queryByArgs(reachSettingqueryObj);
	}
	
	public void updateDynamic(ReachSettingUpdateObj updateObj){
		reachSettingDao.updateDynamic(updateObj);
	}
	public Long countByArgs(ReachSettingQueryObj queryObj){
		return reachSettingDao.countByArgs(queryObj);
	}

	@Override
	public void updateDynamic(ReachSetting oldSetting, Float modifyData) {
		ReachSettingUpdateObj updateObj = new ReachSettingUpdateObj();
		updateObj.setReachId(oldSetting.getReachId());
		oldSetting.setEndDate(BaseUtils.getFirstDayOnCurrentMonth());
		oldSetting.setStatus(2);
		updateObj.setNewUpdAttObj(oldSetting);
		this.reachSettingDao.updateDynamic(updateObj);
		
		ReachSetting newSetting = new ReachSetting();
		newSetting.setUsingDate(BaseUtils.getFirstDayOnCurrentMonth());
		newSetting.setSettingLevel(oldSetting.getSettingLevel());
		newSetting.setReachPerformance(modifyData);
		newSetting.setStatus(1);
		newSetting.setType(1);
		this.reachSettingDao.create(newSetting);
		
	}
}