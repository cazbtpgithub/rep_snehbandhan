package com.xanadutec.coreflex.setup;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.FtpInfo;
import com.xanadutec.coreflex.model.ServerInfo;

@Repository("setupDao")
@Transactional
public class SetupDaoImpl implements SetupDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveServerInfo(ServerInfo serverInfo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(serverInfo);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public void saveFcmServerInfo(FcmNotification fcmNotification) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(fcmNotification);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public void saveFtpServerInfo(FtpInfo ftpInfo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(ftpInfo);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public void updateServerInfo(ServerInfo serverInfo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(serverInfo);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public void updateFcmServerInfo(FcmNotification fcmNotification) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(fcmNotification);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public void updateFtpServerInfo(FtpInfo ftpInfo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(ftpInfo);
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
	}

	@Override
	public ServerInfo getServerInfo() {
		Session session = sessionFactory.getCurrentSession();
		try {
			ServerInfo serverInfo=(ServerInfo) session.get(ServerInfo.class ,1);
			return serverInfo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return null;
	}

	@Override
	public FcmNotification getFcmServerInfo() {
		Session session = sessionFactory.getCurrentSession();
		try {
			FcmNotification fcmNotification=(FcmNotification) session.get(FcmNotification.class ,1);
			return fcmNotification;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return null;
	}

	@Override
	public FtpInfo getFtpServerInfo() {
		Session session = sessionFactory.getCurrentSession();
		try {
			FtpInfo ftpInfo=(FtpInfo) session.get(FtpInfo.class ,1);
			return ftpInfo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return null;
	}

}
