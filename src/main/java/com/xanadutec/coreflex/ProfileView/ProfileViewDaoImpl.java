package com.xanadutec.coreflex.ProfileView;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.ImagePath;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Religion;

@Repository("ProfileViewDao")
@Transactional
public class ProfileViewDaoImpl implements ProfileViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public ImagePath getImagePath() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(ImagePath.class);
		List<ImagePath>  imagePaths =criteria.list();
		return imagePaths.get(0);
	}
}
