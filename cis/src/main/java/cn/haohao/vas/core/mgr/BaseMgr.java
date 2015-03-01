package cn.haohao.vas.core.mgr;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 原子业务基类
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class BaseMgr implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Log logger = LogFactory.getLog(getClass());

	protected NamedParameterJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
