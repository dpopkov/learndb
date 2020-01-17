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

Sample Statements
-----------------

### Sales Orders Database

List for each customer and order date the customer full name and the
total cost of items ordered on each date.
```sql
SELECT custfirstname, custlastname, orderdate, sum(quotedprice * quantityordered)
FROM customers
JOIN orders USING (customerid)
JOIN order_details USING (ordernumber)
GROUP BY customerid, orderdate;
```

### Entertanment Agency Database

Display each entertainment group ID, entertainment group member,
and the amount of pay for each member based on the total contract
price divided by the number of members in the group.
```sql
SELECT entertainerid, mbrfirstname, mbrlastname, sum(contractprice) / (
	SELECT count(memberid) FROM entertainer_members em
	WHERE em.status <> 3 AND em.entertainerid = entertainers.entertainerid
) AS memberpay
FROM entertainers
JOIN entertainer_members USING (entertainerid)
JOIN engagements USING (entertainerid)
JOIN members USING (memberid)
WHERE entertainer_members.status <> 3
GROUP BY entertainerid, mbrfirstname, mbrlastname
ORDER BY mbrfirstname, mbrlastname;
```

### School Scheduling Database

For completed classes, list by category and student the category
name, the student name, and the student’s average grade of all
classes taken in that category.
```sql
SELECT categorydescription, studfirstname, studlastname, avg(grade) AS AvgGrade
FROM students
JOIN student_schedules USING (studentid)
JOIN student_class_status USING (classstatus)
JOIN classes USING (classid)
JOIN subjects USING (subjectid)
JOIN categories USING (categoryid)
WHERE classstatusdescription = 'Completed'
GROUP BY categorydescription, studfirstname, studlastname;
```

### Bowling League Database

Show me for each tournament and match the tournament ID, the tour-
nament location, the match number, the name of each team, and the
total of the handicap score for each team.
```sql
SELECT t.tourneyid, t.tourneylocation, tm.matchid, te.teamname, sum(bs.handicapscore)
FROM tournaments AS t
JOIN tourney_matches AS tm ON t.tourneyid = tm.tourneyid
JOIN match_games AS mg ON tm.matchid = mg.matchid
JOIN bowler_scores AS bs ON mg.matchid = bs.matchid AND mg.gamenumber = bs.gamenumber
JOIN bowlers AS b ON bs.bowlerid = b.bowlerid
JOIN teams AS te ON b.teamid = te.teamid
GROUP BY t.tourneyid, tm.matchid, te.teamname
ORDER BY t.tourneyid, t.tourneylocation, tm.matchid, te.teamname;
```

Display the highest raw score for each bowler.
```sql
SELECT bowlerfirstname, bowlerlastname, max(rawscore)
FROM bowlers
JOIN bowler_scores USING (bowlerid)
GROUP BY bowlerid
ORDER BY bowlerfirstname, bowlerlastname;
```

### Recipes Database

Show me how many recipes exist for each class of ingredients.
```sql
SELECT ingredientclassdescription, count(DISTINCT recipeid)
FROM ingredient_classes
JOIN ingredients USING (ingredientclassid)
JOIN recipe_ingredients USING (ingredientid)
GROUP BY ingredientclassdescription
ORDER BY ingredientclassdescription;
```


Problems to solve
-----------------

### Sales Orders Database

1 - Show me each vendor and the average by vendor of the number of days to deliver products.
```sql
SELECT vendname, avg(daystodeliver) AS AvgDaysDeliver
FROM vendors JOIN product_vendors USING (vendorid)
GROUP BY vendorid, vendname;
```

2 - Display for each product the product name and the total sales.
```sql
SELECT productname, sum(quotedprice * quantityordered) AS TotalSales
FROM products JOIN order_details USING (productnumber)
GROUP BY productnumber, productname;
```

3 - List all vendors and the count of products sold by each.
```sql
SELECT vendname, count(productnumber) AS CountOfProduct
FROM vendors JOIN product_vendors USING (vendorid)
GROUP BY vendorid, vendname;
```

4 - Challenge: Now solve problem 3 by using a subquery.
```sql
SELECT vendname, (
	SELECT count(*) FROM product_vendors AS pv
	WHERE pv.vendorid = vendors.vendorid
) AS CountOfProduct
FROM vendors;
```

### Entertainment Agency Database

1 - Show each agent's name, the sum of the contract price for the engagements booked, and the agent's total commission.
```sql
SELECT agtfirstname, agtlastname, sum(contractprice) AS TotalContracts, (sum(contractprice) * commissionrate) AS TotalCommission
FROM agents
JOIN engagements USING (agentid)
GROUP BY agentid;	-- agtfirstname, agtlastname, commissionrate
```

### School Scheduling Database

1 - Display by category the category name and the count of classes offered.
```sql
SELECT categorydescription, count(classid)
FROM categories
JOIN subjects USING (categoryid)
JOIN classes USING (subjectid)
GROUP BY categoryid;	-- categorydescription
```

2 - List each staff member and the count of classes each is scheduled to teach.
```sql
SELECT stffirstname, stflastname, count(classid)
FROM staff
JOIN faculty_classes USING (staffid)
GROUP BY staffid; 	-- stffirstname, stflastname
```

3 - Challenge: Now solve problem 2 by using a subquery.
```sql
SELECT s.stffirstname, s.stflastname, (
SELECT count(*) FROM faculty_classes AS fc
WHERE fc.staffid = s.staffid
) AS ClassCount
FROM staff AS s
WHERE (
SELECT count(*) FROM faculty_classes AS fc
WHERE fc.staffid = s.staffid
) <> 0
ORDER BY s.stffirstname, s.stflastname;
```

### Bowling League Database

1 - Display for each bowler the bowler name and the average of the bowler's raw game scores.
```sql
SELECT bowlerfirstname, bowlerlastname, avg(rawscore) AS AvgRawScore
FROM bowlers
JOIN bowler_scores USING (bowlerid)
GROUP BY bowlerid;	-- bowlerfirstname, bowlerlastname
```

### Recipes Database

1 - If I want to cook all the recipes in my cookbook, how much of each ingredient must I ahve on hand?
```sql
SELECT ingredientname, sum(amount) AS TotalAmount, measurementdescription
FROM ingredients
JOIN recipe_ingredients AS ri USING (ingredientid)
JOIN recipes USING (recipeid)
JOIN measurements AS m ON m.measureamountid = ri.measureamountid
GROUP BY ingredientid, measurementdescription
ORDER BY ingredientname;
```

2 - List all meat ingredients and the count of recipes that tincldue each one.
```sql
SELECT ingredientname, count(recipeid)
FROM ingredients
JOIN recipe_ingredients USING (ingredientid)
JOIN ingredient_classes USING (ingredientclassid)
WHERE ingredientclassdescription = 'Meat'
GROUP BY ingredientname;
```

3 - Challenge: Now solve problem 2 by using a subquery.
```sql
SELECT i.ingredientname, (
	SELECT count(recipeid) FROM recipe_ingredients AS ri
	WHERE ri.ingredientid = i.ingredientid
) AS IngCount
FROM ingredients AS i
WHERE ingredientclassid = (
	SELECT ingredientclassid FROM ingredient_classes
	WHERE ingredientclassdescription = 'Meat'
) ORDER BY IngCount DESC;
```

