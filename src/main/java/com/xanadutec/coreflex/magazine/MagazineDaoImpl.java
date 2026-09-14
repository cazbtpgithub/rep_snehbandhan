package com.xanadutec.coreflex.magazine;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.MagazinePdf;
import com.xanadutec.coreflex.model.UserModelImagesName;

@Repository("MagazineDao")
@Transactional
public class MagazineDaoImpl implements MagazineDao 
{
      @Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MagazinePdf> getMagazinePdfFilesName() 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria=	session.createCriteria(MagazinePdf.class);
			criteria.addOrder(Order.desc("magazinePdfId"));
			List<MagazinePdf> magazinePdfList = criteria.list();
			return magazinePdfList;
		} catch (Exception e) 
		{
			e.printStackTrace();
 		}
		return null;
	}

	@Override
	public void update_MagazinePdfFileName(String actualPdfFileName, String magazineFileId,String nameWithHash)
	{
		Session session =sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MagazinePdf.class);
		criteria.add(Restrictions.eq("magazinePdfId", Integer.parseInt(magazineFileId)));
		MagazinePdf  magazinePdfObject = (MagazinePdf) criteria.uniqueResult();
		magazinePdfObject.setMagazineImageNames(actualPdfFileName);
		magazinePdfObject.setMagzineImgeNamesWithHash(nameWithHash);
		}

	@Override
	public String getImageNameAccordingToFolderName(String folderName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MagazinePdf.class);
		criteria.add(Restrictions.eq("magazineImageNames", folderName));
		MagazinePdf image = (MagazinePdf) criteria.uniqueResult();
		return image.getMagzineImgeNamesWithHash();
	}

	@Override
	public void deleteMagazineDetailsByMagazineTableId(int magazineTableRowID) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			MagazinePdf magazinePdfObject = new MagazinePdf();
			magazinePdfObject.setMagazinePdfId(magazineTableRowID);
			session.delete(magazinePdfObject);
			} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
