DROP TABLE IF EXISTS `ast_CommunicationType_M`;

CREATE TABLE `ast_CommunicationType_M` ( `commType` VARCHAR(64) NOT NULL, `commTypeName` VARCHAR(128) NOT NULL, `commTypeDescription` VARCHAR(256) NULL DEFAULT NULL, `commGroupId` VARCHAR(64) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`commType`));

