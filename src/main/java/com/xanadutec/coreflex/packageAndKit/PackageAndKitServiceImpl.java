package com.xanadutec.coreflex.packageAndKit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Package;

@Service("PackageAndKitService")
public class PackageAndKitServiceImpl implements PackageAndKitService {

	@Autowired
	PackageAndKitDao packageDao;

	@Override
	public void savepackage(String packageName, String amount, String no_of_msg, String no_of_email,
			String no_of_profiles, String no_of_days) {
		packageDao.savepackage(packageName, amount, no_of_msg, no_of_email, no_of_profiles, no_of_days);

	}

	@Override
	public Kit saveKit(String userName, String password, String packageId) {
		return packageDao.saveKit(userName, password, packageId);
	}

	@Override
	public List<Package> getListOfPackage() {
		return packageDao.getListOfPackage();
	}

	@Override
	public List<Kit> getListOfKit() {
		return packageDao.getListOfKit();
	}

	@Override
	public List<Kit> getListOfKitByPackage(String packageId) {
		return packageDao.getListOfKitByPackage(packageId);
	}

	@Override
	public Package getPackageByPackageId(int packageObj) {
		return packageDao.getPackageByPackageId(packageObj);
	}

	@Override
	public Package getPackageByPackageName(String name) {
		return packageDao.getPackageByPackageName(name);
	}

	@Override
	public void updatePackage(Package pack) {
		packageDao.updatePackage(pack);
	}

}
