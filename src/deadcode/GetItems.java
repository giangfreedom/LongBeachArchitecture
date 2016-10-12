package deadcode;
import CVrequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;














import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;

import model.Volumes;


public class GetItems {
	private	jsonParserObj parsedjsonObj = new jsonParserObj();
	private	jsonParserArray parsedjsonArray = new jsonParserArray();
	
	public void getVolumesList (String volumeName){	
		// we call CVrequest pass in volume Name that we got from user
		// then we get a jsonarray back
		JSONArray volarray = CVrequest.searchVolume(volumeName);	

		for(int i = 0; i < volarray.length(); i++){
			Volumes volumes = new Volumes(volarray.getJSONObject(i));
			System.out.println();			
			System.out.println("name: " + volumes.getName()
					+ "\t\t\tstart_year: " + volumes.getStartYear()
					+ "\tpublisher: " + volumes.getPubliher()
					+ "\t\tid: " + volumes.getID()
					+ "\tcount_of_issues: " + volumes.getCountofIssue()
					+ "\timage medium size link: " + volumes.getImage()
					+ "\tresources type: " + volumes.getResource_type());
			System.out.println();
		}
	}
	
	public IssueResults getIssuesList (String issueID){
		// make call to CVrequest
		JsonNode response = CVrequest.getVolumeIDs(issueID,1);
		//System.out.println(response);

		JSONArray ja = response.getObject().getJSONArray("results");
		//System.out.println(ja.toString());
		
		// issue obj that alex will get
		IssueResults issueObj = new IssueResults();
		
		issueObj = parsedjsonArray.IssueArrayParser(ja);
		
		return issueObj;
	}
	
	public StringObject getIssuesDetail (JSONObject JsonIssueObject){
		// issue obj that alex will get
		StringObject issueStringObj = new StringObject();

		issueStringObj = parsedjsonObj.issueDetailParser(JsonIssueObject);
		
		return issueStringObj;
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
	}
	/*
	* Returns all data for the given issue id
	* @param issId - String of the comic's unqiue ID
	* @return JSON object with the following keys
	* person_credits<br> concept_credits<br> first_appearance_storyarcs<br> aliases<br> deck<br> description<br>
	* api_detail_url<br> issue_number<br> location_credits<br> cover_date<br> id<br> date_last_updated<br> store_date<br>
	* character_credits<br> first_appearance_locations<br> image<br> site_detail_url<br> first_appearance_objects<br>
	* first_appearance_concepts<br> first_appearance_characters<br> volume<br>date_added<br> first_appearance_teams<br>
	* team_credits<br>name<br>story_arc_credits<br>character_died_in<br>object_credits<br>has_staff_review<br>
	* team_disbanded_in
	*/
	public static JSONObject getJsonObjIssue (String issueID){
		JSONObject joIssue = new JSONObject();
		try{
			joIssue = CVrequest.getIssue(issueID);
		}catch (Exception e) {  
			   e.printStackTrace();  
	  	} 
		return joIssue;
	}
}
