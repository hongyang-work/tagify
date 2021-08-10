import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static Map<Integer, Map<String, String>> testCases;

    public static void main(String[] args) {
        testCases.forEach((key, value) -> {
            String i = value.get("input");
            Tagify tagify = new Tagify(i);
            String o = tagify.getProcessedText();
            String e = value.get("expected");
            String s = o.equals(e) ? "PASS" : "FAIL";

            if (s.equals("FAIL")) {
                System.out.println("Test case " + key);
                System.out.println("input:\t\t" + i);
                System.out.println("output:\t\t" + o);
                System.out.println("expected:\t" + e);
                System.out.println("status\t\t" + s);
                System.out.println();
            }
        });
    }

    static {
        testCases = new TreeMap<>();

        testCases.put(0, new HashMap<String, String>() {{
            put("input", "");
            put("expected", "");
        }});

        testCases.put(1, new HashMap<String, String>() {{
            put("input", null);
            put("expected", "");
        }});

        testCases.put(2, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"value\", \"prefix\": \"@\"}]]");
            put("expected", "@value");
        }});

        testCases.put(3, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"value\", \"prefix\": \"@\"}]] [[{\"value\": \"value\", \"prefix\": \"@\"}]]");
            put("expected", "@value @value");
        }});

        testCases.put(4, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"value\", \"prefix\": \"@\"}]][[{\"value\": \"value\", \"prefix\": \"@\"}]]");
            put("expected", "@value@value");
        }});

        testCases.put(5, new HashMap<String, String>() {{
            put("input", "hello[[{\"value\": \"value\", \"prefix\": \"@\"}]]hello");
            put("expected", "hello@valuehello");
        }});

        testCases.put(6, new HashMap<String, String>() {{
            put("input", "hello [[{\"value\": \"test\", \"prefix\": \"@\"}]] hello [[{\"value\": \"value\", \"prefix\": \"@\"}]]");
            put("expected", "hello @test hello @value");
        }});

        testCases.put(7, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"\\\"}]]\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]");
            put("expected", "@\"}]] [[{\"aaa\"}]]");
        }});

        testCases.put(8, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]");
            put("expected", "@[[{\" [[{\"aaa\"}]]");
        }});

        testCases.put(9, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]\"}]]");
            put("expected", "@[[{\" [[{\"aaa\"}]]\"}]]");
        }});

        testCases.put(10, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"[[{\"aaa\"}]]");
            put("expected", "@[[{\" [[{\"[[{\"aaa\"}]]");
        }});

        testCases.put(11, new HashMap<String, String>() {{
            put("input", "[[{\"value\": \"[[{\\\"value\\\": \\\"value\\\", \\\"prefix\\\": \\\"@\\\"}]]\", \"prefix\": \"@\"}]]");
            put("expected", "@[[{\"value\": \"value\", \"prefix\": \"@\"}]]");
        }});

        testCases.put(12, new HashMap<String, String>() {{
            put("input", "[[[[{\"value\": \"value\", \"prefix\": \"@\"}]]]]");
            put("expected", "[[@value]]");
        }});

        testCases.put(13, new HashMap<String, String>() {{
            put("input", "[[{\" [[{\"value\":\"value\",\"prefix\":\"@\"}]] \"}]]");
            put("expected", "[[{\" @value \"}]]");
        }});

        testCases.put(14, new HashMap<String, String>() {{
            put("input", "[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]]");
            put("expected", "[[{\" @\"value\" \"}]]");
        }});

        testCases.put(15, new HashMap<String, String>() {{
            put("input", "[[{\"value\":\"value\",\"prefix\":\"@\"}]] \"}]]");
            put("expected", "@value \"}]]");
        }});

        testCases.put(16, new HashMap<String, String>() {{
            put("input", "[[{\" [[{\"value\":\"value\",\"prefix\":\"@\"}]]");
            put("expected", "[[{\" @value");
        }});

        testCases.put(17, new HashMap<String, String>() {{
            put("input", "[[{\"[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]]");
            put("expected", "[[{\"[[{\" @\"value\" \"}]]");
        }});

        testCases.put(18, new HashMap<String, String>() {{
            put("input", "[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]] \"}]]");
            put("expected", "[[{\" @\"value\" \"}]] \"}]]");
        }});

        testCases.put(19, new HashMap<String, String>() {{
            put("input", "[[{\" [[{\"value\": \"[[{\\\"value\\\": \\\"value\\\", \\\"prefix\\\": \\\"@\\\"}]]\", \"prefix\":\"@\"}]] \"}]]");
            put("expected", "[[{\" @[[{\"value\": \"value\", \"prefix\": \"@\"}]] \"}]]");
        }});

        testCases.put(20, new HashMap<String, String>() {{
            put("input", " [[{\" [[{\"value\": \"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]] \"}]]");
            put("expected", " [[{\" @value\"}]]\"}]] \"}]]");
        }});

        testCases.put(21, new HashMap<String, String>() {{
            put("input", " [[{\" [[{\"value\": \"[[{\\\"[[{\\\"value\", \"prefix\": \"@\"}]] \"}]]");
            put("expected", " [[{\" @[[{\"[[{\"value \"}]]");
        }});

        testCases.put(22, new HashMap<String, String>() {{
            put("input", " [[{\"value\": \"[[{\\\"[[{\\\"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]] \"}]]");
            put("expected", " @[[{\"[[{\"value\"}]]\"}]] \"}]]");
        }});

        testCases.put(23, new HashMap<String, String>() {{
            put("input", " [[{\" [[{\"value\": \"[[{\\\"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]]");
            put("expected", " [[{\" @[[{\"value\"}]]\"}]]");
        }});

        testCases.put(24, new HashMap<String, String>() {{
            put("input", " [[{\"value\": \"[[{\\\"[[{\\\"value\\\"}]]\", \"prefix\": \"@\"}]] \"}]]");
            put("expected", " @[[{\"[[{\"value\"}]] \"}]]");
        }});

        testCases.put(25, new HashMap<String, String>() {{
            put("input", "the [[{\"value\": \"quick\", \"prefix\": \"@\"}]] brown fox [[{\"value\": \"jumps\", \"prefix\": \"@\"}]] over the [[{\"value\": \"lazy\", \"prefix\": \"@\"}]] dog");
            put("expected", "the @quick brown fox @jumps over the @lazy dog");
        }});

        testCases.put(26, new HashMap<String, String>() {{
            put("input", "there are no tags in this string");
            put("expected", "there are no tags in this string");
        }});
    }
}
