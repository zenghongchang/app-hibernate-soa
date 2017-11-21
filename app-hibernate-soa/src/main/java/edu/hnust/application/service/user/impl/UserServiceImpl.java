package edu.hnust.application.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hnust.application.dao.CommonDao;
import edu.hnust.application.orm.user.User;
import edu.hnust.application.service.user.UserService;
import edu.hnust.application.system.ReturnPageData;
import edu.hnust.application.util.Md5Util;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private CommonDao commonDao;
    
    /**
     * 用户登陆校验
     * 
     * @param loginName
     * @param password
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2017年11月18日]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public User validateUser(String loginName, String password) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return null;
        }
        password = new Md5Util(password).toMD5();
        User user = (User)commonDao.getRow(User.class, Restrictions.and(Restrictions.eq("loginName", loginName), Restrictions.eq("password", password)));
        if (null == user)
            return null;
        return user;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<User> queryUserByKey(Map<String, Object> map) {
        if (MapUtils.isEmpty(map))
            return null;
        return commonDao.fetchCollection(User.class, map);
    }
    
    @Override
    public User queryUserById(Integer id) {
        if (id == null)
            return null;
        return (User)commonDao.load(User.class, id);
    }
    
    @Override
    public Long getTotalUser(Map<String, Object> map) {
        if (MapUtils.isEmpty(map)) {
            return 0L;
        }
        return commonDao.fetchCollectionCount(User.class, map);
    }
    
    @Override
    public Boolean updateUser(User user) {
        if (null == user) {
            return false;
        }
        return commonDao.saveOrUpdate(user);
    }
    
    @Override
    public Boolean addUser(User user) {
        if (null == user) {
            return false;
        }
        return commonDao.saveOrUpdate(user);
    }
    
    @Override
    public Boolean deleteUserById(Integer id) {
        if (id == null) {
            return false;
        }
        return commonDao.deleteById(User.class, id);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ReturnPageData<User> pageQueryUser(Map<String, Object> requestArgs) {
        ReturnPageData<User> data = new ReturnPageData<User>();
        List<User> list = commonDao.fetchCollection(User.class, requestArgs);
        Long count = commonDao.fetchCollectionCount(User.class, requestArgs);
        data.setCollection(list);
        data.setCollectionCount(count);
        return data;
    }
}