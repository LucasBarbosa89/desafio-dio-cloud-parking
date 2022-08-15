package one.digitalinnovation.parking;

import org.testcontainers.containers.MSSQLServerContainer;

public abstract class AbstractContainer {

    static final MSSQLServerContainer SQL_SERVER_CONTAINER;

    static {
        SQL_SERVER_CONTAINER = new MSSQLServerContainer("localhost");
        SQL_SERVER_CONTAINER.start();

        System.setProperty("spring.datasource.url", SQL_SERVER_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", SQL_SERVER_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", SQL_SERVER_CONTAINER.getPassword());
    }
}
