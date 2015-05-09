package cn.haohao.cis.notice.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.service.INoticeService;
import cn.haohao.cis.notice.service.INoticeUnreadService;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.cis.notice.vo.NoticeUnreadQueryObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.utils.Constants;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/notice")
public class NoticeController extends MultiActionController{
	
	@Autowired
	private INoticeService noticeService;
	@Autowired 
	private INoticeUnreadService noticeUnreadService;
	
	@RequestMapping("/goIndexNotice")
	public String goLaunchNotice(HttpServletRequest request){
		Notice topNotice = this.noticeService.getTopNotice();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(topNotice!=null){
			NoticeQueryObj queryObj = new NoticeQueryObj();
			queryObj.setNoticeId(topNotice.getNoticeId());
			queryObj.setVisitorId(loginedUser.getUserId());
			List<Notice> list = this.noticeService.pageQueryIsReadByArgs(queryObj).getContent();
			if(list!=null&&list.size()!=0){
				NoticeUnreadQueryObj unreadQueryObj = new NoticeUnreadQueryObj();
				unreadQueryObj.setNoticeId(topNotice.getNoticeId());
				unreadQueryObj.setUserId(loginedUser.getUserId());
				this.noticeUnreadService.deleteNoticeUnread(unreadQueryObj);
			}
			request.setAttribute("notice", topNotice);
			return "noticeView";
		}
		else 
			return "redirect:/userIncome/goIncomeInfo";
	}
	
	@RequestMapping("/goNoticeList")
	public String goNoticeList(HttpServletRequest request){
		return "noticeList";
	}
	
	@RequestMapping("/getNoticeList")
	public @ResponseBody Map<String,Object> getNoticeList(HttpServletRequest request,NoticeQueryObj queryObj){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		queryObj.setStatus(1);
		queryObj.setVisitorId(loginedUser.getUserId());
		try{
			resMap.put("notices",this.noticeService.pageQueryIsReadByArgs(queryObj));
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/getNoticeListWithStatus")
	public @ResponseBody Map<String,Object> getNoticeListWithStatus(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		NoticeQueryObj queryObj = new NoticeQueryObj();
		queryObj.setStatus(1);
		queryObj.setVisitorId(loginedUser.getUserId());
		queryObj.setReadFlag(2);
		queryObj.setPageSize(100000);
		try{
			List<Notice> notices = this.noticeService.pageQueryIsReadByArgs(queryObj).getContent();
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for (Notice notice : notices) {
				Map<String,Object> noticeSimple = new HashMap<String, Object>();
				noticeSimple.put("id", notice.getNoticeId());
				noticeSimple.put("title", notice.getTitle());
				noticeSimple.put("createDate", notice.getCreateDate());
				noticeSimple.put("readFlag", notice.getReadFlag());
				result.add(noticeSimple);
			}
			
			resMap.put("result",result);
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/goNoticeView/{noticeId}")
	public String goNoticeView(HttpServletRequest request,@PathVariable Integer noticeId){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		NoticeQueryObj queryObj = new NoticeQueryObj();
		queryObj.setNoticeId(noticeId);
		queryObj.setVisitorId(loginedUser.getUserId());
		List<Notice> list = this.noticeService.pageQueryIsReadByArgs(queryObj).getContent();
		if(list!=null&&list.size()!=0){
			NoticeUnreadQueryObj unreadQueryObj = new NoticeUnreadQueryObj();
			unreadQueryObj.setNoticeId(noticeId);
			unreadQueryObj.setUserId(loginedUser.getUserId());
			this.noticeUnreadService.deleteNoticeUnread(unreadQueryObj);
			Notice notice = list.get(0);
			request.setAttribute("notice", notice);
		}
		//request.setAttribute("noticeMgrActive", Constants.ACTIVE_CLASS);
		return "noticeView";
	}
}