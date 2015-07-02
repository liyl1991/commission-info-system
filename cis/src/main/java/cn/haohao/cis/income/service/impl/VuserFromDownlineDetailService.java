package cn.haohao.cis.income.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import cn.haohao.cis.income.dao.IVuserFromDownlineDetailDao;
import cn.haohao.cis.income.service.IVuserFromDownlineDetailService;
import cn.haohao.cis.income.model.VuserFromDownlineDetail;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailQueryObj;
import cn.haohao.cis.income.vo.VuserFromDownlineDetailUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class VuserFromDownlineDetailService implements IVuserFromDownlineDetailService {
	
	@Autowired
	private IVuserFromDownlineDetailDao vuserFromDownlineDetailDao;
	
	public void batchDeleteVuserFromDownlineDetail(List<VuserFromDownlineDetail> vuserFromDownlineDetailList) {
		vuserFromDownlineDetailDao.batchDelete(vuserFromDownlineDetailList);
	}

	public VuserFromDownlineDetail createVuserFromDownlineDetail(VuserFromDownlineDetail vuserFromDownlineDetail) {
		vuserFromDownlineDetailDao.create(vuserFromDownlineDetail);
		return vuserFromDownlineDetail;
	}

	public void deleteVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailQueryObj) {
		vuserFromDownlineDetailDao.delete(vuserFromDownlineDetailQueryObj);
	}

	public VuserFromDownlineDetail getVuserFromDownlineDetailById(Integer vuserFromDownlineDetailId) {
		return vuserFromDownlineDetailDao.getById(vuserFromDownlineDetailId);
	}

	public VuserFromDownlineDetail modifyVuserFromDownlineDetail(VuserFromDownlineDetail vuserFromDownlineDetail) {
		vuserFromDownlineDetailDao.update(vuserFromDownlineDetail);
		return vuserFromDownlineDetail;
	}

	public Page<VuserFromDownlineDetail> pageQueryVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailqueryObj) {
		return vuserFromDownlineDetailDao.pageCountByArgs(vuserFromDownlineDetailqueryObj);
	}
	
	public List<VuserFromDownlineDetail> queryVuserFromDownlineDetail(VuserFromDownlineDetailQueryObj vuserFromDownlineDetailqueryObj) {
		return vuserFromDownlineDetailDao.queryByArgs(vuserFromDownlineDetailqueryObj);
	}
	
	public void updateDynamic(VuserFromDownlineDetailUpdateObj updateObj){
		vuserFromDownlineDetailDao.updateDynamic(updateObj);
	}
	public Long countByArgs(VuserFromDownlineDetailQueryObj queryObj){
		return vuserFromDownlineDetailDao.countByArgs(queryObj);
	}
}