#!/usr/bin/env ruby
#################################################
# Filename: ruby_get_max_temperature_map.rb
# Author:   jerry_0824
# Email:    63935127##qq.com
# Date:     2016-09-09
# Time:     12:26
# Version:  v1.0.0
#################################################

#!/usr/bin/env ruby -w

STDIN.each_line do |line|
    val = line
    year, temp, q = val[15, 4], val[87, 5], val[92, 1]
    puts "#{year}\t#{temp}" if (temp != "+9999" && q =~ /[01459]/)
end
