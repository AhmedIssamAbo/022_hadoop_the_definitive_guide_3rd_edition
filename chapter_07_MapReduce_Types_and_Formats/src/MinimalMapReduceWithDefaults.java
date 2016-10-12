import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.ToolRunner;

import javax.tools.Tool;
import java.io.IOException;

/**
 * Filename: MinimalMapReduceWithDefaults.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     10:13
 * Version:  v1.0.0
 */
public class MinimalMapReduceWithDefaults extends Configured implements Tool {
    @Override
    public int run(String[] args) throws IOException {
        Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);

        if (null == job) {
            return -1;
        }

        job.setInputFormat(TextInputFormat.class);

        job.setMapperClass(Mapper.class);

        job.setMapOutputKeyClass(LongWritable,class);
        job.setMapOutputValueClass(Text.class);

        job.setPartitionerClass(HashPartitioner.class);

        job.setNumReduceTasks(1);
        job.setReducerClass(Reducer.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner(new MinimalMapReduceWithDefaults(), args);

        System.exit(exitCode);
    }
}
