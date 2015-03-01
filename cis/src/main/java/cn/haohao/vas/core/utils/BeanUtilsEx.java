package cn.haohao.vas.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import cn.haohao.vas.core.vo.Result;

public class BeanUtilsEx {
	/**
	 * 克隆vo分页信息至view分页信息
	 * @param <T>
	 * @param <S>
	 * @param srcObj
	 * @param destObj
	 * @param tClass
	 */
/*	public static <T,S> void clone( DataTableResult<S> srcObj , DataTableResult<T> destObj,Class<T> tClass){
		if (srcObj==null || destObj==null) throw new RuntimeException("srcObj and destObj null denied!");
		destObj.setAaData(new ArrayList<T>());
		destObj.setiTotalDisplayRecords(srcObj.getiTotalDisplayRecords());
		destObj.setiTotalRecords(srcObj.getiTotalRecords());
		for(Object src:srcObj.getAaData()){
			try {
				T t = tClass.newInstance();
				BeanUtils.copyProperties(t, src);
				destObj.getAaData().add(t);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} 
		}
	}*/
	
	/**
	 * 克隆vo分页信息至view分页信息
	 * @param <T>
	 * @param <S>
	 * @param srcObj
	 * @param destObj
	 * @param tClass
	 */
	public static <T,S> void clone( Result<S> srcObj,Result<T> destObj,Class<T> tClass){
		if (srcObj==null || destObj==null) throw new RuntimeException("srcObj and destObj null denied!");
		destObj.setContent(new ArrayList<T>());
		destObj.setPage(srcObj.getPage());
		for(Object src:srcObj.getContent()){
			try {
				T t = tClass.newInstance();
				BeanUtils.copyProperties(t, src);
				destObj.getContent().add(t);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} 
		}
	}
	
	public static <T,S> void clone( List<S> srcObj,List<T> destObj,Class<T> tClass){
		if (srcObj==null || destObj==null) throw new RuntimeException("srcObj and destObj null denied!");
		for(Object src:srcObj){
			try {
				T t = tClass.newInstance();
				BeanUtils.copyProperties(t, src);
				destObj.add(t);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} 
		}
	}
}
