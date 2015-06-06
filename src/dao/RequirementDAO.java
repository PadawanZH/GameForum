package dao;

import java.util.List;
import java.util.Set;
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
 * Requirement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Requirement
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RequirementDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RequirementDAO.class);
	// property constants
	public static final String OS = "os";
	public static final String PROCESSOR = "processor";
	public static final String MEMORY = "memory";
	public static final String GRAPHICS = "graphics";
	public static final String HARD_DRIVE = "hardDrive";
	public static final String SOUND_CARD = "soundCard";

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

	public void save(Requirement transientInstance) {
		log.debug("saving Requirement instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Requirement persistentInstance) {
		log.debug("deleting Requirement instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Requirement findById(java.lang.Integer id) {
		log.debug("getting Requirement instance with id: " + id);
		try {
			Requirement instance = (Requirement) getCurrentSession().get(
					"dao.Requirement", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Requirement instance) {
		log.debug("finding Requirement instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Requirement")
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
		log.debug("finding Requirement instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Requirement as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOs(Object os) {
		return findByProperty(OS, os);
	}

	public List findByProcessor(Object processor) {
		return findByProperty(PROCESSOR, processor);
	}

	public List findByMemory(Object memory) {
		return findByProperty(MEMORY, memory);
	}

	public List findByGraphics(Object graphics) {
		return findByProperty(GRAPHICS, graphics);
	}

	public List findByHardDrive(Object hardDrive) {
		return findByProperty(HARD_DRIVE, hardDrive);
	}

	public List findBySoundCard(Object soundCard) {
		return findByProperty(SOUND_CARD, soundCard);
	}

	public List findAll() {
		log.debug("finding all Requirement instances");
		try {
			String queryString = "from Requirement";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Requirement merge(Requirement detachedInstance) {
		log.debug("merging Requirement instance");
		try {
			Requirement result = (Requirement) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Requirement instance) {
		log.debug("attaching dirty Requirement instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Requirement instance) {
		log.debug("attaching clean Requirement instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RequirementDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RequirementDAO) ctx.getBean("RequirementDAO");
	}
}