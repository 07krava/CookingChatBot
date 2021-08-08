package mate.academy.javabot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MateAcademyBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "krava_simple_cooking_bot";
    }

    @Override
    public String getBotToken() {
        return "1926581875:AAG2PDp2LzZ-eUKmEjzEGrE7xiCfMQqtv1Q";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println("Message test " + message.getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello user! I received your message: " + message.getText());
        sendMessage.setChatId(String.valueOf(message.getChatId()));


        if(message.getText().equals("/start")){
            String text = "Welcome to Recipe Bot! Please pass the meal of the day!\n";

            sendMessage.enableMarkdown(true);
            ReplyKeyboardMarkup keyboardMarkup = getMenuKeyboard();
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage.setText(text);
        }

        if (message.getText().equals("breakfast")){
            String menu = "Breakfast menu!\n";
            menu = menu + "1. Blueberry-Banana-Nut Smoothie\n";
            menu = menu + "2. Classic Omelet and Greens\n";
            menu = menu + "3. Curry-Avocado_Crispy Egg_Toast\n";

            sendMessage.setText(menu);
        }

        if (message.getText().equals("dinner")){
            String menu = "dinner menu\n";
            menu = menu + "1. Creamy Lemon Chicken Pasta\n";
            menu = menu + "2. Turkey Tacos\n";
            menu = menu + "3. Vegetarian Lasagna\n";

            sendMessage.setText(menu);
        }

        if (message.getText().equals("lunch")){
            String text = "Lunch menu is in progress...";

            sendMessage.setText(text);
        }

        if (message.getText().equals("supper")){
            String text = "Supper menu is in progress...";

            sendMessage.setText(text);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getMenuKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("breakfast");
        keyboardRow.add("dinner");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("lunch");
        keyboardSecondRow.add("supper");
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
