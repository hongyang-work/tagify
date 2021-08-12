import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Tagify {

    private final String rawText;
    private final String parsedText;
    private final List<Tag> tags;

    public Tagify(String rawText) {
        this.tags = new ArrayList<>();
        this.rawText = rawText;
        this.parsedText = parse(rawText);
    }

    public String getRawText() {
        return rawText;
    }

    public String getParsedText() {
        return parsedText;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Range> getRanges() {
        return tags.stream().map(Tag::getRange).collect(Collectors.toList());
    }

    private String parse(String s) {
        if (s == null) return "";

        int[] dp = locateTags();
        StringBuilder message = new StringBuilder();

        int i = s.length() - 1;
        while (i >= 0) {
            String subMessage = dp[i] > 0 ? s.substring(i - dp[i], i + 1) : "";
            Tag tag = toTag(subMessage, message.length());
            if (tag != null) this.tags.add(tag);
            message.append( tag != null ? tag.reverse() : s.charAt(i) );
            i -= tag != null ? dp[i] + 1 : 1;
        }

        reverseTagIndex(message.length());
        return message.reverse().toString();
    }

    private void reverseTagIndex(int length) {
        for (Tag tag : tags) {
            Range range = tag.getRange();
            int start = length - range.getEnd();
            int end = length - range.getStart();
            range.setStart(start);
            range.setEnd(end);
        }
        Collections.reverse(tags);
    }

    private boolean isValidTag(String s) {
        try {
            return toTag(s) != null;
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }

    private Tag toTag(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, Tag[][].class)[0][0];
    }

    private Tag toTag(String s, int start) {
        if (!isValidTag(s)) return null;
        Tag tag = toTag(s);
        Range range = new Range(start, start + tag.length());
        tag.setRange(range);
        return tag;
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
