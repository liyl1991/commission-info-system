package cn.haohao.cis.audit.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.audit.model.VauditInfo;
import cn.haohao.cis.audit.vo.VauditInfoQueryObj;
import cn.haohao.cis.audit.vo.VauditInfoUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IVauditInfoService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param vauditInfo
	 * @return
	 */
	public VauditInfo createVauditInfo(VauditInfo vauditInfo);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param vauditInfo
	 * @return
	 */
	public VauditInfo modifyVauditInfo(VauditInfo vauditInfo);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param vauditInfo
	 * @return
	 */
	public void deleteVauditInfo(VauditInfoQueryObj vauditInfoQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param vauditInfoList
	 * @return
	 */
	public void batchDeleteVauditInfo(List<VauditInfo> vauditInfoList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param vauditInfoId
	 * @return
	 */
	public VauditInfo getVauditInfoById(Long vauditInfoId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param vauditInfoqueryObj
	 * @return
	 */
	public Page<VauditInfo> pageQueryVauditInfo(VauditInfoQueryObj vauditInfoqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param vauditInfoqueryObj
	 * @return
	 */
	public List<VauditInfo> queryVauditInfo(VauditInfoQueryObj vauditInfoqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(VauditInfoUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(VauditInfoQueryObj queryObj);
}