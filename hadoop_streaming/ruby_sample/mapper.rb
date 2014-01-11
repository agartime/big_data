#!/usr/bin/env ruby
STDIN.each_line do |line|
  val = line.split(" ")
  val.each do |palabra|
    palabrad = palabra.downcase
    puts "%s\t1" % [palabrad] if palabrad =~ /^[a-z]+$/
  end
end
