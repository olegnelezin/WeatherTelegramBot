package command;

public interface CommandValue {
    String UNKNOWN_COMMAND = "Не понимаю вас, напишите /help, чтобы узнать список комманд";

    String START_COMMAND = "Данный бот создан для того, чтобы показывать погоду в любом городе мира.\nЧтобы узнать погоду, введите команду вида /weather 'название города'.\n/help - информация о командах и боте.";

    String HELP_COMMAND = "/weather 'название города' - узнать погоду\nДанный бот использует библиотеку openweathermap api";
}
