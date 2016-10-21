#!/usr/bin/python python3
# -*- coding:utf-8 -*-
#
# Filename: sort_reducer.py
# Author:   jerry_0824
# Email:    63935127#qq.com
# Date:     2016-09-08
# Time:     21:37
# Version:  v1.0.0
#

import sys

last_group = None

for line in sys.stdin:
    val = line.strip()
    (year, temp) = val.split("\t")
    group = year

    if last_group != group:
        print val
        last_group = group
