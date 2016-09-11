#!/usr/bin/env python
#################################################
# Filename: python_get_max_temperature_reduce.py
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     13:39
# Version:  v1.0.0
#################################################

import sys

(last_key, max_val) = (None, -sys.maxint)
for line in sys.stdin
    (key, val) = line.strip().split("\t")
    if last_key and last_key != key:
        print "%s\t%s" % (last_key, max_val)
        (last_key, max_val) = (key, int(val))
    else:
        (last_key, max_val) = (key, max(max_val, int(val)))

    if last_key:
        print "%s\t%s" % (last_key, max_val)

