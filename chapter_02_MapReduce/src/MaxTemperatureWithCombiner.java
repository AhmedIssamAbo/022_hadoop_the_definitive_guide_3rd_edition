import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

/**
 * Filename: MaxTemperatureWithCombiner.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     10:54
 * Version:  v1.0.0
 */
public class MaxTemperatureWithCombiner {
    public static void main(String[] args) throws Exception {
        if (2 != args.length) {
            System.err.println("Usage: MaxTemperatureWithCombiner <input path> " + "<output path>");

            System.exit(-1);
        }
    }

    Job job = new Job();
    job.setJarByClass(MaxTemperatureWithCombiner.class);
    job.setJobName("Max Temperature");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapperClass(MaxTemperature.class);
    job.setCombinerClass(MaxTemperature.class);
    job.setReducerClass(MaxTemperature.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
}
