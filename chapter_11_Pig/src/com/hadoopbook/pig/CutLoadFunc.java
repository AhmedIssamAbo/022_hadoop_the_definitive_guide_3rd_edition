package com.hadoopbook.pig;

import org.apache.commons.lang.math.Range;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.pig.LoadFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigSplit;
import org.apache.pig.data.DataByteArray;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

import java.io.IOException;

/**
 * Filename: CutLoadFunc.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     9:53
 * Version:  v1.0.0
 */
public class CutLoadFunc extends LoadFunc {
    private static final log LOG = LogFactory.getLog(CutLoadFunc.class);

    private final List<Range> ranges;
    private final TupleFactory tupleFactory = TupleFactory.getInstance();
    private RecordReader reader;

    public CutLoadFunc(String cutPattern) {
        ranges = Range.parse(cutPattern);
    }

    @Override
    public void setLocation(String location, Job job) throws IOException {
        FileInputFormat.setInputPaths(job, location);
    }

    @Override
    public InputFormat getInputFormat() {
        return new TextInputFormat();
    }

    @Override
    public void prepareToRead(RecordReader reader, PigSplit split) {
        this.reader = reader;
    }

    @Override
    public Tuple getNext() throws IOException {
        try {
            if (!reader.nextKeyValue()) {
                return null;
            }

            Text value = (Text)reader.getCurrentValue();
            String line = value.toString();
            Tuple tuple = tupleFactory.newTuple(ranges.size());

            for (int i = 0; i < ranges.size(); ++i) {
                Range range = ranges.get(i);
                if (range.getEnd() > line.length()) {
                    LOG.warn(String.format("Range end (%s) is longer than line length (%s)", range.getEnd(), line.length()));

                    continue;
                }

                tuple.set(i, new DataByteArray(range.getSubString(line)));
            }

            return tuple;
        } catch (InterruptedException e) {
            throw new ExecException(e);
        }
    }
}
