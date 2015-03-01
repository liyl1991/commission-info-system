package cn.haohao.vas.core.vo;

//j-import-b
import cn.haohao.vas.core.model.BaseModel;
//j-import-e

/**
 * 数据字典
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class DataDict extends BaseModel {

	private static final long serialVersionUID = 1L;

	//构造函数
	public DataDict(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 数据字典类型
	 */
	private java.lang.Integer dictType;
	/*
	 * 数据字典名称
	 */
	private java.lang.String dictName;
	/*
	 * 类id
	 */
	private java.lang.Integer classId;
	/*
	 * 类名
	 */
	private java.lang.String className;
	/*
	 * 记录id
	 */
	private java.lang.Integer entryId;
	/*
	 * 记录内容
	 */
	private java.lang.String entryName;
	/*
	 * 记录描述
	 */
	private java.lang.Object entryDesc;
	/*
	 * 序列
	 */
	private java.lang.Integer seq;
	/*
	 * 实体父节点id
	 */
	private java.lang.Integer parentId;
	/*
	 * objStatus
	 */
	private java.lang.Integer objStatus;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 数据字典类型
	 */
	public java.lang.Integer getDictType(){
		return this.dictType;
	}
	/*
	 * 设置 数据字典类型
	 */
	public void setDictType(java.lang.Integer dictType){
		this.dictType = dictType;
	}
	/*
	 * 获取 数据字典名称
	 */
	public java.lang.String getDictName(){
		return this.dictName;
	}
	/*
	 * 设置 数据字典名称
	 */
	public void setDictName(java.lang.String dictName){
		this.dictName = dictName;
	}
	/*
	 * 获取 类id
	 */
	public java.lang.Integer getClassId(){
		return this.classId;
	}
	/*
	 * 设置 类id
	 */
	public void setClassId(java.lang.Integer classId){
		this.classId = classId;
	}
	/*
	 * 获取 类名
	 */
	public java.lang.String getClassName(){
		return this.className;
	}
	/*
	 * 设置 类名
	 */
	public void setClassName(java.lang.String className){
		this.className = className;
	}
	/*
	 * 获取 记录id
	 */
	public java.lang.Integer getEntryId(){
		return this.entryId;
	}
	/*
	 * 设置 记录id
	 */
	public void setEntryId(java.lang.Integer entryId){
		this.entryId = entryId;
	}
	/*
	 * 获取 记录内容
	 */
	public java.lang.String getEntryName(){
		return this.entryName;
	}
	/*
	 * 设置 记录内容
	 */
	public void setEntryName(java.lang.String entryName){
		this.entryName = entryName;
	}
	/*
	 * 获取 记录描述
	 */
	public java.lang.Object getEntryDesc(){
		return this.entryDesc;
	}
	/*
	 * 设置 记录描述
	 */
	public void setEntryDesc(java.lang.Object entryDesc){
		this.entryDesc = entryDesc;
	}
	/*
	 * 获取 序列
	 */
	public java.lang.Integer getSeq(){
		return this.seq;
	}
	/*
	 * 设置 序列
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
	}
	/*
	 * 获取 实体父节点id
	 */
	public java.lang.Integer getParentId(){
		return this.parentId;
	}
	/*
	 * 设置 实体父节点id
	 */
	public void setParentId(java.lang.Integer parentId){
		this.parentId = parentId;
	}
	/*
	 * 获取 objStatus
	 */
	public java.lang.Integer getObjStatus(){
		return this.objStatus;
	}
	/*
	 * 设置 objStatus
	 */
	public void setObjStatus(java.lang.Integer objStatus){
		this.objStatus = objStatus;
	}
	//方法 end

	public Long getId(){
		if (dictType==null) return null;
		return (long)this.dictType;
	}
}
