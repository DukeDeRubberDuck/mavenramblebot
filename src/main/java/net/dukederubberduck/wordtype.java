package net.dukederubberduck;

enum wordtype implements Textconstants
{
    noun1 (NOUN1_TXT, NOUN1_ALLOWED_ENDINGS),
    noun2 (NOUN2_TXT, NOUN2_ALLOWED_ENDINGS),
    verb (VERB_TXT, VERB_ALLOWED_ENDINGS),
    adj (ADJ_TXT, ADJ_ALLOWED_ENDINGS),
    noun3 (NOUN3_TXT, NOUN3_ALLOWED_ENDINGS);

    private String descriptionText;
    private String [] allowedEndings;

    wordtype (String descriptionText, String [] allowedEndings)
    {
        this.descriptionText = descriptionText;
        this.allowedEndings = allowedEndings;
    }

    public String[] getAllowedEndings()
    {
        return allowedEndings;
    }

    public int getOffset()
    {
        return allowedEndings[0].length();
    }

    public String getDescriptionText()
    {
        return descriptionText;
    }
}