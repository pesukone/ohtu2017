package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
		String studentNr = "012345678";
		if (args.length > 0) {
			studentNr = args[0];
		}
		
		String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
		String courseUrl = "http://ohtustats2017.herokuapp.com/courses/1.json";
		
		String bodyText = Request.Get(url).execute().returnContent().asString();
		String courseBody = Request.Get(courseUrl).execute().returnContent().asString();
		
		Gson mapper = new Gson();
		JsonArray subs = mapper.fromJson(bodyText, JsonArray.class);
		JsonObject course = mapper.fromJson(courseBody, JsonObject.class);
		
		int totalHours = 0;
		int exercises = 0;
		
		System.out.println("Kurssi: " + course.get("name").getAsString() + " " + course.get("term").getAsString());
		
		System.out.println("opiskelijanumero: " + studentNr);
		
		for (int i = 0; i < subs.size(); i++) {
			JsonObject sub = (JsonObject) subs.get(i);
			
			System.out.print("viikko " + sub.get("week") + ": ");
			System.out.print("tehtyjä tehtäviä yhteensä: ");
			
			int done = 0;
			int iterating = 1;
			while (!sub.get("a" + iterating).isJsonNull()) {
				if (sub.get("a" + iterating).getAsBoolean()) {
					done++;
				}
				exercises++;
				iterating++;
			}
			System.out.print(done);
			System.out.print(" (maksimi " + course.get("week" + sub.get("week")).getAsInt() + ")");
			
			System.out.print(", aikaa kului " + sub.get("hours") + " tuntia, tehdyt tehtävät:");
			totalHours += sub.get("hours").getAsInt();
			
			iterating = 1;
			while (!sub.get("a" + iterating).isJsonNull()) {
				if (sub.get("a" + iterating).getAsBoolean()) {
					System.out.print(" " + iterating);
				}
				iterating++;
			}
			
			System.out.println();
		}
		
		System.out.println("yhteensä: " + exercises + " tehtävää " + totalHours + " tuntia");
    }
}