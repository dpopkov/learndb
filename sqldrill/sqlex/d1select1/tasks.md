#### Using DBх ["Компьютерная фирма"]((db-scheme.md#compfirm)
-------------------------------

Задание: 1  
Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd
```sql
SELECT model, speed, hd FROM pc WHERE price < 500;
```

Задание: 2  
Найдите производителей принтеров. Вывести: maker.
```sql
SELECT DISTINCT maker FROM product WHERE type = 'Printer';
```

Задание: 3   
Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол. 
```sql
SELECT model, ram, screen FROM laptop WHERE price > 1000;
```

Задание: 4   
Найдите все записи таблицы Printer для цветных принтеров.
```sql
SELECT * FROM printer WHERE color = 'y';
```

Задание: 5  
Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.
```sql
SELECT model, speed, hd FROM pc WHERE cd IN ('12x', '24x') AND price < 600;
```

Задание: 6  
Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт, найти скорости таких ПК-блокнотов. Вывод: производитель, скорость.
```sql
SELECT DISTINCT maker, speed
FROM product INNER JOIN laptop ON product.model = laptop.model
WHERE hd >= 10;
```

Задание: 7  
Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква).
```sql
SELECT model, price FROM (
    SELECT model, price FROM pc
    UNION
    SELECT model, price FROM laptop
    UNION
    SELECT model, price FROM printer
) AS mp
WHERE model IN (
    SELECT model FROM product
    WHERE maker = 'B'
);
```

Задание: 8  
Найдите производителя, выпускающего ПК, но не ПК-блокноты.
```sql
SELECT m_pc.maker FROM (
    SELECT DISTINCT maker
    FROM product WHERE type = 'PC'
) AS m_pc
LEFT JOIN (
    SELECT DISTINCT maker
    FROM product WHERE type = 'Laptop'
) AS m_la
ON m_pc.maker = m_la.maker
WHERE m_la.maker IS NULL;
```

Задание: 9  
Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
```sql
SELECT DISTINCT maker
FROM pc INNER JOIN product AS p
ON pc.model = p.model
WHERE speed >= 450;
```

Задание: 10  
Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price 
```sql
SELECT model, price FROM printer 
WHERE price = (SELECT max(price) FROM printer);
```

Задание: 11  
Найдите среднюю скорость ПК.
```sql
SELECT avg(speed) FROM pc;
```

Задание: 12  
Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
```sql
SELECT avg(speed) FROM laptop WHERE price > 1000;
```

Задание: 13  
Найдите среднюю скорость ПК, выпущенных производителем A. 
```sql
SELECT avg(speed) FROM pc
INNER JOIN product ON pc.model = product.model
WHERE maker = 'A';
```

#### Using DB ["Корабли"](db-scheme.md#ships)
--------------------

Задание: 14  
Найдите класс, имя и страну для кораблей из таблицы Ships, имеющих не менее 10 орудий.
```sql
SELECT classes.class, name, country
FROM ships INNER JOIN classes ON ships.class = classes.class
WHERE numGuns >= 10;
```

Задание: 15  
Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD 
```sql
SELECT hd FROM pc
GROUP BY hd
HAVING count(hd) >= 2;
```

Задание: 16  
Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.
```sql
SELECT DISTINCT pc2.model, pc1.model, pc1.speed, pc1.ram
FROM pc AS pc1
INNER JOIN pc AS pc2
ON pc1.speed = pc2.speed AND pc1.ram = pc2.ram
WHERE pc1.model < pc2.model;
```

Задание: 17  
Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК.
Вывести: type, model, speed 
```sql
SELECT DISTINCT type, laptop.model, speed
FROM laptop JOIN product ON laptop.model = product.model
WHERE speed < ALL (SELECT speed FROM pc);
```

Задание: 18  
Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price 
```SQL
SELECT DISTINCT maker, price
FROM product INNER JOIN printer 
ON product.model = printer.model
WHERE color = 'y' AND price = (
    SELECT min(price) FROM printer WHERE color = 'y'
);
```

Задание: 19  
Для каждого производителя, имеющего модели в таблице Laptop, найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана. 
```sql
SELECT maker, avg(screen)
FROM product INNER JOIN laptop ON product.model = laptop.model
GROUP BY maker;
```

