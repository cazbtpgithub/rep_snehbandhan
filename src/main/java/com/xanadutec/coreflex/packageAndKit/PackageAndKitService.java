package com.xanadutec.coreflex.packageAndKit;

import java.util.List;

import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Package;

public interface PackageAndKitService {
	
	public Kit saveKit(String userName,String password,String packageId);

	public List<Package> getListOfPackage();
	public List<Kit> getListOfKit();
	public List<Kit> getListOfKitByPackage(String packageId);
	void savepackage(String packageName, String amount, String no_of_msg, String no_of_email, String no_of_profiles, String no_of_days);
	
	public Package getPackageByPackageId(int packageObj);
	public Package getPackageByPackageName(String name);
	public void updatePackage(Package pack);
	
	

}
