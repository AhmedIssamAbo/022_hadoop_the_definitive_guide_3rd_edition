import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.util.ToolRunner;
import org.quartz.JobBuilder;

import javax.naming.Context;
import javax.tools.Tool;
import java.io.IOException;

/**
 * Filename: MaxTemperatureWithCounters.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     14:39
 * Version:  v1.0.0
 */
public class MaxTemperatureWithCounters extends Configured implements Tool {
    enum Temperature {
        MISSING,
        MALFORMED
    }

    static class MaxTemperatureMapperWithCounters extends Mapper<LongWritable, Text, Text, IntWritable> {
        private NcdcRecordParser parser = new NcdcRecordParser();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parser(value);
            if (parser.isValidTemperature()) {
                int airTemperatue = parser.getAirTemperature();
                context.write(new Text(parser.getYear()), new IntWritable(airTemperatue));
            } else if (parser.isMalformedTemperature()) {
                system.err.println("Ignoring possibly corrupt input: " + value);
                context.getCounter(Temperature.MALFORMED).increment(1);
            } else if (parser.isMissingTemperature()) {
                context.getCounter(Temperature.MISSING).increment(1);
            }

            // dynamic counter
            context.getCounter("TemperatureQuality", parser.getQuality()).increment(1);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);
        if (null == job) {
            return -1;
        }

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(MaxTemperatureMapperWithCounters.class);
        job.setCombinerClass(MaxTemperatureReducer.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MaxTemperatureMapperWithCounters(), args);

        System.exit(exitCode);
    }
}
