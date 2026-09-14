package com.xanadutec.coreflex.setup;


import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.FtpInfo;
import com.xanadutec.coreflex.model.ServerInfo;




public interface SetupDao 
{
	public void saveServerInfo(ServerInfo serverInfo);
	public void saveFcmServerInfo(FcmNotification fcmNotification);
	public void saveFtpServerInfo(FtpInfo ftpInfo);
	
	
	public ServerInfo getServerInfo();
	public FcmNotification getFcmServerInfo();
	public FtpInfo getFtpServerInfo();
	
	
	public void updateServerInfo(ServerInfo serverInfo);
	public void updateFcmServerInfo(FcmNotification fcmNotification);
	public void updateFtpServerInfo(FtpInfo ftpInfo);

}
