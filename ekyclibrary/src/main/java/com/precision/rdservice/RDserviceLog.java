package com.precision.rdservice;

import android.annotation.SuppressLint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RDserviceLog {
	public static int iEnableLog = 0;

	@SuppressLint({ "SdCardPath", "SimpleDateFormat" }) public static int WriteLog(String message)
	{
		try 
		{
			if (iEnableLog == 1)
			{
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
				String dateNow = formatter.format(currentDate.getTime());
				File dir = new File("/sdcard/RDserviceLog");
				if (!dir.exists())
				{
					dir.mkdir();
				}
				
					File file = new File("/sdcard/RDserviceLog/"+ dateNow.toString() + ".txt");
					if (!file.exists()) 
					{
						file.createNewFile();
					}
					DateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss.SSS");
					Date dat1 = new Date();
					FileWriter fstream = new FileWriter(file, true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(dat1.toString());
					out.write(":");
					//out.write("\0\0\0");
					out.write(message);
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