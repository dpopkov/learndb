Working with Databases Using JDBC
=================================

* Basic CRUD Operations
* Stored Procedures
* Managing Transactions
* BLOB and CLOB
* Metadata & Pooling Database Connections

History
-------

Year    |   JDBC    | JDK   | Features
--------|-----------|-------|------
1997    | 1.2       | 1.1   | Updatable ResultSets, refactored DatabaseMetaData, Schedulers for increased performance
2001    | 3.0       | 1.4   | Blob and Clob can be altered, Passing parameters to CallableStatement, java.sql.BOOLEAN
2006    | 4.0       | 6     | DataSet implementation using Annotations, Support for RowId SQL type, SQL XML support
2011    | 4.0       | 7     | RowSet Factory Interface, RowSet Provider class, try-with-resources
2014    | 4.2       | 8     | Support for large update counts, REF_CURSOR support, java.sql.DriverAction

JDBC driver
-----------

A JDBC driver is a set of Java classes that implement the JDBC interfaces, targeting a specific Database.
* The JDBC interface comes with standard Java
* Implementations of these interfaces is specific to the Database