import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tagify {

    private final String rawText;
    private final String processedText;
    private final List<Tag> tags;


    public Tagify(String rawText) {
        this.tags = new ArrayList<>();
        this.rawText = rawText;
        this.processedText = parse(rawText);
    }

    public String getRawText() {
        return rawText;
    }

    public String getProcessedText() {
        return processedText;
    }

    public List<Tag> getTags() {
        return tags;
    }

    private String parse(String s) {
        if (s == null) return "";

        int[] dp = locateTags();
        StringBuilder message = new StringBuilder();

        int i = s.length() - 1;
        while (i >= 0) {
            int start = i - dp[i];
            int end = i + 1;
            String subMessage = dp[i] > 0 ? s.substring(start, end) : "";

            Tag tag = toTag(subMessage);
            if (tag == null) {
                message.insert(0, s.charAt(i));
                i -= 1;
            } else {
                tag.setRange(new Range(start, end));
                tags.add(tag);
                message.insert(0, tag);
                i -= dp[i] + 1;
            }
        }
        return message.toString();
    }

    private Tag toTag(String s) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(s, Tag[][].class)[0][0];
        } catch (JsonSyntaxException | NullPointerException e) {
           return null;
        }
    }

    // dynamic programming and stack approach
    // memoize at each index to keep track of the length the tag
    // stack to keep track of nested opening and closing
    public int[] locateTags() {
        if (rawText == null) return new int[0];

        int[] dp = new int[rawText.length()];
        Stack<Integer> stack = new Stack<>();

        int lastOpen = 0;
        for (int i = 0; i < rawText.length(); i++) {

            char curr = rawText.charAt(i);
            char prev4 = i > 3 ? rawText.charAt(i - 4) : '\0';
            char prev3 = i > 2 ? rawText.charAt(i - 3) : '\0';
            char prev2 = i > 1 ? rawText.charAt(i - 2) : '\0';
            char prev1 = i > 0 ? rawText.charAt(i - 1) : '\0';
            char next1 = i < rawText.length() - 1 ? rawText.charAt(i + 1) : '\0';
            char next2 = i < rawText.length() - 2 ? rawText.charAt(i + 2) : '\0';
            char next3 = i < rawText.length() - 3 ? rawText.charAt(i + 3) : '\0';

            if (curr == '[' && next1 == '[' && next2 == '{' && next3 == '\"')
                stack.push(i);

            if (prev4 != '\\' && prev3 == '\"' && prev2 == '}' && prev1 == ']' && curr == ']') {
                int currOpen = !stack.isEmpty() ? stack.pop() : lastOpen;
                dp[i] = i - currOpen;
                lastOpen = currOpen;
            }
        }
        return dp;
    }
}
