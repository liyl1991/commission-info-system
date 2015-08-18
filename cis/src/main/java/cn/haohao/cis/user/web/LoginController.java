package cn.haohao.cis.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.audit.model.AuditInfo;
import cn.haohao.cis.audit.service.IAuditInfoService;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.Constants;
import cn.haohao.cis.validate.DataValidater;
import cn.haohao.vas.core.exception.BusinessException;
import cn.haohao.vas.core.utils.MD5Encoder;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/login")
public class LoginController extends MultiActionController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IAuditInfoService auditInfoService;
	private Log log = LogFactory.getLog("adminLog");
	@RequestMapping("/doLogin")
	public String doLogin(UserQueryObj loginInfo, String loginName, HttpServletRequest request){
		if(StringUtils.isNotEmpty(loginInfo.getPassword()) && StringUtils.isNotEmpty(loginName)){
			loginInfo.setPassword(MD5Encoder.encode(loginInfo.getPassword()));
			loginInfo.setIdCard(loginName);
			List<User> user = this.userService.queryUser(loginInfo);
			if(user.size()==1){
				User current = user.get(0);
				if(current.getLevel().equalsIgnoreCase("X")||current.getStatus()==2){
					request.setAttribute("errorMsg", "用户名或者密码错误，请重新输入。");
					return "login";
				}
				else if(current.getStatus()==3){
					AuditInfo auditInfo = this.auditInfoService.getAuditInfoById(current.getUserId());
					if(auditInfo.getAuditStatus()==1)
						request.setAttribute("errorMsg", "您的账号异常，请联系管理员！");
					else if(auditInfo.getAuditStatus()==2)
						request.setAttribute("errorMsg", "您的账号申请没有通过审核！");
					else if(auditInfo.getAuditStatus()==3)
						request.setAttribute("errorMsg", "您的账号正等待管理员审核，请耐心等待！");
					return "login";
				}
				request.getSession().setAttribute(Constants.LOGINED_USER_BEAN_NAME, current);
				if(current.isAdmin()){
					this.log.info(current.getName()+"-登陆系统！");
					return "redirect:/userMgr/goUserMgr";
				}
				else
					return "redirect:/notice/goIndexNotice";
			}
		}
		request.setAttribute("errorMsg", "用户名或者密码错误，请重新输入。");
		return "login";
	}
	
	@RequestMapping("/doRegister")
	public @ResponseBody Map<String,Object> doRegister(HttpServletRequest request,
			User registerUser,String pwd,String affirmPwd){
		Map<String,Object> resMap = new HashMap<String,Object>();
		try {
			DataValidater.userRegisterValidate(registerUser, pwd,affirmPwd,userService);
			registerUser.setPassword(MD5Encoder.encode(pwd));
			registerUser.setLevel("E");
			registerUser.setStatus(3);//默认待审核状态
			registerUser.setUserRole(1);
			registerUser = this.userService.createUserForRegister(registerUser);
			
			resMap.put("result", true);
		} catch (BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "系统错误");
		}
		return resMap;
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.LOGINED_USER_BEAN_NAME);
		request.getSession().invalidate();
		return "login";
	}
	
	/*@RequestMapping("/goIndex")
	public String goIndex(HttpServletRequest request){
		request.setAttribute("indexActive", Constants.ACTIVE_CLASS);
		return "index";
	}*/
	
}