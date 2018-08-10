package net.dukederubberduck;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class Ramblebot extends TelegramLongPollingBot implements Textconstants
{
    wordtype wt = null;

    ButtonsBuilder bb = new ButtonsBuilder();
    InputChecker ic = new InputChecker();
    dbManager dbm = new dbManager();

    @Override
    public void onUpdateReceived (Update update)
    {
        if (update.hasMessage() && wt == null)
        {
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText();

            if (message.equals("I'am bored") || message.equals("/abyss"))
            {
                sendMsg(chatId, dbm.dbGetRandomPhrase(), false);
            }
            else if (message.equals("About me") || message.equals("/start") || message.equals("/START") || message.equals("/about"))
            {
                sendMsg(chatId, ABOUT_TXT, false);
            }
            else if (message.equals("/inputrules"))
            {
                sendMsg(chatId, INPUTRULES_TXT, false);
            }
            else if (message.equals("/why"))
            {
                sendMsg(chatId, WHY_TXT, false);
            }
            else if (message.equals("Add to the Abyss") || message.equals("/add"))
            {
                sendMsg(chatId, ABYSS_TXT, true);
            }
            else sendMsg(chatId, DEFAULT_TXT, false);
        }

        if (update.hasMessage() && wt != null)
        {
            sendMsg(update.getMessage().getChatId().toString(), ic.CheckAll(update.getMessage().getText().toLowerCase(), wt), false);
            wt = null;
        }

        if (update.hasCallbackQuery())
        {
            wt = wordtype.valueOf(update.getCallbackQuery().getData());
            sendMsg(update.getCallbackQuery().getMessage().getChatId().toString(), wt.getDescriptionText(), false);
        }
    }

    public synchronized void sendMsg(String chatId, String s, boolean inline)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        if (inline) bb.setInlineButtons(sendMessage);

        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername()
    {
        return "ddrdtestbot";
    }

    @Override
    public String getBotToken()
    {
        return "633359295:AAFfDgrJ8wEs-5iieaAgGhFC-oAn2yXqBuk";
    }

    public static void main(String[] args)
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try
        {
            telegramBotsApi.registerBot(new Ramblebot());
        }
        catch (TelegramApiRequestException e)
        {
            e.printStackTrace();
        }
    }
}

