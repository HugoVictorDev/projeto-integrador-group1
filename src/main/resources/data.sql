create table produto (productid long primary key auto_increment , productname varchar(250), manufacturingdate varchar (max ), manufacturingtime varchar (max), duedate varchar (max));

INSERT INTO PRODUTO (productname, manufacturingdate, manufacturingtime, duedate) VALUES ('Biscoito', '15-10-2021', '15-10-2021', '15-10-2021'),
                                                                                            ('Nescau', '15-10-2021', '15-10-2021', '15-10-2021'),
--------------------------------------------------------------------------------

INSERT INTO REPRESENTATIVE (NAME, CPF)
VALUES ('IVO', '321.456.789.45');
INSERT INTO REPRESENTATIVE (NAME, CPF)
VALUES ('SILAS', '789.456.789.45'),
       ('IVO', '321.456.789.45');

--------------------------------------------------------------------------------

INSERT INTO inbounderorder (ORDER_NUMBER, ORDER_DATE)
VALUES (1, '8978989');
--------------------------------------------------------------------------------
--INSERT INTO batchstock (batchNumber, CURRENTTEMPERATURE, MINIMUMTEMPERATURE, CURRENTQUALITY)
--VALUES ('asdsad', 'CURRENTTEMPERATURE', 'MINIMUMTEMPERATURE', 'CURRENTQUALITY');
--------------------------------------------------------------------------------

--INSERT INTO batchstockitem ();
--VALUES ('SILAS', '789.456.789.45'),
--       ('IVO', '321.456.789.45');


--------------------------------------------------------------------------------

--INSERT INTO batchstock (batchNumber, CURRENTTEMPERATURE, MINIMUMTEMPERATURE, CURRENTQUALITY)
--VALUES ('BATCHNUMBER', 'CURRENTTEMPERATURE', 'MINIMUMTEMPERATURE', 'CURRENTQUALITY')
--------------------------------------------------------------------------------

--INSERT INTO BATCHSTOCKITEM ();
