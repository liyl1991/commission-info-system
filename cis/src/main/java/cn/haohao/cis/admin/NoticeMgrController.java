package cn.haohao.cis.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.service.INoticeService;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.cis.notice.vo.NoticeUpdateObj;
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.utils.Constants;
import cn.haohao.vas.core.exception.BusinessException;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/noticeMgr")
public class NoticeMgrController extends MultiActionController{
	
	@Autowired
	private INoticeService noticeService;
	
	private Log log = LogFactory.getLog("adminLog");
	@RequestMapping("/goLaunchNotice")
	public String goLaunchNotice(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("noticeMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/notice/launchNotice";
		}
		else
			return "index";
	}
	
	@RequestMapping("/goNoticeMgr")
	public String goNoticeMgr(HttpServletRequest request){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			request.setAttribute("noticeMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/notice/noticeMgr";
		}
		else
			return "index";
	}
	
	@RequestMapping("/goNoticeView/{noticeId}")
	public String goNoticeMgr(HttpServletRequest request,@PathVariable Integer noticeId){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			Notice notice = this.noticeService.getNoticeById(noticeId);
			request.setAttribute("notice", notice);
			request.setAttribute("noticeMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/notice/noticeView";
		}
		else
			return "index";
	}
	
	@RequestMapping("/goUpdateNotice/{noticeId}")
	public String goUpdateNotice(HttpServletRequest request,@PathVariable Integer noticeId){
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(loginedUser.isAdmin()){
			Notice notice = this.noticeService.getNoticeById(noticeId);
			request.setAttribute("notice", notice);
			request.setAttribute("noticeMgrActive", Constants.ACTIVE_CLASS);
			return "mgr/notice/updateNotice";
		}
		else
			return "index";
	}
	
	@RequestMapping("/doLaunchNotice")
	public @ResponseBody Map<String,Object> doLaunchNotice(HttpServletRequest request,Notice notice){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//设置属性
		if(notice.getTopFlag()==1)
			notice.setTopDate(new Date());
		notice.setCreateDate(new Date());
		notice.setCreateUser(loginedUser.getUserId());
		notice.setStatus(1);
		try{
			this.noticeService.createNotice(notice);
			resMap.put("result", true);
			this.log.info(loginedUser.getName()+"-发布公告->"+notice.getTitle());
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doUpdateNotice")
	public @ResponseBody Map<String,Object> doUpdateNotice(HttpServletRequest request,Notice notice){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		//设置属性
		NoticeUpdateObj updateObj = new NoticeUpdateObj();
		updateObj.setNoticeId(notice.getNoticeId());
		if(notice.getTopFlag()==1)
			notice.setTopDate(new Date());
		else
			notice.setTopDate(null);;
		updateObj.setNewUpdAttObj(notice);
		try{
			this.noticeService.updateDynamic(updateObj);
			resMap.put("result", true);
			this.log.info(loginedUser.getName()+"-修改了公告公告->"+notice.getTitle());
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doSetTopNotice/{noticeId}")
	public @ResponseBody Map<String,Object> doSetTopNotice(HttpServletRequest request,@PathVariable Integer noticeId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		Notice notice = this.noticeService.getNoticeById(noticeId);
		NoticeUpdateObj updateObj = new NoticeUpdateObj();
		try{
			if(notice==null){
				resMap.put("result", false);
				throw new BusinessException("请求数据有误");
			}
			if(notice.getTopFlag()==1){
				updateObj.getNewUpdAttObj().setTopFlag(2);
				updateObj.setSetTopDateNull("set");
			}
			else if(notice.getTopFlag()==2){
				updateObj.getNewUpdAttObj().setTopFlag(1);
				updateObj.getNewUpdAttObj().setTopDate(new Date());
			}
			updateObj.setNoticeId(noticeId);
			this.noticeService.updateDynamic(updateObj);
			resMap.put("result", true);
			if(notice.getTopFlag()==1)
				this.log.info(loginedUser.getName()+"-取消置顶公告->"+notice.getTitle());
			else if(notice.getTopFlag()==2)
				this.log.info(loginedUser.getName()+"-置顶公告->"+notice.getTitle());
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/doDelete/{noticeId}")
	public @ResponseBody Map<String,Object> doDelete(HttpServletRequest request,@PathVariable Integer noticeId){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		NoticeUpdateObj updateObj = new NoticeUpdateObj();
		Notice notice = this.noticeService.getNoticeById(noticeId);
		try{
			updateObj.getNewUpdAttObj().setStatus(2);
			updateObj.setNoticeId(noticeId);
			this.noticeService.updateDynamic(updateObj);
			resMap.put("result", true);
			this.log.info(loginedUser.getName()+"-删除公告->"+notice.getTitle()+",id->"+noticeId);
		}catch(BusinessException be){
			resMap.put("result", false);
			resMap.put("msg", be.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("result", false);
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
	
	@RequestMapping("/getNoticeList")
	public @ResponseBody Map<String,Object> getNoticeList(HttpServletRequest request,NoticeQueryObj queryObj){
		Map<String,Object> resMap = new HashMap<String, Object>();
		User loginedUser = (User)request.getSession().getAttribute(Constants.LOGINED_USER_BEAN_NAME);
		if(!loginedUser.isAdmin())
			return resMap;
		queryObj.setStatus(1);
		try{
			resMap.put("notices",this.noticeService.pageQueryNotice(queryObj));
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("msg", "操作失败");
		}
		return resMap;
	}
}