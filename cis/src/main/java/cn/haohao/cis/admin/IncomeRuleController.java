package cn.haohao.cis.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.model.ReachSetting;
import cn.haohao.cis.rule.model.SpecialSetting;
import cn.haohao.cis.rule.model.VUserIncomeSetting;
import cn.haohao.cis.rule.service.IIncomeRuleService;
import cn.haohao.cis.rule.service.IIncomeSettingService;
import cn.haohao.cis.rule.service.IReachSettingService;
import cn.haohao.cis.rule.service.ISpecialSettingService;
import cn.haohao.cis.rule.service.IVUserIncomeSettingService;
import cn.haohao.cis.rule.vo.IncomeRuleQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.rule.vo.IncomeSettingUpdateObj;
import cn.haohao.cis.rule.vo.ReachSettingQueryObj;
import cn.haohao.cis.rule.vo.SpecialSettingQueryObj;
import cn.haohao.cis.rule.vo.VUserIncomeSettingQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.BaseUtils;
import cn.haohao.cis.utils.Constants;
import cn.haohao.cis.validate.DataValidater;
import cn.haohao.vas.core.exception.BusinessException;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/incomeRuleMgr")
public class IncomeRuleController extends MultiActionController{
	
	@Autowired
	private IIncomeRuleService incomeRuleService;
	@Autowired
	private IReachSettingService reachSettingService;
	@Autowired
	private IIncomeSettingService incomeSettingService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IVUserIncomeSettingService vUserIncomeSettingService;
	@Autowired
	private ISpecialSettingService specialSettingService;
	
	private Log log = LogFactory.getLog("adminLog");
	@RequestMapping("/goIncomeRuleMgr")
	public String goIncomeRuleMgr(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			ReachSettingQueryObj reachSettingQueryObj = new ReachSettingQueryObj();
			reachSettingQueryObj.setStatus(1);
			List<ReachSetting> reachSettings = this.reachSettingService.queryReachSetting(reachSettingQueryObj);
			List<IncomeRule> incomeRules = this.incomeRuleService.queryIncomeRule(new IncomeRuleQueryObj());
			IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
			incomeSettingQueryObj.setRuleId(0);
			incomeSettingQueryObj.setStatus(1);
			request.setAttribute("baseRuleSetting", this.incomeSettingService.getIncomeSetting(incomeSettingQueryObj));
			request.setAttribute("incomeRules", incomeRules);
			request.setAttribute("reachSettings", reachSettings);
			request.setAttribute("ruleMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/rule/incomeRuleMgr";
		}
		else
			return "index";
	}
	
	/**
	 * 根据规则主线获取当前配置信息
	 * @param id
	 * @param request
	 */
	@RequestMapping(value = "/getIncomeSetting/{ruleId}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getIncomeSetting(HttpServletRequest request, @PathVariable Integer ruleId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		IncomeSettingQueryObj queryObj = new IncomeSettingQueryObj();
		queryObj.setRuleId(ruleId);
		queryObj.setStatus(1);
		queryObj.setType(1);
		IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
		incomeSettingQueryObj.setRuleId(0);
		incomeSettingQueryObj.setStatus(1);
		resMap.put("baseRuleSetting", this.incomeSettingService.getIncomeSetting(incomeSettingQueryObj));
		resMap.put("settings", this.incomeSettingService.queryIncomeSetting(queryObj));
		return resMap;
	}
	
	/**
	 * 修改达标规则
	 * @param id
	 * @param request
	 */
	@RequestMapping("/doUpdateReach")
	public @ResponseBody Map<String,Object> doDeleteUser(HttpServletRequest request,
			Float reachC,Float reachD,Float reachE){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//取出当前配置
		ReachSettingQueryObj reachSettingQueryObj = new ReachSettingQueryObj();
		reachSettingQueryObj.setStatus(1);
		reachSettingQueryObj.setType(1);
		List<ReachSetting> reachSettings = this.reachSettingService.queryReachSetting(reachSettingQueryObj);
		//对比是否有更改
		List<ReachSetting> updatedList = new ArrayList<ReachSetting>();
		for (ReachSetting reachSetting : reachSettings) {
			Float modifyData = null;
			if("C".equals(reachSetting.getSettingLevel()))
				modifyData = reachC;
			else if("D".equals(reachSetting.getSettingLevel()))
				modifyData = reachD;
			else if("E".equals(reachSetting.getSettingLevel()))
				modifyData = reachE;
			if(reachSetting.getReachPerformance().floatValue() != modifyData.floatValue()){
				this.reachSettingService.updateDynamic(reachSetting, modifyData);
				updatedList.add(reachSetting);
			}
			
		}
		resMap.put("updatedList", updatedList);
		resMap.put("result", true);
		if(updatedList.size() > 0)
			log.info(loginedUser.getName()+"-修改了达标业绩值");
		return resMap;
	}
	
