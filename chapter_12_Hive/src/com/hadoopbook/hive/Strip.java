package com.hadoopbook.hive;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * Filename: Strip.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-07
 * Time:     20:07
 * Version:  v1.0.0
 */
public class Strip extends UDF {
    private Text result = new Text();

    public Text evaluate(Text str) {
        if (null == str) {
            return null;
        }

        result.set(StringUtils.strip(str.toString()));

        return result;
    }

    public Text evaluate(Text str, String stripChars) {
        if (null == str) {
            return null;
        }

        result.set(StringUtils.strip(str.toString(), stripChars));
    }
}
