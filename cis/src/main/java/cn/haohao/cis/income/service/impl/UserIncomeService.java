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
import cn.haohao.cis.utils.Arith;
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
				income.setIncome(Arith.mul(inputObj.getIncome().floatValue(), user.getIncomeSetting().getProportion().floatValue()));
				income.setFromDownline(xIncome.getUserId());
				incomes.add(income);
			}
		} else {
			IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
			incomeSettingQueryObj.setRuleId(0);
			incomeSettingQueryObj.setStatus(1);
			IncomeSetting baseSetting = this.incomeSettingDao.queryByArgs(incomeSettingQueryObj).get(0);
			Float base = baseSetting.getProportion();
			User userB = null;
			for (int i = 0; i < userUplineList.size(); i++) {
				if( "B".equalsIgnoreCase(userUplineList.get(i).getLevel())){
					userB = userUplineList.get(i);
				} else {
					base = Arith.sub(base.floatValue(), userUplineList.get(i).getIncomeSetting().getProportion().floatValue());
					UserIncome income = new UserIncome();
					income.setFromIncomeId(incomeId);
					income.setIncomeDate(inputObj.getDate());
					income.setUserId(userUplineList.get(i).getUserId());
					income.setPerformance(inputObj.getIncome());
					income.setSettingId(userUplineList.get(i).getIncomeSetting().getSettingId());
					income.setIncome( Arith.mul(inputObj.getIncome().floatValue(), userUplineList.get(i).getIncomeSetting().getProportion().floatValue()) );
					if( i == 0 ){
						income.setFromDownline(xIncome.getUserId());
					} else {
						income.setFromDownline(userUplineList.get( i - 1 ).getUserId());
					}
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
				income.setIncome(Arith.mul(inputObj.getIncome().floatValue(), base.floatValue()));
				income.setFromDownline(userUplineList.get(userUplineList.size() - 2).getUserId());
				incomes.add(income);
			}
		}
		this.userIncomeDao.batchCreate(incomes);
	}
}