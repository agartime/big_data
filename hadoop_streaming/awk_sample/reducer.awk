BEGIN {
	FS = "\t"
}
{
	s[$1] = $2 + s[$1]
}
END {
	for(p in s) {
		print p"\t"s[p]
	}
}
