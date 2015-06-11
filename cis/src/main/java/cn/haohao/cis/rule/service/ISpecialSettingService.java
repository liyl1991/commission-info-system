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
	 * @param sepcialSetting
	 * @return
	 */
	public SpecialSetting createSepcialSetting(SpecialSetting sepcialSetting);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param sepcialSetting
	 * @return
	 */
	public SpecialSetting modifySepcialSetting(SpecialSetting sepcialSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param sepcialSetting
	 * @return
	 */
	public void deleteSepcialSetting(SpecialSettingQueryObj sepcialSettingQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param sepcialSettingList
	 * @return
	 */
	public void batchDeleteSepcialSetting(List<SpecialSetting> sepcialSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param sepcialSettingId
	 * @return
	 */
	public SpecialSetting getSepcialSettingById(Long sepcialSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param sepcialSettingqueryObj
	 * @return
	 */
	public Page<SpecialSetting> pageQuerySepcialSetting(SpecialSettingQueryObj sepcialSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param sepcialSettingqueryObj
	 * @return
	 */
	public List<SpecialSetting> querySepcialSetting(SpecialSettingQueryObj sepcialSettingqueryObj);
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