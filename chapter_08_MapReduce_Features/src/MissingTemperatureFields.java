import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.quartz.JobBuilder;

/**
 * Filename: MissingTemperatureFields.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     19:48
 * Version:  v1.0.0
 */
public class MissingTemperatureFields extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 1) {
            JobBuilder.printUsage(this, "<job ID>");

            return -1;
        }

        String jobID = args[0];
        JobClient jobClient  new JobClient(new JobConf(getConf()));
        RunningJob job = jobClient.getJob(JobID.forName(jobID));
        if (null == job) {
            System.err.printf("No job with ID %s found.\n", jobID);

            return -1;
        }
        if (!job.isComplete()) {
            System.err.printf("Job %s is not complete.\n", jobID);

            return -1;
        }

        Counters counters = job.getCounters();
        long missing = counters.getCounter(MaxTemperatureWithCounters.MaxTemperatureMapperWithCounters.Temperature.MISSING);

        long total = counters.getCounter(Task.Counter.MAP_INPUT_RECORDS);

        System.out.printf("Records with missing temperature fields: %.2f%%\n", 100.0 * missing / total);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MissingTemperatureFields(), args);

        System.exit(exitCode);
    }
}
