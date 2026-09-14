package com.xanadutec.coreflex.BranchDetails;

import java.util.List;

import com.xanadutec.coreflex.model.BranchModel;

public interface BranchService {
	
	public void saveBranch(BranchModel branchModel);
	public List<BranchModel> getBranchList();
	public List<BranchModel> getBranchDetailsByBranchId(String branchId);
	public void deleteBranchDetailsByBranchID(int branchId);

}
