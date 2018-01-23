using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using experitestClient;

namespace Experitest
{
    [TestClass]
    public class test3
    {
        private string host = "localhost";
        private int port = 8889;
        private string projectBaseDirectory = "C:\\Users\\moshe.levin\\workspace\\tutorial";
        protected Client client = null;
        
        [TestInitialize()]
        public void SetupTest()
        {
            client = new Client(host, port, true);
            client.SetProjectBaseDirectory(projectBaseDirectory);
            client.SetReporter("xml", "reports", "test3");
        }

        [TestMethod]
        public void Testtest3()
        {
            client.SetDevice("ios_app:navot 5s");
            client.Launch("com.apple.AppStore", false, true);
            if(client.WaitForElement("NATIVE", "xpath=//*[@text='Top Charts']", 0, 10000))
            {
                  // If statement
            }
            client.Click("NATIVE", "xpath=//*[@text='Top Charts']", 0, 1);
            if(client.WaitForElement("NATIVE", "xpath=//*[@text='Free']", 0, 10000))
            {
                  // If statement
            }
            client.Click("NATIVE", "xpath=//*[@text='Free']", 0, 1);
            if(client.WaitForElement("NATIVE", "xpath=//*[starts-with(@text, \"1,\")]", 0, 10000))
            {
                  // If statement
            }
            string str0 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"1,\")]", 0);
            string str1 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"2,\")]", 0);
            string str2 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"3,\")]", 0);
            string str3 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"4,\")]", 0);
            string str4 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"5,\")]", 0);
            if(client.SwipeWhileNotFound("Down", 500, 200, "NATIVE", "xpath=//*[starts-with(@text, \"6,\")]", 0, 1000, 5, false))
            {
                  // If statement
            }
            string str5 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"6,\")]", 0);
            if(client.SwipeWhileNotFound("Down", 500, 200, "NATIVE", "xpath=//*[starts-with(@text, \"7,\")]", 0, 1000, 5, false))
            {
                  // If statement
            }
            string str6 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"7,\")]", 0);
            if(client.SwipeWhileNotFound("Down", 500, 200, "NATIVE", "xpath=//*[starts-with(@text, \"8,\")]", 0, 1000, 5, false))
            {
                  // If statement
            }
            string str7 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"8,\")]", 0);
            if(client.SwipeWhileNotFound("Down", 500, 200, "NATIVE", "xpath=//*[starts-with(@text, \"9,\")]", 0, 1000, 5, false))
            {
                  // If statement
            }
            string str8 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"9,\")]", 0);
            if(client.SwipeWhileNotFound("Down", 500, 200, "NATIVE", "xpath=//*[starts-with(@text, \"10,\")]", 0, 1000, 5, false))
            {
                  // If statement
            }
            string str9 = client.ElementGetText("NATIVE", "xpath=//*[starts-with(@text, \"10,\")]", 0);
            client.Click("default", "aliexpress", 0, 1);
            client.Click("NATIVE", "xpath=//*[@text='Download']", 0, 1);
        }

        [TestCleanup()]
        public void TearDown()
        {
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.GenerateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
        client.ReleaseClient();
        }
    }
}