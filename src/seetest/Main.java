package seetest;


import com.experitest.client.*;
import org.testng.annotations.*;

public class Main {
	

	
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51IjoxMTUsInhwLnAiOjIsInhwLm0iOiJNQSIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE4MzE1NzI1MzksImlzcyI6ImNvbS5leHBlcml0ZXN0In0.sPv-i-q02EmdH1fXwy7PF4eTyRTCtj2orbcSyG6SZQw";

    
    protected String projectBaseDirectory = "\\repo";
    Client client = null;
//    GridClient grid = null;

    @BeforeMethod
    public void setUp(){
    	
//    	grid = new GridClient(accessKey, "https://sales.experitest.com",443, true);
//    	client = grid.lockDeviceForExecution("i dont get this","", 10, 50000);


        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
    }

	

	class newmethod extends Client{
		private String h;
		private int p;
		private boolean b;
		
		@SuppressWarnings("deprecation")
		public newmethod (String host,int port,boolean boo) {
			h = host;
			p = port;
			b=boo;
			
			
		}
	@Override
	public String generateReport(boolean Boolean) {
		
		String string;
		string ="";
		return string;
		
		
		
		}
	}
	


	 
	
	


	

}
