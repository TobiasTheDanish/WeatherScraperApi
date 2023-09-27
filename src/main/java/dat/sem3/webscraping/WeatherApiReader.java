package dat.sem3.webscraping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dat.sem3.dto.CurrentWeatherDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherApiReader {

    public static CurrentWeatherDTO getCurrentWeather() {
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .get()
                    .url("https://vejr.eu/api.php?location=København&degree=C")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    System.err.println("Request failed with code: " + response.code());
                    return null;
                }

                String responseBody = response.body().string();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                return gson.fromJson(responseBody, CurrentWeatherDTO.class);


            } catch (IOException e) {
                System.err.println("An error occurred while making the API request:");
                e.printStackTrace();
                return null;
            }
    }
}
