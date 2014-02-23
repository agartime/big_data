package com.agartime.utad.hbase.nasdaq;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.agartime.utad.hbase.nasdaq.mappers.EmpresaToHBaseMapper;



/**
 *  Rowkey: empresa
	Column Family: price, totals
	Qualifier: metrica
	Version: timestamp

		Adaptar el MR
		almacenar la media de las cotizaciones maximas de cada mes para cada empresa
		almacenar un contador para el numero de veces que cada empresa supera la cotizacion maxima 24.00
		visualizar todas las empresas que o han superado la cotizacion maxima de 24.0 o estan por debajo de la minima de 10.0
		visualizar todos los dias en los que al menos una empresa ha superado la cotizacion maxima
 *
 */
public class EmpresaSample {

    public static final String tableName="Empresa";
    public static final String fileName="file:/tmp/NASDAQ_daily_prices_subset_RK-emp.tsv";
    public static final String columnFamilty1="price";
    public static final String columnFamilty2="totals";

	private static Configuration conf = null;
    private static HBaseAdmin admin = null;
    
    private static void initialize() throws MasterNotRunningException, ZooKeeperConnectionException {
	    conf = HBaseConfiguration.create();
        admin = new HBaseAdmin(conf);
    }
    
    
    private static void createTableInHBase() throws IOException {
    	if (admin.tableExists(tableName)) {
    		//borraTabla();
    		System.err.println("The table already exists. Aborting.");
    		return;
    	}

    	// instancia el descriptor de la tabla
    	HTableDescriptor desc = new HTableDescriptor(tableName);
    	// instancia el descriptor de la CF1
    	HColumnDescriptor coldef1 = new HColumnDescriptor(columnFamilty1);
    	coldef1.setMaxVersions(300);
    	// instancia el descriptor de la CF2
    	HColumnDescriptor coldef2 = new HColumnDescriptor(columnFamilty2);
    	coldef2.setMaxVersions(300);
    	
    	// añade los descriptores de las CF al general de la tabla
    	desc.addFamily(coldef1);
    	desc.addFamily(coldef2);
    	// crea la tabla
    	admin.createTable(desc);
    }
    
    /*
     * Ejecuta el job M/R
     */
	private static void execute() throws Exception {
		Configuration config = conf;
		
		// construye el objeto job y le asigna los tipos de entrada y salida
		Job job = new Job(config, "EmpresaSample");
		job.setJarByClass(EmpresaSample.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// asigna los formatos de entrada y salida del job
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		// asigna la clase Map
		job.setMapperClass(EmpresaToHBaseMapper.class);
		// indica que no hay reducer
		job.setNumReduceTasks(0);
		
		// asigna el fichero de entrada
		FileInputFormat.setInputPaths(job, new Path(fileName));

		// Vincula este job a la tabla de HBase como salida
		TableMapReduceUtil.initTableReducerJob(tableName, null, job);

		// lanza el job
		job.waitForCompletion(true);		
	}
	
	public static void query1(HTable tabla, String minTime, String maxTime) throws IOException {
	    List<Filter> filters = new ArrayList<Filter>();

        // instancia la lista de filtros con AND
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
        RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("DELL")));
        
        ValueFilter minFilter = new ValueFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(minTime)));
        ValueFilter maxFilter = new ValueFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes(maxTime)));

        filterList.addFilter(filter);
        filterList.addFilter(minFilter);
        filterList.addFilter(maxFilter);
        
        // instancia el scan y asigna los filtros
	    Scan scan = new Scan();
	    scan.setFilter(filterList);
	    scan.setMaxVersions(Integer.MAX_VALUE);
	    
	    // instancia el iterador
	    ResultScanner scanner = tabla.getScanner(scan);
	    
	    
	    for (Result result : scanner) {
	    	float count=0;
	    	float suma=0;
	        List<KeyValue> listaResultados=result.list();
	        for (KeyValue kv : listaResultados) {
	        	if (kv.matchingFamily(Bytes.toBytes("price")) && kv.matchingQualifier(Bytes.toBytes("high"))) {
	        		count++;
	        		System.out.println("KeyValue: " + kv.toString());
	            	System.out.println("VALOR="+Bytes.toString(kv.getValue()));
	            	suma+=Float.parseFloat(Bytes.toString(kv.getValue()));
	        	}
	        }
	        
	        System.out.println("MEDIA: "+suma/count);

	    	
	    	/*	    	for (KeyValue kv : result.raw()) {
				System.out.println("FLOAT: "+kv.getValue());, String 
			}
	    
	    	System.out.println("")*/;
/*	      byte [] highValues = result.getValue(Bytes.toBytes(columnFamilty1),Bytes.toBytes("high"));
	      System.out.println(Bytes.toString(highValues));*/
	    }
	    scanner.close();
	    
	    // devuelve el ultimo rk leido
	
	}

   
    public static void main(String [] args) {
    	try {
			EmpresaSample.initialize();
			//EmpresaSample.createTableInHBase();
			EmpresaSample.execute();
			HTable tabla=new HTable(EmpresaSample.conf, tableName);
			System.out.println("TIME: "+Timestamp.valueOf("1997-01-01 00:00:00").getTime()+" "+Timestamp.valueOf("1997-01-31 23:59:59").getTime());
			EmpresaSample.query1(tabla,String.valueOf(Timestamp.valueOf("1997-01-01 00:00:00").getTime()),String.valueOf(Timestamp.valueOf("1997-01-31 23:59:59").getTime()));
			
			
		    // sufijo para paginar en base a la RK final de la pagina anterior

		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
