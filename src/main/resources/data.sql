INSERT INTO PRODUCT (PRODUCT_NAME, MANUFACTURING_DATE, MANUFACTURING_TIME, DUE_DATE)
VALUES ('lela', 'lala', 'lala', 'lala'),
       ('LJH', 'UKHF', 'JGFF', 'JFGFJ'),
       ('l9la', 'l0la', 'lal3', 'lal8');

--------------------------------------------------------------------------------
INSERT INTO WAREHOUSE (NAME, ADDRESS, SIZE)
VALUES ('MELI', 'RUA HUM', '2'),
       ('MERCADO', 'RUA TRES', '7');

--------------------------------------------------------------------------------
INSERT INTO REPRESENTATIVE (NAME, CPF, WAREHOUSE_ID)
VALUES ('SILAS', '789.456.789.45', 1),
       ('IVO', '321.456.789.45', 2);

--------------------------------------------------------------------------------
INSERT INTO BATCHSTOCK(ID, BATCH_NUMBER, CURRENT_QUALITY,  CURRENT_TEMPRATURE,  INITIAL_QUALITY,  MINIMUM_TEMPRATURE)
VALUES (1, '23', '56', '88', '99', '98'),
       (2, '23', '56', '88', '99', '98');

--------------------------------------------------------------------------------
INSERT INTO BATCHSTOCKITEMS (ID, QUANTITY, BATCHSTOCK_ID)
VALUES (1, 1, 1),
       (2, 2, 2);

--------------------------------------------------------------------------------

INSERT INTO INBOUNDORDER ( ORDER_DATE, REPRESENTATIVE)
VALUES ( CURRENT_DATE, 1),
       ( CURRENT_DATE, 1);

--------------------------------------------------------------------------------
