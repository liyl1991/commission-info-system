package cn.haohao.cis.income.service;
//j-import-b
import java.util.List;

import org.springframework.data.domain.Page;

import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.vo.UserIncomeInputObj;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.income.vo.UserIncomeUpdateObj;
import cn.haohao.cis.user.model.User;
//j-import-e
/**
 *	SERVICE
 */
public interface IUserIncomeService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param userIncome
	 * @return
	 */
	public UserIncome createUserIncome(UserIncome userIncome);
	
	public void createUserIncome(List<User> userUplineList, UserIncomeInputObj inputObj);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param userIncome
	 * @return
	 */
	public UserIncome modifyUserIncome(UserIncome userIncome);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param userIncome
	 * @return
	 */
	public void deleteUserIncome(UserIncomeQueryObj userIncomeQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param userIncomeList
	 * @return
	 */
	public void batchDeleteUserIncome(List<UserIncome> userIncomeList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param userIncomeId
	 * @return
	 */
	public UserIncome getUserIncomeById(Long userIncomeId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param userIncomequeryObj
	 * @return
	 */
	public Page<UserIncome> pageQueryUserIncome(UserIncomeQueryObj userIncomequeryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param userIncomequeryObj
	 * @return
	 */
	public List<UserIncome> queryUserIncome(UserIncomeQueryObj userIncomequeryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(UserIncomeUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(UserIncomeQueryObj queryObj);
	
	public UserIncome getIncomeSum(Integer userId);
}