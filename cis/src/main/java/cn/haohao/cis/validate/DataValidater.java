package cn.haohao.cis.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.service.IIncomeSettingService;
import cn.haohao.cis.rule.vo.IncomeSettingQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
import cn.haohao.cis.utils.Arith;
import cn.haohao.vas.core.exception.BusinessException;

public class DataValidater {
	/**
	 * 验证用户更新信息
	 */
	public static void userUpdateValidate(User newObj,IUserService userService){
		User oldObj = userService.getUserById(newObj.getUserId());
		//身份证验证
		if(StringUtils.isNotEmpty(newObj.getIdCard())){
			IDCard.IDCardValidate(newObj.getIdCard());
			//查询身份证是否被使用
			if(!oldObj.getIdCard().equals(newObj.getIdCard())){
				UserQueryObj userQueryObj = new UserQueryObj();
				userQueryObj.setIdCard(newObj.getIdCard());
				List<User> userList = userService.queryUser(userQueryObj);
				if(userList.size()>1)
					throw new BusinessException("读取数据出错");
				else if(userList.size()==1){
					if(userList.get(0).getUserId()!=newObj.getUserId())
						throw new BusinessException("您输入的身份证号码已被使用，请确认！");
				}
			}
		}
		
		//等级验证
		if(oldObj.getLevel().compareToIgnoreCase(newObj.getLevel())>0){
			//自身等级必须低于上级
			User upline = userService.getUserById(newObj.getUplineUser());
			if(upline.getLevel().compareTo(newObj.getLevel()) >= 0){
				throw new BusinessException("员工等级必须比上级等级低");
			}
		}
		if(oldObj.getLevel().compareToIgnoreCase(newObj.getLevel())<0){
			//降级操作必须考虑所有下线还是低于自身等级
			UserQueryObj userQueryObj = new UserQueryObj();
			userQueryObj.setUplineUser(oldObj.getUserId());
			List<User> downlines = userService.queryUser(userQueryObj);
			for (User downline : downlines) {
				if(downline.getLevel().compareTo(newObj.getLevel())>=0){
					throw new BusinessException("要将员工降级到"+newObj.getLevel()+",必须该员工所有下线都低于这个等级。");
				}
			}
		}
	}
	/**
	 * 验证审核未通过用户更新信息
	 */
	public static void userUpdateAuditValidate(User newObj,IUserService userService){
		User oldObj = userService.getUserById(newObj.getUserId());
		//身份证验证
		if(StringUtils.isNotEmpty(newObj.getIdCard())){
			IDCard.IDCardValidate(newObj.getIdCard());
			//查询身份证是否被使用
			if(!oldObj.getIdCard().equals(newObj.getIdCard())){
				UserQueryObj userQueryObj = new UserQueryObj();
				userQueryObj.setIdCard(newObj.getIdCard());
				List<User> userList = userService.queryUser(userQueryObj);
				if(userList.size()>1)
					throw new BusinessException("读取数据出错");
				else if(userList.size()==1){
					if(userList.get(0).getUserId()!=newObj.getUserId())
						throw new BusinessException("您输入的身份证号码已被使用，请确认！");
				}
			}
		}
	}
	
	/**
	 * 验证新增用户信息
	 */
	public static void userInputValidate(User newObj,IUserService userService){
		//身份证验证
		if(newObj.getIdCard()==null||newObj.getIdCard().trim().length()==0){
			throw new BusinessException("员工身份证号不能为空");
		}else
			IDCard.IDCardValidate(newObj.getIdCard());
		if(newObj.getName()==null||newObj.getName().trim().length()==0)
			throw new BusinessException("员工姓名不能为空");
		//等级验证，自身等级必须低于上级
		User upline = userService.getUserById(newObj.getUplineUser());
		if(newObj.getLevel().compareToIgnoreCase(upline.getLevel())<=0){
			throw new BusinessException("员工等级必须比自己上线低");
		}
	}
	
