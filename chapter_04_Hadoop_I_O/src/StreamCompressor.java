import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import sun.misc.IOUtils;

import javax.security.auth.login.Configuration;

/**
 * Filename: StreamCompressor.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     18:12
 * Version:  v1.0.0
 */
public class StreamCompressor {
    public static void main(String[] args) throws Exception {
        String codecClassname = args[0];
        Class<?> codecClass = Class.forName(codecClassname);
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);

        CompressionOutputStream out = codec.createOutputStream(System.out);
        IOUtils.copyBytes(System.in, out, 4096, false);
        out.finish();
    }
}
