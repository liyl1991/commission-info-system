package cn.haohao.cis.income.web;

import java.util.HashMap;
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
import cn.haohao.cis.user.service.IVuserIncomeService;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/userIncome")
public class UserIncomeController extends MultiActionController{
	
	@Autowired
	private IUserIncomeService userIncomeService;
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(UserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		//历史收入记录
		queryObj.setUserId(loginedUser.getUserId());
		Page<UserIncome> incomeList = this.userIncomeService.pageQueryUserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		resMap.put("preIncome", this.vuserIncomeService.getVuserIncomeById(loginedUser.getUserId()));
		
		//总记录
		resMap.put("incomeSum", this.userIncomeService.getIncomeSum(loginedUser.getUserId()));
		
		return resMap;
	}
	
	@RequestMapping("/getDownlineIncomeInfo")
	public @ResponseBody Map<String,Object> getDownlineIncomeInfo(UserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		VuserIncome preIncome = this.vuserIncomeService.getVuserIncomeById(queryObj.getUserId());
		if(preIncome==null||
				preIncome.getUplineUser().intValue() != loginedUser.getUserId().intValue()){//如果该员工不是登陆人下线则无法查看
			return resMap;
		}
		//历史收入记录
		Page<UserIncome> incomeList = this.userIncomeService.pageQueryUserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		resMap.put("preIncome", preIncome);
		
		//总记录
		resMap.put("incomeSum", this.userIncomeService.getIncomeSum(queryObj.getUserId()));
		
		return resMap;
	}
	
	@RequestMapping("/goDownlineIncome/{id}")
	public String goDownlineIncome(@PathVariable Integer id,HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		VuserIncome downlineIncomeInfo = this.vuserIncomeService.getVuserIncomeById(id);
		if(downlineIncomeInfo==null||
				downlineIncomeInfo.getUplineUser().intValue() != loginedUser.getUserId().intValue()){//如果该员工不是登陆人下线则无法查看
			return "redirect:/login/goIndex";
		}
		request.setAttribute("downlineActive", Constants.ACTIVE_CLASS);
		request.setAttribute("downlineIncomeInfo", downlineIncomeInfo);
		return "downlineIncome";
	}
}