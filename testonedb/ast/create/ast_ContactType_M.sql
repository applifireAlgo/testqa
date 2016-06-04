DROP TABLE IF EXISTS `ast_ContactType_M`;

CREATE TABLE `ast_ContactType_M` ( `contactTypeId` VARCHAR(64) NOT NULL, `contactType` VARCHAR(128) NOT NULL, `contactTypeDesc` VARCHAR(256) NULL DEFAULT NULL, `contactTypeIcon` VARCHAR(128) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`contactTypeId`));

