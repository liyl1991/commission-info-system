package cn.haohao.vas.datadict.service.impl;

import java.util.List;
import cn.haohao.vas.core.mgmt.BaseMgmt;
import cn.haohao.vas.core.vo.DataDict;
import cn.haohao.vas.core.vo.Result;
import cn.haohao.vas.datadict.dao.IDataDictDao;
import cn.haohao.vas.datadict.service.IDataDictService;
import cn.haohao.vas.datadict.vo.DataDictQueryObj;
public class DataDictService extends BaseMgmt implements IDataDictService {

	private static final long serialVersionUID = 1L;
	private IDataDictDao dataDictDao;

	public List<DataDict> getDataDictByParent(Integer dictType, Integer classId, Integer parentId) {
		DataDictQueryObj param = new DataDictQueryObj();
		param.setDictType(dictType);
		param.setClassId(classId);
		param.setParentId(parentId);
		return dataDictDao.queryByArgs(param);
	}

	public List<DataDict> getDataDictByClassId(Integer dictType, Integer classId) {
		DataDictQueryObj param = new DataDictQueryObj();
		param.setDictType(dictType);
		param.setClassId(classId);
		return dataDictDao.queryByArgs(param);
	}

	public Result<DataDict> pageQueryDataDict(DataDictQueryObj dataDictqueryObj) {
		return dataDictDao.pageCountByArgs(dataDictqueryObj);
	}

	public List<DataDict> queryDataDict(DataDictQueryObj dataDictqueryObj) {
		return dataDictDao.queryByArgs(dataDictqueryObj);
	}

	public void setDataDictDao(IDataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}
}
