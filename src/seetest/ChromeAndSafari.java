package seetest;

import java.io.BufferedWriter;
import java.io.File;

//chrome test and safari


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class ChromeAndSafari extends Main {
	test test1 = new test();
	String device=test1.getter();
	String path;
	int failcount = 0;
	public String devicename = "";
	public String serialnumber ="";


	@Test(groups = { "seetest" })
	public void runtest() {
		
		if (device.equals("")) {
			String device = client.waitForDevice("@os='android' or @os='ios' and @added='true'", 300000);
		}
		else {
			client.setDevice(device);
		}
		
		devicename = client.getDeviceProperty("device.name");
        serialnumber = client.getDeviceProperty("device.sn");

		if (devicename.startsWith("adb:")) {
			devicename = devicename.substring(4, devicename.length());
		}
		if (devicename.startsWith("ios_app:")) {
			devicename = devicename.substring(8, devicename.length());
		}
		String os = client.getDeviceProperty("device.os");

		long millis = System.currentTimeMillis();
		path = "RUN_" + millis + "\\" + devicename;
		
		client.setReporter("xml", path, "chrome and safari test");

		client.launch("http://www.cnn.com", true, false);
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}

		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='Regions' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton = client.hybridRunJavascript("", 0, "history.go(-1);");

		if (client.waitForElement("WEB", "xpath=//*[@id='logo' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);

		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='U.S. Politics' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton1 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*/*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[1]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton2 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*/*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[2]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton3 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// tech
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*/*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[3]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton4 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// sport
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='Sport' and @nodeName='A' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton5 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// travel
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV'] and ./*[@nodeName='IMG']])[4]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton6 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// style
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV'] and ./*[@nodeName='IMG']])[5]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton7 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// health
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='Health' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton8 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// features
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='Features' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton9 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// video
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='Video' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton10 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
			// If statement
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// VR
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=(//*[@id='nav-expanded-menu' and @nodeName='DIV']/*/*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV'] and ./*[@nodeName='IMG']])[6]",
				0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton11 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
		// more
		if (client.swipeWhileNotFound("Down", 400, 2000, "WEB",
				"xpath=//*[@text='More…' and ./parent::*[@nodeName='DIV']]", 0, 1000, 5, true)) {
		}
		if (client.waitForElement("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 10000)) {
		}
		String backbutton12 = client.hybridRunJavascript("", 0, "history.go(-1);");
		if (client.waitForElement("WEB", "xpath=//*[@text='Home' and @nodeName='A']", 0, 10000)) {
		}
		client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);
	}

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
    	File tempFile = new File("overall summary.txt");
  	  	boolean exists = tempFile.exists();


       if (result.getStatus() == ITestResult.FAILURE) {
    	// write to report of fail

    				if (exists) {

    					String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
    							+ " has failed on the following test: Chrome and Safari test";
    					BufferedWriter writer = null;

    					writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

    					writer.append(' ');
    					writer.newLine();
    					writer.append(failedstring);
    					writer.close();

    				} else {
    					System.out.println("in else exists");

    					String failedstring = "the device " + this.devicename + " serial number " + this.serialnumber
    							+ " has Failed on the following test: Chrome and Safari test";

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
						+ " has Passed on the following test: Chrome and Safari test";
				BufferedWriter writer = null;

				writer = new BufferedWriter(new FileWriter("overall summary.txt", true));

				writer.append(" ");
				writer.newLine();
				writer.append(success);
				writer.close();

			} else {
				System.out.println("in success else");

				String success = "the device " + this.devicename + " serial number " + this.serialnumber
						+ " has Passed on the following test: Chrome and Safari test";

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
