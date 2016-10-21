/**
 * Filename: NavigableMap.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-07
 * Time:     15:32
 * Version:  v1.0.0
 */
public class NavigableMap <Long, Integer> getStationObservations(HTable table, String stationId, long maxStamp, int maxCount) throws IOException {
    byte[] startRow = RowKeyConverter.makeObservationRowKey(StationId, maxStamp);

    NavigableMap<Long, Integer> resultMap = new TreeMap<Long, Integer>();

    Scan scan = new Scan(startRow);
    scan.addColumn(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
    ResultScanner scanner = table.getScanner(scan);
    Result res = null;
    int count = 0;

    try {
        while ((res = scanner.next()) != null && ++count < maxCount) {
            byte[] row = res.getRow();
            byte[] value = res.getValue(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
            Long stamp = Long.MAX_VALUE;
            Bytes.toLong(row, row.length - Bytes.SIZEOF_LONG, Bytes.SIZEOF_LONG);
            Integer temp = Bytes.toInt(value);
            resultMap.put(stamp, temp);
        }
    } finally {
        scanner.close();
    }

    return resultMap;
}

/*
 * Return the last ten observations.
 */
public NavigableMap<Long, Integer> getStationObservations(HTable table, String stationId) throws IOException {
    return getStationObservation(table, stationId, Long.MAX_VALUE, 10);
}
