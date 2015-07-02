package cn.haohao.cis.admin;

import java.util.ArrayList;
import java.util.Date;
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

import cn.haohao.cis.income.model.VuserFromDownlineDetail;
import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.service.IVuserFromDownlineDetailService;
import cn.haohao.cis.income.service.IVuserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeInputObj;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailQueryObj;
import cn.haohao.cis.income.vo.VuserIncomeQueryObj;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.model.VspecialSetting;
import cn.haohao.cis.rule.service.IIncomeRuleService;
import cn.haohao.cis.rule.service.IIncomeSettingService;
import cn.haohao.cis.rule.service.IVUserIncomeSettingService;
import cn.haohao.cis.rule.service.IVspecialSettingService;
import cn.haohao.cis.rule.vo.IncomeRuleQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.VspecialSettingQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.BaseUtils;
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
	private IIncomeRuleService incomeRuleService;
	@Autowired
	private IVUserIncomeSettingService vUserIncomeSettingService;
	@Autowired
	private IVspecialSettingService vspecialService;
	@Autowired
	private IIncomeSettingService incomeSettingService;
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	@Autowired
	private IVuserFromDownlineDetailService vuserFromDownlineDetailService;
	
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
	
	@RequestMapping("/getUserIncomeList")
	public @ResponseBody Page<VuserIncome> getUserIncomeList(HttpServletRequest request, VuserIncomeQueryObj queryObj){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return null;
		queryObj.setStatus(1);
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order("level"));
		queryObj.setSort(orders);
		return this.vuserIncomeService.pageQueryVuserIncome(queryObj);
	}
	
	@RequestMapping("/goUserIncomeFrom/{year}/{month}/{userId}")
	public String goUserIncomeFrom(HttpServletRequest request,@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer userId){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("user", this.userService.getUserById(userId));
			return "mgr/income/incomeFrom";
		}
		else
			return "index";
	}
	
	@RequestMapping("/getUserIncomeFrom")
	public @ResponseBody Page<VuserFromDownlineDetail> getUserIncomeFrom(HttpServletRequest request, VuserFromDownlineDetailQueryObj queryObj, UserIncomeInputObj inputObj){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return null;
		queryObj.setIncomeDate(inputObj.getDate());
		
		return this.vuserFromDownlineDetailService.pageQueryVuserFromDownlineDetail(queryObj);
	}
	/**
	 * 获取x级员工各个上级的人员及提成比例
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getUserList(HttpServletRequest request, UserIncomeInputObj inputObj){
		
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
		this.setUsersIncomeSetting(rule, userUplineList, inputObj.getDate());
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
			this.setUsersIncomeSetting(this.incomeRuleService.getIncomeRuleById(inputObj.getRuleId()), userUplineList, inputObj.getDate());
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
	
	private void setUsersIncomeSetting(IncomeRule rule, List<User> list , Date forDate){
		if(rule.getDetailContent().length() > 2){
			for (User user : list) {
				if( !user.isLevelB() ){
					VspecialSettingQueryObj vspecialSettingQueryObj = new VspecialSettingQueryObj();
					vspecialSettingQueryObj.setUserId(user.getUserId());
					vspecialSettingQueryObj.setEffectiveDate(forDate);
					vspecialSettingQueryObj.setRuleId(rule.getRuleId());
					List<VspecialSetting> specialSettings = this.vspecialService.queryVspecialSetting(vspecialSettingQueryObj);
					if( BaseUtils.isListOnlyHasOne(specialSettings)){
						user.setIncomeSetting(this.incomeSettingService.getIncomeSettingById(specialSettings.get(0).getSettingId()));
					} else {
						IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
						incomeSettingQueryObj.setRuleId(rule.getRuleId());
						incomeSettingQueryObj.setType(1);
						incomeSettingQueryObj.setSettingLevel(user.getLevel());
						incomeSettingQueryObj.setEffectiveDate(forDate);
						List<IncomeSetting> commonSetting = this.incomeSettingService.queryIncomeSetting(incomeSettingQueryObj);
						if( BaseUtils.isListOnlyHasOne(commonSetting) ){
							user.setIncomeSetting(this.incomeSettingService.queryIncomeSetting(incomeSettingQueryObj).get(0));
						}
					}
				}
			}
		} else {
			VspecialSettingQueryObj vspecialSettingQueryObj = new VspecialSettingQueryObj();
			vspecialSettingQueryObj.setUserId(list.get(0).getUserId());
			vspecialSettingQueryObj.setEffectiveDate(forDate);
			List<VspecialSetting> specialSettings = this.vspecialService.queryVspecialSetting(vspecialSettingQueryObj);
			if( BaseUtils.isListOnlyHasOne(specialSettings)){
				list.get(0).setIncomeSetting(this.incomeSettingService.getIncomeSettingById(specialSettings.get(0).getSettingId()));
			} else {
				IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
				incomeSettingQueryObj.setRuleId(rule.getRuleId());
				incomeSettingQueryObj.setType(1);
				incomeSettingQueryObj.setSettingLevel(list.get(0).getLevel());
				incomeSettingQueryObj.setEffectiveDate(forDate);
				List<IncomeSetting> commonSetting = this.incomeSettingService.queryIncomeSetting(incomeSettingQueryObj);
				if( BaseUtils.isListOnlyHasOne(commonSetting) ){
					list.get(0).setIncomeSetting(this.incomeSettingService.queryIncomeSetting(incomeSettingQueryObj).get(0));
				}
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