	/**
	 * 修改提成规则
	 * @param id
	 * @param request
	 */
	@RequestMapping("/doUpdateIncomeRule")
	public @ResponseBody Map<String,Object> doUpdateIncomeRule(HttpServletRequest request, IncomeSettingUpdateObj updateObj, Integer incomeRuleId){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		try {
			DataValidater.incomeSettingUpdateValidate(updateObj.getSettings(), this.incomeSettingService);
		} catch (BusinessException e) {
			resMap.put("msg", e.getMessage());
			resMap.put("result", false);
			return resMap;
		}
		Integer updatedCnt = 0;
		List<IncomeSetting> settings = updateObj.getSettings();
		for (IncomeSetting incomeSetting : settings) {
			IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
			incomeSettingQueryObj.setStatus(1);
			incomeSettingQueryObj.setRuleId(incomeRuleId);
			incomeSettingQueryObj.setSettingLevel(incomeSetting.getSettingLevel());
			IncomeSetting oldSetting = this.incomeSettingService.getIncomeSetting(incomeSettingQueryObj);
			if(oldSetting.getProportion().floatValue() != incomeSetting.getProportion().floatValue()){
				IncomeSettingUpdateObj settingUpdateObj = new IncomeSettingUpdateObj();
				settingUpdateObj.setSettingId(oldSetting.getSettingId());
				settingUpdateObj.getNewUpdAttObj().setStatus(2);
				settingUpdateObj.getNewUpdAttObj().setEndDate(BaseUtils.getFirstDayOnCurrentMonth());
				incomeSetting.setStatus(1);
				incomeSetting.setRuleId(incomeRuleId);
				incomeSetting.setType(1);
				incomeSetting.setUsingDate(BaseUtils.getFirstDayOnCurrentMonth());
				this.incomeSettingService.updateDynamic(settingUpdateObj, incomeSetting);
				updatedCnt++;
			}
		}
		resMap.put("updatedCnt", updatedCnt);
		resMap.put("result", true);
		IncomeRule updatedRule = this.incomeRuleService.getIncomeRuleById(incomeRuleId);
		if (updatedCnt > 0 ) 
			log.info(loginedUser.getName()+"-修改了主线["+updatedRule.getDetailContent()+"]的提成比例");
		return resMap;
	}
	
	/**
	 * 修改提成基础规则
	 * @param id
	 * @param request
	 */
	@RequestMapping("/doUpdateBaseRule")
	public @ResponseBody Map<String,Object> doUpdateBaseRule(HttpServletRequest request, Float newProportion){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		if( newProportion.floatValue() > 1F){
			resMap.put("result", false);
			resMap.put("msg", "比例设置不得大于1");
			return resMap;
		}
		IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
		incomeSettingQueryObj.setRuleId(0);
		incomeSettingQueryObj.setStatus(1);
		IncomeSetting oldSetting = this.incomeSettingService.getIncomeSetting(incomeSettingQueryObj);
		Boolean updatedFlag = false;
		if(oldSetting.getProportion().floatValue() != newProportion.floatValue()){
			IncomeSettingUpdateObj settingUpdateObj = new IncomeSettingUpdateObj();
			settingUpdateObj.setSettingId(oldSetting.getSettingId());
			settingUpdateObj.getNewUpdAttObj().setStatus(2);
			settingUpdateObj.getNewUpdAttObj().setEndDate(BaseUtils.getFirstDayOnCurrentMonth());
			IncomeSetting incomeSetting = new IncomeSetting();
			incomeSetting.setStatus(1);
			incomeSetting.setRuleId(0);
			incomeSetting.setType(1);
			incomeSetting.setProportion(newProportion);
			incomeSetting.setSettingLevel("0");
			incomeSetting.setUsingDate(BaseUtils.getFirstDayOnCurrentMonth());
			this.incomeSettingService.updateDynamic(settingUpdateObj, incomeSetting);
			updatedFlag = true;
		}
		resMap.put("updatedFlag", updatedFlag);
		resMap.put("result", true);
		if (updatedFlag) 
			log.info(loginedUser.getName()+"-修改了基础提成比例为" + newProportion*100 + "%");
		return resMap;
	}
	
