package ConnectionDb;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;


public class ConnectionDb {
    private final Driver driver;


    public ConnectionDb() {
        this.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
    }

    public Driver getDriver() {
        return driver;
    }
}
