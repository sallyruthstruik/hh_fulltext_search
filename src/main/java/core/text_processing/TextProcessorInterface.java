package core.text_processing;

import java.util.Set;

/**
 * Created by Станислав on 08.02.2015.
 */
public interface TextProcessorInterface {
    Set<String> processText(String text);

    String stem(String word);
}
