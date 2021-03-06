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

Methods added in SQLException
-----------------------------

* getErrorCode(): vendor-specific exception code as integer
* getSQLState(): vendor-specific exception code as 5-character string
* getNextException(), setNextException(), iterator(): tools for navigating chains
of multiple exceptions

Using the ResultSet cursor
--------------------------

ResultSet encapsulates data returned from the database server. 

* each instance of ResultSet has a cursor - an in-memory pointer that goes from row to row
* the starting cursor position is __before__ the first row of data
* the most common operation is to move forward from row to row
```java
ResultSet resultSet = stmt.executeQuery("SELECT * FROM tablename");
while (resultSet.next()) {
    String columnValue = resultSet.getString("columnName");
    System.out.println(columnValue);
}
``` 

Using scrollable ResultSet
--------------------------

By default result sets are forward only.
* ResultSet can be made scrollable with most databases 
* select scrolling when creating the Statement object:
```java
Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
```
__For maximum portability be explicit with your ResultSet options.__  

Moving the cursor:
* methods that move the cursor to specific rows
    - rs.beforeFirst();
    - rs.first();
    - rs.last();
    - rs.afterLast();
    - rs.absolute(int row);
* boolean methods that check the cursor position
    - rs.isBeforeFirst();
    - rs.isFirst();
    - rs.isLast();
    - rs.isAfterLast();

Limit row
---------
Do not used setMaxRows(int) method of Statement.  
Use static SQL:
```sql
SELECT * FROM table-name LIMIT offset-rows, num-rows;
```


Prepared Statements
-------------------
```java
PreparedStatement stmt = conn.prepareStatement(SQL);
stmt.setDouble(1, doubleValue);
ResultSet rs = stmt.executeQuery();
```


Stored Procedures
-----------------
```java
final String SQL = "{call GetToursByPrice(?)}";
CallableStatement stmt = conn.prepareCall(SQL);
stmt.setDouble(1, maxPrice);
ResultSet rs = stmt.executeQuery();
```
With __out__ parameter:
```java
final String SQL = "{call GetToursWithCountByPrice(?, ?)}";
CallableStatement stmt = conn.prepareCall(SQL);
stmt.setDouble(1, maxPrice);
stmt.registerOutParameter("total", Types.INTEGER);
ResultSet rs = stmt.executeQuery();
int nRows = stmt.getInt("total");
```


Generic Getters
---------------
```java
int tourId      = rs.getObject("tourId", Integer.class);
String tourName = rs.getObject("tourName", String.class);
double price    = rs.getObject("price", Double.class);
```


Java Beans
----------

Java Bean class has a name, it has private properties to represent its values,
and public setter and getter methods, that allow the rest of the application to
access and modify those values.  
True Java Bean is supposed to be serializable.


Insert SQL
----------

Using `Statement.RETURN_GENERATED_KEYS` and `stmt.getGeneratedKeys()`: 
```java
PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
stmt.setString(1, bean.getUserName());
int affected = stmt.executeUpdate();
if (affected == 1) {
    ResultSet keys = stmt.getGeneratedKeys();
    if (keys.next()) {
        int newKey = keys.getInt(1);
        bean.setAdminId(newKey);
        return true;
    }
}
```


Update SQL
----------
```java
String sql = "UPDATE admin SET userName = ?, password = ? WHERE adminId = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, bean.getUserName());
stmt.setString(2, bean.getPassword());
stmt.setInt(3, bean.getAdminId());
int affected = stmt.executeUpdate();
if (affected == 1) {
    return true;
}
```


Delete SQL
----------
```java
String sql = "DELETE FROM admin WHERE adminId = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, adminId);
int deleted = stmt.executeUpdate();
if (deleted == 1) {
    return true;
}
```


Updatable ResultSet
-------------------
The result set should contain __primary key__.
```java
String sql = "SELECT * FROM admin WHERE adminId = ?";
PreparedStatement stmt = conn.prepareStatement(sql,
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
stmt.setInt(1, bean.getAdminId());
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
    rs.updateString("userName", bean.getUserName());
    rs.updateString("password", bean.getPassword());
    rs.updateRow();
    return true;
}
```


Connection Management
---------------------

You should minimize opening and closing Connections by reusing Connections.
```java
try {
    ConnectionManager.getInstance().setDbType(DbType.MYSQL);
    AdminManager manager = new AdminManager(ConnectionManager.getInstance().getConnection());
    manager.displayAllRows();
    // other crud operations 
    // ...
    manager.displayAllRows();
} finally {
    ConnectionManager.getInstance().close();
}
```


Transactions
------------

In JDBC transactions are auto-committed by default (if not `setAutoCommit(false);`). 
MySQL's InnoDB storage engine supports transactions. MyISAM does not.
```java
Connection conn = connMgr.getConnection();
conn.setAutoCommit(false);
// some operations ...
conn.commit(); // OR conn.rollback();
```


Using Metadata
--------------

### Receive tables in database
```java
DatabaseMetaData metaData = conn.getMetaData();
final String[] tableTypes = {"TABLE"};
ResultSet rsTables = metaData.getTables(null, "%", "%", tableTypes);
// Parameter 'catalog' = null - it is name of the database and is already selected in the connection string
// Parameters 'schemaPattern' and 'tableNamePattern' are set as wildcard '%'
while (rsTables.next()) {
    System.out.println(rsTables.getString("TABLE_NAME"));
}
```

### Receive names and data types of the database columns
```java
for (String tableName : tables) {
    ResultSet rsColumns = metaData.getColumns(null, "%", tableName, "%");
    while (rsColumns.next()) {
        String columnName = rsColumns.getString("COLUMN_NAME");
        String typeName = rsColumns.getString("TYPE_NAME");
    }
}
```