package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.model.VspecialSetting;
import cn.haohao.cis.rule.vo.VspecialSettingQueryObj;
import cn.haohao.cis.rule.vo.VspecialSettingUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IVspecialSettingService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param vspecialSetting
	 * @return
	 */
	public VspecialSetting createVspecialSetting(VspecialSetting vspecialSetting);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param vspecialSetting
	 * @return
	 */
	public VspecialSetting modifyVspecialSetting(VspecialSetting vspecialSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param vspecialSetting
	 * @return
	 */
	public void deleteVspecialSetting(VspecialSettingQueryObj vspecialSettingQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param vspecialSettingList
	 * @return
	 */
	public void batchDeleteVspecialSetting(List<VspecialSetting> vspecialSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param vspecialSettingId
	 * @return
	 */
	public VspecialSetting getVspecialSettingById(Long vspecialSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param vspecialSettingqueryObj
	 * @return
	 */
	public Page<VspecialSetting> pageQueryVspecialSetting(VspecialSettingQueryObj vspecialSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param vspecialSettingqueryObj
	 * @return
	 */
	public List<VspecialSetting> queryVspecialSetting(VspecialSettingQueryObj vspecialSettingqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(VspecialSettingUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(VspecialSettingQueryObj queryObj);
}