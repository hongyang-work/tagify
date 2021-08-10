import java.util.List;

public class TagifyString {
    private List<Tag> tags;
    private String rawText;
    private String processedText;

    public TagifyString(String rawText) {
        this.rawText = rawText;
    }

    public String getRawText() {
        return rawText;
    }
}
