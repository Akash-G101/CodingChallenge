import java.lang.*;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Billing {

	public static void main(String[] args) throws IOException, ParseException 
	{
           String[] strArray = null;
	   File f1 = new File("D://test.txt");
	   List<String> lines = Collections.emptyList();
		try 
		{
		 lines = Files.readAllLines(Paths.get("D://test.txt"), StandardCharsets.UTF_8);
		}
		catch(FileNotFoundException e)
		{
		 e.printStackTrace();
		}
		
		String timearray[] = new String[lines.size()];
		String userarray[] = new String[lines.size()];
		String actionarray[] = new String[lines.size()];
		for(int k=0; k<lines.size()-1;k++)		
		{
		    String delim = "";
                    StringBuilder sb = new StringBuilder();
                    sb.append(lines.get(k));
                    sb.append(delim);
                    String res = sb.toString();
                    strArray = res.split(" ");
                    timearray[k] = strArray[0];
    		    userarray[k]= strArray[1];
    		    actionarray[k]=strArray[2];

		}

	        int freq[] = new int[lines.size()];
		long[] differenceInSeconds=new long[lines.size()];
		    
                for(int i=0; i<lines.size()-1;i++)
 		{
                  freq[i]=1;
 		  differenceInSeconds[i]=0;

			for(int j=i+1; j<lines.size()-1;j++)
			{
				freq[i]=1;
			        if (userarray[i].equals(userarray[j])&&actionarray[i]!=actionarray[j])
			        {

				freq[i]++;
				userarray[j]="0";
				
				SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("HH:mm:ss");
	                        Date date1 =  simpleDateFormat.parse(timearray[i]);
		                Date date2 = simpleDateFormat.parse(timearray[j]);
		                long differenceInMilliSeconds  = Math.abs(date2.getTime() - date1.getTime());
		                long differenceInHours  = (differenceInMilliSeconds / (60 * 60 * 1000))% 24;
		                long differenceInMinutes  = (differenceInMilliSeconds / (60 * 1000)) % 60;
		                differenceInSeconds[i]  =differenceInSeconds[i]+(differenceInMilliSeconds / 1000) % 60;
		        
				//Time duration calculation logic!!

			        }
				
			}
			
 		}
		
		 for(int i = 0; i <lines.size(); i++)
		 {    
	             if(userarray[i] != null && userarray[i] != "0")    
	             System.out.println(userarray[i] + " " + freq[i]+" "+differenceInSeconds[i]);    
	         }      	
            }
}

