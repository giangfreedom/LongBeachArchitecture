import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;

import results.IssueResults;
import results.VolumeResults;
import model.IssueObject;
import model.StringObject;
import model.VolumeObj;


public class GetItems {
	private	jsonParserObj parsedjsonObj = new jsonParserObj();
	private	jsonParserArray parsedjsonArray = new jsonParserArray();
	
	public VolumeResults getVolumesList (String volumeName){
		VolumeResults volumes = new VolumeResults();
		
		// we call CVrequest pass in volume Name that we got from user
		// then we get a jsonarray back
		JSONArray volarray = CVrequest.searchVolume(volumeName);		
		
		volumes = parsedjsonArray.VolumeArrayParser(volarray);
		
		return volumes;
	}
	
	public IssueResults getIssuesList (String issueID){
		// make call to CVrequest
		JsonNode response = CVrequest.getVolumeIDs(issueID,1);
		//System.out.println(response);

		JSONArray ja = response.getObject().getJSONArray("results");
		//System.out.println(ja.toString());
		/*
		Gson gson = new Gson();
  	  	try {  
		   //write converted json data to a file named "CountryGSON.json"  
		   FileWriter writer = new FileWriter("C:\\Users\\SuperAdmin\\Desktop\\jamejson\\IssueArray.json");  
		   writer.write(gson.toJson(ja));  
		   writer.close();  
		    
  	  	} catch (IOException e) {  
		   e.printStackTrace();  
  	  	}  	*/
		// issue obj that alex will get
		IssueResults issueObj = new IssueResults();
		
		issueObj = parsedjsonArray.IssueArrayParser(ja);
		
		return issueObj;
	}
	
	public StringObject getIssuesDetail (String issueID){
		// make call to CVrequest
		JSONObject joIssue = CVrequest.getIssue(issueID);
		/*
		Gson gson = new Gson();
  	  	try {  
		   //write converted json data to a file named "CountryGSON.json"  
		   FileWriter writer = new FileWriter("C:\\Users\\SuperAdmin\\Desktop\\jamejson\\IssueDetail.json");  
		   writer.write(gson.toJson(joIssue));  
		   writer.close();  
		    
  	  	} catch (IOException e) {  
		   e.printStackTrace();  
  	  	} */ 	
		// issue obj that alex will get
		StringObject issueStringObj = new StringObject();

		issueStringObj = parsedjsonObj.issueDetailParser(joIssue);
		
		return issueStringObj;
	}
}