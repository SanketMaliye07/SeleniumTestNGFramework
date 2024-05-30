package Utilities;

 
import java.io.FileReader;
 

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 

public class JSONUtils {
	 private static final String JSON_FILE_PATH = "src/main/java/TestData/LoginData.json";

	 public static JSONObject readJsonFile() {
	        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
	            JSONParser parser = new JSONParser();
	            return (JSONObject) parser.parse(reader);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error reading JSON file: " + e.getMessage());
	        }
	        return null;
	    }
	 
	 public static String getJsonValue(JSONObject jsonObject, String fieldName) {
	        if (jsonObject != null && jsonObject.containsKey(fieldName)) {
	            return jsonObject.get(fieldName).toString();
	        } else {
	            System.out.println("Field '" + fieldName + "' not found in JSON.");
	            return null;
	        }
	    }
}
