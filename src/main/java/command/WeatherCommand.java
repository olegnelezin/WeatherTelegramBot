package command;

import org.telegram.telegrambots.meta.api.objects.Update;
import service.ApiConnectorService;
import service.SendMessageService;
import service.SendWeatherService;

public class WeatherCommand implements Command {

    private final SendMessageService sendMessageService;
    private final SendWeatherService sendWeatherService;

    public WeatherCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
        sendWeatherService = new SendWeatherService(new ApiConnectorService());
    }

    @Override
    public void execute(Update update) {
        String weatherCity = update.getMessage().getText().toLowerCase().split(" ")[1].toLowerCase();
        sendWeatherService.setCity(weatherCity);

        String chatId = update.getMessage().getChatId().toString();
        sendMessageService.sendMessage(chatId, sendWeatherService.getWeatherInformation());
    }
}
