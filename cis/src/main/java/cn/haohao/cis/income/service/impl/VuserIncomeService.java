package cn.haohao.cis.income.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.income.dao.IVuserIncomeDao;
import cn.haohao.cis.income.service.IVuserIncomeService;
import cn.haohao.cis.income.model.VuserIncome;
import cn.haohao.cis.income.vo.VuserIncomeQueryObj;
import cn.haohao.cis.income.vo.VuserIncomeUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class VuserIncomeService implements IVuserIncomeService {
	
	@Autowired
	private IVuserIncomeDao vuserIncomeDao;
	
	public void batchDeleteVuserIncome(List<VuserIncome> vuserIncomeList) {
		vuserIncomeDao.batchDelete(vuserIncomeList);
	}

	public VuserIncome createVuserIncome(VuserIncome vuserIncome) {
		vuserIncomeDao.create(vuserIncome);
		return vuserIncome;
	}

	public void deleteVuserIncome(VuserIncomeQueryObj vuserIncomeQueryObj) {
		vuserIncomeDao.delete(vuserIncomeQueryObj);
	}

	public VuserIncome getVuserIncomeById(Integer vuserIncomeId) {
		return vuserIncomeDao.getById(vuserIncomeId);
	}

	public VuserIncome modifyVuserIncome(VuserIncome vuserIncome) {
		vuserIncomeDao.update(vuserIncome);
		return vuserIncome;
	}

	public Page<VuserIncome> pageQueryVuserIncome(VuserIncomeQueryObj vuserIncomequeryObj) {
		return vuserIncomeDao.pageCountByArgs(vuserIncomequeryObj);
	}
	
	public List<VuserIncome> queryVuserIncome(VuserIncomeQueryObj vuserIncomequeryObj) {
		return vuserIncomeDao.queryByArgs(vuserIncomequeryObj);
	}
	
	public void updateDynamic(VuserIncomeUpdateObj updateObj){
		vuserIncomeDao.updateDynamic(updateObj);
	}
	public Long countByArgs(VuserIncomeQueryObj queryObj){
		return vuserIncomeDao.countByArgs(queryObj);
	}

	@Override
	public VuserIncome getIncomeSum(Integer userId) {
		return this.vuserIncomeDao.getIncomeSum(userId);
	}
}