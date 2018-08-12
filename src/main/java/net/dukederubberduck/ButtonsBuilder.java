package net.dukederubberduck;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

class ButtonsBuilder
{
    public synchronized void setInlineButtons (SendMessage sendMessage)
    {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("Первое существительное").setCallbackData("noun1"));
        buttons.add(buttons1);
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        buttons2.add(new InlineKeyboardButton().setText("Второе существительное").setCallbackData("noun2"));
        buttons.add(buttons2);
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        buttons3.add(new InlineKeyboardButton().setText("Глагол").setCallbackData("verb"));
        buttons3.add(new InlineKeyboardButton().setText("Прилагательное").setCallbackData("adj"));
        buttons.add(buttons3);
        List<InlineKeyboardButton> buttons4 = new ArrayList<>();
        buttons4.add(new InlineKeyboardButton().setText("Третье существительное").setCallbackData("noun3"));
        buttons.add(buttons4);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
    }
}
