package com.javastudy.coworkings.util;

import org.hsqldb.jdbc.JDBCDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DataSourceLoader {
    private static JDBCDataSource dataSource;
    private static Statement statement;
    private static String dmlDbData;
    private static String ddlDbSchema;
    private static String ddlDbCleanup;

    public static void init(String dmlFilePath) throws IOException, URISyntaxException {
        loadScripts(dmlFilePath);

        dataSource = new JDBCDataSource();
        dataSource.setURL("jdbc:hsqldb:mem:testcase;shutdown=true;sql.syntax_pgs=true");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        Connection connection;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void reloadData() throws SQLException {
        statement.executeUpdate(ddlDbSchema);
        statement.executeUpdate(dmlDbData);
    }

    public static void cleanSchema() throws SQLException {
        statement.executeUpdate(ddlDbCleanup);
    }

    private static void loadScripts(String dmlFilePath) throws URISyntaxException, IOException {
        URI dmlDbDataURI = Objects.requireNonNull(DataSourceLoader.class.getClassLoader().getResource(dmlFilePath)).toURI();
        dmlDbData = new String(Files.readAllBytes(Paths.get(dmlDbDataURI)));

        URI ddlDbSchemaURI = Objects.requireNonNull(DataSourceLoader.class.getClassLoader().getResource("db/ddl_db_schema.sql")).toURI();
        ddlDbSchema = new String(Files.readAllBytes(Paths.get(ddlDbSchemaURI)));

        URI ddlDbCleanupURI = Objects.requireNonNull(DataSourceLoader.class.getClassLoader().getResource("db/ddl_db_cleanup.sql")).toURI();
        ddlDbCleanup = new String(Files.readAllBytes(Paths.get(ddlDbCleanupURI)));
    }
}
