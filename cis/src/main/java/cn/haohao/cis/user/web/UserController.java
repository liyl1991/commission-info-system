package cn.haohao.cis.user.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.model.VuserIncome;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.service.IVuserIncomeService;
import cn.haohao.cis.user.vo.UserPwdUpdateObj;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.user.vo.UserUpdateObj;
import cn.haohao.cis.user.vo.VuserIncomeQueryObj;
import cn.haohao.cis.utils.Constants;
import cn.haohao.vas.core.utils.MD5Encoder;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/user")
public class UserController extends MultiActionController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	
	@RequestMapping("/goPersonInfo")
	public String goPersonInfo(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.getUplineUser()!=null){
			User uplineUser = this.userService.getUserById(loginedUser.getUplineUser());
			request.setAttribute("uplineUser", uplineUser);
		}
		return "personInfo";
	}
	
	@RequestMapping("/goDownline")
	public String goDownline(HttpServletRequest request){
		request.setAttribute("downlineActive", Constants.ACTIVE_CLASS);
		return "downlinePerson";
	}
	
	@RequestMapping("/doUpdatePwd")
	public @ResponseBody Map<String,Object> doUpdatePwd(UserPwdUpdateObj pwdUpdateObj,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(pwdUpdateObj.getOldPwd())
				||StringUtils.isEmpty(pwdUpdateObj.getNewPwd())
				||StringUtils.isEmpty(pwdUpdateObj.getNewPwd2())){
			resMap.put("result", false);
			resMap.put("msg", "输入的信息不完整");
		}
		else if(pwdUpdateObj.getNewPwd().equals(pwdUpdateObj.getNewPwd2())){
			User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
			String loginedPwd = this.userService.getUserById(loginedUser.getUserId()).getPassword();
			if(!MD5Encoder.encode(pwdUpdateObj.getOldPwd()).equals(loginedPwd)){
				resMap.put("result", false);
				resMap.put("msg", "原密码输入有误");
				return resMap;
			}else if(pwdUpdateObj.getNewPwd().length()>50){
				resMap.put("result", false);
				resMap.put("msg", "密码长度过大，请勿超过50个字符");
				return resMap;
			}
			UserUpdateObj userUpdateObj = new UserUpdateObj();
			userUpdateObj.setUserId(loginedUser.getUserId());
			userUpdateObj.getNewUpdAttObj().setPassword(MD5Encoder.encode(pwdUpdateObj.getNewPwd()));
			this.userService.updateDynamic(userUpdateObj);
			request.getSession().setAttribute(Constants.LOGINED_USER_BEAN_NAME,
					this.userService.getUserById(userUpdateObj.getUserId()));
			resMap.put("result", true);
			resMap.put("msg", "密码修改成功，请牢记您的新密码");
		}
		else{
			resMap.put("result", false);
			resMap.put("msg", "两次输入密码不一致");
		}
		return resMap;
	}
	@RequestMapping("/getDownline")
	public @ResponseBody Map<String,Object> getDownline(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		queryObj.setStatus(1);
		queryObj.setUplineUser(loginedUser.getUserId());
		Page<VuserIncome> downlineUsers = this.vuserIncomeService.pageQueryVuserIncome(queryObj);
		resMap.put("downlineUsers", downlineUsers);
		return resMap;
	}
	/**
	 * 统计下线人员数
	 */
	@RequestMapping("/getDownlineCount")
	public @ResponseBody Map<String,Object> getDownlineCount(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		UserQueryObj userQueryObj = new UserQueryObj();
		userQueryObj.setStatus(1);
		userQueryObj.setUplineUser(loginedUser.getUserId());
		this.userService.countByArgs(userQueryObj);
		resMap.put("downlineCount", this.userService.countByArgs(userQueryObj));
		return resMap;
	}
	
}
