package com.maksymzelinskyi.service;

import com.maksymzelinskyi.util.PropertiesHolder;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class TelegramMessanger {

    private PropertiesHolder propertiesHolder = PropertiesHolder.getInstance();
    private final String BOT_API_KEY = propertiesHolder.getProps().getProperty("BOT_KEY");
    private final String CHAT_ID = propertiesHolder.getProps().getProperty("CHAT_ID");
    private TelegramBot bot;

    public TelegramMessanger() {
        bot = new TelegramBot(BOT_API_KEY);
    }

    public void sendMessage(String message) {
        bot.execute(new SendMessage(Long.parseLong(CHAT_ID), message));
    }
}
