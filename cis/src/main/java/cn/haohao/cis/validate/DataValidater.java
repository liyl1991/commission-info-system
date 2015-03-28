package cn.haohao.cis.validate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.haohao.cis.income.model.UserIncome;
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
			//IDCard.IDCardValidate(newObj.getIdCard());
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
		//身份证是否已存在
		UserQueryObj queryObj = new UserQueryObj();
		queryObj.setIdCard(newObj.getIdCard());
		if(userService.queryUser(queryObj).size()>0)
			throw new BusinessException("该员工身份证"+newObj.getIdCard()+",已存在,请确认");
	}
	
	/**
	 * 验证新增用户收入信息
	 */
	public static void userIncomeInputValidate(UserIncome newObj,IUserService userService){
		User targetUser = userService.getUserById(newObj.getUserId());
		if(targetUser==null)
			throw new BusinessException("员工信息无效");
		/*if("X".equalsIgnoreCase(targetUser.getLevel())){
			
		}*/
		
	}
}
