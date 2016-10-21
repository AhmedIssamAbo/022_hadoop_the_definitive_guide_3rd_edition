package com.hadoopbook.hive;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;

/**
 * Filename: Mean.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-07
 * Time:     21:35
 * Version:  v1.0.0
 */
public class Mean extends UDAF {
    public static class MeanDoubleUDAFEvaluator implements UDAFEvaluator {
        public static class PartialResult {
            double sum;
            long count;
        }

        private PartialResult partial;

        public void init() {
            partial = null;
        }

        public boolean iterate(DoubleWritable value) {
            if (null == value) {
                return true;
            }

            if (null == partial) {
                partial = new PartialResult();
            }

            partial.sum += value.get();
            ++partial.count;

            return true;
        }

        public PartialResult terminatePartial() {
            return partial;
        }

        public boolean merge(PartialResult other) {
            if (null == other) {
                return true;
            }

            if (null == partial) {
                partial = new PartialResult();
            }

            partial.sum += other.sum;
            partial.count += other.count;

            return true;
        }

        public DoubleWritable terminate() {
            if (null == partial) {
                return null;
            }

            return new DoubleWritable(partial.sum / partial.count);
        }
    }
}
