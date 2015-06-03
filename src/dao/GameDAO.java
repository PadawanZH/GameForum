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
 * A data access object (DAO) providing persistence and search support for Game
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see dao.Game
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class GameDAO {
	private static final Logger log = LoggerFactory.getLogger(GameDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String STUDIO_ID = "studioId";
	public static final String PLATFORM = "platform";
	public static final String TYPE = "type";
	public static final String LOGO_ADDR = "logoAddr";
	public static final String REQUIREMENT_ID = "requirementId";
	public static final String POST_NUM = "postNum";
	public static final String OWNER_ID = "ownerId";

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

	public void save(Game transientInstance) {
		log.debug("saving Game instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Game persistentInstance) {
		log.debug("deleting Game instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Game findById(java.lang.Integer id) {
		log.debug("getting Game instance with id: " + id);
		try {
			Game instance = (Game) getCurrentSession().get("dao.Game", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Game instance) {
		log.debug("finding Game instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Game")
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
		log.debug("finding Game instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Game as model where model."
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

	public List findByStudioId(Object studioId) {
		return findByProperty(STUDIO_ID, studioId);
	}

	public List findByPlatform(Object platform) {
		return findByProperty(PLATFORM, platform);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByLogoAddr(Object logoAddr) {
		return findByProperty(LOGO_ADDR, logoAddr);
	}

	public List findByRequirementId(Object requirementId) {
		return findByProperty(REQUIREMENT_ID, requirementId);
	}

	public List findByPostNum(Object postNum) {
		return findByProperty(POST_NUM, postNum);
	}

	public List findByOwnerId(Object ownerId) {
		return findByProperty(OWNER_ID, ownerId);
	}

	public List findAll() {
		log.debug("finding all Game instances");
		try {
			String queryString = "from Game";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Game merge(Game detachedInstance) {
		log.debug("merging Game instance");
		try {
			Game result = (Game) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Game instance) {
		log.debug("attaching dirty Game instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Game instance) {
		log.debug("attaching clean Game instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List queryFourTopGame(){
		log.debug("finding Game instances with four top Game");
		try {
			String queryString = "select a from Game as a ,Game as b "
					+ "Where a.type=b.type and a.postNum<=b.postNum "
					+ "group by a.id "
					+ "having count(b.id)<=4 "
					+ "order by a.type,a.postNum,a.id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public static GameDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GameDAO) ctx.getBean("GameDAO");
	}
}