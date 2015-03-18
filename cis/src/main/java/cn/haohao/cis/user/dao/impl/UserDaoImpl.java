package cn.haohao.cis.user.dao.impl;
//j-import-b
import cn.haohao.cis.user.model.User;
import cn.haohao.cis.user.dao.IUserDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class UserDaoImpl extends BaseDaoMybatisImpl<User> implements IUserDao {
	public UserDaoImpl(){
		this(User.class);
	}
	public UserDaoImpl(Class<User> type) {
		super(type);
	}
}