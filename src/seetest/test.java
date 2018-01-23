package seetest;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class test extends Main{
//	Bellow you can choose in minutes how long to run the test suit
	static int min_to_run=1;
	static long millisec_to_run=min_to_run*60000;
	static long current_time;
	static long total_time;
	static long time_left;
	static String device = "";

	public static void main(String args[]) {
		
		
//	choose here which kind of run, normal or timed if you
//	would like to run timed remove the comment from timerunner() otherwise remove comment
//	from normalrunner()
//		timerunner();
		normalrunner();

	
	}
	public static void timerunner () {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites= new ArrayList<>();
//		if you would like to run the entire suit remove the comment from 
//		the line of testng.xml on the next line-
//		suites.add("testng.xml");
//		if you would like to run just one test choose the test you would like 
//		and remove the comment line
//		suites.add("");
		
		testng.setTestSuites(suites);
		
		current_time = System.currentTimeMillis() ;
		total_time = current_time+millisec_to_run;
		time_left= total_time-System.currentTimeMillis();
		while (time_left>0) {
			time_left=time_left-System.currentTimeMillis();
			testng.run();
			time_left=time_left-System.currentTimeMillis();
		}
	}
	
	public static void normalrunner () {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites= new ArrayList<>();
		String chosen_test;
//		if you would like to run the entire suit remove the comment from 
//		the line of testng.xml on the next line-
		suites.add("testng.xml");
//		
//		if you would like to run just one test, choose the test you would like 
//		and remove the comment line
//		add to the following quotes which test to run between the 
//		following:
//		chromeandsafari	IOS_appstoretest	PlaystoreTest	TestMyapp
//		EribankLogin	Test5_mytest	Test6_mytest1	Test7_mytest2
//		choose which device to run the test on-
//		put your device name here:
//		device = "adb:Nexus 9";
//		
//		chosen_test = "Test5_mytest";
//		suites.add(chosen_test+".xml");
		
		testng.setTestSuites(suites);
		testng.run();
		}
	
	

	public  String getter () {
		return device;
	}
	
	
	}
 


