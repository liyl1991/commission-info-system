package cn.haohao.cis.user.service.impl;
//j-import-b
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.audit.dao.IAuditInfoDao;
import cn.haohao.cis.audit.model.AuditInfo;
import cn.haohao.cis.audit.vo.AuditInfoUpdateObj;
import cn.haohao.cis.user.dao.IUserDao;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.user.vo.UserUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IAuditInfoDao auditInfoDao;
	
	public void batchDeleteUser(List<User> userList) {
		userDao.batchDelete(userList);
	}

	public User createUser(User user) {
		user.setUserId(userDao.getSequence());
		userDao.create(user);
		return user;
	}
	
	public User createUserForRegister(User user) {
		userDao.create(user);
		//创建审核信息
		UserQueryObj userQueryObj = new UserQueryObj();
		userQueryObj.setIdCard(user.getIdCard());
		user = this.userDao.queryByArgs(userQueryObj).get(0);
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setUserId(user.getUserId());
		auditInfo.setRegisterDate(new Date());
		auditInfo.setAuditStatus(3);//默认状态，等待
		this.auditInfoDao.create(auditInfo);
		return user;
	}

	public void deleteUser(UserQueryObj userQueryObj) {
		userDao.delete(userQueryObj);
	}

	public User getUserById(Integer userId) {
		return userDao.getById(userId);
	}

	public User modifyUser(User user) {
		userDao.update(user);
		return user;
	}

	public Page<User> pageQueryUser(UserQueryObj userqueryObj) {
		return userDao.pageCountByArgs(userqueryObj);
	}
	
	public List<User> queryUser(UserQueryObj userqueryObj) {
		return userDao.queryByArgs(userqueryObj);
	}
	
	public void updateDynamic(UserUpdateObj updateObj){
		userDao.updateDynamic(updateObj);
	}
	public Long countByArgs(UserQueryObj queryObj){
		return userDao.countByArgs(queryObj);
	}

	@Override
	public void updateAndPassAudit(UserUpdateObj updateObj,User adminInfo) {
		//修改审核状态
		AuditInfoUpdateObj auditInfoUpdateObj = new AuditInfoUpdateObj();
		auditInfoUpdateObj.setUserId(updateObj.getUserId());
		auditInfoUpdateObj.getNewUpdAttObj().setAuditStatus(1);
		auditInfoUpdateObj.getNewUpdAttObj().setAuditDate(new Date());
		auditInfoUpdateObj.getNewUpdAttObj().setAuditAdmin(adminInfo.getUserId());
		this.auditInfoDao.updateDynamic(auditInfoUpdateObj);
		//修改员工状态
		updateObj.setUserId(updateObj.getUserId());
		updateObj.getNewUpdAttObj().setStatus(1);
		this.userDao.updateDynamic(updateObj);
		
	}
}