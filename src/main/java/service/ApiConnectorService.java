package service;

import model.WeatherModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class ApiConnectorService {
    public void connectToApi(WeatherModel weatherModel, String city) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=88e3de03af66b0d78f74b46972440612&units=metric&lang=ru");
            Scanner scanner = new Scanner((InputStream) url.getContent());
            StringBuilder result = new StringBuilder();

            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }


            JSONObject obj = new JSONObject(result.toString());

            weatherModel.setName(obj.getString("name"));

            JSONArray weatherInfo = (JSONArray) obj.get("weather");
            JSONObject description = (JSONObject) weatherInfo.iterator().next();
            weatherModel.setMainInfo(description.getString("description"));

            JSONObject main = obj.getJSONObject("main");
            weatherModel.setTemp(main.getDouble("temp"));
            weatherModel.setHumidity(main.getDouble("humidity"));

            JSONObject wind = obj.getJSONObject("wind");
            weatherModel.setSpeed(wind.getDouble("speed"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
