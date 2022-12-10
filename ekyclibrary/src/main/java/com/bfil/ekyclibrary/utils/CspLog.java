package com.bfil.ekyclibrary.utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CspLog {
	public static int iEnableLog = 0;

	public static int WriteLog(String message)
	{
		try 
		{
			Log.i("Log", message);
			if (iEnableLog == 1)
			{
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
				String dateNow = formatter.format(currentDate.getTime());
				File dir = new File("/sdcard/CSPlog");
				if (!dir.exists())
				{
					dir.mkdir();
				}
				
					File file = new File("/sdcard/CSPlog/"+ dateNow.toString() + ".txt");
					if (!file.exists()) 
					{
						file.createNewFile();
					}
					DateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss.SSS");
					Date dat1 = new Date();
				FileWriter fstream = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(fstream);
				String strDate = dat1.toString();
				System.out.println("Date : " + strDate);

				String strday = strDate.substring(0, 10);
				System.out.println("Day : " + strday);
				String strTime = strDate.substring(11, 19);
				System.out.println("Time : " + strTime);
				String strYear = strDate.substring(20);
				System.out.println("Year : " + strYear);
				// out.write(dat1.toString());
				// out.write(":");
				out.write(strday);

				out.write("|");
				out.write(strTime);
				out.write("|");

				out.write(strYear);

				out.write("|");

				// out.write("\0\0\0");
				out.write(message);
				out.write("|");

				out.newLine();
				out.close();
				
			}
		}
		catch (Exception Ex)
		{
			System.out.println("Exception in writelog fn"+Ex.getMessage().toString());
		}
		return 0;
	}
}