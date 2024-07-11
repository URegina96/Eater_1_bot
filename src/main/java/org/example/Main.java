package org.example;

// Главный класс программы package org.example.три;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class); // Создаем объект для работы с Telegram Bots API
        telegramBotsApi.registerBot(new TGB()); // Регистрируем нашего бота
    }
}