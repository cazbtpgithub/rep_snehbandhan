package com.xanadutec.coreflex.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xanadutec.coreflex.model.EmailTime;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.UserModel;

@Repository("loginDao")
@Transactional
public class LoginDaoImpl implements LoginDao, UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username) {

		System.out.println("Getting access details from employee dao !!");
		UserModel dbUser = getByUname(username);
		// Ideally it should be fetched from database and populated instance of
		// #org.springframework.security.core.userdetails.User should be
		// returned from this method
		// UserDetails user = new User(username, "password", true, true, true,
		// true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER")
		// });

		UserDetails user = buildUserFromUserEntity(dbUser);
		
	
		try {
			if (user == null)
				throw new UsernameNotFoundException("user name not found");

		} catch (Exception e) {
			throw new UsernameNotFoundException("database error");
		}
		return user;
	}

	private User buildUserFromUserEntity(UserModel userEntity) {
		
		// convert model user to spring security user
		String username = userEntity.getUserName();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(userEntity.getUserRoll().getId()));

	}

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			System.out.println("HIIII");
			roles.add("ROLE_USER");
		}
		else if (role.intValue() == 3) {
			System.out.println("HIIII");
			roles.add("ROLE_DEALER");
		}
		else if (role.intValue() == 4) {
			roles.add("ROLE_EMPLOYEE");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	public UserModel getByUname(String uname) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userName", uname));
		List<UserModel> users = criteria.list();
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public UserModel getUserByUnamePassword(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));
		List<UserModel> users = criteria.list();
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public List<UserModel> getListOfUserIfUnamePasswordIsValid(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));
		List<UserModel> users = criteria.list();
		UserModel userModel = null;
		if (users != null && !users.isEmpty()) {
			userModel = users.get(0);
		}
		if (userModel != null) {
			criteria = session.createCriteria(UserModel.class);
			return criteria.list();
		}
		return null;
	}

	@Override
	public void addUser(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		UserModel userModel = new UserModel();
		userModel.setUserName(userName);
		userModel.setPassword(password);
		session.save(userModel);
	}

	@Override
	public UserModel getUserByUname(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userName", userName));
		List<UserModel> users = criteria.list();
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public UserModel getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userId", id));
		return (UserModel) criteria.uniqueResult();
	}

	@Override
	public void updateEmailTime() {
		Session session = sessionFactory.getCurrentSession();
		String hql8 = "update EmailTime set emailDate =:emailDate where emaiId =:emaiId ";
		Query query8 = session.createQuery(hql8);
		query8.setParameter("emailDate", new Date());
		query8.setParameter("emaiId", 1);
		query8.executeUpdate();
	}

	@Override
	public Date getPrevEmailTime() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EmailTime.class);
			criteria.add(Restrictions.eq("emaiId", 1));
			EmailTime time=(EmailTime) criteria.uniqueResult();
			return time.getEmailDate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel getUserDetailsBy(long id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userId", id));
		return (UserModel) criteria.uniqueResult();
	}
}
