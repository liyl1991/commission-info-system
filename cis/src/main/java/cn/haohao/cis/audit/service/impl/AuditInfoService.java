package cn.haohao.cis.audit.service.impl;
//j-import-b
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.audit.dao.IAuditInfoDao;
import cn.haohao.cis.audit.service.IAuditInfoService;
import cn.haohao.cis.audit.model.AuditInfo;
import cn.haohao.cis.audit.vo.AuditInfoQueryObj;
import cn.haohao.cis.audit.vo.AuditInfoUpdateObj;
import cn.haohao.cis.user.dao.IUserDao;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserUpdateObj;
import cn.haohao.vas.core.exception.BusinessException;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class AuditInfoService implements IAuditInfoService {
	
	@Autowired
	private IAuditInfoDao auditInfoDao;
	@Autowired
	private IUserDao userDao;
	
	public void batchDeleteAuditInfo(List<AuditInfo> auditInfoList) {
		auditInfoDao.batchDelete(auditInfoList);
	}

	public AuditInfo createAuditInfo(AuditInfo auditInfo) {
		auditInfoDao.create(auditInfo);
		return auditInfo;
	}

	public void deleteAuditInfo(AuditInfoQueryObj auditInfoQueryObj) {
		auditInfoDao.delete(auditInfoQueryObj);
	}

	public AuditInfo getAuditInfoById(Integer auditInfoId) {
		return auditInfoDao.getById(auditInfoId);
	}

	public AuditInfo modifyAuditInfo(AuditInfo auditInfo) {
		auditInfoDao.update(auditInfo);
		return auditInfo;
	}

	public Page<AuditInfo> pageQueryAuditInfo(AuditInfoQueryObj auditInfoqueryObj) {
		return auditInfoDao.pageCountByArgs(auditInfoqueryObj);
	}
	
	public List<AuditInfo> queryAuditInfo(AuditInfoQueryObj auditInfoqueryObj) {
		return auditInfoDao.queryByArgs(auditInfoqueryObj);
	}
	
	public void updateDynamic(AuditInfoUpdateObj updateObj){
		auditInfoDao.updateDynamic(updateObj);
	}
	public Long countByArgs(AuditInfoQueryObj queryObj){
		return auditInfoDao.countByArgs(queryObj);
	}

	public void passAudit(Integer userId,User adminInfo) {
		//修改审核状态
		AuditInfoUpdateObj auditInfoUpdateObj = new AuditInfoUpdateObj();
		auditInfoUpdateObj.setUserId(userId);
		auditInfoUpdateObj.getNewUpdAttObj().setAuditStatus(1);
		auditInfoUpdateObj.getNewUpdAttObj().setAuditDate(new Date());
		auditInfoUpdateObj.getNewUpdAttObj().setAuditAdmin(adminInfo.getUserId());
		this.auditInfoDao.updateDynamic(auditInfoUpdateObj);
		//修改员工状态
		UserUpdateObj userUpdateObj = new UserUpdateObj();
		userUpdateObj.setUserId(userId);
		userUpdateObj.getNewUpdAttObj().setStatus(1);
		this.userDao.updateDynamic(userUpdateObj);
	}
}