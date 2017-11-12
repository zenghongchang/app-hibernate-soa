package edu.hnust.application.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import edu.hnust.application.page.PageBean;

@Component("commonDao")
public class CommonDao {
    
    @Resource
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getNewSession() {
        return this.sessionFactory.openSession();
    }
    
    public Object load(Class<?> clazz, String id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            Object o = session.get(clazz, Long.valueOf(id));
            
            tr.commit();
            session.close();
            return o;
        } catch (Exception e) {
            tr.rollback();
            session.close();
            return null;
        }
    }
    
    public Object load(Class<?> clazz, Long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            Object o = session.get(clazz, id);
            
            tr.commit();
            session.close();
            return o;
        } catch (Exception e) {
            tr.rollback();
            session.close();
            return null;
        }
    }
    
    public Object load(Class<?> clazz, int id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            Object o = session.get(clazz, Integer.valueOf(id));
            tr.commit();
            session.close();
            return o;
        } catch (Exception e) {
            tr.rollback();
            session.close();
            return null;
        }
    }
    
    public Boolean merge(Object obj) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            session.merge(obj);
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.close();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean save(Object obj) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            session.save(obj);
            tr.commit();
            session.close();
        } catch (Exception e) {
            tr.rollback();
            session.close();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean saveOrUpdate(Object obj) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            session.saveOrUpdate(obj);
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.close();
            return false;
        }
        return true;
    }
    
    public Boolean saveOrUpdateAll(List<?> objList) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            for (Object obj : objList) {
                session.saveOrUpdate(obj);
            }
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.close();
            return false;
        }
        return true;
    }
    
    public void delete(Object obj) {
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            session.delete(obj);
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.close();
        }
    }
    
    public void deleteAll(Collection<Object> entities) {
        if (null == entities) {
            return;
        }
        Session session = null;
        Transaction tr = null;
        try {
            session = getSessionFactory().openSession();
            session.setCacheMode(CacheMode.IGNORE);
            tr = (null != session.getTransaction()) && (session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            for (Iterator<Object> localIterator = entities.iterator(); localIterator.hasNext();) {
                Object e = localIterator.next();
                session.delete(e);
            }
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.close();
        }
    }
    
    public Boolean checkExist(Class<?> clazz, Criterion simpleEx) {
        if (null == getRow(clazz, simpleEx)) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    @SuppressWarnings({"rawtypes"})
    public Object getRow(Class<?> clazz, Criterion simpleEx) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            List result = session.createCriteria(clazz).add(simpleEx).setMaxResults(1).list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, Criterion simpleEx, Session session) {
        List result = session.createCriteria(clazz).add(simpleEx).setMaxResults(1).list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, Criterion simpleEx, Order sortOrder) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz).add(simpleEx).setMaxResults(1);
            cri.addOrder(sortOrder);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    public Boolean checkExist(Class<?> clazz, List<Criterion> simpleExList) {
        if (null == getRow(clazz, simpleExList)) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean checkExist(Class<?> clazz, Criterion simpleEx, Session sess) {
        if (null == getRow(clazz, simpleEx, sess)) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean checkExist(Class<?> clazz, List<Criterion> simpleExList, Session sess) {
        if (null == getRow(clazz, simpleExList, sess)) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, Criterion[] simpleExList) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.setMaxResults(1);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.setMaxResults(1);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Order orderBy) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.setMaxResults(1);
            cri.addOrder(orderBy);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Session session) {
        Criteria cri = session.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.setMaxResults(1);
        List result = cri.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, Criterion criterion, Order orderBy, Session session) {
        Criteria cri = session.createCriteria(clazz);
        cri.add(criterion);
        cri.setMaxResults(1);
        cri.addOrder(orderBy);
        List result = cri.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Session session) {
        Criteria cri = session.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.setMaxResults(1);
        cri.addOrder(orderBy);
        List result = cri.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            for (String criName : criterias.keySet()) {
                cri.createCriteria(criName, criName.replace('.', '_'));
                
                if (null != criterias.get(criName)) {
                    for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                        cri.add(simpleEx);
                    }
                }
            }
            cri.setMaxResults(1);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias, Session session) {
        try {
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            for (String criName : criterias.keySet()) {
                cri.createCriteria(criName, criName.replace('.', '_'));
                if (null != criterias.get(criName)) {
                    for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                        cri.add(simpleEx);
                    }
                }
            }
            cri.setMaxResults(1);
            List result = cri.list();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRow(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias, Order orderBy) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            for (String criName : criterias.keySet()) {
                cri.createCriteria(criName, criName.replace('.', '_'));
                
                if (null != criterias.get(criName)) {
                    for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                        cri.add(simpleEx);
                    }
                }
            }
            cri.addOrder(orderBy);
            cri.setMaxResults(1);
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRowAsc(Class<?> clazz, Criterion simpleEx, String orderBy) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz).add(simpleEx).setMaxResults(1);
            cri.addOrder(Order.asc(orderBy));
            List result = cri.list();
            session.close();
            if (result.size() > 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public Object getRowAsc(Class<?> clazz, Criterion simpleEx, String orderBy, Session session) {
        Criteria cri = session.createCriteria(clazz);
        cri.add(simpleEx);
        cri.setMaxResults(1);
        cri.addOrder(Order.asc(orderBy));
        List result = cri.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion simpleEx) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (simpleEx != null) {
                cri.add(simpleEx);
            }
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion simpleEx, String[] fields) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (simpleEx != null) {
                cri.add(simpleEx);
            }
            ProjectionList pl = Projections.projectionList();
            pl = addFields(pl, fields);
            cri.setProjection(pl);
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.addOrder(orderBy);
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, List<Order> orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Object localObject = simpleExList.iterator(); ((Iterator)localObject).hasNext();) {
                Criterion simpleEx = (Criterion)((Iterator)localObject).next();
                cri.add(simpleEx);
            }
            for (Order order : orderBys) {
                cri.addOrder(order);
            }
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Integer pageSize, List<Order> orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            for (Object localObject = simpleExList.iterator(); ((Iterator)localObject).hasNext();) {
                Criterion simpleEx = (Criterion)((Iterator)localObject).next();
                cri.add(simpleEx);
            }
            for (Order order : orderBys) {
                cri.addOrder(order);
            }
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Integer pageSize, Integer pageNumber, Order orderBy) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            for (Object localObject = simpleExList.iterator(); ((Iterator)localObject).hasNext();) {
                Criterion simpleEx = (Criterion)((Iterator)localObject).next();
                cri.add(simpleEx);
            }
            if (orderBy != null) {
                cri.addOrder(orderBy);
            }
            cri.setFirstResult((pageNumber.intValue() - 1) * pageSize.intValue());
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Integer pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.addOrder(orderBy);
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Integer pageSize, Session session) {
        List result = new ArrayList();
        Criteria cri = session.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        cri.setMaxResults(pageSize.intValue());
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion critrion, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        if (null != critrion) {
            cri.add(critrion);
        }
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Session sess, List<Order> orderBys) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        for (Iterator<Criterion> localObject = simpleExList.iterator(); ((Iterator)localObject).hasNext();) {
            Criterion simpleEx = (Criterion)((Iterator)localObject).next();
            cri.add(simpleEx);
        }
        for (Order order : orderBys) {
            cri.addOrder(order);
        }
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResultDistinct(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, String distinct, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Integer pageSize, String distinct, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        cri.setMaxResults(pageSize.intValue());
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion simpleEx, Order orderBy, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        if (simpleEx != null) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion simpleEx, Order orderBy) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (simpleEx != null) {
                cri.add(simpleEx);
            }
            cri.addOrder(orderBy);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion simpleEx, Order orderBy, Integer pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (simpleEx != null) {
                cri.add(simpleEx);
            }
            cri.addOrder(orderBy);
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    public List<?> getResult(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, String distinct, String groupProperty, Session session) {
        Criteria cri = session.createCriteria(clazz);
        for (Criterion simpleEx : simpleExList) {
            cri.add(simpleEx);
        }
        cri.addOrder(orderBy);
        ProjectionList pl = Projections.projectionList();
        pl.add(Projections.distinct(Projections.property(distinct)));
        
        List<?> obj = cri.setProjection(pl).list();
        return obj;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, int pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            cri.add(criterion);
            cri.setMaxResults(pageSize);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, int pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.setMaxResults(pageSize);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, int pageSize, int pageNumber) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            if (simpleExList != null) {
                for (Criterion simpleEx : simpleExList) {
                    cri.add(simpleEx);
                }
            }
            cri.setFirstResult((pageNumber - 1) * pageSize);
            cri.setMaxResults(pageSize);
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, int pageSize, int pageNumber, List<Order> orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Object localObject = simpleExList.iterator(); ((Iterator)localObject).hasNext();) {
                Criterion simpleEx = (Criterion)((Iterator)localObject).next();
                cri.add(simpleEx);
            }
            for (Order order : orderBys) {
                cri.addOrder(order);
            }
            cri.setFirstResult((pageNumber - 1) * pageSize);
            cri.setMaxResults(pageSize);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void _parseCriterias(Criteria cri, Map<String, List<Criterion>> criterias, List<Criterion> criterions) {
        List conditions = new ArrayList();
        if (null != criterions) {
            for (Criterion simpleEx : criterions) {
                String condition = simpleEx.toString();
                if (!conditions.contains(condition)) {
                    cri.add(simpleEx);
                    conditions.add(condition);
                }
            }
        }
        for (String criName : criterias.keySet()) {
            cri.createCriteria(criName, criName.replace('.', '_'));
            if (null != criterias.get(criName)) {
                for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                    String condition = simpleEx.toString();
                    if (!conditions.contains(condition)) {
                        cri.add(simpleEx);
                        conditions.add(condition);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias, Integer pageSize, List<Order> orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, simpleExList);
            for (Order order : orderBys) {
                cri.addOrder(order);
            }
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, Map<String, List<Criterion>> criterias) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (null != criterion) {
                cri.add(criterion);
            }
            _parseCriterias(cri, criterias, null);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, Map<String, List<Criterion>> criterias, Order orderBy) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            if (null != criterion) {
                cri.add(criterion);
            }
            _parseCriterias(cri, criterias, null);
            cri.addOrder(orderBy);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, Map<String, List<Criterion>> criterias, Order orderBy, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        if (null != criterion) {
            cri.add(criterion);
        }
        _parseCriterias(cri, criterias, null);
        cri.addOrder(orderBy);
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, Map<String, List<Criterion>> criterias, Session sess) {
        List result = new ArrayList();
        Criteria cri = sess.createCriteria(clazz);
        if (null != criterion) {
            cri.add(criterion);
        }
        for (String criName : criterias.keySet()) {
            cri.createCriteria(criName, criName.replace('.', '_'));
            if (null != criterias.get(criName)) {
                for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                    cri.add(simpleEx);
                }
            }
        }
        result = cri.list();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, Criterion criterion, Map<String, List<Criterion>> criterias, Order orderBy, Integer pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (null != criterion) {
                cri.add(criterion);
            }
            _parseCriterias(cri, criterias, null);
            cri.addOrder(orderBy);
            cri.setMaxResults(pageSize.intValue());
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResultDistinct(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            if (null != criterions) {
                for (Criterion simpleEx : criterions) {
                    cri.add(simpleEx);
                }
            }
            for (String criName : criterias.keySet()) {
                cri.createCriteria(criName, criName.replace('.', '_'));
                if (null != criterias.get(criName)) {
                    for (Criterion simpleEx : (List<Criterion>)criterias.get(criName)) {
                        cri.add(simpleEx);
                    }
                }
            }
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, List<Order> orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            for (Order orderBy : orderBys) {
                cri.addOrder(orderBy);
            }
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, Order orderBy) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            cri.addOrder(orderBy);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, Order orderBy, Integer pageSize, Integer pageNumber) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            cri.addOrder(orderBy);
            cri.setFirstResult((pageNumber.intValue() - 1) * pageSize.intValue());
            cri.setMaxResults(pageSize.intValue());
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, edu.hnust.application.util.Order orderBy, Integer pageSize, Integer pageNumber) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            if (orderBy != null && StringUtils.isNotEmpty(orderBy.getPropertyName())) {
                if (orderBy.isAscending()) {
                    cri.addOrder(Order.asc(orderBy.getPropertyName()));
                } else {
                    cri.addOrder(Order.desc(orderBy.getPropertyName()));
                }
            }
            cri.setFirstResult((pageNumber.intValue() - 1) * pageSize.intValue());
            cri.setMaxResults(pageSize.intValue());
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, Order orderBy, Integer pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            cri.addOrder(orderBy);
            cri.setMaxResults(pageSize.intValue());
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = cri.list();
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, edu.hnust.application.util.Order orderBy, Integer pageSize) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            if (orderBy != null && StringUtils.isNotEmpty(orderBy.getPropertyName())) {
                if (orderBy.isAscending()) {
                    cri.addOrder(Order.asc(orderBy.getPropertyName()));
                } else {
                    cri.addOrder(Order.desc(orderBy.getPropertyName()));
                }
            }
            cri.setMaxResults(pageSize.intValue());
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public Long getResultCount(Class<?> clazz, Criterion simpleEx) {
        Session session = null;
        Long count = Long.valueOf(0L);
        try {
            session = getSessionFactory().openSession();
            List result = session.createCriteria(clazz).add(simpleEx).setProjection(Projections.projectionList().add(Projections.rowCount())).list();
            session.close();
            if (CollectionUtils.isNotEmpty(result)) {
                count = new Long(String.valueOf(result.get(0)));
            } else {
                count = new Long(String.valueOf(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return Long.valueOf(0L);
        }
        return count;
    }
    
    @SuppressWarnings("rawtypes")
    public Long getResultCount(Class<?> clazz, List<Criterion> simpleExList) {
        Session session = null;
        Long count = Long.valueOf(0L);
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            cri.setProjection(Projections.projectionList().add(Projections.rowCount()));
            List result = cri.list();
            session.close();
            if (CollectionUtils.isNotEmpty(result)) {
                count = new Long(String.valueOf(result.get(0)));
            } else {
                count = new Long(String.valueOf(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return Long.valueOf(0L);
        }
        return count;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Long> getResultCount(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias) {
        Session session = null;
        List counts = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            ProjectionList pl = Projections.projectionList();
            pl.add(Projections.count("id"));
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            counts = new ArrayList();
            if (0 == cri.setProjection(pl).list().size())
                counts.add(Long.valueOf(0L));
            else {
                counts.add((Long)cri.setProjection(pl).list().get(0));
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return counts;
    }
    
    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public List<Long> getResultCount(Class<?> clazz, List<Criterion> criterions, List<String> criterias) {
        Session session = null;
        List counts = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion criterion : criterions) {
                cri.add(criterion);
            }
            for (String criName : criterias) {
                cri.createCriteria(criName, criName.replace('.', '_'), 1);
            }
            ProjectionList pl = Projections.projectionList();
            pl.add(Projections.count("id"));
            counts = new ArrayList();
            counts.add((Long)cri.setProjection(pl).list().get(0));
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
        return counts;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias, Session session) {
        try {
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, simpleExList);
            return cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult(Class<?> clazz, List<Criterion> simpleExList, Map<String, List<Criterion>> criterias, Session session, String[] orderBys) {
        try {
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, simpleExList);
            for (String orderBy : orderBys) {
                if (orderBy.contains("asc"))
                    cri.addOrder(Order.asc(orderBy.replace("_asc", "")));
                else if (orderBy.contains("desc")) {
                    cri.addOrder(Order.desc(orderBy.replace("_desc", "")));
                }
            }
            return cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResult2(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, String type, String[] orderBys) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            for (String orderBy : orderBys) {
                if (orderBy.contains("asc"))
                    cri.addOrder(Order.asc(orderBy.replace("_asc", "")));
                else if (orderBy.contains("desc")) {
                    cri.addOrder(Order.desc(orderBy.replace("_desc", "")));
                }
            }
            result = cri.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object fetchRow(Class<?> clazz, Map<String, Object> query) {
        List criterions = getRequestRestriction(query);
        return getRow(clazz, criterions);
    }
    
    public ProjectionList addFields(ProjectionList pl, String[] fields) {
        for (String field : fields)
            pl.add(Projections.property(field), field);
        return pl;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List getResult(Class<?> clazz, List<Criterion> criterions, Map<String, List<Criterion>> criterias, int pageSize, int pageNumber) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            _parseCriterias(cri, criterias, criterions);
            ProjectionList ProjectionList = Projections.projectionList();
            if (ProjectionList.getLength() > 0) {
                cri.setProjection(ProjectionList);
            }
            cri.setFirstResult((pageNumber - 1) * pageSize);
            cri.setMaxResults(pageSize);
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            result = new ArrayList(cri.list());
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResultByHql(String hql, Map<String, Object> paramMap) {
        Transaction tr = null;
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            tr = (null != session.getTransaction() && session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            Query query = session.createQuery(hql);
            if (paramMap != null && !paramMap.isEmpty()) {
                for (String key : paramMap.keySet()) {
                    query.setParameter(key, paramMap.get(key));
                }
            }
            result = query.list();
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public List getResultBySql(String sql) {
        Transaction tr = null;
        Session session = null;
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            tr = (null != session.getTransaction() && session.getTransaction().isActive()) ? session.getTransaction() : session.beginTransaction();
            result = session.createSQLQuery(sql).list();
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return result;
        }
        return result;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public PageBean getPage(Class<?> clazz, List<Criterion> simpleExList, Order orderBy, Integer pageSize, Integer pageNumber) {
        Session session = null;
        PageBean pageBean = new PageBean(new ArrayList(), 0, pageSize);
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            if (orderBy != null) {
                cri.addOrder(orderBy);
            }
            cri.setMaxResults(pageSize.intValue());
            cri.setFirstResult((pageNumber - 1) * pageSize);
            result = cri.list();
            Long total = getResultCount(clazz, simpleExList);
            pageBean = new PageBean(result, Integer.parseInt(total.intValue() + ""), pageSize);
            session.close();
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return pageBean;
        }
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public PageBean getPage(Class<?> clazz, List<Criterion> simpleExList, edu.hnust.application.util.Order orderBy, Integer pageSize, Integer pageNumber) {
        Session session = null;
        PageBean pageBean = new PageBean(new ArrayList(), 0, pageSize);
        List result = new ArrayList();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(clazz);
            for (Criterion simpleEx : simpleExList) {
                cri.add(simpleEx);
            }
            if (orderBy != null && StringUtils.isNotEmpty(orderBy.getPropertyName())) {
                if (orderBy.isAscending()) {
                    cri.addOrder(Order.asc(orderBy.getPropertyName()));
                } else {
                    cri.addOrder(Order.desc(orderBy.getPropertyName()));
                }
            }
            cri.setMaxResults(pageSize.intValue());
            cri.setFirstResult((pageNumber - 1) * pageSize);
            result = cri.list();
            Long total = getResultCount(clazz, simpleExList);
            pageBean = new PageBean(result, Integer.parseInt(total.intValue() + ""), pageSize);
            session.close();
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return pageBean;
        }
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ArrayList<Criterion> getRequestRestriction(Map<String, Object> query) {
        ArrayList<Criterion> restrictions = new ArrayList<Criterion>();
        if (null != query) {
            for (String key : query.keySet()) {
                Object value = query.get(key);
                Map<String, Object> compareValue;
                if ((value instanceof Map)) {
                    compareValue = (Map)value;
                    for (String compare : compareValue.keySet()) {
                        if ("$ge".equals(compare)) {
                            restrictions.add(Restrictions.ge(key, compareValue.get(compare)));
                        } else if ("$le".equals(compare)) {
                            restrictions.add(Restrictions.le(key, compareValue.get(compare)));
                        } else if ("$gt".equals(compare)) {
                            restrictions.add(Restrictions.gt(key, compareValue.get(compare)));
                        } else if ("$lt".equals(compare)) {
                            restrictions.add(Restrictions.lt(key, compareValue.get(compare)));
                        } else if ("$in".equals(compare)) {
                            restrictions.add(Restrictions.in(key, (Collection)compareValue.get(compare)));
                        } else if ("$like".equals(compare)) {
                            restrictions.add(Restrictions.ilike(key, "%" + compareValue.get(compare) + "%"));
                        } else if ("$left_like".equals(compare)) {
                            restrictions.add(Restrictions.ilike(key, "%" + compareValue.get(compare)));
                        } else if ("$right_like".equals(compare)) {
                            restrictions.add(Restrictions.ilike(key, compareValue.get(compare) + "%"));
                        } else if ("$ne".equals(compare)) {
                            restrictions.add(Restrictions.ne(key, compareValue.get(compare)));
                        } else if ("$null".equals(compare)) {
                            restrictions.add(Restrictions.isNull(key));
                        } else if ("$empty".equals(compare)) {
                            restrictions.add(Restrictions.isEmpty(key));
                        } else if ("$not_null".equals(compare)) {
                            restrictions.add(Restrictions.isNotNull(key));
                        } else if ("$not_empty".equals(compare)) {
                            restrictions.add(Restrictions.isNotEmpty(key));
                        }
                    }
                } else {
                    restrictions.add(Restrictions.eq(key, query.get(key)));
                }
            }
        }
        return restrictions;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Long fetchCollectionCount(Class<?> clazz, Map<String, Object> requestArgs) {
        List criterions = getRequestRestriction((HashMap)requestArgs.get("query"));
        return getResultCount(clazz, criterions);
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List fetchCollection(Class<?> clazz, Map<String, Object> requestArgs) {
        List<Criterion> criterions = getRequestRestriction((HashMap)requestArgs.get("query"));
        String sortField = CommonDaoHelper.getRequestSortField(requestArgs);
        String sortDirection = CommonDaoHelper.getRequestSortDirection(requestArgs);
        Integer pageSize = CommonDaoHelper.getRequestPageSize(requestArgs);
        Integer pageNumber = CommonDaoHelper.getRequestPageNumber(requestArgs);
        Order order = null;
        if ("-1".equals(sortDirection)) {
            order = Order.desc(sortField);
        } else {
            order = Order.asc(sortField);
        }
        return getResult(clazz, criterions, pageSize, pageNumber, order);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Boolean update(Class<?> clazz, Map<String, Object> requestArgs) {
        Object id = requestArgs.get("id");
        if (null == id) {
            return Boolean.valueOf(false);
        }
        Session sess = null;
        Transaction tr = null;
        try {
            sess = this.sessionFactory.openSession();
            tr = (null != sess.getTransaction()) && (sess.getTransaction().isActive()) ? sess.getTransaction() : sess.beginTransaction();
            Object object = sess.get(clazz, new Integer(id.toString()));
            
            requestArgs.remove("id");
            Map updates = (Map)requestArgs.get("updates");
            if (null == updates) {
                updates = new HashMap<>();
                updates.putAll(requestArgs);
            }
            BeanUtils.populate(object, updates);
            sess.saveOrUpdate(object);
            tr.commit();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            sess.close();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Boolean batchUpdate(Class<?> clazz, Map<String, Object> requestArgs) {
        List<Integer> ids = (List<Integer>)requestArgs.get("ids");
        if (null == ids) {
            return Boolean.valueOf(false);
        }
        Session sess = null;
        Transaction tr = null;
        try {
            sess = this.sessionFactory.openSession();
            tr = (null != sess.getTransaction()) && (sess.getTransaction().isActive()) ? sess.getTransaction() : sess.beginTransaction();
            Map updates = (Map)requestArgs.get("updates");
            if (null == updates) {
                updates = new HashMap<>();
                updates.putAll(requestArgs);
            }
            updates.remove("ids");
            for (Integer id : ids) {
                Object object = sess.get(clazz, id);
                BeanUtils.populate(object, updates);
                sess.saveOrUpdate(object);
            }
            tr.commit();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            sess.close();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean save(Class<?> clazz, Map<String, Object> requestArgs) {
        Session sess = null;
        Transaction tr = null;
        try {
            sess = this.sessionFactory.openSession();
            tr = (null != sess.getTransaction()) && (sess.getTransaction().isActive()) ? sess.getTransaction() : sess.beginTransaction();
            Object object = clazz.newInstance();
            requestArgs.remove("id");
            BeanUtils.populate(object, requestArgs);
            sess.saveOrUpdate(object);
            tr.commit();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            sess.close();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
    
    public Boolean deleteById(Class<?> clazz, Integer id) {
        Object object = load(clazz, id.intValue());
        if (null == object) {
            return Boolean.valueOf(false);
        }
        delete(object);
        return true;
    }
    
    public void closeSession(Session session) {
        if (null != session && session.isOpen()) {
            session.close();
        }
    }
}