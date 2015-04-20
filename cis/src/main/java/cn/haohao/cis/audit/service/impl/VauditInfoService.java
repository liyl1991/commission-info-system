package cn.haohao.cis.audit.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import cn.haohao.cis.audit.dao.IVauditInfoDao;
import cn.haohao.cis.audit.service.IVauditInfoService;
import cn.haohao.cis.audit.model.VauditInfo;
import cn.haohao.cis.audit.vo.VauditInfoQueryObj;
import cn.haohao.cis.audit.vo.VauditInfoUpdateObj;
import cn.haohao.vas.core.exception.BusinessException;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class VauditInfoService implements IVauditInfoService {
	
	@Autowired
	private IVauditInfoDao vauditInfoDao;
	
	public void batchDeleteVauditInfo(List<VauditInfo> vauditInfoList) {
		vauditInfoDao.batchDelete(vauditInfoList);
	}

	public VauditInfo createVauditInfo(VauditInfo vauditInfo) {
		vauditInfoDao.create(vauditInfo);
		return vauditInfo;
	}

	public void deleteVauditInfo(VauditInfoQueryObj vauditInfoQueryObj) {
		vauditInfoDao.delete(vauditInfoQueryObj);
	}

	public VauditInfo getVauditInfoById(Long vauditInfoId) {
		return vauditInfoDao.getById(vauditInfoId);
	}

	public VauditInfo modifyVauditInfo(VauditInfo vauditInfo) {
		vauditInfoDao.update(vauditInfo);
		return vauditInfo;
	}

	public Page<VauditInfo> pageQueryVauditInfo(VauditInfoQueryObj vauditInfoqueryObj) {
		return vauditInfoDao.pageCountByArgs(vauditInfoqueryObj);
	}
	
	public List<VauditInfo> queryVauditInfo(VauditInfoQueryObj vauditInfoqueryObj) {
		return vauditInfoDao.queryByArgs(vauditInfoqueryObj);
	}
	
	public void updateDynamic(VauditInfoUpdateObj updateObj){
		vauditInfoDao.updateDynamic(updateObj);
	}
	public Long countByArgs(VauditInfoQueryObj queryObj){
		return vauditInfoDao.countByArgs(queryObj);
	}
}