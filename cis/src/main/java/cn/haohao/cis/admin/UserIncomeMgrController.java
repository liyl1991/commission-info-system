package cn.haohao.cis.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeInputObj;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.service.IIncomeRuleService;
import cn.haohao.cis.rule.service.IIncomeSettingService;
import cn.haohao.cis.rule.service.ISpecialSettingService;
import cn.haohao.cis.rule.service.IVUserIncomeSettingService;
import cn.haohao.cis.rule.vo.IncomeRuleQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.service.IVuserIncomeService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/userIncomeMgr")
public class UserIncomeMgrController extends MultiActionController{
	
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserIncomeService userIncomeService;
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	@Autowired
	private IIncomeRuleService incomeRuleService;
	@Autowired
	private IVUserIncomeSettingService vUserIncomeSettingService;
	@Autowired
	private ISpecialSettingService specialSettingService;
	@Autowired
	private IIncomeSettingService incomeSettingService;
	
	private Log log = LogFactory.getLog("adminLog");
	
	@RequestMapping("/goIncomeMgr")
	public String goIndex(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("incomeMgrActive", Constants.ACTIVE_CLASS);
			UserQueryObj userQueryObj = new UserQueryObj();
			userQueryObj.setStatus(1);
			userQueryObj.setLevel("X");
			request.setAttribute("forSelectUsers", this.userService.queryUser(userQueryObj));
			return "mgr/income/incomeMgr";
		}
		else
			return "index";
	}
	
	/**
	 * 获取x级员工各个上级的人员及提成比例
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getIncomeInfo(HttpServletRequest request, Integer userId){
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		User user = this.userService.getUserById(userId);
		if( !"X".equalsIgnoreCase(user.getLevel())){
			resMap.put("msg", "只能选择X级别的员工");
			return resMap;
		}
		List<User> userUplineList = new ArrayList<User>();
		userUplineList = this.getUserUplineWithSetting(user, userUplineList);
		//拼接主线
		String ruleContent = user.getLevel();
		for (User upline : userUplineList) {
			ruleContent = upline.getLevel() + ruleContent;
		}
		//根据主线获取id
		IncomeRuleQueryObj incomeRuleQueryObj = new IncomeRuleQueryObj();
		incomeRuleQueryObj.setDetailContent(ruleContent);
		List<IncomeRule> rules = this.incomeRuleService.queryIncomeRule(incomeRuleQueryObj);
		IncomeRule rule = null;
		if( rules != null && rules.size() ==1){
			rule = rules.get(0);
		}
		this.setUsersIncomeSetting(rule, userUplineList);
		resMap.put("currentRule", rule);
		resMap.put("userUplineList", userUplineList);
		IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
		incomeSettingQueryObj.setRuleId(0);
		incomeSettingQueryObj.setStatus(1);
		resMap.put("baseRuleSetting", this.incomeSettingService.getIncomeSetting(incomeSettingQueryObj));
		return resMap;
	}
	
	@RequestMapping("/doInputUserIncome")
	public @ResponseBody Map<String,Object> doInputUserIncome(HttpServletRequest request, UserIncomeInputObj inputObj){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		User user = this.userService.getUserById(inputObj.getUserId());
		if( !"X".equalsIgnoreCase(user.getLevel())){
			resMap.put("msg", "只能选择X级别的员工");
			return resMap;
		}
		List<User> userUplineList = new ArrayList<User>();
		try{
			userUplineList = this.getUserUplineWithSetting(user, userUplineList);
			this.setUsersIncomeSetting(this.incomeRuleService.getIncomeRuleById(inputObj.getRuleId()), userUplineList);
			this.userIncomeService.createUserIncome(userUplineList, inputObj);
			resMap.put("result", true);
			log.info(loginedUser.getName()+"-添加了创收信息->"+user.getName()+"("+user.getIdCard()+"):"+inputObj.getIncome());
		} catch (Exception e){
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "系统出错");
		}
		return resMap;
	}
	
	private void setUsersIncomeSetting(IncomeRule rule, List<User> list){
		if(rule.getDetailContent().length() > 2){
			for (User user : list) {
				if( !user.isLevelB() ){
					VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
					vUserIncomeSettingQueryObj.setUserId(user.getUserId());
					vUserIncomeSettingQueryObj.setRuleId(rule.getRuleId());
					List<VUserIncomeSetting> currentUserSetting = this.vUserIncomeSettingService.queryVUserIncomeSetting(vUserIncomeSettingQueryObj);
					if(currentUserSetting != null && currentUserSetting.size() == 1){
						Integer settingId = currentUserSetting.get(0).getSpecialSettingId();
						IncomeSetting setting = this.incomeSettingService.getIncomeSettingById(settingId);
						user.setIncomeSetting(setting);
					} else {
						IncomeSettingQueryObj queryObj = new IncomeSettingQueryObj();
						queryObj.setStatus(1);
						queryObj.setType(1);
						queryObj.setRuleId(rule.getRuleId());
						queryObj.setSettingLevel(user.getLevel());
						IncomeSetting setting = this.incomeSettingService.queryIncomeSetting(queryObj).get(0);
						user.setIncomeSetting(setting);
					}
				}
			}
		} else {
			VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
			vUserIncomeSettingQueryObj.setUserId(list.get(0).getUserId());
			vUserIncomeSettingQueryObj.setRuleId(rule.getRuleId());
			List<VUserIncomeSetting> currentUserSetting = this.vUserIncomeSettingService.queryVUserIncomeSetting(vUserIncomeSettingQueryObj);
			if(currentUserSetting != null && currentUserSetting.size() == 1){
				Integer settingId = currentUserSetting.get(0).getSpecialSettingId();
				IncomeSetting setting = this.incomeSettingService.getIncomeSettingById(settingId);
				list.get(0).setIncomeSetting(setting);
			} else {
				IncomeSettingQueryObj queryObj = new IncomeSettingQueryObj();
				queryObj.setStatus(1);
				queryObj.setType(1);
				queryObj.setRuleId(rule.getRuleId());
				queryObj.setSettingLevel(list.get(0).getLevel());
				IncomeSetting setting = this.incomeSettingService.queryIncomeSetting(queryObj).get(0);
				list.get(0).setIncomeSetting(setting);
			}
		}
	}
	
	private List<User> getUserUplineWithSetting(User user, List<User> list){
		
		User upline = this.userService.getUserById(user.getUplineUser());
		list.add(upline);
		if( !"B".equalsIgnoreCase(upline.getLevel()) ){
			list = this.getUserUplineWithSetting(upline, list);
		}
		return list;
	}
}