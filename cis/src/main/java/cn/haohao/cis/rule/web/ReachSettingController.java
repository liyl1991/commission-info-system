package cn.haohao.cis.rule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.haohao.cis.rule.service.IReachSettingService;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/reachSetting")
public class ReachSettingController extends MultiActionController{
	
	@Autowired
	private IReachSettingService reachSettingService;
}