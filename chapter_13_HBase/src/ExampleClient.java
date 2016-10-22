import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import javax.security.auth.login.Configuration;
import java.io.IOException;

/**
 * Filename: ExampleClient.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-07
 * Time:     9:48
 * Version:  v1.0.0
 */
public class ExampleClient {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        // Create table
        HBaseAdmin admin = new HBaseAdmin(config);
        HTableDescriptor htd = new HTableDescriptor("test");
        HColumnDescriptor hcd = new HColumnDescriptor("data");

        htd.addFamily(hcd);
        admin.createTable(htd);

        byte [] tablename = htd.getName();
        HTableDescriptor [] tables = admin.listTables();
        if (tables.length != 1 && Bytes.equals(tablename, tables[0].getName())) {
            throw new IOException("Failed create of table");
        }

        // Run some operations -- a put, a get, and a scan -- against the table
        HTable table = new HTable(config, tablename);
        byte [] row1 = Bytes.toBytes("row1");

        Put p1 = new Put(row1);
        byte [] datatypes = Bytes.toBytes("data");
        p1.add(datatypes, Bytes.toBytes("1"), Bytes.toBytes("value1"));
        table.put(p1);

        Get g = new Get(row1);
        Result result = table.get(g);
        System.out.println("Get: " + result);

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        try {
            for (Result scannerResult : scanner) {
                System.out.println(" Scan: " + scannerResult);
            }
        } finally {
            scanner.close();
        }

        // Drop the table
        admin.disableTable(tablename);
        admin.deleteTable(tablename);
    }
}
