import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Filename: LoggingIdentityMapper.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     7:52
 * Version:  v1.0.0
 */
public class LoggingIdentityMapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT> extends Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT> {
    private static final Log LOG = LogFactory.getLog(LoggingIdentityMapper.class);

    @Override
    public void map(KEYIN key, VALUEIN value, Mapper.Context context) throws IOException, InterruptedException {
        // Log to stdout file
        System.out.println("Map key: " + key);

        // Log to syslog file
        LOG.info("Map key: " + key);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Map value: " + value);
        }
        context.write((KEYOUT)key, (VALUEOUT)value);
    }
}
