#!/usr/bin/env python
#################################################
# Filename: python_get_max_temperature_map.py
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     13:36
# Version:  v1.0.0
#################################################

import re
import sys

for line i sys.stdin:
    var = line.strip()
    (year, temp, q) = (val[15:19], val[87:92], val[92:93])
    if (temp != "+9999" and re.match("[01459]", q)):
        print "%s\t%s" % (year, temp)

