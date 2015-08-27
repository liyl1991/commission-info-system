package cn.haohao.cis.income.web;

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

import cn.haohao.cis.income.model.VuserFromDownlineDetail;
import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.cis.income.service.IVuserFromDownlineDetailService;
import cn.haohao.cis.income.service.IVuserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeInputObj;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailQueryObj;
import cn.haohao.cis.income.vo.VuserIncomeQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.service.IUserService;
import cn.haohao.cis.utils.BaseUtils;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/userIncome")
public class UserIncomeController extends MultiActionController{
	
	@Autowired
	private IVuserIncomeService vuserIncomeService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IVuserFromDownlineDetailService vuserFromDownlineDetailService;
	
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		//历史收入记录
		queryObj.setUserId(loginedUser.getUserId());
		resMap.put("incomeList", this.vuserIncomeService.pageQueryVuserIncome(queryObj));
		//上月记录
		queryObj = new VuserIncomeQueryObj();
		queryObj.setUserId(loginedUser.getUserId());
		queryObj.setIncomeDate(BaseUtils.getSecondDayOnPreMonth());
		List<VuserIncome> list = this.vuserIncomeService.queryVuserIncome(queryObj);
		if(list != null && list.size() == 1)
			resMap.put("preIncome", list.get(0));
		//总记录
		resMap.put("incomeSum", this.vuserIncomeService.getIncomeSum(loginedUser.getUserId()));
		return resMap;
	}
	
	@RequestMapping("/getDownlineIncomeInfo")
	public @ResponseBody Map<String,Object> getDownlineIncomeInfo(VuserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		Integer userId = queryObj.getUserId();
		User targetUser = this.userService.getUserById(userId);
		if( targetUser == null || targetUser.getUplineUser() != loginedUser.getUserId()){//如果该员工不是登陆人下线则无法查看
			return resMap;
		}
		//历史收入记录
		Page<VuserIncome> incomeList = this.vuserIncomeService.pageQueryVuserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		queryObj = new VuserIncomeQueryObj();
		queryObj.setUserId(userId);
		queryObj.setIncomeDate(BaseUtils.getSecondDayOnPreMonth());
		List<VuserIncome> list = this.vuserIncomeService.queryVuserIncome(queryObj);
		if(list != null && list.size() == 1)
			resMap.put("preIncome", list.get(0));
		//总记录
		resMap.put("incomeSum", this.vuserIncomeService.getIncomeSum(userId));
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
	
	@RequestMapping("/goIncomeInfo")
	public String goIndex(HttpServletRequest request){
		request.setAttribute("indexActive", Constants.ACTIVE_CLASS);
		return "incomeInfo";
	}
	
	@RequestMapping("/goUserIncomeFrom/{year}/{month}/{userId}")
	public String goUserIncomeFrom(HttpServletRequest request,@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer userId){
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		return "incomeFrom";
	}
	
	@RequestMapping("/getUserIncomeFrom")
	public @ResponseBody Page<VuserFromDownlineDetail> getUserIncomeFrom(HttpServletRequest request, VuserFromDownlineDetailQueryObj queryObj, UserIncomeInputObj inputObj){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		queryObj.setIncomeDate(inputObj.getDate());
		queryObj.setUserId(loginedUser.getUserId());
		return this.vuserFromDownlineDetailService.pageQueryVuserFromDownlineDetail(queryObj);
	}
}