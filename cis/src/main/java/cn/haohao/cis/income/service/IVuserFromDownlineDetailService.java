package cn.haohao.cis.income.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.income.model.VuserFromDownlineDetail;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailQueryObj;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IVuserFromDownlineDetailService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param vuserFromDownlineDetail
	 * @return
	 */
	public VuserFromDownlineDetail createVuserFromDownlineDetail(VuserFromDownlineDetail vuserFromDownlineDetail);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param vuserFromDownlineDetail
	 * @return
	 */
	public VuserFromDownlineDetail modifyVuserFromDownlineDetail(VuserFromDownlineDetail vuserFromDownlineDetail);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param vuserFromDownlineDetail
	 * @return
	 */
	public void deleteVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param vuserFromDownlineDetailList
	 * @return
	 */
	public void batchDeleteVuserFromDownlineDetail(List<VuserFromDownlineDetail> vuserFromDownlineDetailList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param vuserFromDownlineDetailId
	 * @return
	 */
	public VuserFromDownlineDetail getVuserFromDownlineDetailById(Integer vuserFromDownlineDetailId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param vuserFromDownlineDetailqueryObj
	 * @return
	 */
	public Page<VuserFromDownlineDetail> pageQueryVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param vuserFromDownlineDetailqueryObj
	 * @return
	 */
	public List<VuserFromDownlineDetail> queryVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(VuserFromDownlineDetailUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(VuserFromDownlineDetailQueryObj queryObj);
}