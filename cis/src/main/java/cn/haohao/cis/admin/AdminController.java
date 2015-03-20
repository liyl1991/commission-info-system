package cn.haohao.cis.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.model.VuserIncome;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.user.service.IVuserIncomeService;
import cn.haohao.cis.user.vo.VuserIncomeQueryObj;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/admin")
public class AdminController extends MultiActionController{
	
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	@Autowired
	private IUserIncomeService userIncomeService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/goAdminMgr")
	public String goAdminMgr(HttpServletRequest request){
		
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin())
			return "mgr/adminMgr";
		else
			return "index";
	}
	
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getIncomeInfo(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		queryObj.setStatus(1);
		queryObj.setUserRole(1);
		Page<VuserIncome> downlineUsers = this.vuserIncomeService.pageQueryVuserIncome(queryObj);
		resMap.put("downlineUsers", downlineUsers);
		return resMap;
	}
	
	@RequestMapping("/goIncomeInfo/{id}")
	public String goIncomeInfo(@PathVariable("id") Integer id,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return "index";
		request.setAttribute("targetId", id);
		return "mgr/userIncomeInfo";
	}
	
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(UserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//历史收入记录
		Page<UserIncome> incomeList = this.userIncomeService.pageQueryUserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		queryObj.setIncomeDate(this.getPrecedingDate());
		List<UserIncome> preIncome = this.userIncomeService.queryUserIncome(queryObj);
		if(preIncome.size()!=0)
			resMap.put("preIncome", preIncome.get(0));
		
		//总记录
		resMap.put("incomeSum", this.userIncomeService.getIncomeSum(loginedUser.getUserId()));
		//员工信息
		resMap.put("userInfo", this.userService.getUserById(queryObj.getUserId()));
		return resMap;
	}
	
	private Date getPrecedingDate(){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int month = c.get(Calendar.MONTH),year = c.get(Calendar.YEAR);
		try {
			if(month!=0){
				return sdf.parse(year+"-"+month+"-01");
			}else{
				return sdf.parse((year-1)+"-12-01");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}