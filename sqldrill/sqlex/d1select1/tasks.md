База Данных "Компьютерная фирма"
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


