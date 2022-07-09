package bot;

import command.CommandContainer;
import config.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import service.SendMessageServiceImpl;

public class WeatherBot extends TelegramLongPollingBot {
    public static final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;


    @Override
    public String getBotUsername() {
        return BotConfig.botName;
    }

    @Override
    public String getBotToken() {
        return BotConfig.botToken;
    }


    public WeatherBot() {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().toLowerCase();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.getCommand(commandIdentifier).execute(update);
            }
        }
    }
}
