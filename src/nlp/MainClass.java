/**main class is for testing purpos only.
 * Once this module to produce event class from sentence be ready to deploy
 * we will use it with the android app to make our E-Knock.
 */
package nlp;

import java.util.Scanner;

public class MainClass {
    private static final String MESSAGE_TAGGER_MODEL_PATH = "models\\mainMessageTaggerModel.zip";
    private static final String TOKENIZER_MODEL_PATH = "models\\en-token.bin";

    public static void main(String[] args) {

        System.out.println("Enter Sentence for tokenize : ");
        Scanner s = new Scanner(System.in);
        String sentence = s.nextLine();
        s.close();

        PosTagger tagger = new PosTagger(MESSAGE_TAGGER_MODEL_PATH, TOKENIZER_MODEL_PATH);
        TokenizerUtils tokenizer = new TokenizerUtils("models\\en-token.bin");

        String[] tokens = tokenizer.tokenizeStringUsingLearnableTokenizer(sentence);
        String[] tags = tagger.tagifyString(tokens);

        System.out.print("tokens_tags : ");
        for (int i = 0; i < tokens.length; i++)
            System.out.print(" " + tokens[i] + "_" + tags[i]);

        String eventString;
        eventString = new SentenceToEventExtractor(MESSAGE_TAGGER_MODEL_PATH, TOKENIZER_MODEL_PATH).extractEventFromString(sentence).toString();
        System.out.println("\n" + eventString);// + "Time : " + TimeParser.parseTime(eventString).toString());
    }

}