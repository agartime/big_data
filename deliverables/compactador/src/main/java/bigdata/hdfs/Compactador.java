package bigdata.hdfs;

import java.io.IOException;
import java.util.List;

import javax.naming.ConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.Writer;

public class Compactador {
    private Path originFiles;
    private Path destinyFile;
    private FileSystem fileSystem;
    private Configuration configuration;

    public Compactador(String originGlob, String destinyFile) throws IOException {
        this(new Path(originGlob),new Path(destinyFile), new Configuration());
    }

    public Compactador(String originGlob, String destinyFile, Configuration configuration) throws IOException {
        this(new Path(originGlob),new Path(destinyFile), configuration);
    }

    private Compactador(Path originFiles, Path destinyFile, Configuration configuration) throws IOException {
        this.originFiles=originFiles;
        this.destinyFile=destinyFile;
        this.configuration=configuration;
        this.fileSystem=FileSystem.get(configuration);
    }

    private boolean isWellConfigured() {
        try {
            return (!this.existsDestinyFile());
        } catch (IOException e) {
            return false;
        }
    }

    private boolean existsDestinyFile() throws IOException {
        return this.fileSystem.exists(this.destinyFile);
    }

    public void execute() throws ConfigurationException, IOException {
        if (this.isWellConfigured()) {
            FileStatus [] dir = this.fileSystem.globStatus(this.originFiles);
            Writer sequenceWriter = SequenceFile.createWriter(this.configuration,Writer.file(this.destinyFile),Writer.keyClass(LongWritable.class),Writer.valueClass(Text.class));
            int i=0;
            for (FileStatus fileStatus : dir) {
                FSDataInputStream in = this.fileSystem.open(fileStatus.getPath());
                List<String> lines =  IOUtils.readLines(in);
                for (String line : lines) {
                    sequenceWriter.append(new LongWritable(++i), new Text(line));
                }
            }
            sequenceWriter.close();
        } else throw new ConfigurationException("Bad configuration. Please, change the destiny file if it already exists, or delete it before trying again.");
    }

    public void printDecompressed() {
        Reader sequenceReader;
        try {
            sequenceReader = new Reader(this.configuration, Reader.file(this.destinyFile));

            LongWritable key = new LongWritable();
            Text value = new Text();
            while (sequenceReader.next(key, value)) {
                System.out.println(key+" "+value);
            }
            sequenceReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length<2) {
            System.err.println("Usage: bigdata.hdfs.Compactador <glob-origin-files> <destiny-file> (debug)]");
            return;
        } else {
            try {
                Compactador compactador = new Compactador(args[0],args[1]);
                compactador.execute();

                if (args[2].equalsIgnoreCase("debug")) {
                    compactador.printDecompressed();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ConfigurationException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

        }
    }

}