package cn.haohao.vas.datadict.service;

import java.util.List;

import cn.haohao.vas.core.vo.DataDict;
import cn.haohao.vas.core.vo.Result;
import cn.haohao.vas.datadict.vo.DataDictQueryObj;

public interface IDataDictService {

	public Result<DataDict> pageQueryDataDict(DataDictQueryObj dataDictqueryObj);

	public List<DataDict> queryDataDict(DataDictQueryObj dataDictqueryObj);

	public List<DataDict> getDataDictByParent(Integer dictType, Integer classId, Integer parentId);
	
	public List<DataDict> getDataDictByClassId(Integer dictType, Integer classId);
}