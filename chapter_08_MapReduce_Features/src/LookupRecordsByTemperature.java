import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.MapFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.quartz.JobBuilder;

/**
 * Filename: LookupRecordsByTemperature.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     21:13
 * Version:  v1.0.0
 */
public class LookupRecordsByTemperature extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if (2 != args.length) {
            JobBuilder.printUsage(this, "<path> <key>");

            return -1;
        }

        Path path = new Path(args[0]);
        IntWritable key = new IntWritable(Integer.parseInt(args[1]));

        ArrayFile.Reader[] readers = MapFileOutputFormat.getReaders(path, getConf());
        Partitioner<IntWritable, Text> partitioner = new HashPartitioner<IntWritable, Text>();
        Text val = new Text();

        SequenceFile.Reader reader = readers[partitioner.getPartition(key, val, readers.length)];
        Writable entry = reader.get(key, val);
        if (null == entry) {
            System.err.println("Key not found: " + key);
        }

        NcdcRecordParser parser = new NcdcRecordParser();
        IntWritable nextKey = new IntWritable();
        do {
            parser.parse(val.toString());
            System.out.printf("%s\t%s\n", parser.getStationId(), parser.getYear());
        } while (reader.next(nextKey, val) && key.equals(nextKey));

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new SortDataPreprocessor(), args);
        System.exit(exitCode);
    }
}
