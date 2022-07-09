package command;

import com.google.common.collect.ImmutableMap;
import service.SendMessageService;

import static command.CommandName.*;

public class CommandContainer {

    ImmutableMap<String, Command> commandMap;

    private final Command unknownCommand;

    public CommandContainer(SendMessageService sendMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendMessageService))
                .put(WEATHER.getCommandName(), new WeatherCommand(sendMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command getCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
