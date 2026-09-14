package com.xanadutec.coreflex.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.FtpInfo;
import com.xanadutec.coreflex.model.ServerInfo;

@Service("setupService")
public class SetupServiceImpl implements SetupService {

	@Autowired
	private SetupDao setupDao;

	@Override
	public void saveServerInfo(ServerInfo serverInfo) {
		setupDao.saveServerInfo(serverInfo);
	}

	@Override
	public void saveFcmServerInfo(FcmNotification fcmNotification) {
		setupDao.saveFcmServerInfo(fcmNotification);
	}

	@Override
	public void saveFtpServerInfo(FtpInfo ftpInfo) {
		setupDao.saveFtpServerInfo(ftpInfo);
	}

	@Override
	public void updateServerInfo(ServerInfo serverInfo) {
		setupDao.updateServerInfo(serverInfo);
	}

	@Override
	public void updateFcmServerInfo(FcmNotification fcmNotification) {
		setupDao.updateFcmServerInfo(fcmNotification);
	}

	@Override
	public void updateFtpServerInfo(FtpInfo ftpInfo) {
		setupDao.updateFtpServerInfo(ftpInfo);
	}

	@Override
	public ServerInfo getServerInfo() {
		return setupDao.getServerInfo();
	}

	@Override
	public FcmNotification getFcmServerInfo() {
		return setupDao.getFcmServerInfo();
	}

	@Override
	public FtpInfo getFtpServerInfo() {
		return setupDao.getFtpServerInfo();
	}

}
