import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Stack;

public class TagifyParser {

    private static final TagifyParser INSTANCE = new TagifyParser();

    private TagifyParser() {}

    public static TagifyParser getInstance() {
        return INSTANCE;
    }

    public String parse(String s) {
        if (s == null) return "";
        int[] dp = locateTags(s);
        return parse(s, dp);
    }

    // iterate from the end of the array and jump backwards
    public String parse(String s, int[] dp) {
        StringBuilder message = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            String subMessage = dp[i] > 0 ? s.substring(i - dp[i], i + 1) : "";
            String tag = !subMessage.isEmpty() ? toTag(subMessage) : String.valueOf(s.charAt(i));
            message.insert(0, tag);
            i -= dp[i] > 0 ? dp[i] + 1 : 1;
        }
        return message.toString();
    }

    // convert raw tagify tag to presentable tag
    public String toTag(String s) {
        try {
            Gson gson = new Gson();
            Tag[][] tags = gson.fromJson(s, Tag[][].class);
            Tag tag = tags[0][0];
            return tag.getPrefix() + tag.getValue();

        } catch (JsonSyntaxException e) {
            // Note: this block can be parallelized

            if (s.length() < 9) return s;

            String json, parsed;
            String open = s.substring(0, 4);
            String close = s.substring(s.length() - 4);

            // remove close
            json = s.substring(0, s.length() - 4);
            parsed = parse(json);
            String right = parsed + close;
            if (!parsed.equals(json)) return right;

            // remove both open and close
            json = s.substring(4, s.length() - 4);
            parsed = parse(json);
            String both = open + parsed + close;
            if (!parsed.equals(json)) return both;

            return s;
        }
    }

    // dynamic programming and stack approach
    // memoize at each index to keep track of the length the tag
    // stack to keep track of nested opening and closing
    public int[] locateTags(String s) {
        int[] dp = new int[s.length()];
        Stack<Integer> stack = new Stack<>();

        int lastOpen = 0;
        for (int i = 0; i < s.length(); i++) {

            char curr = s.charAt(i);
            char prev4 = i > 3 ? s.charAt(i - 4) : '\0';
            char prev3 = i > 2 ? s.charAt(i - 3) : '\0';
            char prev2 = i > 1 ? s.charAt(i - 2) : '\0';
            char prev1 = i > 0 ? s.charAt(i - 1) : '\0';
            char next1 = i < s.length() - 1 ? s.charAt(i + 1) : '\0';
            char next2 = i < s.length() - 2 ? s.charAt(i + 2) : '\0';
            char next3 = i < s.length() - 3 ? s.charAt(i + 3) : '\0';

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
