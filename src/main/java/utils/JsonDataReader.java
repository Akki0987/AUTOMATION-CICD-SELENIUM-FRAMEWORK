package utils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.UsersData2;

public class JsonDataReader {

	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
//		1. Read json file data as String
		File file = new File(System.getProperty("user.dir")+filePath);
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
//		2. Convert json data String into hashmap
		ObjectMapper mapper = new ObjectMapper();
		
		List <HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
		{});
		
		return data;
	}
	
	public List<UsersData2> getJsonDataToPOJO(String filePath) throws IOException
	{
		File file = new File(System.getProperty("user.dir")+ filePath);
		
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<UsersData2> data = mapper.readValue(jsonContent, new TypeReference<List<UsersData2>>() {});
		
		return data;
	}
}
