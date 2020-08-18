 CREATE DATABASE /*!32312 IF NOT EXISTS*/`DKExample70G` /*!40100 DEFAULT CHARACTER SET utf8 */;
 
 USE `DKExample70G`;
 
CREATE TABLE IF NOT EXISTS `PoslovniPartner` (
 `SifraPartnera` int (11) NOT NULL   , 
 `NazivPartnera` varchar (30)    DEFAULT NULL, 
 `Adresa` varchar (50)    DEFAULT NULL, 
 `PIB` varchar (15)    DEFAULT NULL, 
 `DatumOsnivanja` date       DEFAULT NULL, 
 `UkupniIznos` double       DEFAULT 0, 
 `SifraGrada` int (4)    DEFAULT 0, 
  PRIMARY KEY (`SifraPartnera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DELIMITER $$
DROP PROCEDURE IF EXISTS `upgrade_database` $$
CREATE PROCEDURE upgrade_database()
BEGIN
 DECLARE _count INT;
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'SifraPartnera'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `SifraPartnera` int (11) NOT NULL   ; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'NazivPartnera'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `NazivPartnera` varchar (30)    DEFAULT NULL; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'Adresa'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `Adresa` varchar (50)    DEFAULT NULL; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'PIB'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `PIB` varchar (15)    DEFAULT NULL; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'DatumOsnivanja'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `DatumOsnivanja` date       DEFAULT NULL; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'UkupniIznos'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `UkupniIznos` double       DEFAULT 0; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'PoslovniPartner' AND COLUMN_NAME = 'SifraGrada'); 
 IF _count = 0 THEN  ALTER TABLE PoslovniPartner ADD COLUMN  `SifraGrada` int (4)    DEFAULT 0; END IF;   
 ALTER TABLE PoslovniPartner DROP PRIMARY KEY, ADD PRIMARY KEY (`SifraPartnera`);
 
  
END $$
DELIMITER ;
 
CALL upgrade_database();
 
 
 
  CREATE DATABASE /*!32312 IF NOT EXISTS*/`DKExample70G` /*!40100 DEFAULT CHARACTER SET utf8 */;
 
 USE `DKExample70G`;
 
CREATE TABLE IF NOT EXISTS `Grad` (
 `SifraGrada` int (11) NOT NULL   , 
 `NazivGrada` varchar (50)    DEFAULT NULL, 
  PRIMARY KEY (`SifraGrada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DELIMITER $$
DROP PROCEDURE IF EXISTS `upgrade_database` $$
CREATE PROCEDURE upgrade_database()
BEGIN
 DECLARE _count INT;
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Grad' AND COLUMN_NAME = 'SifraGrada'); 
 IF _count = 0 THEN  ALTER TABLE Grad ADD COLUMN  `SifraGrada` int (11) NOT NULL   ; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Grad' AND COLUMN_NAME = 'NazivGrada'); 
 IF _count = 0 THEN  ALTER TABLE Grad ADD COLUMN  `NazivGrada` varchar (50)    DEFAULT NULL; END IF;   
 ALTER TABLE Grad DROP PRIMARY KEY, ADD PRIMARY KEY (`SifraGrada`);
 
  
END $$
DELIMITER ;
 
CALL upgrade_database();
 
 
 
  CREATE DATABASE /*!32312 IF NOT EXISTS*/`DKExample70G` /*!40100 DEFAULT CHARACTER SET utf8 */;
 
 USE `DKExample70G`;
 
CREATE TABLE IF NOT EXISTS `Proizvod` (
 `SifraProizvoda` int (11) NOT NULL   , 
 `NazivProizvoda` varchar (50)    DEFAULT NULL, 
  PRIMARY KEY (`SifraProizvoda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DELIMITER $$
DROP PROCEDURE IF EXISTS `upgrade_database` $$
CREATE PROCEDURE upgrade_database()
BEGIN
 DECLARE _count INT;
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Proizvod' AND COLUMN_NAME = 'SifraProizvoda'); 
 IF _count = 0 THEN  ALTER TABLE Proizvod ADD COLUMN  `SifraProizvoda` int (11) NOT NULL   ; END IF;   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Proizvod' AND COLUMN_NAME = 'NazivProizvoda'); 
 IF _count = 0 THEN  ALTER TABLE Proizvod ADD COLUMN  `NazivProizvoda` varchar (50)    DEFAULT NULL; END IF;   
 ALTER TABLE Proizvod DROP PRIMARY KEY, ADD PRIMARY KEY (`SifraProizvoda`);
 
  
END $$
DELIMITER ;
 
CALL upgrade_database();
 
 
 
  CREATE DATABASE /*!32312 IF NOT EXISTS*/`DKExample70G` /*!40100 DEFAULT CHARACTER SET utf8 */;
 
 USE `DKExample70G`;
 
CREATE TABLE IF NOT EXISTS `Counter` (
 `TableName` varchar(100)  ,
 `Counter` int  DEFAULT 0,
 PRIMARY KEY (`TableName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
 
DELIMITER $$
DROP PROCEDURE IF EXISTS `upgrade_database_counter` $$
CREATE PROCEDURE upgrade_database_counter()
BEGIN
 DECLARE _count INT;
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS  
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Counter' AND COLUMN_NAME = 'TableName'); 
 IF _count = 0 THEN  ALTER TABLE Counter ADD COLUMN  `TableName` varchar(100)  ; END IF; 
   
 SET _count = (  SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS  
 WHERE   TABLE_SCHEMA = 'DKExample70G' AND TABLE_NAME = 'Counter' AND COLUMN_NAME = 'Counter'); 
 IF _count = 0 THEN  ALTER TABLE Counter ADD COLUMN  `Counter` int  DEFAULT 0; END IF;
 
 SET _count = (  SELECT COUNT(*) FROM Counter Where TableName = 'PoslovniPartner'); IF _count = 0 THEN   insert  into `counter`(`TableName`,`Counter`) values  ('PoslovniPartner',0);  END IF;  
 SET _count = (  SELECT COUNT(*) FROM Counter Where TableName = 'Grad'); IF _count = 0 THEN   insert  into `counter`(`TableName`,`Counter`) values  ('Grad',0);  END IF;  
 SET _count = (  SELECT COUNT(*) FROM Counter Where TableName = 'Proizvod'); IF _count = 0 THEN   insert  into `counter`(`TableName`,`Counter`) values  ('Proizvod',0);  END IF;  
 
   
END $$
DELIMITER ;
 
CALL upgrade_database_counter();