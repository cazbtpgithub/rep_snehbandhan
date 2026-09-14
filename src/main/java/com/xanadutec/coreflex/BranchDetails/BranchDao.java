package com.xanadutec.coreflex.BranchDetails;

import java.util.List;

import com.xanadutec.coreflex.model.BranchModel;

public interface BranchDao {

	 List<BranchModel> getBranchList();

	void saveBranch(BranchModel branch);

	public List<BranchModel> getBranchDetailsByBranchId(String branchId);

	void deleteBranchDetailsByBranchID(int branchId);

}