	/**
	 * 验证新增用户收入信息
	 */
	public static void userIncomeInputValidate(UserIncome newObj,IUserService userService,IUserIncomeService userIncomeService){
		User targetUser = userService.getUserById(newObj.getUserId());
		if(targetUser==null)
			throw new BusinessException("员工信息无效");
		//录入的月份是否存在
		Calendar objDate = Calendar.getInstance();
		objDate.setTime(newObj.getIncomeDate());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM");
		UserIncomeQueryObj userIncomeQueryObj = new UserIncomeQueryObj();
		userIncomeQueryObj.setYearEq(objDate.get(Calendar.YEAR));
		userIncomeQueryObj.setMonthEq(objDate.get(Calendar.MONTH)+1);
		userIncomeQueryObj.setUserId(newObj.getUserId());
		if(userIncomeService.queryUserIncome(userIncomeQueryObj).size()>0)
			throw new BusinessException("该员工在"+sdf.format(newObj.getIncomeDate())+"月份已经有收入信息，请确认");
		//验证当前录入信息的月份不能大于当前月
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		if(objDate.get(Calendar.YEAR)>now.get(Calendar.YEAR))
			throw new BusinessException("您只能录入当前月份及之前的收入信息，请检查收入月份");
		else if(objDate.get(Calendar.YEAR)==now.get(Calendar.YEAR)
				&&objDate.get(Calendar.MONTH)>now.get(Calendar.MONTH))
			throw new BusinessException("您只能录入当前月份及之前的收入信息，请检查收入月份");
	}
	
	public static void userRegisterValidate(User registerUser ,String pwd,String affirmPwd,IUserService userService){
		if(registerUser.getIdCard()==null||registerUser.getIdCard().trim().length()==0){
			throw new BusinessException("请输入您的身份证号码!");
		}else
			IDCard.IDCardValidate(registerUser.getIdCard());
		
		UserQueryObj userQueryObj = new UserQueryObj();
		userQueryObj.setIdCard(registerUser.getIdCard());
		List<User> userList = userService.queryUser(userQueryObj);
		if(userList.size()!=0){
			throw new BusinessException("您输入的身份证号码已被注册，请确认！");
		}
		
		if(pwd==null||pwd.trim().length()==0){
			throw new BusinessException("请输入密码！");
		}
		if(affirmPwd==null||affirmPwd.trim().length()==0){
			throw new BusinessException("请输入确认密码！");
		}
		if(!pwd.equals(affirmPwd)){
			throw new BusinessException("两次输入密码不一致！");
		}
	}
	
	public static void incomeSettingUpdateValidate(List<IncomeSetting> newSettings, IIncomeSettingService incomeSettingService) {
		if(newSettings == null || newSettings.size() == 0)
			throw new BusinessException("系统出错！");
		Float newTotal = 0F;
		for (IncomeSetting incomeSetting : newSettings) {
			if(incomeSetting.getProportion().floatValue() > 100F){
				throw new BusinessException("比例填写不能大于100！");
			}
			newTotal = Arith.add(newTotal.floatValue(), incomeSetting.getProportion().floatValue());
		}
		IncomeSettingQueryObj incomeSettingQueryObj = new IncomeSettingQueryObj();
		incomeSettingQueryObj.setRuleId(0);
		incomeSettingQueryObj.setStatus(1);
		IncomeSetting baseIncomeSetting = incomeSettingService.getIncomeSetting(incomeSettingQueryObj);
		Float base = Arith.mul(baseIncomeSetting.getProportion().floatValue(), 100F);
		if((newTotal.floatValue() >= base && !"B".equals(newSettings.get(0).getSettingLevel()))
				||(newTotal.floatValue() > base && "B".equals(newSettings.get(0).getSettingLevel()))){
			throw new BusinessException("总提成比例不能为大于基础提成比例，且【B级】所剩提成比例不能为0！");
		}
	}
}
