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
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.vo.UserQueryObj;
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
	 * 验证新增用户信息
	 */
	public static void userInputValidate(User newObj,IUserService userService){
		//身份证验证
		if(newObj.getIdCard()==null||newObj.getIdCard().trim().length()==0){
			throw new BusinessException("员工身份证号不能为空");
		}else
			IDCard.IDCardValidate(newObj.getIdCard());
		//身份证是否已存在
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setIdCard(newObj.getIdCard());
		if(userService.queryUser(queryObj).size()>0)
			throw new BusinessException("该员工身份证"+newObj.getIdCard()+",已存在,请确认");
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
	public static void main(String[] args) {
		Calendar ca = Calendar.getInstance();
		Calendar ca1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ca.setTime(new Date());
		try {
			ca1.setTime(sdf.parse("2016/01/11"));
			System.out.println("ca"+ca.get(Calendar.YEAR)+"-"+ca.get(Calendar.MONTH));
			System.out.println("ca1"+ca1.get(Calendar.YEAR)+"-"+ca1.get(Calendar.MONTH));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
