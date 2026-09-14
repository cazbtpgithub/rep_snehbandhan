package com.xanadutec.coreflex.packageAndKit;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.Status;

@Repository("PackageAndKitDao")
@Transactional
public class PackageAndKitDaoImpl implements PackageAndKitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savepackage(String packageName, String amount, String no_of_msg, String no_of_email,
			String no_of_profiles, String no_of_days) {
		Status status = new Status();
		status.setStatus_id(1);
		Package pack = new Package();
		pack.setAmount(amount);
		pack.setStatus_id(status);
		pack.setPackage_Name(packageName);
		pack.setNo_of_email(no_of_email);
		pack.setNo_of_msg(no_of_msg);
		pack.setNo_of_profiles(no_of_profiles);
		pack.setNo_of_days(no_of_days);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(pack);
		}

	@Override
	public Kit saveKit(String userName, String password, String packageId) {
		Status status = new Status();
		status.setStatus_id(2);
		Package pack = new Package();
		pack.setPackage_id(Integer.parseInt(packageId));
		Kit kit = new Kit();
		kit.setKit_password(password);
		kit.setKit_username(userName);
		kit.setPackage_id(pack);
		kit.setStatus_id(status);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(kit);
		return kit;
	}

	@Override
	public List<Package> getListOfPackage() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Package.class);
		List<Package> list = criteria.list();
		return list;
	}

	@Override
	public List<Kit> getListOfKit() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit.class);
		List<Kit> list = criteria.list();
		return list;
	}

	@Override
	public List<Kit> getListOfKitByPackage(String packageId) {
		Package pack = new Package();
		pack.setPackage_id(Integer.parseInt(packageId));
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit.class);
		criteria.add(Restrictions.eq("package_id", pack));
		List<Kit> list = criteria.list();
		return list;
	}

	@Override
	public Package getPackageByPackageId(int packageObj) {
		Session session = sessionFactory.getCurrentSession();
		Package pack = (Package) session.get(Package.class, packageObj);
		return pack;
	}

	@Override
	public Package getPackageByPackageName(String name) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria= session.createCriteria(Package.class);
			criteria.add(Restrictions.eq("package_Name", name));
			Package pack =(Package) criteria.list().get(0);		
			return pack;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePackage(Package pack) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(pack);
		}
}
