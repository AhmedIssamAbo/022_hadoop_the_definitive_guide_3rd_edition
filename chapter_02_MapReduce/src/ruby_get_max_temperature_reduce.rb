#!/usr/bin/env ruby
#################################################
# Filename: ruby_get_max_temperature_reduce.rb
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     12:26
# Version:  v1.0.0
#################################################

last_key, max_val = nil, -1000000

STDIN.each_line do |line|
    key, val = line.split("\t")
    if last_key && last_key != key
        puts "#{last_key}\t#{max_val}"
        last_key, max_val = key, val.to_i
    else
        last_key, max_val = key, [max_val, val.to_i].max
    end
end

puts "#{last_key}\t#{max_val}" if last_key
