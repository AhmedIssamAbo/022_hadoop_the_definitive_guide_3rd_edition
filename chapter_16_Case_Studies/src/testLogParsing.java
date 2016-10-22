/**
 * Filename: testLogParsing.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-06
 * Time:     21:41
 * Version:  v1.0.0
 */
public class testLogParsing throws IOException{
        Hfs source = new Hfs(new TextLine(new Fields("line")), sampleData);

        Hfs Sink = new Hfs(new TextLine(), outputPath + "/parser", SinkMode.REPLACE);

        Pipe pipe = new Pipe("parser");

        // split "line" on tabs
        pipe = new Each(pipe, new Fields("line"), new RegexSplitter("\t"));
        pipe = new LogParser(pipe);
        pipe = new LogRules(pipe);

        // testing only assertions
        pipe = new ParserAssertions(pipe);
        Flow flow = new FlowConnector().connect(souce, sink, pipe);
        flow.complete();    // run the test flow

        // verify there are 98 tuples, 2 fields, and matches the regex pattern
        // for TextLine schemes the tuples are { "offset", "line" }
        validate Length（flow, 98, 2, Pattern.complite("^[0-9]+(\\t[^\\t]*){19}$"));
}
