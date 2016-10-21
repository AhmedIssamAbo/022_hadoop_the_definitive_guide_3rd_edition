import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayFile;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.MapFileOutputFormat;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.lib.HashPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.quartz.JobBuilder;

/**
 * Filename: LookupRecordByTemperature.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     20:49
 * Version:  v1.0.0
 */
public class LookupRecordByTemperature extends Configured implements Tool {

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
        Writable entry = MapFileOutputFormat.getEntry(readers, partitioner, key, val);
        if (null == entry) {
            System.err.println("Key not found: " + key);
        }

        NcdcRecordParser parser = new NcdcRecordParser();
        parser.parse(val.toString());
        System.out.printf("%s\t%s\n", parser.getStationId(), parser.getYear());

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new SortDataPreprocessor(), args);
        System.exit(exitCode);
    }
}
