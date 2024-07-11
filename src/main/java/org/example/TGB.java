// Импортируем необходимые классы из библиотек Telegram
package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

// Объявляем класс для создания Telegram бота, расширяем TelegramLongPollingBot
public class TGB extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "тут название бота"; // Метод для получения имени бота
    }

    @Override
    public String getBotToken() {
        return "тут токен"; // Метод для получения токена бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        // получаем информацию о сообщении
        String chatID = update.getMessage().getChatId().toString(); // Получаем ID чата
        String text = update.getMessage().getText(); // Получаем текст сообщения

        // создаем объект для отправки сообщения
        SendMessage sendMessage = new SendMessage(); // Создаем объект для отправки сообщения
        sendMessage.setChatId(chatID); // Устанавливаем ID чата для отправки сообщения

        // проверяем текст сообщения
        if (text.equals("/start")) {
            // отправляем сообщение с вопросом "Чем помочь" и кнопкой "Начать"
            sendMessage.setText("Чем помочь?"); // Устанавливаем текст сообщения
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(); // Создаем объект для клавиатуры
            List<KeyboardRow> keyboard = new ArrayList<>(); // Создаем список клавиатурных рядов
            KeyboardRow row = new KeyboardRow(); // Создаем клавиатурный ряд
            row.add(new KeyboardButton("Начать")); // Добавляем кнопку "Начать" в ряд
            keyboard.add(row); // Добавляем ряд в список клавиатур
            keyboardMarkup.setKeyboard(keyboard); // Устанавливаем клавиатуру в объект для отправки сообщения
            sendMessage.setReplyMarkup(keyboardMarkup); // Добавляем клавиатуру к сообщению
        } else if (text.equals("Начать")) {
            // отправляем сообщение с тремя кнопками "1", "2", "3"
            sendMessage.setText("Выберите опцию:"); // Устанавливаем текст сообщения
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(); // Создаем объект для клавиатуры
            List<KeyboardRow> keyboard = new ArrayList<>(); // Создаем список клавиатурных рядов
            KeyboardRow row = new KeyboardRow(); // Создаем клавиатурный ряд
            row.add(new KeyboardButton("1")); // Добавляем кнопку "1" в ряд
            row.add(new KeyboardButton("2")); // Добавляем кнопку "2" в ряд
            row.add(new KeyboardButton("3")); // Добавляем кнопку "3" в ряд
            keyboard.add(row); // Добавляем ряд в список клавиатур
            keyboardMarkup.setKeyboard(keyboard); // Устанавливаем клавиатуру в объект для отправки сообщения
            sendMessage.setReplyMarkup(keyboardMarkup); // Добавляем клавиатуру к сообщению
        } else {
            // обрабатываем выбор пользователя
            String responseText = "";
            switch (text) {
                case "1":
                    responseText = "Вы выбрали первую опцию";
                    break;
                case "2":
                    responseText = "Вы выбрали вторую опцию";
                    break;
                case "3":
                    responseText = "Вы выбрали третью опцию";
                    break;
                default:
                    responseText = "Для начала работы введите /start";
                    break;
            }
            sendMessage.setText(responseText); // Устанавливаем текст ответа
        }

        try {
            // отправляем сообщение пользователю
            this.execute(sendMessage); // Отправляем сообщение пользователю
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
