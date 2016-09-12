import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import sun.misc.IOUtils;

import javax.security.auth.login.Configuration;
import java.net.URI;

/**
 * Filename: FileSystemDoubleCat.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     17:22
 * Version:  v1.0.0
 */
public class FileSystemDoubleCat {
    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;

        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);

            // go back to the start fo the file
            in.seek(0);

            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
