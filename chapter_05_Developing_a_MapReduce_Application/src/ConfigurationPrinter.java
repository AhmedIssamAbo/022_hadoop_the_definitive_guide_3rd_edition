import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import javax.security.auth.login.Configuration;

/**
 * Filename: ConfigurationPrinter.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-10
 * Time:     7:33
 * Version:  v1.0.0
 */
public class ConfigurationPrinter extends Configured implements Tool {
    static {
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
        Configuration.addDefaultResource("mapred-default.xml");
        Configuration.addDefaultResource("mapred-site.xml");
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();

        for (Entry<String, String> entry: conf) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new ConfigurationPrinter(), args);

        System.exit(exitCode);
    }
}

