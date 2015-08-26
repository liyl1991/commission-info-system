package cn.haohao.cis.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.cis.income.service.IVuserIncomeService;
import cn.haohao.cis.income.vo.VuserIncomeQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.user.vo.UserUpdateObj;
import cn.haohao.cis.utils.BaseUtils;
import cn.haohao.cis.utils.Constants;
import cn.haohao.cis.validate.DataValidater;
import cn.haohao.vas.core.exception.BusinessException;
import cn.haohao.vas.core.utils.MD5Encoder;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/userMgr")
public class UserMgrController extends MultiActionController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	private Log log = LogFactory.getLog("adminLog");
	/**
	 * 至员工管理页 
	 * @param request
	 */
	@RequestMapping("/goUserMgr")
	public String goUserMgr(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("adminMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/user/userMgr";
		}
		else
			return "index";
	}
	/**
	 * 至员工管理页 
	 * @param request
	 */
	@RequestMapping("/goInputSuccess/{type}/{userId}")
	public String goInputSuccess(HttpServletRequest request, @PathVariable Integer userId, @PathVariable String type){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("adminMgrActive", Constants.ACTIVE_CLASS);
			User current = this.userService.getUserById(userId);
			request.setAttribute("user", current);
			request.setAttribute("type", type);
			request.setAttribute("upline", this.userService.getUserById(current.getUplineUser()));
			return "mgr/user/inputUserSuccess";
		}
		else
			return "index";
	}
	/**
	 * 获取员工信息列表
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Page<User> getIncomeInfo(UserQueryObj queryObj,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return null;
		queryObj.setStatus(1);
		queryObj.setUserRole(1);
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order("level"));
		queryObj.setSort(orders);
		return this.userService.pageQueryUser(queryObj);
	}
	
	/**
	 * 根据员工id跳转员工收入信息页
	 * @param id
	 * @param request
	 */
	@RequestMapping("/goUserDetail/{id}")
	public String goUserDetail(@PathVariable("id") Integer id,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return "index";
		//选中菜单设定
		request.setAttribute("adminMgrActive", Constants.ACTIVE_CLASS);
		//要查看的用户
		User targetUser = this.userService.getUserById(id);
		request.setAttribute("user", targetUser);
		//用户的上级
		User uplineUser = this.userService.getUserById(targetUser.getUplineUser());
		request.setAttribute("uplineUser", uplineUser);
		//可选上级列表
		UserQueryObj userQueryObj = new UserQueryObj();
		userQueryObj.setStatus(1);
		//userQueryObj.setLevelLt(targetUser.getLevel());
		//if(!"B".equalsIgnoreCase(targetUser.getLevel())){//只有B级的上线可以是管理员
		//}
		userQueryObj.setLevelNotEq("X");
		List<User> uplineCandidate = this.userService.queryUser(userQueryObj);
		request.setAttribute("uplineCandidate", uplineCandidate);
		return "mgr/user/adminPersonInfo";
	}
	
	/**
	 * 根据员工id获取员工下线员工列表（分页查询）
	 * @param id
	 * @param request
	 */
	@RequestMapping("/getUserDownlines")
	public @ResponseBody Page<User> getUserDownlines(UserQueryObj queryObj,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return null;
		queryObj.setStatus(Constants.USER_STATUS_ENABLED);
		return this.userService.pageQueryUser(queryObj);
	}
	
	/**
	 * 根据员工id获取获取员工收入信息
	 * @param id
	 * @param request
	 */
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		Integer userId = queryObj.getUserId();
		//历史收入记录
		resMap.put("incomeList", this.vuserIncomeService.pageQueryVuserIncome(queryObj));
		//上月记录
		queryObj = new VuserIncomeQueryObj();
		queryObj.setUserId(userId);
		queryObj.setIncomeDate(BaseUtils.getSecondDayOnPreMonth());
		List<VuserIncome> list = this.vuserIncomeService.queryVuserIncome(queryObj);
		if(list != null && list.size() == 1)
			resMap.put("preIncome", list.get(0));
		
		//总记录
		resMap.put("incomeSum", this.vuserIncomeService.getIncomeSum(userId));
		//员工信息
		resMap.put("userInfo", this.userService.getUserById(queryObj.getUserId()));
		return resMap;
	}
	/**
	 * 根据员工id删除员工信息
	 * @param id
	 * @param request
	 */
	@RequestMapping("/doDeleteUser/{userId}")
	public @ResponseBody Map<String,Object> doDeleteUser(@PathVariable Integer userId,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//判断是否有下级
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setUplineUser(userId);
		List<User> downline = this.userService.queryUser(queryObj);
		if(downline.size()!=0){
			resMap.put("result", false);
			resMap.put("msg", "该用户尚有"+downline.size()+"位下级，无法删除！");
			return resMap;
		}
		UserUpdateObj updateObj = new UserUpdateObj();
		updateObj.setUserId(userId);
		updateObj.getNewUpdAttObj().setStatus(2);
		this.userService.updateDynamic(updateObj);
		resMap.put("result", true);
		this.log.info(loginedUser.getName()+"-删除用户->"+this.userService.getUserById(userId).getName());
		return resMap;
	}
	
	@RequestMapping("/doUpdateUser")
	public @ResponseBody Map<String,Object> doUpdateUser(User newObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		try{
			DataValidater.userUpdateValidate(newObj, userService);
			UserUpdateObj updateObj = new UserUpdateObj();
			updateObj.setUserId(newObj.getUserId());
			updateObj.setNewUpdAttObj(newObj);
			this.userService.updateDynamic(updateObj);
			resMap.put("result", true);
			this.log.info(loginedUser.getName()+"-修改用户->"+this.userService.getUserById(newObj.getUserId()).getName());
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch(Exception e){
			resMap.put("result", false);
			resMap.put("msg", "修改失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doInputUser")
	public @ResponseBody Map<String,Object> doInputUser(User user, HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		try{
			DataValidater.userInputValidate(user, userService);
			//身份证是否已存在
			UserQueryObj queryObj = new UserQueryObj();
			queryObj.setIdCard(user.getIdCard());
			List<User> existList = userService.queryUser(queryObj);
			if(existList.size() > 0){
				if(existList.size() == 1 && existList.get(0).getStatus() == Constants.USER_STATUS_DISABLED){
					resMap.put("result", false);
					resMap.put("deletedUser", existList.get(0));
					resMap.put("deletedUpline", this.userService.getUserById(existList.get(0).getUplineUser()));
					return resMap;
				} 
				throw new BusinessException("该员工身份证"+user.getIdCard()+",已存在,请确认");
			}
				
			user.setStatus(1);
			user.setUserRole(1);
			user.setPassword(MD5Encoder.encode(Constants.DEFAULT_PASSWORD));
			user = this.userService.createUser(user);
			resMap.put("user", user);
			resMap.put("result", true);
			this.log.info(loginedUser.getName()+"-新增用户->"+user.getName()+"["+user.getIdCard()+"]");
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch (Exception e) {
			resMap.put("result", false);
			resMap.put("msg", "新增失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doRecoverUser")
	public @ResponseBody Map<String,Object> doRecoverUser(Integer userId, HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		
		UserUpdateObj updateObj = new UserUpdateObj();
		updateObj.setUserId(userId);
		updateObj.getNewUpdAttObj().setStatus(Constants.USER_STATUS_ENABLED);
		User user = this.userService.getUserById(userId);
		try {
			this.userService.updateDynamic(updateObj);
			resMap.put("result", true);
			resMap.put("user", user);
		} catch (Exception e) {
			resMap.put("result", false);
			resMap.put("msg", "失败");
		}
		this.log.info(loginedUser.getName()+"-恢复用户->"+user.getName()+"["+user.getIdCard()+"]");
		return resMap;
	}
	
	@RequestMapping("/goInputUser")
	public String goInputUser(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return "index";
		//选中菜单设定
		request.setAttribute("adminMgrActive", Constants.ACTIVE_CLASS);
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setStatus(1);
		queryObj.setLevelLt(Constants.USER_LEVEL_E);
		//queryObj.setLevelNotEq("X");//x级不可能为上级
		request.setAttribute("uplineCandidate", this.userService.queryUser(queryObj));
		return "mgr/user/adminInputUser";
	}
	
	@RequestMapping("/getListorUpSelect")
	public @ResponseBody List<User> getListorUpSelect(HttpServletRequest request, String level){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			return null;
		}
		//选中菜单设定
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setStatus(1);
		queryObj.setLevelLt(level);
		if(!Constants.USER_LEVEL_B.equalsIgnoreCase(level)){
			queryObj.setLevelNotEq(Constants.USER_LEVEL_A);
		}
		return this.userService.queryUser(queryObj);
	}
	
}