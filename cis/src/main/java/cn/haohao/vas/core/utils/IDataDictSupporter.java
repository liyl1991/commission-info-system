package cn.haohao.vas.core.utils;

import java.io.Serializable;
import java.util.List;

import cn.haohao.vas.core.vo.DataDict;

public interface IDataDictSupporter extends Serializable {

	public List<DataDict> getDataDictByClassId(Integer classId);
	
	public DataDict getDataDictByEntryId(Integer classId, Integer entryId);
	
	public List<DataDict> getDataDictByParent(Integer classId, Integer parentId);
	
}
