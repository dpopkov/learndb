Chapter 13: Grouping Data
=========================

```sql
SELECT entstagename, contractprice
FROM entertainers
INNER JOIN engagements USING (entertainerid)
ORDER BY entstagename;
```

```sql
SELECT entstagename, count(contractprice) AS NumContracts, 
        sum(contractprice) AS TotPrice, min(contractprice) AS MinPrice, 
        round(avg(contractprice),2 ) AS AvgPrice, max(contractprice) AS MaxPrice
FROM entertainers
INNER JOIN engagements USING (entertainerid)
GROUP BY entstagename
ORDER BY entstagename;
```

Show me for each customer the customer first and last names, the count of contracts for the customer, the total price of all the contracts, the lowest contract price, the highest contract price, and the average price of all the contracts.
```sql
SELECT custfirstname, custlastname,
        count(contractprice) AS NumContracts, sum(contractprice) AS TotPrice, 
        min(contractprice) AS MinPrice, round(avg(contractprice),2 ) AS AvgPrice, 
        max(contractprice) AS MaxPrice
FROM customers
JOIN engagements USING (customerid)
GROUP BY custfirstname, custlastname
ORDER BY custfirstname, custlastname;
```

Mixing Columns and Expressions
------------------------------

Show me for each customer the customer full name, the customer full address, the latest contract date for the customer, and the total price of all the contracts.
```sql
SELECT (custfirstname || ' ' || custfirstname) AS fullname,
    (custstreetaddress || ', ' || custcity || ', ' || custstate) AS FullAddress,
    max(startdate) AS LatestDate, sum(contractprice) AS TotPrice
FROM customers
JOIN engagements USING (customerid)
GROUP BY custfirstname, custfirstname, custstreetaddress, custcity, custstate
ORDER BY fullname;
```

Using GROUP BY in a Subquery in a WHERE Clause
----------------------------------------------

Display the engagement contract whose price is greater than the sum of all contracts for any other customer.
```sql
SELECT custfirstname, custlastname, startdate, contractprice
FROM customers
JOIN engagements
ON customers.customerid = engagements.customerid
WHERE contractprice > ALL (
	SELECT SUM(contractprice)
	FROM engagements AS e2
	WHERE e2.customerid <> customers.customerid
	GROUP BY e2.customerid
);
```

Simulating a SELECT DISTINCT Statement
--------------------------------------

Show me the unique city names from the customers table.  
```sql
SELECT custcity FROM customers GROUP BY custcity;
```

Culumn Restrictions
-------------------

Display the customer ID, customer full name, and the total of all engagement contract prices.
```sql
SELECT customerid, custfirstname || ' ' || custlastname, sum(contractprice)
FROM customers 
JOIN engagements USING (customerid)
GROUP BY customerid, custfirstname, custlastname;
```

Grouping on Expressions
-----------------------

Show me for each customer in the state of Washington the customer
full name, the customer full address, the latest contract date for the
customer, and the total price of all the contracts.
```sql
SELECT custfirstname || ' ' || custlastname, custstreetaddress || ', ' || custcity , max(startdate), sum(contractprice)
FROM customers JOIN engagements USING (customerid)
WHERE custstate = 'WA'
GROUP BY custfirstname, custlastname, custstreetaddress, custcity;
```


