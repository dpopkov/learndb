JDBC
====

JDBC - API, a set of interfaces and classes that let you easily connect to relational databases. 
Originally known as the "Java Database Connectivity" API. Released as part of JDK 1.1 in 1997.

History of JDBC
---------------

Version | new features  | Java version
--------|---------------|-------------
1.0	| Connection, Statement, ResultSet
1.2	| Updatable ResultSet  
2.0	| DataSource interface, pooled connections | ver.1.2
2.1	| Scrollable ResultSet, batch SQL statements, BLOB, CLOB, ARRAY 
3.0 | Prepared statements, BOOLEAN, passing params to callable statements, DatabaseMetaData API | ver.1.4
4.0 | Auto-loadable drivers (no more Class.forName()), SQL SML support              | Java 6
4.1 | Main classes are closable by try-with-resources enhancements to RowSet API    | Java 7
4.2 | REF_CURSOR, DriverAction, SQLType, JDBCType, Rowset 1.2  | Java 8
4.3 | Support for Database Sharding | Java 9

Alternatives to JDBC (use JDBC in background)
--------------------------------------------

* higher level abstractions
    - Spring JdbcTemplate
* data mapping APIs
    - Hibernate
    - iBATIS
    - Java Persistence API (JPA)
    
JDBC driver
-----------

JDBC drivers are software libraries that communicate between a Java application and an RDBMS, or a database file.
All JDBC drivers follow the API defined in Java SE. A driver library package will contain specific implementation of
these interfaces:

* Connection, ResultSet
* Statement, PreparedStatement, CallableStatement