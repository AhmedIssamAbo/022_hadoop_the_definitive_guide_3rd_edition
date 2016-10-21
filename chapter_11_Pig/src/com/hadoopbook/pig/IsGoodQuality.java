package com.hadoopbook.pig;

import org.apache.pig.FilterFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;

import java.io.IOException;

/**
 * Filename: IsGoodQuality.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-08
 * Time:     8:47
 * Version:  v1.0.0
 */
public class IsGoodQuality extends FilterFunc {
    @Override
    public boolean exec(Tuple tuple) throws IOException {
        if (null == tuple || 0 == tuple.size()) {
            return false;
        }

        try {
            Object object = tuple.get(0);
            if (null == object) {
                return false;
            }

            int i = (Integer) object;

            return 0 == i || 1 == i || 4 == i || 5 == i || 9 == i;
        } catch (ExecException e) {
            throw new IOException(e);
        }
    }
}
