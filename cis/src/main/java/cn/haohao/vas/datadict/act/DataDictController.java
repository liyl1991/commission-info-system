package cn.haohao.vas.datadict.act;

import java.util.List;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.haohao.vas.core.vo.DataDict;
import cn.haohao.vas.datadict.service.IDataDictService;
public class DataDictController extends MultiActionController {

	private Integer dictType = 9999;
	private IDataDictService dataDictService;

	public List<DataDict> getDataDictByParent(Integer classId, Integer parentId) {
		return dataDictService.getDataDictByParent(dictType, classId, parentId);
	}

	public List<DataDict> getDataDictByClassId(Integer classId) {
		return dataDictService.getDataDictByClassId(dictType, classId);
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}
}
