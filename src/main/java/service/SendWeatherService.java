package service;

import com.vdurmont.emoji.EmojiParser;
import model.WeatherModel;

public class SendWeatherService {
    String city;

    ApiConnectorService apiConnectorService;

    public SendWeatherService(ApiConnectorService apiConnectorService) {
        this.apiConnectorService = apiConnectorService;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherInformation() {
        WeatherModel weatherModel = new WeatherModel();
        apiConnectorService.connectToApi(weatherModel, city);
        String result = "Город: " + weatherModel.getName() + ", " + weatherModel.getMainInfo() + "\n" +
                ":temperature:Температура: " + weatherModel.getTemp() + ":temperature:\n" +
                ":droplet:Влажность: " + weatherModel.getHumidity() + ":droplet:\n" +
                ":dash:Скорость ветра: " + weatherModel.getSpeed() + " м/c" + ":dash:\n";
        return EmojiParser.parseToUnicode(result);
    }
}
