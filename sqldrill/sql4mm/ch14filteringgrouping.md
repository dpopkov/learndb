Chapter 14: Filtering Grouped Data
==================================


A New Meaning for "Focus Groups"
--------------------------------

Show me the entertainer groups that play in a jazz style and have
more than three members.
```sql
SELECT entstagename, count(memberid)
FROM entertainers
JOIN entertainer_styles USING (entertainerid)
JOIN musical_styles USING (styleid)
JOIN entertainer_members USING (entertainerid)
WHERE stylename = 'Jazz'
GROUP BY entstagename
HAVING count(memberid) > 3;
```

Wheare You Filter Makes a Difference
------------------------------------

### Should You Filter in WHERE or in HAVING?

Show me the states on the west coast of the US where the 
total of the orders is greater than $1 million.
```sql
SELECT custstate, sum(quotedprice * quantityordered) AS SumOfOrders
FROM customers
JOIN orders USING (customerid)
JOIN order_details USING (ordernumber)
WHERE custstate IN ('WA', 'OR', 'CA')
GROUP by custstate
HAVING sum(quotedprice * quantityordered) > 1000000;
```

### Avoiding the HAVING COUNT Trap

Show me the subject categories that have fewer than three full professors teaching that subject.

This query __does not list__ subject categories with zero professors:
```sql
SELECT categorydescription, count(staffid)
FROM faculty
JOIN faculty_categories USING (staffid)
JOIN categories USING (categoryid)
WHERE title = 'Professor'
GROUP BY categorydescription
HAVING count(staffid) < 3;
```

This query __does list__ subject categories with zero professors:
```sql
SELECT categorydescription, (
	SELECT count(staffid)
	FROM faculty
	JOIN faculty_categories USING (staffid)
	JOIN categories AS c2 USING (categoryid)
	WHERE c2.categoryid = categories.categoryid
	AND title = 'Professor'
) AS ProfCount
FROM categories
WHERE (
	SELECT count(staffid)
	FROM faculty
	JOIN faculty_categories USING (staffid)
	JOIN categories AS c3 USING (categoryid)
	WHERE c3.categoryid = categories.categoryid
	AND title = 'Professor'
) < 3;
```

Sample Statements
-----------------

### Sales Orders Database

List for each customer and order date the customer's full name and the total cost of items ordered that is greater than $1,000.
```sql
SELECT concat(custfirstname, ' ', custlastname) AS FullName, orderdate, sum(quotedprice * quantityordered) AS TotalCost
FROM customers
JOIN orders USING (customerid)
JOIN order_details USING (ordernumber)
GROUP BY custfirstname, custlastname, orderdate
HAVING sum(quotedprice * quantityordered) > 1000;
```

### Entertainment Agency Database

Which agents booked more than $3,000 worth of business in December 2017?
```sql
SELECT agtfirstname, agtlastname, sum(contractprice) AS TotalBooked
FROM agents
JOIN engagements USING (agentid)
WHERE startdate BETWEEN '2017-12-01' AND '2017-12-31'
GROUP BY agtfirstname, agtlastname
HAVING sum(contractprice) > 3000;
```

### School Scheduling Database

For completed classes, list by category and student the category name, the student name, and the student's average grade of all classes taken in that category for those students who have an average higher than 90.
```sql
SELECT categorydescription, studfirstname, studlastname, avg(grade) AS AverageGrade
FROM classes
JOIN subjects USING (subjectid)
JOIN categories USING (categoryid)
JOIN student_schedules USING (classid)
JOIN students USING (studentid)
JOIN student_class_status USING (classstatus)
WHERE classstatusdescription = 'Completed'
GROUP BY categorydescription, studfirstname, studlastname
HAVING avg(grade) > 90;
```

List each staff member and the count of classes each is scheduled to teach for those staff membes who teach at least one but fewer than three classes.
```sql
SELECT stffirstname, stflastname, count(classid) AS ClassCount
FROM staff
JOIN faculty_classes USING (staffid)
GROUP BY stffirstname, stflastname
HAVING count(classid) < 3;
```

### Bowling League Database

List the bowlers whose highest raw scores are more than 20 pins higher than their current averages.
```sql
SELECT bowlerlastname, bowlerfirstname, 
avg(rawscore) AS CurrentAvg, max(rawscore) AS HighScore
FROM bowlers
JOIN bowler_scores USING (bowlerid)
GROUP BY bowlerlastname, bowlerfirstname
HAVING max(rawscore) - avg(rawscore) > 20;
```

### Recipes Database

List the recipes that contain both beef and garlic.
```sql
SELECT recipetitle
FROM recipes
JOIN recipe_ingredients USING (recipeid)
JOIN ingredients USING (ingredientid)
WHERE ingredientname IN ('Beef', 'Garlic')
GROUP BY recipetitle
HAVING count(*) = 2;
```

