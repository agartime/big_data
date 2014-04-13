package com.utad.cassandra.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.ImmutableMap;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.ddl.ColumnFamilyDefinition;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.serializers.IntegerSerializer;
import com.netflix.astyanax.serializers.StringSerializer;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;

public class Utils {

	public static Map<String, Keyspace> keyspaces = new ConcurrentHashMap<String, Keyspace>();

	public static Keyspace getKeyspace(String clusterName, String seeds,
			int port, String keyspaceName) {

		if (keyspaces.containsKey(keyspaceName)) {
			return keyspaces.get(keyspaceName);
		}

        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                .forCluster(clusterName)
                .forKeyspace(keyspaceName)
                .withAstyanaxConfiguration(
                        new AstyanaxConfigurationImpl()
                                .setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE))
                .withConnectionPoolConfiguration(
                        new ConnectionPoolConfigurationImpl("MyConnectionPool")
                                .setPort(port).setMaxConnsPerHost(1)
                                .setSeeds(seeds))
                .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                .buildKeyspace(ThriftFamilyFactory.getInstance());

        context.start();
		
		Keyspace keyspace = context.getClient();

		keyspaces.put(keyspaceName, keyspace);
		return keyspace;
	}

	public static ColumnFamily<String, String> getColumnFamily(String ksName,
			String cfName) throws ConnectionException {
		Keyspace ks = getKeyspace(ksName);

		ColumnFamily<String, String> cf;

		ColumnFamilyDefinition cfDefinition = ks.describeKeyspace()
				.getColumnFamily(cfName);

		if (cfDefinition == null) {

			cf = new ColumnFamily<String, String>(cfName,
					StringSerializer.get(), StringSerializer.get());

			ks.createColumnFamily(
					cf,
					ImmutableMap.<String, Object> builder()
							.put("default_validation_class", "BytesType")
							.put("key_validation_class", "BytesType")
							.put("comparator_type", "BytesType").build());
		} else {
			cf = new ColumnFamily<String, String>(
					cfName, StringSerializer.get(),
					StringSerializer.get());
		}

		return cf;
	}

	public static Keyspace getKeyspace(String keyspaceName) {
		return getKeyspace("utad", "localhost:9160", 9160, keyspaceName);
	}

	public static Map<String, String> ReadRow(String keyspace,
			String columnfamily, String rowKey) {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

		// TODO return a linked map that contains all the key->values

		return result;
	}

	public static Map<String, String> ReadFromUntil(String keyspace,
			String columnfamily, String rowKey, String from, String until) {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

		// TODO return a linked map that contains all the key->values

		return result;
	}

	public static Map<String, String> ReadFirstN(String keyspace,
			String columnfamily, String rowKey, int n) {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

		// TODO return a linked map that contains all the key->values

		return result;
	}
}
