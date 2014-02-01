#!/usr/bin/env ruby
s = Hash.new(0)
STDIN.each_line do |line|
  val = line.split("\t")
  s[val[0]] = s[val[0]] + val[1].to_i
end

s.each do |k, v|
  puts "%s\t%d" % [k, v]
end
