package dataProviders;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import pojo.UserData;
import pojo.UsersData2;
import utils.JsonDataReader;

public class E2EData {

    @DataProvider(name = "getEData")
    public Object[][] getData() {
        
        // Dataset 1 – Akki
        HashMap<String, String> hm1 = new HashMap<>();
        hm1.put("name", "Akki");
        hm1.put("email", "dailyyHustlin44@gmail.com");
        hm1.put("password", "Broken@098");
        hm1.put("fName", "Abhishek");
        hm1.put("lName", "Chauhan");
        hm1.put("company", "Nugats Technologies");
        hm1.put("address", "Sector 62");
        hm1.put("address2", "Azad Vihar Colony, Block A");
        hm1.put("city", "Noida");
        hm1.put("state", "UttarPradesh");
        hm1.put("zipcode", "280001");
        hm1.put("country", "India");
        hm1.put("mobNo", "8494045494");
        hm1.put("nameOnCard", "Abhishek");
        hm1.put("cardNumber", "8596454662");
        hm1.put("cvc", "585");
        hm1.put("expMonth", "10");
        hm1.put("expYear", "2050");

        // Dataset 2 – John
        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("name", "John");
        hm2.put("email", "EntXJonathan123@gmail.com");
        hm2.put("password", "Johnny@123");
        hm2.put("fName", "John");
        hm2.put("lName", "Doe");
        hm2.put("company", "Tech Solutions");
        hm2.put("address", "MG Road");
        hm2.put("address2", "Block B");
        hm2.put("city", "Bangalore");
        hm2.put("state", "Karnataka");
        hm2.put("zipcode", "560001");
        hm2.put("country", "India");
        hm2.put("mobNo", "9123456780");
        hm2.put("nameOnCard", "John Doe");
        hm2.put("cardNumber", "4111111111111111");
        hm2.put("cvc", "123");
        hm2.put("expMonth", "08");
        hm2.put("expYear", "2030");

        // Dataset 3 – Sara
        HashMap<String, String> hm3 = new HashMap<>();
        hm3.put("name", "Sara");
        hm3.put("email", "sara_tester@gmail.com");
        hm3.put("password", "Sara@456");
        hm3.put("fName", "Sara");
        hm3.put("lName", "Khan");
        hm3.put("company", "Global Corp");
        hm3.put("address", "Park Street");
        hm3.put("address2", "Near Metro");
        hm3.put("city", "Kolkata");
        hm3.put("state", "WestBengal");
        hm3.put("zipcode", "700016");
        hm3.put("country", "India");
        hm3.put("mobNo", "9876543210");
        hm3.put("nameOnCard", "Sara Khan");
        hm3.put("cardNumber", "5555555555554444");
        hm3.put("cvc", "456");
        hm3.put("expMonth", "12");
        hm3.put("expYear", "2035");

        // Return all datasets as Object[][]
        return new Object[][] {
            { hm1 },
            { hm2 },
            { hm3 }
        };
    }
    
    @DataProvider(name = "getEDataPOJO")
    public Object[][] getData1() {

        UserData user1 = new UserData(
            "Akki", "dailyyHustlin44@gmail.com", "Broken@098",
            "Abhishek", "Chauhan", "Nugats Technologies",
            "Sector 62", "Azad Vihar Colony, Block A", "Noida",
            "UttarPradesh", "280001", "India", "8494045494",
            "Abhishek", "8596454662", "585", "10", "2050"
        );

        UserData user2 = new UserData(
            "John", "EntXJonathan123@gmail.com", "Johnny@123",
            "John", "Doe", "Tech Solutions",
            "MG Road", "Block B", "Bangalore",
            "Karnataka", "560001", "India", "9123456780",
            "John Doe", "4111111111111111", "123", "08", "2030"
        );

        UserData user3 = new UserData(
            "Sara", "sara_tester@gmail.com", "Sara@456",
            "Sara", "Khan", "Global Corp",
            "Park Street", "Near Metro", "Kolkata",
            "WestBengal", "700016", "India", "9876543210",
            "Sara Khan", "5555555555554444", "456", "12", "2035"
        );

        return new Object[][] {
            { user1 },
            { user2 },
            { user3 }
        };
    }
    
    @DataProvider(name = "getJsonDataHashMap")
    public Object[][] getDataUsingJson() throws IOException
    {
    	JsonDataReader jsReader = new JsonDataReader();
    	String filePath = "\\src\\test\\resources\\TestData\\UsersData.json";
    	
    	List<HashMap<String,String>> data = jsReader.getJsonDataToHashMap(filePath);
    	
    	return new Object[][] 
    	{
    		{data.get(0)},
    		{data.get(1)},
    		{data.get(2)}
    	};
    }
    
    @DataProvider (name = "getJsonDataPOJO")
    public Object[][] getDataUsingJsonPOJO() throws IOException
    {
    	JsonDataReader jsReader = new JsonDataReader();
    	String filePath = "\\src\\test\\resources\\TestData\\UsersData.json";
    	
    	List<UsersData2> data = jsReader.getJsonDataToPOJO(filePath);
		return new Object[][] {
			{data.get(0)},
			{data.get(1)},
			{data.get(2)}
		};
    }
}