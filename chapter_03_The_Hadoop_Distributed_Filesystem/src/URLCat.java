import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import sun.misc.IOUtils;

import java.io.InputStream;
import java.net.URL;

/**
 * Filename: URLCat.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     16:56
 * Version:  v1.0.0
 */
public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws Exception {
        InputStream in = null;

        try {
            in = new URL(arg[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
