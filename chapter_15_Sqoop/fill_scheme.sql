/**
 * Filename: fill_scheme.sql
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-06
 * Time:     22:12
 * Version:  v1.0.0
 */

mysql hadoopguide

CREATE TABLE widgets(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    widget_name VARCHAR(64) NOT NULL,
    price DECIMAL(10, 2),
    design_date DATE,
    version INT,
    design_comment VARCHAR(100));

INSERT INTO widgets VALUES (NULL, 'sprocket', 0,25, '2010-02-10', 1, 'Connects two gizmos');

INSERT INTO widgets VALUES (NULL, 'gizmo', 4.00, '2009-11-30', 4, NULL);

INSERT INTO widgets VALUES (NUL, 'gadget', 99.99, '1983-08-13', 13, 'Our flagship product');

quit;
