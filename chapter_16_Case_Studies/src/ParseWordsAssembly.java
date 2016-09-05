/**
 * Filename: ParseWordsAssembly.jsp
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-02
 * Time:     22:44
 * Version:  v1.0.0
 */

import java.nio.channels.Pipe;

public class ParseWordsAssembly extends SubAssembly {
    public ParseWordsAssembly(Pipe previous)
    {
        String regexString = "(?<!\\pL)(?=\\pL)[^ ]*(?<=\\pL)(?!\\pL)";
        Function regex = new RegexGenerator(new Fields("word"), regexString);
        previous = new Each(previous, new Fields("line"), regex);

        String exprString = "word.toLowerCase()";
        Function expression = new ExpressionFunction(new Fields("word"), exprString, String.class);

        previous = new Each(previous, new Fields("word")), expression);
        setTails(previous);
    }
}
