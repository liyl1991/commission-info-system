package cn.haohao.cis.income.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.income.dao.IUserIncomeDao;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.income.vo.UserIncomeUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class UserIncomeService implements IUserIncomeService {
	
	@Autowired
	private IUserIncomeDao userIncomeDao;
	
	public void batchDeleteUserIncome(List<UserIncome> userIncomeList) {
		userIncomeDao.batchDelete(userIncomeList);
	}

	public UserIncome createUserIncome(UserIncome userIncome) {
		userIncomeDao.create(userIncome);
		return userIncome;
	}

	public void deleteUserIncome(UserIncomeQueryObj userIncomeQueryObj) {
		userIncomeDao.delete(userIncomeQueryObj);
	}

	public UserIncome getUserIncomeById(Long userIncomeId) {
		return userIncomeDao.getById(userIncomeId);
	}

	public UserIncome modifyUserIncome(UserIncome userIncome) {
		userIncomeDao.update(userIncome);
		return userIncome;
	}

	public Page<UserIncome> pageQueryUserIncome(UserIncomeQueryObj userIncomequeryObj) {
		return userIncomeDao.pageCountByArgs(userIncomequeryObj);
	}
	
	public List<UserIncome> queryUserIncome(UserIncomeQueryObj userIncomequeryObj) {
		return userIncomeDao.queryByArgs(userIncomequeryObj);
	}
	
	public void updateDynamic(UserIncomeUpdateObj updateObj){
		userIncomeDao.updateDynamic(updateObj);
	}
	public Long countByArgs(UserIncomeQueryObj queryObj){
		return userIncomeDao.countByArgs(queryObj);
	}

	@Override
	public UserIncome getIncomeSum(Integer userId) {
		return this.userIncomeDao.getIncomeSum(userId);
	}
}