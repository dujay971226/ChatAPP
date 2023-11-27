package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CORE{

    public static String[] searchJournals(String content) throws IOException {
        String[] spl = content.split(";");
        String format = spl[0];
        int j=0;
        for (String i: spl){
            if (j!=0) {
                format = format + " AND " + i;
            }
            j=1;
        }
        System.out.println(format);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse("https://api.core.ac.uk/v3/search/journals/")
                .newBuilder();
        urlBuilder.addQueryParameter("api_key", "VvHk7Ywj3rcgPLx1SNlmCMiBG2odIyDZ");
        urlBuilder.addQueryParameter("q", format);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();


        Response response = client.newCall(request).execute();
        System.out.println(response);
        JSONObject responseBody = new JSONObject(response.body().string());


        JSONArray result = responseBody.getJSONArray("results");
        List<String> converter = new ArrayList<>();
        for(int i = 0; i < result.length(); i++){
            if(result.getJSONObject(i).getJSONArray("identifiers").get(2).toString().startsWith("url")) {
                converter.add(result.getJSONObject(i).getString("title") + "-----" + result.getJSONObject(i).getJSONArray("identifiers").get(2).toString());
            }

            else{
                converter.add(result.getJSONObject(i).getString("title") + "-----" + "no url provided");
            }
        }
        int size = converter.size();
        return converter.toArray(new String[size]);
    }

    public static String searchJournalsByISSN(String issn) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.core.ac.uk/v3/journals/issn:" + issn;
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(url)
                .newBuilder();
        urlBuilder.addQueryParameter("api_key", "VvHk7Ywj3rcgPLx1SNlmCMiBG2odIyDZ");

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        JSONObject responseBody = new JSONObject(responseBodyString);
        List<String> theJournal = new ArrayList<>();

        if (responseBody.has("title")) {
            theJournal.add("The title: " + responseBody.getString("title").toString());

            if (responseBody.getJSONArray("identifiers").get(2).toString().startsWith("url")) {
                theJournal.add(responseBody.getJSONArray("identifiers").get(2).toString());
            } else {
                theJournal.add("no url provided");
            }

            return theJournal.toString();
        }
        else{
            return "no ournal founded";
        }

    }

    public static String searchByDOI(String DOI) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.core.ac.uk/v3/discover";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("doi", DOI);
        RequestBody body = RequestBody.create(mediaType, jsonBody.toString());


        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("api_key", "VvHk7Ywj3rcgPLx1SNlmCMiBG2odIyDZ")
                .build();

        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        JSONObject responseBody = new JSONObject(responseBodyString);
        if(responseBody.has("fullTextLink") && !responseBody.isNull("fullTextLink")) {
            return responseBody.getJSONObject("fullTextLink").toString();
        }
        else
        {return "no text founded";}

    }

}

