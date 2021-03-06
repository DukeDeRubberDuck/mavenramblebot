package net.dukederubberduck;

public interface Textconstants
{
    // Ниже тексты типовых ответов бота
    String ABYSS_TXT = "Хотите добавить слово в бездну? Вот доступные типы слов:";
    String DEFAULT_TXT = "Я все понимаю, но время для ИИ-революции еще не пришло... пожалуйста, придерживайтесь списка доступных команд.";
    String ABOUT_TXT = "Я — Бредобот. Собираю типовые фразы из бездны доступных слов.\n\n" +
            "/abyss выводит случайную фразу,\n" +
            "/add помогает добавить новое слово в бездну,\n" +
            "/inputrules покажет подробные правила ввода,\n" +
            "/why если вы не понимаете зачем я нужен.";
    String INPUTRULES_TXT = "Принимаются только русские слова. Правильно написанные, поставленные в указанную форму и не более двадцати пяти букв в длину.";
    String WHY_TXT = "Этот бот — учебный проект. Строит типовую фразу из пользовательского ввода. Функционал ограничен, но возможно будет расширен в дальнейшем. Написан на Джаве.";

    // Ниже упрощение связи с базой данных
    //"postgresql-angular-72908";
    String DB_URL = "jdbc:postgresql://ec2-54-217-214-201.eu-west-1.compute.amazonaws.com:5432/ddmsugnlpolh1k?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    String USER = "sfeutjfxiqktiw";
    String PASS = "656ff93e41a1fea6d4d0adaeed653f675f0fd17b8a94cb6facb8ddf5cc39d14f";

    //  Ниже константы enum wordtype. Допустимые окончания должны быть одинаковой (внутри массива) длинны.
    String [] NOUN1_ALLOWED_ENDINGS = {"а", "я", "и", "ы"};
    String [] NOUN2_ALLOWED_ENDINGS = {"ы"};
    String [] VERB_ALLOWED_ENDINGS = {"т"};
    String [] ADJ_ALLOWED_ENDINGS = {"ую", "юю"};
    String [] NOUN3_ALLOWED_ENDINGS = {"у", "ю", "ь"};
    String NOUN1_TXT = "Введите первое существительное фразы. Множественное число, именительный падеж, описывающий существ (пример: лоси):";
    String NOUN2_TXT = "Введите второе существительное фразы. Множественное число, именительный падеж, описывающий качества существ (пример: авантюристы):";
    String VERB_TXT = "Введите глагол. Обязательно отвечающий на вопрос «что делают?» (пример: пьют):";
    String ADJ_TXT = "Введите прилагательное фразы. Обязательно отвечающее на вопрос «какую?» (пример: желтую):";
    String NOUN3_TXT = "Введите третье существительное фразы. Единственное число, женский род, винительный падеж, описывающий предмет (пример: воду):";

    // Сообщения-описания специфических ошибок ввода
    String INPUT_FAILURE_DUPLICATE = "Это слово уже есть в бездне.";
    String INPUT_FAILURE_TOO_LONG = "Слишком длинный ввод. Введите одно слово.";
    String INPUT_FAILURE_WHITE_SPACE = "Ввод разбит пробелами. Введите одно слово.";
    String INPUT_FAILURE_TYPO = "Орфографическая ошибка.";
    String INPUT_FAILURE_WRONG_ENDING = "Неверная форма слова.";
}
