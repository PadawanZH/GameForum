package dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usergroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Usergroup
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UsergroupDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsergroupDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PERMISSIONS = "permissions";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Usergroup transientInstance) {
		log.debug("saving Usergroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usergroup persistentInstance) {
		log.debug("deleting Usergroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usergroup findById(java.lang.Integer id) {
		log.debug("getting Usergroup instance with id: " + id);
		try {
			Usergroup instance = (Usergroup) getCurrentSession().get(
					"dao.Usergroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usergroup instance) {
		log.debug("finding Usergroup instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Usergroup")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Usergroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usergroup as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPermissions(Object permissions) {
		return findByProperty(PERMISSIONS, permissions);
	}

	public List findAll() {
		log.debug("finding all Usergroup instances");
		try {
			String queryString = "from Usergroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usergroup merge(Usergroup detachedInstance) {
		log.debug("merging Usergroup instance");
		try {
			Usergroup result = (Usergroup) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usergroup instance) {
		log.debug("attaching dirty Usergroup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usergroup instance) {
		log.debug("attaching clean Usergroup instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsergroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsergroupDAO) ctx.getBean("UsergroupDAO");
	}
}