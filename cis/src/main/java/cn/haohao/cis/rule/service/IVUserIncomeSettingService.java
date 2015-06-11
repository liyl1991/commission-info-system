package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;

import org.springframework.data.domain.Page;

import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.VUserIncomeSettingUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IVUserIncomeSettingService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param vUserIncomeSetting
	 * @return
	 */
	public VUserIncomeSetting createVUserIncomeSetting(VUserIncomeSetting vUserIncomeSetting);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param vUserIncomeSetting
	 * @return
	 */
	public VUserIncomeSetting modifyVUserIncomeSetting(VUserIncomeSetting vUserIncomeSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param vUserIncomeSetting
	 * @return
	 */
	public void deleteVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param vUserIncomeSettingList
	 * @return
	 */
	public void batchDeleteVUserIncomeSetting(List<VUserIncomeSetting> vUserIncomeSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param vUserIncomeSettingId
	 * @return
	 */
	public VUserIncomeSetting getVUserIncomeSettingById(Long vUserIncomeSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param vUserIncomeSettingqueryObj
	 * @return
	 */
	public Page<VUserIncomeSetting> pageQueryVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param vUserIncomeSettingqueryObj
	 * @return
	 */
	public List<VUserIncomeSetting> queryVUserIncomeSetting(VUserIncomeSettingQueryObj vUserIncomeSettingqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(VUserIncomeSettingUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(VUserIncomeSettingQueryObj queryObj);
	
	public Float getDownlineSpecialMax(VUserIncomeSettingQueryObj queryObj);
	
	public List<VUserIncomeSetting> queryUserSpecial(VUserIncomeSettingQueryObj queryObj);
}