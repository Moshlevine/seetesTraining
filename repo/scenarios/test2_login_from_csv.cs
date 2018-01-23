using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using experitestClient;

namespace Experitest
{
    [TestClass]
    public class test2loginfromcsv
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
            client.SetReporter("xml", "reports", "test2loginfromcsv");
        }

        [TestMethod]
        public void Testtest2loginfromcsv()
        {
            client.SetDevice("adb:Samsung Note 3");
            client.Launch("com.experitest.ExperiBank/.LoginActivity", true, true);
            client.ElementSendText("NATIVE", "hint=Username", 0, "company");
            client.ElementSendText("NATIVE", "hint=Password", 0, "company");
            client.Click("NATIVE", "text=Login", 0, 1);
            client.VerifyElementFound("NATIVE", "id=makePaymentButton", 0);
            string str0 = client.GetTextIn("default", "Make Payment", 0, "TEXT", "Up", 0, 250);
            client.Click("default", "Make Payment", 0, 1);
            if(client.WaitForElement("default", "Phone", 0, 10000))
            {
                  // If statement
            }
            client.ElementSendText("default", "Phone", 0, "052");
            client.ElementSendText("default", "Name", 0, "dan");
            client.ElementSendText("default", "Amount", 0, "35");
            client.Click("default", "Select", 0, 1);
            client.Click("default", "Greenland", 0, 1);
            client.Click("default", "Send Payment", 0, 1);
            client.Click("default", "Yes", 0, 1);
            client.VerifyIn("default", "Make Payment", 0, "Up", "TEXT", "30.00$", 0, 250);
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