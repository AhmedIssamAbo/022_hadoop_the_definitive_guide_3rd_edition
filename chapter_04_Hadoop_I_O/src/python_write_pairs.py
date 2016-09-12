#!/usr/bin/env python
#################################################
# Filename: python_write_pairs.py
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     21:56
# Version:  v1.0.0
#################################################

import os
import string
import sys

form avro import schema
from avro import io
from avro import datafile

if __name__ == '__main__':
    if len(sys.argv) != 2:
        sys.exit('Usage: %s <data_file>' % sys.argv[0])

    avro_file = sys.argv[1]
    writer = open(avro_file, 'wb')
    datum_writer = io.DatumWriter()

    schema_object = schema.parse("
        { "type" : "record",
            "name" : "Pair",
            "doc" : "StringPair.",
            "fields" : [
                { "name" : "left", "type" : "string"},
                { "name" : "right", "type": "string"}
            ]
          }")

    dfw = datafile.DataFileWriter(writer, datum_writer, schema_object)
    for line in sys.stdin.readline():
        (left, right) = string.split(line.strip(), ',')
        dfw.append({'left':left, 'right':right});
    dfw.close()
