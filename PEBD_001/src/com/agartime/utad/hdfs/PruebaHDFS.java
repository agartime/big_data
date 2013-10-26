package com.agartime.utad.hdfs;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class PruebaHDFS {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileSystem fs = FileSystem.get(new Configuration(true));
		System.out.println(fs);

		// To add a local FS
		// FileSystem fsLocal = FileSystem.getLocal(new Configuration());		
		Path path = new Path("/user/cloudera/prueba-hdfs.path");
		FSDataOutputStream out = fs.create(path, true); //true = overrides the file
		out.write("Hola Mundo!!!".getBytes());		
		out.close();
		
		/*
		FSDataOutputStream out2 = fs.append(path);
		out2.write("LINEA 2".getBytes());
		out2.close();
		*/
		
		FSDataInputStream in = fs.open(path);
		byte[] buffer = new byte[1024];
		in.read(buffer);
		System.out.println(new String(buffer));
		in.seek(0); //We read from the beginning again
		
		List <String> lines = IOUtils.readLines(in); //We can use IOUtils to get all the file
		System.out.println(lines);
		
		FileSystem fsLocal = FileSystem.getLocal(new Configuration());
		System.out.println(fsLocal);
		
		FileStatus status = fs.getFileStatus(path); //We can read metada from the filesystem
		System.out.println(status.getOwner());
		
		FileStatus [] dir = fs.listStatus(new Path("/user/cloudera")); //Using globStatus we could use regexps
		for (FileStatus fileStatus : dir) {
			System.out.println(fileStatus.getPath() + " " + status.getOwner() + " " + status.getLen());
		}
		
		fs.close();
	}

}
