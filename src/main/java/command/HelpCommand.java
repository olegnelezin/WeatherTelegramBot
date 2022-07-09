package command;

import org.telegram.telegrambots.meta.api.objects.Update;
import service.SendMessageService;

public class HelpCommand implements Command {

    private final SendMessageService sendMessageService;

    public HelpCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendMessageService.sendMessage(chatId, CommandValue.HELP_COMMAND);
    }
}
