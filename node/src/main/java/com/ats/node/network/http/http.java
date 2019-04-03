import java.io.*;
import java.net.*

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	// HTTP GET request
	private void sendGet(IP) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) new URL(IP).openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to node : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}