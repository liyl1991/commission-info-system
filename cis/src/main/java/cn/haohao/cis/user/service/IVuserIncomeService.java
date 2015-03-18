package cn.haohao.cis.user.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.user.model.VuserIncome;
import cn.haohao.cis.user.vo.VuserIncomeQueryObj;
import cn.haohao.cis.user.vo.VuserIncomeUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IVuserIncomeService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param vuserIncome
	 * @return
	 */
	public VuserIncome createVuserIncome(VuserIncome vuserIncome);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param vuserIncome
	 * @return
	 */
	public VuserIncome modifyVuserIncome(VuserIncome vuserIncome);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param vuserIncome
	 * @return
	 */
	public void deleteVuserIncome(VuserIncomeQueryObj vuserIncomeQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param vuserIncomeList
	 * @return
	 */
	public void batchDeleteVuserIncome(List<VuserIncome> vuserIncomeList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param vuserIncomeId
	 * @return
	 */
	public VuserIncome getVuserIncomeById(Long vuserIncomeId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param vuserIncomequeryObj
	 * @return
	 */
	public Page<VuserIncome> pageQueryVuserIncome(VuserIncomeQueryObj vuserIncomequeryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param vuserIncomequeryObj
	 * @return
	 */
	public List<VuserIncome> queryVuserIncome(VuserIncomeQueryObj vuserIncomequeryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(VuserIncomeUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(VuserIncomeQueryObj queryObj);
}