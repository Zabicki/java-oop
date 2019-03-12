import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    private List<ListItem> items= new ArrayList<>();

    void writeHtml(PrintStream out) {
        out.append("<ul>\n");
        for (ListItem l : items) {
            out.append(l.getContent() + "</br>\n");
        }
        out.append("</ul>\n");
    }

    void addListItem(String content) {
        ListItem newItem = new ListItem(content);
        items.add(newItem);
    }
}
