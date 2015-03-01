package cn.haohao.vas.datadict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import cn.haohao.vas.core.utils.IDataDictSupporter;
import cn.haohao.vas.core.vo.DataDict;
import cn.haohao.vas.datadict.service.IDataDictService;
import cn.haohao.vas.datadict.vo.DataDictQueryObj;

public class DataDictSupporter implements IDataDictSupporter, InitializingBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer dictType = 1111;
	private IDataDictService dataDictService;

	private static Map<Integer, List<DataDict>> dictMap;

	public void afterPropertiesSet() throws Exception {
		// loadFromDb();
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public void loadFromDb() {
		if (dictMap == null) {
			dictMap = new HashMap<Integer, List<DataDict>>();
		}
		dictMap.clear();
		DataDictQueryObj param = new DataDictQueryObj();
		param.setDictType(dictType);
		List<DataDict> dicts = dataDictService.queryDataDict(param);
		for (DataDict dict : dicts) {
			Integer key = dict.getClassId().intValue();
			if (dictMap.containsKey(key)) {// map中class已存在
				List<DataDict> entrys = dictMap.get(key);
				entrys.add(dict);
			} else {
				List<DataDict> entrys = new ArrayList<DataDict>();
				entrys.add(dict);
				dictMap.put(key, entrys);
			}
		}
	}

	public List<DataDict> getDataDictByClassId(Integer classId) {
		List<DataDict> dicts = dictMap.get(classId);
		if (dicts != null)
			return dicts;
		// TODO 若不存在则动态加载

		return new ArrayList<DataDict>(0);
	}

	public DataDict getDataDictByEntryId(Integer classId, Integer entryId) {
		List<DataDict> entrys = dictMap.get(classId);
		if (entrys != null) {
			for (DataDict dict : entrys) {
				if (entryId==null||dict.getEntryId()==null) return null;
				if (entryId.intValue()==dict.getEntryId().intValue()) {
					return dict;
				}
			}
		}
		return null;
	}

	public List<DataDict> getDataDictByParent(Integer classId, Integer parentId) {
		if (parentId==null) return new ArrayList<DataDict>(0);
		List<DataDict> entrys = dictMap.get(classId);
		List<DataDict> result = new ArrayList<DataDict>();
		if (entrys != null) {
			for (DataDict dict : entrys) {
				if (parentId.intValue()==dict.getParentId().intValue()) {
					result.add(dict);
				}
			}
		}
		return result;
	}
	public static final void setDictType(Integer dictType){
		DataDictSupporter.dictType = dictType;
	}
}
