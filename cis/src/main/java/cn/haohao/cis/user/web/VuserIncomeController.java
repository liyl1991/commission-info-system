package cn.haohao.cis.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.haohao.cis.user.service.IVuserIncomeService;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/vuserIncome")
public class VuserIncomeController extends MultiActionController{
	
	@Autowired
	private IVuserIncomeService vuserIncomeService;
}