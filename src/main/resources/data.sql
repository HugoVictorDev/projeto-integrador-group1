INSERT INTO PRODUCT (PRODUCT_NAME, MANUFACTURING_DATE, MANUFACTURING_TIME, DUE_DATE)
VALUES ('lela', 'lala', 'lala', 'lala'),
       ( 'LJH', 'UKHF', 'JGFF', 'JFGFJ'),
       ('luli', 'l9la', 'l0la', 'lal3');

--------------------------------------------------------------------------------
INSERT INTO WAREHOUSE ( NAME, ADDRESS, SIZE)
VALUES ('MELI', 'RUA HUM', '2'),
       ('CITY', 'RUA DOIS', '2'),
       ('MERCADO', 'RUA TRES', '7');

--------------------------------------------------------------------------------
INSERT INTO SECTION (MINIMUM_TEMPRATURE, STOCK, STOCK_TYPE, BATCH_STOCK, WAREHOUSE_ID)
VALUES ('23', '56', '88', '99', 1),
       ('23', '56', '88', '99', 1),
       ('23', '56', '88', '99', 1);

--------------------------------------------------------------------------------

--INSERT INTO REPRESENTATIVE (NAME, CPF, WAREHOUSE_ID)
--VALUES ('SILAS', '789.456.789.45', 1),
--       ('IVO', '321.456.789.45', 2);

--------------------------------------------------------------------------------
INSERT INTO BATCHSTOCK(ID, BATCH_NUMBER, CURRENT_QUALITY,  CURRENT_TEMPRATURE,  INITIAL_QUALITY,  MINIMUM_TEMPRATURE)
VALUES (1, '23', '56', '88', '99', '98'),
       (2, '23', '56', '88', '99', '98');

--------------------------------------------------------------------------------
INSERT INTO BATCHSTOCKITEM (ID, QUANTITY, BATCHSTOCK_ID)
VALUES (1, 1, 1),
       (2, 2, 2);

--------------------------------------------------------------------------------
INSERT INTO SELLERS (SELLER_ID, CPF, NAME )
VALUES (1, 'CAIO', '789.456.789.45');

--------------------------------------------------------------------------------
INSERT INTO PRODUCT (PRODUCT_NAME, MANUFACTURING_DATE, MANUFACTURING_TIME, DUE_DATE, BATCHSTOCKITEM_ID, SELLER_SELLER_ID )
VALUES ('lela', 'lala', 'lala', 'lala',1,1 )

--------------------------------------------------------------------------------
