import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Filename: MaxTemperatureMapperV5.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     8:09
 * Version:  v1.0.0
 */
public class MaxTemperatureMapperV5 extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    enum Temperature {
        MALFORMED
    }

    private NcdcRecordParser parser = new NcdcRecordParser();

    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        parser.parse(value);

        if (parser.isValidTemperature()) {
            int airTemperature = parser.getAirTemperature();
            context.write(new Text(parser.getYear()), new IntWritable(airTemperature));
        } else if (parser.isMalFormedTemperature()) {
            System.err.println("Ignoring possibly corrupt input: " + value);
            context.getCounter(Temperature.MALFORMED, 1);
        }
    }
}
