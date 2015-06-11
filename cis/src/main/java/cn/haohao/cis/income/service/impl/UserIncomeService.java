package cn.haohao.cis.income.service.impl;
//j-import-b
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.income.dao.IUserIncomeDao;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.vo.UserIncomeInputObj;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.income.vo.UserIncomeUpdateObj;
import cn.haohao.cis.rule.dao.IIncomeRuleDao;
import cn.haohao.cis.rule.dao.IIncomeSettingDao;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.vas.core.exception.BusinessException;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class UserIncomeService implements IUserIncomeService {
	
	@Autowired
	private IUserIncomeDao userIncomeDao;
	@Autowired
	private IIncomeRuleDao incomeRuleDao;
	@Autowired
	private IIncomeSettingDao incomeSettingDao;
	
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

	@Override
	public void createUserIncome(List<User> userUplineList,UserIncomeInputObj inputObj) {
		List<UserIncome> incomes = new ArrayList<UserIncome>();
		//x创收
		UserIncome xIncome = new UserIncome();
		xIncome.setPerformance(inputObj.getIncome());
		xIncome.setIncomeDate(inputObj.getDate());
		xIncome.setUserId(inputObj.getUserId());
		Integer incomeId = this.userIncomeDao.getSequence();
		incomeId = incomeId == null ? 1000 : incomeId;
		xIncome.setIncomeId(incomeId);
		incomes.add(xIncome);
		IncomeRule currentRule = this.incomeRuleDao.getById(inputObj.getRuleId());
		if( "BX".equals(currentRule.getDetailContent())){
			for (User user : userUplineList) {
				UserIncome income = new UserIncome();
				income.setFromIncomeId(incomeId);
				income.setIncomeDate(inputObj.getDate());
				income.setUserId(user.getUserId());
				income.setPerformance(inputObj.getIncome());
				income.setSettingId(user.getIncomeSetting().getSettingId());
				income.setIncome(inputObj.getIncome() * user.getIncomeSetting().getProportion());
				incomes.add(income);
			}
		} else {
			IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
			incomeSettingQueryObj.setRuleId(0);
			incomeSettingQueryObj.setStatus(1);
			IncomeSetting baseSetting = this.incomeSettingDao.queryByArgs(incomeSettingQueryObj).get(0);
			Float base = baseSetting.getProportion();
			User userB = null;
			for (User user : userUplineList) {
				if( "B".equalsIgnoreCase(user.getLevel())){
					userB = user;
				} else {
					base -= user.getIncomeSetting().getProportion();
					UserIncome income = new UserIncome();
					income.setFromIncomeId(incomeId);
					income.setIncomeDate(inputObj.getDate());
					income.setUserId(user.getUserId());
					income.setPerformance(inputObj.getIncome());
					income.setSettingId(user.getIncomeSetting().getSettingId());
					income.setIncome(inputObj.getIncome() * user.getIncomeSetting().getProportion());
					incomes.add(income);
				}
			}
			if(base <= 0) 
				throw new BusinessException("录入信息有误");
			else {
				UserIncome income = new UserIncome();
				income.setFromIncomeId(incomeId);
				income.setIncomeDate(inputObj.getDate());
				income.setUserId(userB.getUserId());
				income.setPerformance(inputObj.getIncome());
				income.setIncome(inputObj.getIncome() * base);
				incomes.add(income);
			}
		}
		this.userIncomeDao.batchCreate(incomes);
	}
}