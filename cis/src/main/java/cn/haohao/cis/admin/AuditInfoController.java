package cn.haohao.cis.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.audit.model.VauditInfo;
import cn.haohao.cis.audit.service.IAuditInfoService;
import cn.haohao.cis.audit.service.IVauditInfoService;
import cn.haohao.cis.audit.vo.AuditInfoUpdateObj;
import cn.haohao.cis.audit.vo.VauditInfoQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserUpdateObj;
import cn.haohao.cis.utils.Constants;
import cn.haohao.cis.validate.DataValidater;
import cn.haohao.vas.core.exception.BusinessException;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/auditInfo")
public class AuditInfoController extends MultiActionController{
	
	@Autowired
	private IAuditInfoService auditInfoService;
	@Autowired
	private IVauditInfoService vauditInfoService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/goAuditUser")
	public String goAuditUser(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("auditUserActive", Constants.ACTIVE_CLASS);
			return "mgr/auditUser";
		}
		else
			return "index";
	}
	@RequestMapping("/getAuditList")
	public @ResponseBody Map<String,Object> getAtudiList(VauditInfoQueryObj queryObj ,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		Page<VauditInfo> resultData = this.vauditInfoService.pageQueryVauditInfo(queryObj);
		resMap.put("vauditUsers", resultData);
		return resMap;
	}
	
	@RequestMapping("/getUnAtuditCount")
	public @ResponseBody Map<String,Object> getUnAtuditCount(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		VauditInfoQueryObj queryObj = new VauditInfoQueryObj();
		queryObj.setAuditStatus(3);
		queryObj.setStatus(3);
		Long cnt = this.vauditInfoService.countByArgs(queryObj);
		resMap.put("unAtuditCount", cnt);
		return resMap;
	}
	
	@RequestMapping("/doPassAudit/{userId}")
	public @ResponseBody Map<String,Object> doPassAudit(@PathVariable Integer userId, HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			resMap.put("msg", "权限不足");
			return resMap;
		}
		try {
			this.auditInfoService.passAudit(userId, loginedUser);
			resMap.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "系统出错");
		}
		return resMap;
	}
	@RequestMapping("/doUnpassAudit/{userId}")
	public @ResponseBody Map<String,Object> doUnpassAudit(@PathVariable Integer userId, HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			resMap.put("msg", "权限不足");
			return resMap;
		}
		try {
			AuditInfoUpdateObj updateObj = new AuditInfoUpdateObj();
			updateObj.setUserId(userId);
			updateObj.getNewUpdAttObj().setAuditStatus(2);
			updateObj.getNewUpdAttObj().setAuditAdmin(loginedUser.getUserId());
			updateObj.getNewUpdAttObj().setAuditDate(new Date());
			this.auditInfoService.updateDynamic(updateObj);
			resMap.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "系统出错");
		}
		return resMap;
	}
	/**
	 * 跳转至修改重审页
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/goUpdateAndReaudit/{id}")
	public String goUserDetail(@PathVariable("id") Integer id,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return "index";
		//选中菜单设定
		request.setAttribute("auditUserActive", Constants.ACTIVE_CLASS);
		//要查看的用户
		User targetUser = this.userService.getUserById(id);
		request.setAttribute("user", targetUser);
		
		return "mgr/updateAndReaudit";
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
			DataValidater.userUpdateAuditValidate(newObj, userService);
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
			resMap.put("msg", "系统出错");
		}
		return resMap;
	}
	@RequestMapping("/doUpdateAndPass")
	public @ResponseBody Map<String,Object> doUpdateAndPass(User newObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin()){
			resMap.put("result", false);
			return resMap;
		}
		try{
			DataValidater.userUpdateAuditValidate(newObj, userService);
			UserUpdateObj updateObj = new UserUpdateObj();
			updateObj.setUserId(newObj.getUserId());
			updateObj.setNewUpdAttObj(newObj);
			this.userService.updateAndPassAudit(updateObj,loginedUser);
			
			resMap.put("result", true);
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch(Exception e){
			resMap.put("result", false);
			resMap.put("msg", "系统出错");
		}
		return resMap;
	}
}

