package com.xanadutec.coreflex.magazine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.MagazinePdf;

@Service("MagzineService")
public class MagazineServiceImpl implements MagzineService
{
   @Autowired
   MagazineDao magazineDao;
	@Override
	public List<MagazinePdf> getMagazinePdfFilesName() 
	{
		return magazineDao.getMagazinePdfFilesName();
	}
	@Override
	public void update_MagazinePdfFileName(String actualPdfFileName,
			String magazineFileId, String nameWithHash) {
		magazineDao.update_MagazinePdfFileName(actualPdfFileName, magazineFileId, nameWithHash);
		
	}
	@Override
	public String getImageNameAccordingToFolderName(String folderName) {
		return magazineDao.getImageNameAccordingToFolderName(folderName);
	}
	@Override
	public void deleteMagazineDetailsByMagazineTableId(int magazineTableRowID)
	{
		magazineDao.deleteMagazineDetailsByMagazineTableId(magazineTableRowID);
		
	}
	

}
