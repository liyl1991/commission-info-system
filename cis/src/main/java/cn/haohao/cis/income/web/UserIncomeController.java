package cn.haohao.cis.income.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.income.model.UserIncome;
import cn.haohao.cis.income.service.IUserIncomeService;
import cn.haohao.cis.income.vo.UserIncomeQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/userIncome")
public class UserIncomeController extends MultiActionController{
	
	@Autowired
	private IUserIncomeService userIncomeService;
	
	@RequestMapping("/getIncomeInfo")
	public @ResponseBody Map<String,Object> getIncomeInfo(UserIncomeQueryObj queryObj,HttpServletRequest request){
		
		Map<String,Object> resMap = new HashMap<String,Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		//历史收入记录
		queryObj.setUserId(loginedUser.getUserId());
		Page<UserIncome> incomeList = this.userIncomeService.pageQueryUserIncome(queryObj);
		resMap.put("incomeList", incomeList);
		//上月记录
		queryObj.setIncomeDate(this.getPrecedingDate());
		List<UserIncome> preIncome = this.userIncomeService.queryUserIncome(queryObj);
		if(preIncome.size()!=0)
			resMap.put("preIncome", preIncome.get(0));
		
		//总记录
		resMap.put("incomeSum", this.userIncomeService.getIncomeSum(loginedUser.getUserId()));
		
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