	/**
	 * 获取员工信息列表
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getUserList(UserQueryObj queryObj,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		queryObj.setLevelNotEq("X");
		queryObj.setUserRole(1);
		queryObj.setStatus(1);
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order("level"));
		queryObj.setSort(orders);
		resMap.put("users", this.userService.queryUser(queryObj));
		return resMap;
	}
	
	/**
	 * 获取员工信息列表
	 * @param queryObj
	 * @param request
	 */
	@RequestMapping("/getUserIncomeSetting/{userId}")
	public @ResponseBody Map<String,Object> getUserIncomeSetting(HttpServletRequest request,@PathVariable Integer userId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin() || userId == null)
			return resMap;
		User user = this.userService.getUserById(userId);
		if(user == null) return resMap;
		VUserIncomeSettingQueryObj queryObj = new VUserIncomeSettingQueryObj();
		queryObj.setDetailContentLike("%"+user.getLevel()+"%");
		queryObj.setSettingLevel(user.getLevel());
		queryObj.setUserIdEqOrNull(userId);
		resMap.put("userSettings", this.vUserIncomeSettingService.queryUserSpecial(queryObj));
		
		SpecialSettingQueryObj specialSettingQueryObj = new SpecialSettingQueryObj();
		specialSettingQueryObj.setUserId(userId);
		specialSettingQueryObj.setType(2);
		List<SpecialSetting> specialSettings = this.specialSettingService.querySpecialSetting(specialSettingQueryObj);
		ReachSettingQueryObj reachSettingQueryObj = new ReachSettingQueryObj();
		if(!"B".equals(user.getLevel())){
			if(specialSettings != null && specialSettings.size() == 1){
				resMap.put("specialReach", this.reachSettingService.getReachSettingById(specialSettings.get(0).getSettingId()));
			} else {
				reachSettingQueryObj.setSettingLevel(user.getLevel());
				reachSettingQueryObj.setType(1);
				reachSettingQueryObj.setStatus(1);
				resMap.put("commonReach",this.reachSettingService.queryReachSetting(reachSettingQueryObj).get(0));
			}
		}
		return resMap;
	}
	
	/**
	 * 获取员工对应主线下各级配置情况
	 * @param userId
	 * @param ruleId
	 * @param request
	 */
	@RequestMapping("/getIncomeSettingLine")
	public @ResponseBody Map<String,Object> getIncomeSettingLine(HttpServletRequest request, Integer userId, Integer ruleId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin() || userId == null)
			return resMap;
		User currentUser = this.userService.getUserById(userId);
		IncomeRule incomeRule = this.incomeRuleService.getIncomeRuleById(ruleId);
		resMap.put("currentRule", incomeRule);
		resMap.put("currentUser", currentUser);
		//获取该主线各个等级正常配置
		IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
		incomeSettingQueryObj.setRuleId(ruleId);
		incomeSettingQueryObj.setType(1);
		incomeSettingQueryObj.setStatus(1);
		resMap.put("commonSettings", this.incomeSettingService.queryIncomeSetting(incomeSettingQueryObj));
		//获取请求用户的特殊配置
		VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vUserIncomeSettingQueryObj.setUserId(userId);
		vUserIncomeSettingQueryObj.setRuleId(ruleId);
		List<VUserIncomeSetting> currentUserSetting = this.vUserIncomeSettingService.queryVUserIncomeSetting(vUserIncomeSettingQueryObj);
		if(currentUserSetting != null && currentUserSetting.size() == 1)
			resMap.put("currentUserSetting", currentUserSetting.get(0));
		if("E".equals(currentUser.getLevel())) {
			User uplineUser = this.userService.getUserById(currentUser.getUplineUser()); 
			if("D".equals(uplineUser.getLevel())){
				Float maxD = this.getSpecialSettingByUser(uplineUser, ruleId);
				if( maxD != null) resMap.put("maxD", maxD);
				User grandUser = this.userService.getUserById(uplineUser.getUserId());
				if("C".equals(grandUser.getLevel())){
					Float maxC = this.getSpecialSettingByUser(grandUser, ruleId);
					if( maxC != null) resMap.put("maxC", maxC);
				}
			} else if("C".equals(uplineUser.getLevel())) {
				Float maxC = this.getSpecialSettingByUser(uplineUser, ruleId);
				if( maxC != null) resMap.put("maxC", maxC);
			}
		}else if("C".equals(currentUser.getLevel())){
			String nextLevel = this.getNextLevel(incomeRule.getDetailContent(), currentUser.getLevel());
			if("D".equals(nextLevel)){
				Float maxD = this.getDownlineSpecialMaxByUserId(currentUser.getUserId(), nextLevel, ruleId);
				if( maxD != null) resMap.put("maxD", maxD);
				
				String grandLevel = this.getNextLevel(incomeRule.getDetailContent(), "D");
				if( "E".equals(grandLevel)){
					UserQueryObj userQueryObj = new UserQueryObj();
					userQueryObj.setGrandUserId(userId);
					List<User> lvEUsers = this.userService.queryUser(userQueryObj);
					List<Integer> downlineUserIds = new ArrayList<Integer>();
					for (User downlineUser : lvEUsers) {
						downlineUserIds.add(downlineUser.getUserId());
					}
					Float maxE = this.getDownlineSpecialMaxByUserId(downlineUserIds, "E", ruleId);
					if(maxE != null) resMap.put("maxE", maxE);
				}
				
			} 
		} else if("D".equals(currentUser.getLevel())) {
			String nextLevel = this.getNextLevel(incomeRule.getDetailContent(), currentUser.getLevel());
			String preLevel = this.getPreLevel(incomeRule.getDetailContent(), currentUser.getLevel());
			if("C".equals(preLevel)){
				User uplineUser = this.userService.getUserById(currentUser.getUplineUser());
				Float maxC = this.getSpecialSettingByUser(uplineUser, ruleId);
				if(maxC != null) resMap.put("maxC", maxC);
			}
			if("E".equals(nextLevel)){
				Float maxE = this.getDownlineSpecialMaxByUserId(currentUser.getUserId(), nextLevel, ruleId);
				if(maxE != null) resMap.put("maxE", maxE);
			}
		}
		return resMap;
	}
	
	/**
	 * 获取员工对应主线下各级配置情况
	 * @param userId
	 * @param ruleId
	 * @param request
	 */
	@RequestMapping("/doUpdateUserSpecialSetting")
	public @ResponseBody Map<String,Object> doUpdateUserSpecialSetting(HttpServletRequest request, Integer userId, Integer ruleId, Float newSetting){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin() || userId == null || ruleId ==null || newSetting == null)
			return resMap;
		User currentUser = this.userService.getUserById(userId);
		VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vUserIncomeSettingQueryObj.setUserId(userId);
		vUserIncomeSettingQueryObj.setRuleId(ruleId);
		List<VUserIncomeSetting> currentUserSetting = this.vUserIncomeSettingService.queryVUserIncomeSetting(vUserIncomeSettingQueryObj);
		if(currentUserSetting != null && currentUserSetting.size() == 1){
			Integer specialSettingId = currentUserSetting.get(0).getSpecialSettingId();
			IncomeSetting special = this.incomeSettingService.getIncomeSettingById(specialSettingId);
			if(newSetting.floatValue() != special.getProportion().floatValue()){
				if(BaseUtils.getFirstDayOnCurrentMonth().equals(special.getUsingDate())){
					IncomeSettingUpdateObj settingUpdateObj = new IncomeSettingUpdateObj();
					settingUpdateObj.setSettingId(specialSettingId);
					settingUpdateObj.getNewUpdAttObj().setProportion(newSetting/100);
					this.incomeSettingService.updateDynamic(settingUpdateObj);
				} else {
					special.setProportion(newSetting/100);
					this.incomeSettingService.updateAndCreate(special, userId);
				}
			}else{
				
			}
		} else {
			IncomeSetting newObj = new IncomeSetting();
			newObj.setUsingDate(BaseUtils.getFirstDayOnCurrentMonth());
			newObj.setStatus(1);
			newObj.setSettingLevel(currentUser.getLevel());
			newObj.setType(2);
			newObj.setRuleId(ruleId);
			newObj.setProportion(newSetting/100);
			this.incomeSettingService.createIncomeSetting(newObj, userId);
		}
		resMap.put("result", true);
		return resMap;
	}
	
	/**
	 * 获取员工对应主线下各级配置情况
	 * @param userId
	 * @param ruleId
	 * @param request
	 */
	@RequestMapping("/doDeleteUserSpecialSetting")
	public @ResponseBody Map<String,Object> doDeleteUserSpecialSetting(HttpServletRequest request, Integer userId, Integer ruleId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin() || userId == null || ruleId ==null)
			return resMap;
		VUserIncomeSettingQueryObj vUserIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vUserIncomeSettingQueryObj.setUserId(userId);
		vUserIncomeSettingQueryObj.setRuleId(ruleId);
		List<VUserIncomeSetting> currentUserSetting = this.vUserIncomeSettingService.queryVUserIncomeSetting(vUserIncomeSettingQueryObj);
		if(currentUserSetting != null && currentUserSetting.size() == 1){
			Integer specialSettingId = currentUserSetting.get(0).getSpecialSettingId();
			IncomeSetting special = this.incomeSettingService.getIncomeSettingById(specialSettingId);
			if(BaseUtils.getFirstDayOnCurrentMonth().equals(special.getUsingDate())){
				this.incomeSettingService.deleteSpecialIncomeSetting(special, userId);
			} else {
				this.incomeSettingService.updateAndDelete(special, userId);
			}
		} 
		resMap.put("result", true);
		return resMap;
	}
	
	private Float getSpecialSettingByUser(User user, Integer ruleId){
		VUserIncomeSettingQueryObj vIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vIncomeSettingQueryObj.setDetailContentLike("%"+user.getLevel()+"%");
		vIncomeSettingQueryObj.setSettingLevel(user.getLevel());
		vIncomeSettingQueryObj.setUserId(user.getUserId());
		vIncomeSettingQueryObj.setRuleId(ruleId);
		List<VUserIncomeSetting> uplineSetting = this.vUserIncomeSettingService.queryUserSpecial(vIncomeSettingQueryObj);
		if(uplineSetting != null && uplineSetting.size() == 1){
			return uplineSetting.get(0).getSpecialSetting();
		}
		return null;
	}
	
	private Float getDownlineSpecialMaxByUserId(List<Integer> userIds, String nextLevel, Integer ruleId){
		VUserIncomeSettingQueryObj vIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vIncomeSettingQueryObj.setUplineUserIds(userIds);
		vIncomeSettingQueryObj.setDownlineUserLevel(nextLevel);
		vIncomeSettingQueryObj.setRuleId(ruleId);
		return this.vUserIncomeSettingService.getDownlineSpecialMax(vIncomeSettingQueryObj);
	}
	
	private Float getDownlineSpecialMaxByUserId(Integer userId, String nextLevel, Integer ruleId){
		List<Integer> uplineIds = new ArrayList<Integer>();
		uplineIds.add(userId);
		
		VUserIncomeSettingQueryObj vIncomeSettingQueryObj = new VUserIncomeSettingQueryObj();
		vIncomeSettingQueryObj.setUplineUserIds(uplineIds);
		vIncomeSettingQueryObj.setDownlineUserLevel(nextLevel);
		vIncomeSettingQueryObj.setRuleId(ruleId);
		return this.vUserIncomeSettingService.getDownlineSpecialMax(vIncomeSettingQueryObj);
	}
	
	private String getPreLevel(String ruleContent, String level){
		int index = ruleContent.indexOf(level);
		if(index > 0)
			return ruleContent.substring(index - 1, index);
		return null;
	}
	
	private String getNextLevel(String ruleContent, String level){
		int index = ruleContent.indexOf(level);
		if(index < ruleContent.length() - 1)
			return ruleContent.substring(index + 1, index + 2);
		return null;
	}
	
}