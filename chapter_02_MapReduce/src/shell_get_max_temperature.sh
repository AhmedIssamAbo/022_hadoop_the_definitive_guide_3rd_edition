#!/usr/bin/env bash
#################################################
# Filename: shell_get_max_temperature.sh
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     08:44
# Version:  v1.0.0
#################################################

for year in all/*
do
    echo -ne `basename $year .gz` "\t"
    gunzip -c $year | \
        awk '{ temp = substr($0, 88, 5) + 0;
                q = substrt($0, 93, 1);
                if ( temp != 9999 & q ~ /[01459]/ && temp > max ) max = temp }
            END { print max }'
done