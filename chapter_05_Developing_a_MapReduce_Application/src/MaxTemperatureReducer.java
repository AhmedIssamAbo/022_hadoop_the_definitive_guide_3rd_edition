import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * Filename: MaxTemperatureReducer.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     7:49
 * Version:  v1.0.0
 */
public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int maxValue = Integer.MIN_VALUE;

    for (IntWritable value : values) {
        maxValue = Math.max(maxValue, value.get());
    }

    context.write(key, new IntWritable(maxValue));
}
