package com.xanadutec.coreflex.magazine;

import java.util.List;

import com.xanadutec.coreflex.model.MagazinePdf;

public interface MagazineDao {

	List<MagazinePdf> getMagazinePdfFilesName();

	void update_MagazinePdfFileName(String actualPdfFileName, String magazineFileId,String nameWithHash);
	
	String getImageNameAccordingToFolderName(String folderName);

	void deleteMagazineDetailsByMagazineTableId(int magazineTableRowID);


}
