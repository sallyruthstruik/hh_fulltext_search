package core.text_processing;

import java.util.List;
import java.util.Set;

/**
 * Created by Станислав on 08.02.2015.
 */
public interface TextProcessorInterface {
    Set<String> processText(String text);

    List<String> splitText(String text);

    String stem(String word);
}
