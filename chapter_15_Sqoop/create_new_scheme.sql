/**
 * Filename: create_new_scheme.sql
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-06
 * Time:     22:07
 * Version:  v1.0.0
 */

mysql -u root -p

CREATE DATABASE hadoopguide;

GRANT ALL PRIVILEAGES ON hadoopguide.* TO '%'@'localhost';

GRANT ALL PRIVILEAGES ON hadoopguide.* TO ''@'localhost';

quit;
