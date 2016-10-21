#!/usr/bin/python python3
# -*- coding:utf-8 -*-
#
# Filename: sort_map.py
# Author:   jerry_0824
# Email:    63935127#qq.com
# Date:     2016-09-08
# Time:     21:33
# Version:  v1.0.0
#

import re
import sys

for line in sys.stdin:
    val = line.strip()
    (year, temp, q) = (val[15:19], int(val[87:92]), val[92:93])

    if temp == 9999:
        sys.stderr.write("reporter:counter:Temperature, Missing, 1\n")
    elif re.match("[01459]", q):
        print "%s\t%s" % (year, temp)
