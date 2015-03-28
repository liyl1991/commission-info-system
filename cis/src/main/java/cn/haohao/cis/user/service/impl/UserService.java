package cn.haohao.cis.user.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
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
	
	public void batchDeleteUser(List<User> userList) {
		userDao.batchDelete(userList);
	}

	public User createUser(User user) {
		userDao.create(user);
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
}