import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import sun.misc.IOUtils;

import javax.security.auth.login.Configuration;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Filename: FileDecompressor.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     18:23
 * Version:  v1.0.0
 */
public class FileDecompressor {
    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);
        if (null == codec) {
            System.err.println("No codec found for " + uri);
            System.exit(1);
        }

        String outputUri = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());

        InputStream in = null;
        OutputStream out = null;

        try {
            in = codec.createInputStream(fs.open(inputPath));
            out = fs.create(new Path(outputUri));

            IOUtils.copyBytes(in, out, conf);
        } finally {
            IOUtils.closestream(in);
            IOUtils.closeStream(out);
        }
    }

}
