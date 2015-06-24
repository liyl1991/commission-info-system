package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;

import org.springframework.data.domain.Page;

import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IIncomeSettingService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param incomeSetting
	 * @return
	 */
	public IncomeSetting createIncomeSetting(IncomeSetting incomeSetting);
	
	public IncomeSetting createIncomeSetting(IncomeSetting incomeSetting, Integer userId);
	
	public void updateAndCreate(IncomeSetting incomeSetting,Integer userId);
	
	public void updateAndDelete(IncomeSetting incomeSetting,Integer userId);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param incomeSetting
	 * @return
	 */
	public IncomeSetting modifyIncomeSetting(IncomeSetting incomeSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param incomeSetting
	 * @return
	 */
	public void deleteIncomeSetting(IncomeSettingQueryObj incomeSettingQueryObj);
	
	public void deleteSpecialIncomeSetting(IncomeSetting incomeSetting, Integer userId);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param incomeSettingList
	 * @return
	 */
	public void batchDeleteIncomeSetting(List<IncomeSetting> incomeSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param incomeSettingId
	 * @return
	 */
	public IncomeSetting getIncomeSettingById(Integer incomeSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param incomeSettingqueryObj
	 * @return
	 */
	public Page<IncomeSetting> pageQueryIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param incomeSettingqueryObj
	 * @return
	 */
	public List<IncomeSetting> queryIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj);
	
	public IncomeSetting getIncomeSetting(IncomeSettingQueryObj incomeSettingqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(IncomeSettingUpdateObj updateObj);
	
	public void updateDynamic(IncomeSettingUpdateObj updateObj1,IncomeSettingUpdateObj updateObj2);
	
	public void updateDynamic(IncomeSettingUpdateObj updateObj,IncomeSetting newSetting);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(IncomeSettingQueryObj queryObj);
}