package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	 @DataProvider(name="LoginData")
	 public Object[][] getData() throws Exception
	    
	    {
		 String path=".\\TestData\\OrangeHRM.xlsx";
		   
		   ExcelUtility xlutil=new ExcelUtility(path);
		   
		   int totalrows=xlutil.getRowCount("LoginData");
		   int totalcol=xlutil.getCellCount("LoginData", totalrows);
		   
		 
		   String logindata[][] =new String[totalrows][totalcol];
		   
		   for(int i=1;i<=totalrows;i++)
		   {
		       for(int j=0;j<totalcol;j++)
		       {
		           logindata[i-1][j] = xlutil.getCellData("LoginData", i, j);
		       }
		   }
		   
		  
			return logindata;	   
	    }

}
