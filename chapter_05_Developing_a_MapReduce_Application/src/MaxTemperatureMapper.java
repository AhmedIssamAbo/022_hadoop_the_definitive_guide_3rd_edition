import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Filename: MaxTemperatureMapper.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     7:47
 * Version:  v1.0.0
 */
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.substring(15, 19);
        int airTemperature = Integer.parseInt(line.substring(87, 92));

        context.write(new Text(year), new IntWritable(airTemperature));
    }
}
