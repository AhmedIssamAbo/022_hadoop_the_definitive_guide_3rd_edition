import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Filename: MaxTemperatueMapperTest.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     7:43
 * Version:  v1.0.0
 */
public class MaxTemperatueMapperTest {

    @Test
    public void processValidRecord() throws IOException, InterruptedException {
        Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" +
                                    // Year ^^^^
        "99999V0203201N00261220001CN9999999N9-00111+99999999999");
                                    // Temperature ^^^^

        new MapDriver<LongWritable, Text, Text, IntWritable>()
            .withMapper(new MaxTemperatueMapperTest())
            .withInputValue(value)
            .withOutput(new Text("1950"), new IntWritable(-11))
            .runTest();
    }
}

