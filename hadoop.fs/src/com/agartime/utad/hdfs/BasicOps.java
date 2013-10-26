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

public class BasicOps {

	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystem.get(new Configuration(true));
		System.out.println("FileSystem Object Info: "+fs);

		// To add a local FS we would use:
		// FileSystem fsLocal = FileSystem.getLocal(new Configuration());
		
		String filePathString = "/user/cloudera/prueba-hdfs.path";
		Path path = new Path(filePathString);		
		System.out.println("Creating or replacing file at: "+filePathString);
		FSDataOutputStream out = fs.create(path, true); //true = overrides the file
		System.out.println("Printing something inside the file.");
		out.write("Hello World!!!".getBytes());		
		System.out.println("Closing the file.");
		out.close();
		
		/*
		FSDataOutputStream out2 = fs.append(path);
		out2.write("LINEA 2".getBytes());
		out2.close();
		*/
		
		System.out.println("Opening the file again.");
		FSDataInputStream in = fs.open(path);
		byte[] buffer = new byte[1024];
		
		System.out.println("Reading the file using a 1024 bytes buffer.");
		in.read(buffer);
		
		System.out.println("After reading the file using a 1024 bytes buffer we get: "+new String(buffer));
		System.out.println("Seeking from the beginning of the file again.");
		in.seek(0); 
		
		List <String> lines = IOUtils.readLines(in); 
		System.out.println("After reading all the file using IOUtils, we get: "+lines);
		
		FileSystem fsLocal = FileSystem.getLocal(new Configuration());
		System.out.println("Retrieving some info about the local FileSystem:"+fsLocal);
		
		FileStatus status = fs.getFileStatus(path); 
		System.out.println("We can get some metadata using FileStatus. For example, the owner of the file: "+status.getOwner());
		
		System.out.println("We can also use it to get the contents of a directory. Using globStatus you would be able to filter using regexps or globs.");
		System.out.println("Retrieving the contents of /user/cloudera");
		FileStatus [] dir = fs.listStatus(new Path("/user/cloudera"));
		
		for (FileStatus fileStatus : dir) {
			System.out.println(fileStatus.getPath() + " " + status.getOwner() + " " + status.getLen());
		}
		
		System.out.println("We don't forget to close the fileSystem itself! Good bye!");
		fs.close();
	}

}
