package seetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class Test5_mytest extends Main {
	test test1 = new test();
	String device=test1.getter();
	String path;
    int failcount=0;
	public String devicename ="";
	public String serialnumber ="";



    @Test(groups = {"seetest"})
    public void runtest(){    	
    	if (device.equals("")) {
			String str0 = client.waitForDevice("@os='android'", 300000);
    	}
    	else {
    		client.setDevice(device);
    	}
        devicename = client.getDeviceProperty("device.name");
        serialnumber = client.getDeviceProperty("device.sn");

        if(devicename.startsWith("adb:")){
            devicename=devicename.substring(4,devicename.length());
        }
        if(devicename.startsWith("ios_app:")){
            devicename=devicename.substring(8,devicename.length());
        }
        
    	long millis = System.currentTimeMillis() ;
    	path = "RUN_"+millis+"\\"+devicename;
    	
        client.setReporter("xml", path, "my first test");
		if (client.uninstall("com.ralksoft.sellerprofitcalculator/.MainActivity")) {
		}
		if (client.install("com.ralksoft.sellerprofitcalculator/.MainActivity", true, false)) {
		}
		client.launch("com.ralksoft.sellerprofitcalculator/.MainActivity", false, true);
//		client.startLoggingDevice("somepath");
        String str1 = client.getInstalledApplications();
        String str2 = client.getCounter("cpu");
        client.deviceAction("Volume Up");
        client.deviceAction("Volume Down");
        String str3 = client.getConnectedDevices();
        client.startStepsGroup("check price is correct");
        client.click("NATIVE", "xpath=//*[@text='Fixed price']", 0, 1);
        client.elementSendText("NATIVE", "xpath=//*[@resource-id='com.ralksoft.sellerprofitcalculator:id/item_cost_edit_text']", 0, "20");
        client.closeKeyboard();
        client.elementSendText("NATIVE", "xpath=//*[@resource-id='com.ralksoft.sellerprofitcalculator:id/sale_price_edit_text']", 0, "50");
        client.closeKeyboard();
        client.click("NATIVE", "xpath=//*[@id='radioEbayStore']", 0, 1);
        String str4 = client.capture("Capture");
        client.swipe("Down", 200, 500);
        client.click("NATIVE", "xpath=//*[@text='Calculate']", 0, 1);
        client.stopStepsGroup();
        String str5 = client.stopLoggingDevice();
        String str6 = client.getDeviceLog();
        if(client.applicationClose("")){
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
    	File tempFile = new File("overall summary.txt");
  	  	boolean exists = tempFile.exists();


       if (result.getStatus() == ITestResult.FAILURE) {
    	// write to report of fail

    				if (exists) {

    					String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
    							+ " has failed on the following test: test5 my test";
    					BufferedWriter writer = null;

    					writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

    					writer.append(' ');
    					writer.newLine();
    					writer.append(failedstring);
    					writer.close();

    				} else {
    					System.out.println("in else exists");

    					String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
    							+ " has Failed on the following test: test5 my test";

    					BufferedWriter writer = null;

    					writer = new BufferedWriter(new FileWriter("overall summary.txt"));

    					writer.write(failedstring);
    					writer.close();

    				}
 	      failcount++;

 	      while (failcount<3) {
 	    	  try {
				if (failcount == 2) {
					client.reboot(400000);

					failcount++;
					
				}
 	 	      	runtest();

 	    	  }
 	    	  catch(Exception e){
 	    		  failcount++;
 	    	      client.generateReport(false);
 	    	      client.collectSupportData(path, "", client.getDeviceProperty("device.name"), "", "", "");


 	    	  }
 	      }
 	       	
 	      
 	   }    
		if (result.getStatus() == ITestResult.SUCCESS) {
			// write to report

			if (exists) {
				System.out.println("in success exists");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: test5 my test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: test5 my test";

				BufferedWriter writer = null;
				writer = new BufferedWriter(new FileWriter("overall summary.txt"));
				writer.write(success);
				writer.close();
			}

		}
       client.generateReport(false);
       client.releaseClient();
    	   
    }
}
