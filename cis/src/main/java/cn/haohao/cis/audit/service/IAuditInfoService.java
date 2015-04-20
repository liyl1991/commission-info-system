package cn.haohao.cis.audit.service;
//j-import-b
import java.util.List;

import org.springframework.data.domain.Page;

import cn.haohao.cis.audit.model.AuditInfo;
import cn.haohao.cis.audit.vo.AuditInfoQueryObj;
import cn.haohao.cis.audit.vo.AuditInfoUpdateObj;
import cn.haohao.cis.user.model.User;
//j-import-e
/**
 *	SERVICE
 */
public interface IAuditInfoService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param auditInfo
	 * @return
	 */
	public AuditInfo createAuditInfo(AuditInfo auditInfo);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param auditInfo
	 * @return
	 */
	public AuditInfo modifyAuditInfo(AuditInfo auditInfo);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param auditInfo
	 * @return
	 */
	public void deleteAuditInfo(AuditInfoQueryObj auditInfoQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param auditInfoList
	 * @return
	 */
	public void batchDeleteAuditInfo(List<AuditInfo> auditInfoList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param auditInfoId
	 * @return
	 */
	public AuditInfo getAuditInfoById(Integer auditInfoId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param auditInfoqueryObj
	 * @return
	 */
	public Page<AuditInfo> pageQueryAuditInfo(AuditInfoQueryObj auditInfoqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param auditInfoqueryObj
	 * @return
	 */
	public List<AuditInfo> queryAuditInfo(AuditInfoQueryObj auditInfoqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(AuditInfoUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(AuditInfoQueryObj queryObj);
	
	public void passAudit(Integer userId,User adminInfo);
}