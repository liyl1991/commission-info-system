package cis;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.haohao.cis.notice.service.INoticeService;
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.service.IIncomeSettingService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:cfg/applicationContext.xml"})
public class springTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private INoticeService noticeService;
	
	@Autowired
	private IIncomeSettingService incomeSettingService;

	@Test
	public void test() {
		/*Notice notice = new Notice();
		notice.setNoticeId(12);
		noticeService.createNotice(notice);*/
		IncomeSetting incomeSetting = new IncomeSetting();
		incomeSetting.setSettingId(222);
		incomeSetting.setType(1);
		incomeSetting.setProportion(0.1f);
		incomeSetting.setRuleId(1);
		incomeSetting.setSettingLevel("1");
		incomeSettingService.createIncomeSetting(incomeSetting);
		
	}

}
