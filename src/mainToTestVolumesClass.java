import java.util.Scanner;

import model.Volume;

import org.json.JSONArray;


public class mainToTestVolumesClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// we call CVrequest pass in volume Name that we got from user
		// then we get a jsonarray back
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();
		// we call CVrequest pass in volume Name that we got from user
		// then we get a jsonarray back
		JSONArray volarray = CVrequest.searchVolume(input);	

		for(int i = 0; i < volarray.length(); i++){
			Volume volumes = new Volume(volarray.getJSONObject(i));
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

}
