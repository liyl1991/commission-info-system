package cn.haohao.cis.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.income.vo.UserIncomeUpdateObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.model.VuserIncome;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.service.IVuserIncomeService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.user.vo.UserUpdateObj;
import cn.haohao.cis.user.vo.VuserIncomeQueryObj;
import cn.haohao.cis.utils.Constants;
import cn.haohao.cis.validate.DataValidater;
import cn.haohao.vas.core.exception.BusinessException;
import cn.haohao.vas.core.utils.MD5Encoder;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/admin")
public class AdminController extends MultiActionController{
	
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	@Autowired
	private IUserIncomeService userIncomeService;
	@Autowired
	private IUserService userService;
	/**
	 * 至员工管理页 
	 * @param request
	 */
	@RequestMapping("/goAdminMgr")
	public String goAdminMgr(HttpServletRequest request){
		
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin())
			return "mgr/adminMgr";
		else
			return "index";
	}
	/**
	 * 获取员工信息列表
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getIncomeInfo(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		queryObj.setStatus(1);
		queryObj.setUserRole(1);
		Page<VuserIncome> downlineUsers = this.vuserIncomeService.pageQueryVuserIncome(queryObj);
		resMap.put("downlineUsers", downlineUsers);
		return resMap;
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
		//要查看的用户
		User targetUser = this.userService.getUserById(id);
		request.setAttribute("user", targetUser);
		//用户的上级
		User uplineUser = this.userService.getUserById(targetUser.getUplineUser());
		request.setAttribute("uplineUser", uplineUser);
		//可选上级列表
		UserQueryObj userQueryObj = new UserQueryObj();
		userQueryObj.setStatus(1);
		userQueryObj.setLevelLt(targetUser.getLevel());
		if(!"B".equalsIgnoreCase(targetUser.getLevel())){//只有B级的上线可以是管理员
			userQueryObj.setLevelNotEq("A");
		}
		List<User> uplineCandidate = this.userService.queryUser(userQueryObj);
		request.setAttribute("uplineCandidate", uplineCandidate);
		return "mgr/adminPersonInfo";
	}
	
	/**
	 * 根据员工id获取员工下线员工列表（分页查询）
	 * @param id
	 * @param request
	 */
	@RequestMapping("/getUserDownlines")
	public @ResponseBody Map<String,Object> getUserDownlines(UserQueryObj queryObj,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		resMap.put("downlines", this.userService.pageQueryUser(queryObj));
		return resMap;
	}
	
	/**
	 * 根据员工id获取获取员工收入信息
	 * @param id
	 * @param request
	 */
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(UserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//历史收入记录
		Page<UserIncome> incomeList = this.userIncomeService.pageQueryUserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		queryObj.setIncomeDate(this.getPrecedingDate());
		List<UserIncome> preIncome = this.userIncomeService.queryUserIncome(queryObj);
		if(preIncome.size()!=0)
			resMap.put("preIncome", preIncome.get(0));
		
		//总记录
		resMap.put("incomeSum", this.userIncomeService.getIncomeSum(queryObj.getUserId()));
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
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch(Exception e){
			resMap.put("result", false);
			resMap.put("msg", "修改失败");
		}
		return resMap;
	}
	
	@RequestMapping("/goInputUser")
	public String doInputUser(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return "index";
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setStatus(1);
		queryObj.setLevelNotEq("X");//x级不可能为上级
		request.setAttribute("uplineCandidate", this.userService.queryUser(queryObj));
		return "mgr/adminInputUser";
	}
	
	@RequestMapping("/doInputUser")
	public @ResponseBody Map<String,Object> goInputUser(User user,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		try{
			DataValidater.userInputValidate(user, userService);
			user.setStatus(1);
			user.setUserRole(1);
			user.setPassword(MD5Encoder.encode(Constants.DEFAULT_PASSWORD));
			this.userService.createUser(user);
			resMap.put("result", true);
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch (Exception e) {
			resMap.put("result", false);
			resMap.put("msg", "新增失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doInputUserIncome")
	public @ResponseBody Map<String,Object> doInputUserIncome(UserIncome userIncome,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		try{
			this.userIncomeService.createUserIncome(userIncome);
			resMap.put("result", true);
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch (Exception e) {
			resMap.put("result", false);
			resMap.put("msg", "新增失败");
		}
		return resMap;
	}
	/**
	 * 获取上个月日期
	 * @return
	 */
	private Date getPrecedingDate(){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int month = c.get(Calendar.MONTH),year = c.get(Calendar.YEAR);
		try {
			if(month!=0){
				return sdf.parse(year+"-"+month+"-01");
			}else{
				return sdf.parse((year-1)+"-12-01");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}