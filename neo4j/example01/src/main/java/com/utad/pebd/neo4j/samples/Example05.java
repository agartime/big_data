package com.utad.pebd.neo4j.samples;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

public class Example05
{
    private static final String DB_PATH = "target/neo4j-store-with-new-indexing";

    public static void main( final String[] args )
    {
        System.out.println( "Starting database ..." );

        // START SNIPPET: startDb
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        // END SNIPPET: startDb

        {
            // START SNIPPET: createIndex
            IndexDefinition indexDefinition;
            try ( Transaction tx = graphDb.beginTx() )
            {
                Schema schema = graphDb.schema();
                indexDefinition = schema.indexFor( DynamicLabel.label( "User" ) )
                        .on( "username" )
                        .create();
                tx.success();
            }
            // END SNIPPET: createIndex
            // START SNIPPET: wait
            try ( Transaction tx = graphDb.beginTx() )
            {
                Schema schema = graphDb.schema();
                schema.awaitIndexOnline( indexDefinition, 10, TimeUnit.SECONDS );
            }
            // END SNIPPET: wait
        }

        {
            // START SNIPPET: addUsers
            try ( Transaction tx = graphDb.beginTx() )
            {
                Label label = DynamicLabel.label( "User" );

                // Create some users
                for ( int id = 0; id < 100; id++ )
                {
                    Node userNode = graphDb.createNode( label );
                    userNode.setProperty( "username", "user" + id + "@neo4j.org" );
                }
                System.out.println( "Users created" );
                tx.success();
            }
            // END SNIPPET: addUsers
        }

        {
            // START SNIPPET: findUsers
            Label label = DynamicLabel.label( "User" );
            int idToFind = 45;
            String nameToFind = "user" + idToFind + "@neo4j.org";
            try ( Transaction tx = graphDb.beginTx() )
            {
                try ( ResourceIterator<Node> users =
                        graphDb.findNodesByLabelAndProperty( label, "username", nameToFind ).iterator() )
                {
                    ArrayList<Node> userNodes = new ArrayList<>();
                    while ( users.hasNext() )
                    {
                        userNodes.add( users.next() );
                    }

                    for ( Node node : userNodes )
                    {
                        System.out.println( "The username of user " + idToFind + " is " + node.getProperty( "username" ) );
                    }
                }
            }
            // END SNIPPET: findUsers
        }

        {
            // START SNIPPET: resourceIterator
            Label label = DynamicLabel.label( "User" );
            int idToFind = 45;
            String nameToFind = "user" + idToFind + "@neo4j.org";
            try ( Transaction tx = graphDb.beginTx();
                  ResourceIterator<Node> users = graphDb
                        .findNodesByLabelAndProperty( label, "username", nameToFind )
                        .iterator() )
            {
                Node firstUserNode;
                if ( users.hasNext() )
                {
                    firstUserNode = users.next();
                }
                users.close();
            }
            // END SNIPPET: resourceIterator
        }

        {
            // START SNIPPET: updateUsers
            try ( Transaction tx = graphDb.beginTx() )
            {
                Label label = DynamicLabel.label( "User" );
                int idToFind = 45;
                String nameToFind = "user" + idToFind + "@neo4j.org";

                for ( Node node : graphDb.findNodesByLabelAndProperty( label, "username", nameToFind ) )
                {
                    node.setProperty( "username", "user" + ( idToFind + 1 ) + "@neo4j.org" );
                }
                tx.success();
            }
            // END SNIPPET: updateUsers
        }

        {
            // START SNIPPET: deleteUsers
            try ( Transaction tx = graphDb.beginTx() )
            {
                Label label = DynamicLabel.label( "User" );
                int idToFind = 46;
                String nameToFind = "user" + idToFind + "@neo4j.org";

                for ( Node node : graphDb.findNodesByLabelAndProperty( label, "username", nameToFind ) )
                {
                    node.delete();
                }
                tx.success();
            }
            // END SNIPPET: deleteUsers
        }

        {
            // START SNIPPET: dropIndex
            try ( Transaction tx = graphDb.beginTx() )
            {
                Label label = DynamicLabel.label( "User" );
                for ( IndexDefinition indexDefinition : graphDb.schema()
                        .getIndexes( label ) )
                {
                    // There is only one index
                    indexDefinition.drop();
                }

                tx.success();
            }
            // END SNIPPET: dropIndex
        }

        System.out.println( "Shutting down database ..." );
        // START SNIPPET: shutdownDb
        graphDb.shutdown();
        // END SNIPPET: shutdownDb
    }

}