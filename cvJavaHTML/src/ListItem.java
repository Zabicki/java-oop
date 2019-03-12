//class represents each element of the unordered list containted in ParagraphWithList
public class ListItem {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    ListItem(String content) {
        this.content = content;
    }
}
