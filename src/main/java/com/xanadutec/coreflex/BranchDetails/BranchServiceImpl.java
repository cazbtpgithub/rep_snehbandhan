package com.xanadutec.coreflex.BranchDetails;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.BranchModel;

@Service("BranchService")

public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchDao branchDao;
	@Override
	public List<BranchModel> getBranchList() {
	
		return branchDao.getBranchList();
	}
	@Override
	public void saveBranch(BranchModel branchModel) {	
		branchDao.saveBranch(branchModel);
	}
	@Override
	public List<BranchModel> getBranchDetailsByBranchId(String branchId) {
		return branchDao.getBranchDetailsByBranchId(branchId);
	}
	@Override
	public void deleteBranchDetailsByBranchID(int branchId) 
	{
		branchDao.deleteBranchDetailsByBranchID(branchId);
		
	}
	
	
}
