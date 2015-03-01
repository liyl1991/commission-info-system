package cn.haohao.vas.core.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.haohao.vas.core.utils.IDataDictSupporter;
import cn.haohao.vas.core.vo.DataDict;

public class DataDictTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Log logger = LogFactory.getLog(this.getClass());

	private String id;
	private String class_id;
	private String style;

	// 级联相关属性
	private String parent_id;
	private String root_value = "";
	private String root_text = "----请选择---";
	private PageContext pageContext;
	private String inner_class = "";//html类名
	private String name;//表单名
	private static String dictControllerName = "DataDictController";
	private static IDataDictSupporter dataDictSupporter;

	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<select id='" + getId() + "' style='" + this.style + "' class='"+this.inner_class+"' name='"+this.name+"'>");
		// 默认空白行
		sb.append("<option value='").append(root_value).append("'>");
		sb.append(root_text).append("</option>");
		try {
			if (this.parent_id != null) {//若指定了父元素，则不初始化本下拉框
				sb.append(generTargetScript());
			} else {//否则加载数据字典列表
				List<DataDict> dicts = dataDictSupporter.getDataDictByClassId(Integer.valueOf(class_id));
				for (DataDict dict : dicts) {
					sb.append("<option value='").append(dict.getEntryId()).append("'>");
					sb.append(dict.getEntryName()).append("</option>");
				}
			}
		} catch (Exception e) {
			logger.error("数据字典加载失败");
		}
		sb.append("</select>");
		try {
			//System.out.println(sb.toString());
			logger.debug(sb.toString());
			out.print(sb.toString());
			//System.out.println("-------------------");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return SKIP_BODY;
	}

	private String generTargetScript() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("window.attachEvent(\"onload\", function(){");
		sb.append("document.getElementById(\"" + this.parent_id + "\").attachEvent(\"onchange\", function(){"
				+ dictControllerName + ".getDataDictByParent(" + this.class_id + ",parseInt(document.getElementById('"
				+ this.parent_id + "').value), function(result){ _addOptions(document.getElementById('" + this.id
				+ "'), result); })});");
		sb.append("});");
		// 添加下拉框选项函数
		sb.append("function _addOptions(ele, result){ ");
		//清空列表框
		sb.append("for(var i=ele.length-1;i>=0;i--){ele.remove(i);}");
		//添加请选择
		sb.append("var oOption = document.createElement(\"OPTION\");");
		sb.append("oOption.value = '"+root_value+"';");
		sb.append("oOption.text = '"+root_text+"';");
		sb.append("ele.options.add(oOption);");
		
		sb.append("for(var i=0;i<result.length;i++){");
		sb.append("var oOption = document.createElement(\"OPTION\");");
		sb.append("oOption.value = result[i].entryId;");
		sb.append("oOption.text = result[i].entryName;");
		sb.append("ele.options.add(oOption);");
		sb.append("}");
		// end for
		sb.append("};");
		// end function
		sb.append("</script>");
		return sb.toString();
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String classId) {
		class_id = classId;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setRoot_text(String rootText) {
		this.root_text = rootText;
	}

	public void setRoot_value(String rootValue) {
		this.root_value = rootValue;
	}

	public void setDataDictSupporter(IDataDictSupporter dataDictSupporter) {
		DataDictTag.dataDictSupporter = dataDictSupporter;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public void setDictControllerName(String dictControllerName) {
		DataDictTag.dictControllerName = dictControllerName;
	}

	public void setInner_class(String inner_class) {
		this.inner_class = inner_class;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
