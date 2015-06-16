package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.model.SpecialSetting;
import cn.haohao.cis.rule.vo.SpecialSettingQueryObj;
import cn.haohao.cis.rule.vo.SpecialSettingUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface ISpecialSettingService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param specialSetting
	 * @return
	 */
	public SpecialSetting createSpecialSetting(SpecialSetting specialSetting);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param specialSetting
	 * @return
	 */
	public SpecialSetting modifySpecialSetting(SpecialSetting specialSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param specialSetting
	 * @return
	 */
	public void deleteSpecialSetting(SpecialSettingQueryObj specialSettingQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param specialSettingList
	 * @return
	 */
	public void batchDeleteSpecialSetting(List<SpecialSetting> specialSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param specialSettingId
	 * @return
	 */
	public SpecialSetting getSpecialSettingById(Integer specialSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param specialSettingqueryObj
	 * @return
	 */
	public Page<SpecialSetting> pageQuerySpecialSetting(SpecialSettingQueryObj specialSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param specialSettingqueryObj
	 * @return
	 */
	public List<SpecialSetting> querySpecialSetting(SpecialSettingQueryObj specialSettingqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(SpecialSettingUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(SpecialSettingQueryObj queryObj);
}