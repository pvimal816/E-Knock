/*
*This class accept paths to the model files of tokenizer and tagger and also provides method to extract a information needed to set the reminder
* from the sentence.
*
* */

package nlp;

public class SentenceToEventExtractor {
    String messageTaggerModelPath;
    String tokenizerModelPath;
    PosTagger posTagger;
    TokenizerUtils tokenizerUtils;

    public SentenceToEventExtractor(String messageTaggerModelPath, String tokenizerModelPath) {
        this.messageTaggerModelPath = messageTaggerModelPath;
        this.tokenizerModelPath = tokenizerModelPath;
        posTagger = new PosTagger(messageTaggerModelPath, tokenizerModelPath);
        tokenizerUtils = new TokenizerUtils(tokenizerModelPath);
    }

    public Event extractEventFromString(String sentence) {
        String tokens[] = tokenizerUtils.tokenizeStringUsingLearnableTokenizer(sentence);
        String tags[] = posTagger.tagifyString(tokens);
        return EventExtractor.exractEvent(tokens, tags);
    }
}
