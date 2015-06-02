package dao;

import java.util.Date;
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
 * A data access object (DAO) providing persistence and search support for Guser
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see dao.Guser
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class GuserDAO {
	private static final Logger log = LoggerFactory.getLogger(GuserDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PASSWD = "passwd";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String GROUP_ID = "groupId";
	public static final String POINTS = "points";
	public static final String POST_NUM = "postNum";
	public static final String REPLY_NUM = "replyNum";
	public static final String SIGNATURE = "signature";
	public static final String COIN_NUM = "coinNum";

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

	public void save(Guser transientInstance) {
		log.debug("saving Guser instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Guser persistentInstance) {
		log.debug("deleting Guser instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Guser findById(java.lang.String id) {
		log.debug("getting Guser instance with id: " + id);
		try {
			Guser instance = (Guser) getCurrentSession().get("dao.Guser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Guser instance) {
		log.debug("finding Guser instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Guser")
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
		log.debug("finding Guser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Guser as model where model."
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

	public List findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByGroupId(Object groupId) {
		return findByProperty(GROUP_ID, groupId);
	}

	public List findByPoints(Object points) {
		return findByProperty(POINTS, points);
	}

	public List findByPostNum(Object postNum) {
		return findByProperty(POST_NUM, postNum);
	}

	public List findByReplyNum(Object replyNum) {
		return findByProperty(REPLY_NUM, replyNum);
	}

	public List findBySignature(Object signature) {
		return findByProperty(SIGNATURE, signature);
	}

	public List findByCoinNum(Object coinNum) {
		return findByProperty(COIN_NUM, coinNum);
	}

	public List findAll() {
		log.debug("finding all Guser instances");
		try {
			String queryString = "from Guser";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Guser merge(Guser detachedInstance) {
		log.debug("merging Guser instance");
		try {
			Guser result = (Guser) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Guser instance) {
		log.debug("attaching dirty Guser instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Guser instance) {
		log.debug("attaching clean Guser instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GuserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GuserDAO) ctx.getBean("GuserDAO");
	}
}