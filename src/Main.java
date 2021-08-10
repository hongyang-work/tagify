public class Main {
    public static void main(String[] args) {
        TagifyParser tagifyParser = TagifyParser.getInstance();
        String input;
        String output;
        String expected;

        System.out.println("Test case 0");
        input = "";
        output = tagifyParser.parse(input);
        expected = "";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 1");
        input = null;
        output = tagifyParser.parse(input);
        expected = "";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 2");
        input = "[[{\"value\": \"value\", \"prefix\": \"@\"}]]";
        System.out.println(new TagifyString(input).getProcessedText());
        output = tagifyParser.parse(input);
        expected = "@value";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 3");
        input = "[[{\"value\": \"value\", \"prefix\": \"@\"}]] [[{\"value\": \"value\", \"prefix\": \"@\"}]]";
        output = tagifyParser.parse(input);
        expected = "@value @value";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 4");
        input = "[[{\"value\": \"value\", \"prefix\": \"@\"}]][[{\"value\": \"value\", \"prefix\": \"@\"}]]";
        output = tagifyParser.parse(input);
        expected = "@value@value";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 5");
        input = "hello[[{\"value\": \"value\", \"prefix\": \"@\"}]]hello";
        output = tagifyParser.parse(input);
        expected = "hello@valuehello";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 6");
        input = "hello [[{\"value\": \"test\", \"prefix\": \"@\"}]] hello [[{\"value\": \"value\", \"prefix\": \"@\"}]]";
        output = tagifyParser.parse(input);
        expected = "hello @test hello @value";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 7");
        input = "[[{\"value\": \"\\\"}]]\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]";
        output = tagifyParser.parse(input);
        expected = "@\"}]] [[{\"aaa\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 8");
        input = "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]";
        output = tagifyParser.parse(input);
        expected = "@[[{\" [[{\"aaa\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 9");
        input = "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"aaa\"}]]\"}]]";
        output = tagifyParser.parse(input);
        expected = "@[[{\" [[{\"aaa\"}]]\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 10");
        input = "[[{\"value\": \"[[{\\\"\", \"prefix\": \"@\"}]] [[{\"[[{\"aaa\"}]]";
        output = tagifyParser.parse(input);
        expected = "@[[{\" [[{\"[[{\"aaa\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 11");
        input = "[[{\"value\": \"[[{\\\"value\\\": \\\"value\\\", \\\"prefix\\\": \\\"@\\\"}]]\", \"prefix\": \"@\"}]]";
        output = tagifyParser.parse(input);
        expected = "@[[{\"value\": \"value\", \"prefix\": \"@\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 12");
        input = "[[[[{\"value\": \"value\", \"prefix\": \"@\"}]]]]";
        output = tagifyParser.parse(input);
        expected = "[[@value]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 13");
        input = "[[{\" [[{\"value\":\"value\",\"prefix\":\"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\" @value \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 14");
        input = "[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\" @\"value\" \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 15");
        input = "[[{\"value\":\"value\",\"prefix\":\"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "@value \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 16");
        input = "[[{\" [[{\"value\":\"value\",\"prefix\":\"@\"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\" @value";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 17");
        input = "[[{\"[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\"[[{\" @\"value\" \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 18");
        input = "[[{\" [[{\"value\":\"\\\"value\\\"\",\"prefix\":\"@\"}]] \"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\" @\"value\" \"}]] \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 19");
        input = "[[{\" [[{\"value\": \"[[{\\\"value\\\": \\\"value\\\", \\\"prefix\\\": \\\"@\\\"}]]\", \"prefix\":\"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = "[[{\" @[[{\"value\": \"value\", \"prefix\": \"@\"}]] \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 20");
        input = " [[{\" [[{\"value\": \"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = " [[{\" @value\"}]]\"}]] \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 21");
        input = " [[{\" [[{\"value\": \"[[{\\\"[[{\\\"value\", \"prefix\": \"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = " [[{\" @[[{\"[[{\"value \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 22");
        input = " [[{\"value\": \"[[{\\\"[[{\\\"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = " @[[{\"[[{\"value\"}]]\"}]] \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 23");
        input = " [[{\" [[{\"value\": \"[[{\\\"value\\\"}]]\\\"}]]\", \"prefix\": \"@\"}]]";
        output = tagifyParser.parse(input);
        expected = " [[{\" @[[{\"value\"}]]\"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("Test case 24");
        input = " [[{\"value\": \"[[{\\\"[[{\\\"value\\\"}]]\", \"prefix\": \"@\"}]] \"}]]";
        output = tagifyParser.parse(input);
        expected = " @[[{\"[[{\"value\"}]] \"}]]";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();


        System.out.println("Test case 25");
        input = "the [[{\"value\": \"quick\", \"prefix\": \"@\"}]] brown fox [[{\"value\": \"jumps\", \"prefix\": \"@\"}]] over the [[{\"value\": \"lazy\", \"prefix\": \"@\"}]] dog";
        output = tagifyParser.parse(input);
        expected = "the @quick brown fox @jumps over the @lazy dog";
        System.out.println("input:\t\t" + input);
        System.out.println("output:\t\t" + output);
        System.out.println("expected:\t" + expected);
        System.out.println("status\t\t" + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }
}
