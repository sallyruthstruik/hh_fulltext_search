package core.text_processing;

import java.util.List;

/**
 * Created by Станислав on 08.02.2015.
 */
public interface TokenizerInterface {
    List<String> tokenize(String text);
}
