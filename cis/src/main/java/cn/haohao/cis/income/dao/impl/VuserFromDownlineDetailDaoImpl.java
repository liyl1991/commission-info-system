package cn.haohao.cis.income.dao.impl;
//j-import-b
import cn.haohao.cis.income.model.VuserFromDownlineDetail;
import cn.haohao.cis.income.dao.IVuserFromDownlineDetailDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VuserFromDownlineDetailDaoImpl extends BaseDaoMybatisImpl<VuserFromDownlineDetail> implements IVuserFromDownlineDetailDao {
	public VuserFromDownlineDetailDaoImpl(){
		this(VuserFromDownlineDetail.class);
	}
	public VuserFromDownlineDetailDaoImpl(Class<VuserFromDownlineDetail> type) {
		super(type);
	}
}