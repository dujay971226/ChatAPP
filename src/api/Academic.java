package api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Academic {

    public static String searchJournals(String content) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.semanticscholar.org/graph/v1/paper/search?").newBuilder();
        urlBuilder.addQueryParameter("query", content);
        urlBuilder.addQueryParameter("fields", "title,url,abstract");
        urlBuilder.addQueryParameter("offset", "100");
        urlBuilder.addQueryParameter("limit", "10");

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        String result = "";

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBodyString = response.body().string();
            JSONObject responseBody = new JSONObject(responseBodyString);

            JSONArray papers = responseBody.getJSONArray("data");

            for (int i = 0; i < papers.length(); i++) {
                JSONObject paper = papers.getJSONObject(i);
                String title = paper.getString("title");
                String paperId = paper.getString("url");
                result = result + title + "-----" + paperId + "\n";

            }
            return result;
        } catch (IOException e) {
            return "no text founded";
        }
    }

    public static String searchAuthor(String name) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.semanticscholar.org/graph/v1/author/search?").newBuilder();
        urlBuilder.addQueryParameter("query", name);
        urlBuilder.addQueryParameter("fields", "name,url");
        urlBuilder.addQueryParameter("offset", "100");
        urlBuilder.addQueryParameter("limit", "5");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        String result = "";
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBodyString = response.body().string();
            JSONObject responseBody = new JSONObject(responseBodyString);

            JSONArray papers = responseBody.getJSONArray("data");

            for (int i = 0; i < papers.length(); i++) {
                JSONObject paper = papers.getJSONObject(i);
                String title = paper.getString("name");
                String paperId = paper.getString("url");
                result = result + title + "-----" + paperId + "\n";

            }
            return result;
        } catch (IOException e) {
            return "no author founded";
        }

    }

    public static String searchByDOI(String DOI) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.semanticscholar.org/graph/v1/paper/"+DOI).newBuilder();
        urlBuilder.addQueryParameter("fields", "title,url,abstract");
        urlBuilder.addQueryParameter("offset", "100");
        urlBuilder.addQueryParameter("limit", "1");

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        String result = "";

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBodyString = response.body().string();
            JSONObject responseBody = new JSONObject(responseBodyString);

            String title = responseBody.getString("title");
            String articleUrl = responseBody.getString("url");
            String articleAbstract = "";
            if(!responseBody.isNull("abstract")) {
                articleAbstract = responseBody.getString("abstract");
            }
            else{
                articleAbstract = "no abstract provided";
            }
            result = title + "-----" + articleUrl + "\n" + "ABSTRACT-----" + articleAbstract + "\n";

            return result;
        } catch (IOException e) {
            return "no text founded";
        }

    }
}

