import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

public class Tagify {

    private final TagifyParser tagifyParser = TagifyParser.getInstance();
    private int[] locations;

    private final Map<Tag, Range> tags;

    private final String rawText;
    private String processedText;


    public Tagify(String rawText) {
        this.rawText = rawText;
        this.tags = new HashMap<>();
        process();
    }

    public String getRawText() {
        return rawText;
    }

    public String getProcessedText() {
        return processedText;
    }

    public Map<Tag, Range> getTags() {
        return tags;
    }

    private void process() {
        this.locations = tagifyParser.locateTags(this.rawText);
        processTags();
        processText();
    }

    private void processTags() {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] == 0) continue;

            int start = i - locations[i];
            int end = i + 1;
            Range range = new Range(start, end);

            String tagifyText = this.rawText.substring(start, end);
            Tag tag = toTag(tagifyText);

            tags.put(tag, range);
        }
    }

    private void processText() {
        if (locations == null) this.processedText = tagifyParser.parse(this.rawText);
        this.processedText = tagifyParser.parse(this.rawText, locations);
    }

    private Tag toTag(String s) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(s, Tag[][].class)[0][0];

        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
