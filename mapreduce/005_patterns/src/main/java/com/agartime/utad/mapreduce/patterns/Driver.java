package com.agartime.utad.mapreduce.patterns;

import com.agartime.utad.mapreduce.patterns.invertedindex.*;
import com.agartime.utad.mapreduce.patterns.secondarysort.*;

public class Driver {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.println("Usage: InvertedIndex <input dir> <output dir>\n");
			System.out.println("OR:");
			System.out.println("Usage: SecondarySort <input dir> <output dir>\n");
			System.exit(-1);
		}
		
		String []newArgs = {args[1],args[2]};
		if (args[0].equals("InvertedIndex")) {
			InvertedIndexDriver.main(newArgs);
		} else if (args[0].equals("SecondarySort")) {
			SecondarySortDriver.main(newArgs);
		}
	}
}
