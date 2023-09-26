package dat.sem3.webscraping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.ToString;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class WeatherApiReader {

    public static void ApiReader() {
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .get()
                    .url("https://vejr.eu/api.php?location=København&degree=C")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    System.err.println("Request failed with code: " + response.code());
                    return;
                }

                String responseBody = response.body().string();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Object parsedResponse = gson.fromJson(responseBody, Object.class);
                String prettyJson = gson.toJson(parsedResponse);

                System.out.print("Today's forecast:");
                System.out.println(prettyJson
                        .replace("{", "")
                        .replace("}", "")
                        .replace("\"", ""));


            } catch (IOException e) {
                System.err.println("An error occurred while making the API request:");
                e.printStackTrace();
            }
    }
}
