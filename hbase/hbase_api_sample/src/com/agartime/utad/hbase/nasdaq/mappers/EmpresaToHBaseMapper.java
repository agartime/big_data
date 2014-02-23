package com.agartime.utad.hbase.nasdaq.mappers;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.agartime.utad.hbase.nasdaq.EmpresaStockValues;

/*
 * Clase mapper para hacer mapreduce con un fichero de entrada y una tabla de salida
 * 
 */ 

public final class EmpresaToHBaseMapper extends Mapper<LongWritable, Text, Text, Put>  {
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String messageStr = value.toString();
		if (messageStr.contains("\t")) {
			String[] logRecvArr = messageStr.split("\t");
			if (logRecvArr.length != 8) {
				System.out.println("Linea incorrecta: "+messageStr);
			} else {
				EmpresaStockValues empresa = new EmpresaStockValues(logRecvArr);
				Put put = new Put(Bytes.toBytes(empresa.getStockSymbol()));
				put.add(Bytes.toBytes("price"), Bytes.toBytes("open"), empresa.getTimestamp(), Bytes.toBytes(Float.toString(empresa.getStockPriceOpen())));
				put.add(Bytes.toBytes("price"), Bytes.toBytes("high"), empresa.getTimestamp(), Bytes.toBytes(Float.toString(empresa.getStockPriceHigh())));
				put.add(Bytes.toBytes("price"), Bytes.toBytes("low"), empresa.getTimestamp(), Bytes.toBytes(Float.toString(empresa.getStockPriceLow())));
				put.add(Bytes.toBytes("price"), Bytes.toBytes("close"), empresa.getTimestamp(), Bytes.toBytes(Float.toString(empresa.getStockPriceClose())));
				put.add(Bytes.toBytes("totals"), Bytes.toBytes("volume"), empresa.getTimestamp(), Bytes.toBytes(empresa.getStockVolume()));
				put.add(Bytes.toBytes("totals"), Bytes.toBytes("price_adj_close"), empresa.getTimestamp(), Bytes.toBytes(empresa.getStockPriceAdjClose()));
				context.write(new Text("1"), put);
			}
		}			
	}
}

