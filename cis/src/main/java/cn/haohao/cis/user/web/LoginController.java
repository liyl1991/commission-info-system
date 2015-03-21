package cn.haohao.cis.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.Constants;
import cn.haohao.vas.core.utils.MD5Encoder;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/login")
public class LoginController extends MultiActionController{
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/doLogin")
	public String doLogin(UserQueryObj loginInfo ,HttpServletRequest request){
		if(StringUtils.isNotEmpty(loginInfo.getPassword())){
			loginInfo.setPassword(MD5Encoder.encode(loginInfo.getPassword()));
			loginInfo.setStatus(1);
			List<User> user = this.userService.queryUser(loginInfo);
			if(user.size()==1){
				User current = user.get(0);
				if(current.getLevel().equalsIgnoreCase("X")||current.getStatus()==2){
					request.setAttribute("errorMsg", "用户名或者密码错误，请重新输入。");
					return "login";
				}
				request.getSession().setAttribute(Constants.LOGINED_USER_BEAN_NAME, current);
				if(current.isAdmin())
					return "redirect:/admin/goAdminMgr";
				else
					return "redirect:/login/goIndex";
			}
		}
		request.setAttribute("errorMsg", "用户名或者密码错误，请重新输入。");
		return "login";
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.LOGINED_USER_BEAN_NAME);
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping("/goIndex")
	public String goIndex(HttpServletRequest request){
		request.setAttribute("indexActive", Constants.ACTIVE_CLASS);
		return "index";
	}
	
}