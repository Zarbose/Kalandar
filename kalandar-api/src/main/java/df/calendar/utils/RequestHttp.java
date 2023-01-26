package df.calendar.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHttp {

    private URL url;

    private static final String url_string="http://localhost:8080/kalandar_api_war/rest/rdv";


    public RequestHttp() throws IOException {
        this.url =  new URL(this.url_string);
    }

    public void getRequest(String json) throws IOException{
        HttpURLConnection con = (HttpURLConnection) this.url.openConnection();
        con.setRequestMethod("GET");
        // con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            /*BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());*/
            try{
                InputStream in = new BufferedInputStream(con.getInputStream());
            }
            finally {
                con.disconnect();
            }

        }
        else {
            System.out.println("GET request did not work.");
        }

    }

    public void postRequest(String json) throws IOException {
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);
        String jsonInputString = json;

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

}
