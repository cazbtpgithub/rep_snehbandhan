package com.xanadutec.coreflex.FileUploadDownload;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.FtpInfo;


@Repository("FileUploadDao")
@Transactional
public class FileUploadDaoImpl implements FileUploadDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveFTP(FtpInfo ftpInfo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ftpInfo);
		}

	@Override
	public FtpInfo getFtpInfo() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria =session.createCriteria(FtpInfo.class);
		List<FtpInfo> ftpInfos=criteria.list();
		return ftpInfos.get(0);
	}

}
