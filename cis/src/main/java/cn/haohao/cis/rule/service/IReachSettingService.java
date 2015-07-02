package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.model.ReachSetting;
import cn.haohao.cis.rule.vo.ReachSettingQueryObj;
import cn.haohao.cis.rule.vo.ReachSettingUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IReachSettingService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param reachSetting
	 * @return
	 */
	public ReachSetting createReachSetting(ReachSetting reachSetting);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param reachSetting
	 * @return
	 */
	public ReachSetting modifyReachSetting(ReachSetting reachSetting);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param reachSetting
	 * @return
	 */
	public void deleteReachSetting(ReachSettingQueryObj reachSettingQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param reachSettingList
	 * @return
	 */
	public void batchDeleteReachSetting(List<ReachSetting> reachSettingList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param reachSettingId
	 * @return
	 */
	public ReachSetting getReachSettingById(Integer reachSettingId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param reachSettingqueryObj
	 * @return
	 */
	public Page<ReachSetting> pageQueryReachSetting(ReachSettingQueryObj reachSettingqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param reachSettingqueryObj
	 * @return
	 */
	public List<ReachSetting> queryReachSetting(ReachSettingQueryObj reachSettingqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(ReachSettingUpdateObj updateObj);
	
	public void updateDynamic(ReachSettingUpdateObj updateObj, ReachSettingUpdateObj updateObj2);
	
	public void updateDynamic(ReachSetting oldSetting,Float modifyData);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(ReachSettingQueryObj queryObj);
}