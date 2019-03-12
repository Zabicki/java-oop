import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();

    ParagraphWithList() {

    }

    void writeHtml(PrintStream out) {
        out.append("<p>");
        list.writeHtml(out);
        out.append("</p>\n");
    }

    ParagraphWithList setContent(String content) {
        this.content = content;
        return this;
    }

    ParagraphWithList addListItem(String content) {
        list.addListItem(content);
        return this;
    }
}
