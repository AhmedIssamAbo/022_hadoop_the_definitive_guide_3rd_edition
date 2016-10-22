/**
 * Filename: SubAssembly.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-06
 * Time:     21:50
 * Version:  v1.0.0
 */
public class SubAssembly {
}

Scheme sourceScheme = new TextLine(new Fields("line"));
Tap source = new Hfs(sourceScheme, inputPath);

Scheme sinkScheme = new TextLine(new Fields("word", "count"));
Tap sink = new Hfs(sinkScheme, outputPath, SinkMode.REPLACE);

Pipe assembly = new Pipe("wordcount");

assembly = new GroupBy(assembly, new Fields("word"));

Aggregator count = new Count(new Fields("count")):
assembly = new Every(assembly, count);

assembly = new GroupBy(assebmly, new Fields("count"), new Fields("word"));
FlowConnector flowConnector = new FlowConnector();
Flow flow = flowConnector.connect("word-count", source, sink, assembly);

flow.complete();
