package com.xanadutec.coreflex.chat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  ");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -8);

		List<Date>dateLIst=new ArrayList<>();
		for(int i = 0; i< 8; i++){
		    cal.add(Calendar.DAY_OF_YEAR, 1);
		    String dd=sdf.format(cal.getTime());
		    Date dat=  sdf.parse(dd);
		    dateLIst.add(dat);
		    
		}

	} 

}
