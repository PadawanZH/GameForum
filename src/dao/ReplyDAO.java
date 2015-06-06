package dao;

import java.sql.Timestamp;
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
 * A data access object (DAO) providing persistence and search support for Reply
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see dao.Reply
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ReplyDAO {
	private static final Logger log = LoggerFactory.getLogger(ReplyDAO.class);
	// property constants
	public static final String FLOOR = "floor";
	public static final String FAVOURITE_NUM = "favouriteNum";
	public static final String CONTENTS = "contents";

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

	public void save(Reply transientInstance) {
		log.debug("saving Reply instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reply persistentInstance) {
		log.debug("deleting Reply instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reply findById(java.lang.Integer id) {
		log.debug("getting Reply instance with id: " + id);
		try {
			Reply instance = (Reply) getCurrentSession().get("dao.Reply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reply instance) {
		log.debug("finding Reply instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Reply")
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
		log.debug("finding Reply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reply as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFloor(Object floor) {
		return findByProperty(FLOOR, floor);
	}

	public List findByFavouriteNum(Object favouriteNum) {
		return findByProperty(FAVOURITE_NUM, favouriteNum);
	}

	public List findByContents(Object contents) {
		return findByProperty(CONTENTS, contents);
	}

	public List findAll() {
		log.debug("finding all Reply instances");
		try {
			String queryString = "from Reply";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reply merge(Reply detachedInstance) {
		log.debug("merging Reply instance");
		try {
			Reply result = (Reply) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reply instance) {
		log.debug("attaching dirty Reply instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reply instance) {
		log.debug("attaching clean Reply instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 以属性A的值，以属性B排序
	 * @param A
	 * @param B
	 * @return
	 */
	public List findByAOrderByB(String A, Object value, String B) {
		log.debug("finding Reply instance with property: " + A
				+ ", order by : " + B);
		try {
			String queryString = "from Reply as model where model."
					+ A + "= ? order by model."+B;
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by A name order by B failed", re);
			throw re;
		}
	}
	
	public static ReplyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReplyDAO) ctx.getBean("ReplyDAO");
	}
}