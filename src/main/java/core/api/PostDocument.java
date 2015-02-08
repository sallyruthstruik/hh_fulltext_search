package core.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Станислав on 08.02.2015.
 */
@XmlRootElement
class PostDocument {
    public String text;

    public PostDocument() {
    }

    public PostDocument(String text) {
        this.text = text;
    }
}


