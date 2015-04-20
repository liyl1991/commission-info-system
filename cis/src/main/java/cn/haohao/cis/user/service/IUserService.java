package cn.haohao.cis.user.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.user.vo.UserUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IUserService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param user
	 * @return
	 */
	public User createUser(User user);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param user
	 * @return
	 */
	public User modifyUser(User user);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param user
	 * @return
	 */
	public void deleteUser(UserQueryObj userQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param userList
	 * @return
	 */
	public void batchDeleteUser(List<User> userList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param userqueryObj
	 * @return
	 */
	public Page<User> pageQueryUser(UserQueryObj userqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param userqueryObj
	 * @return
	 */
	public List<User> queryUser(UserQueryObj userqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(UserUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(UserQueryObj queryObj);
	
	public User createUserForRegister(User user);
	
	public void updateAndPassAudit(UserUpdateObj updateObj,User adminInfo);
}