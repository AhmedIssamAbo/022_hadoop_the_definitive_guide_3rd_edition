import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Filename: JoinRecordMapper.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     21:56
 * Version:  v1.0.0
 */
public class JoinRecordMapper extends Mapper<LongWritable, Text, TextPair, Text> {
    private NcdcRecordParser parser = new NcdcRecordParser();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value);

        context.write(new TextPair(parser.getStationId(), "1"), value);
    }
}
