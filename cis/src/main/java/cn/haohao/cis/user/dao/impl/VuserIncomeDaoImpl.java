package cn.haohao.cis.user.dao.impl;
//j-import-b
import cn.haohao.cis.user.model.VuserIncome;
import cn.haohao.cis.user.dao.IVuserIncomeDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VuserIncomeDaoImpl extends BaseDaoMybatisImpl<VuserIncome> implements IVuserIncomeDao {
	public VuserIncomeDaoImpl(){
		this(VuserIncome.class);
	}
	public VuserIncomeDaoImpl(Class<VuserIncome> type) {
		super(type);
	}
}