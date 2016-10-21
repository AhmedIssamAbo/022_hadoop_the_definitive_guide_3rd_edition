import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * Filename: JoinStationMapper.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     21:39
 * Version:  v1.0.0
 */
public class JoinStationMapper extends Mapper<LongWritable, Text, TextPair, Text> {

    private NcdcStationMetadataParser parser = new NcdcStationMetadataParser();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (parser.parse(value)) {
            context.write(new TextPair(parser.getStationId(), "0"), new Text(parser.getStationName()));
        }
    }
}
