package api;

import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class api{

    public static String[] searchJournals(String content, String entityType) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse("https://api.core.ac.uk/v3/search/journals/")
                .newBuilder();
        urlBuilder.addQueryParameter("api_key", "VvHk7Ywj3rcgPLx1SNlmCMiBG2odIyDZ");
        urlBuilder.addQueryParameter("q", content);

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
                converter.add(result.getJSONObject(i).getJSONArray("identifiers").get(2).toString());
            }
            else{
                converter.add("no url provided");
            }
        }
        int size = converter.size();
        return converter.toArray(new String[size]);
    }

    public static String searchWork

}

