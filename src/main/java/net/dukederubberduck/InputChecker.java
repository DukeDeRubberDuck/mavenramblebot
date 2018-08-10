package net.dukederubberduck;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Russian;
import org.languagetool.rules.RuleMatch;
import java.io.IOException;
import java.util.List;

class InputChecker implements Textconstants
{
dbManager dbm = new dbManager();

    public String CheckAll (String input, wordtype wt)
    {
        if (!dbm.dbCheckForDuplicate(input, wt)) return INPUT_FAILURE_DUPLICATE;
        else if (!InputLengthCheck(input)) return INPUT_FAILURE_TOO_LONG;
        else if (!WhiteSpaceCheck(input)) return INPUT_FAILURE_WHITE_SPACE;
        else if (!SimpleSpellCheck(input)) return INPUT_FAILURE_TYPO;
        else if (!CheckEnding(input, wt)) return INPUT_FAILURE_WRONG_ENDING;
        else
        {
            dbm.dbInput(input, wt);
            return "«" + input.substring(0, 1).toUpperCase() + input.substring(1) + "» добавлено в бездну. Спасибо!";
        }
    }

    private boolean InputLengthCheck (String input)
    {
        return input.length() <= 25;
    }

    private boolean WhiteSpaceCheck (String input)
    {
        return input.indexOf(" ") == -1;
    }

    private boolean SimpleSpellCheck (String input)
    {
        boolean answer = true;
        try
        {
            JLanguageTool lt = new JLanguageTool(new Russian());
            List<RuleMatch> matches = lt.check(input);

            for (RuleMatch rm : matches)
            {
                if (rm.getShortMessage().equals("Орфографическая ошибка")
                    || rm.getShortMessage().equals("Возможно нужна буква из кириллицы вместо аналогичной по начертанию латинской или наоборот."))
                {
                    answer = false;
                }
            }

        } catch (IOException e) {e.printStackTrace();}

        return answer;
    }

    private boolean CheckEnding (String input, wordtype wt)
    {
        boolean answer = false;
        for (String allowed : wt.getAllowedEndings())
        {
            if (allowed.equals(input.substring(input.length() - wt.getOffset()).toLowerCase())) answer = true;
        }
        return answer;
    }
}
