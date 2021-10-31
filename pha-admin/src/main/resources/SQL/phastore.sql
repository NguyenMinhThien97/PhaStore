SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phastore`
--

CREATE DATABASE IF NOT EXISTS `phastore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci;
USE `phastore`;
-- --------------------------------------------------------

DROP function IF EXISTS `generateUserId`;

DROP function IF EXISTS `generateUserName`;

DELIMITER $$
CREATE FUNCTION `generateUserId`() RETURNS varchar(11) CHARSET utf8mb4
    READS SQL DATA
    DETERMINISTIC
BEGIN
SET @today = (SELECT CURRENT_DATE());
 SET @day = (SELECT LPAD(DAYOFMONTH(@today), 2, 0));
 SET @month = (SELECT LPAD(MONTH(@today), 2, 0));
 SET @year = (SELECT YEAR(@today));
 SET @userId = (SELECT CONCAT(@year, @month, @day, LPAD(1, 3, 0)));
 SET @maxUserId = (SELECT MAX(UserId) FROM USER);
 SET @flag = (SELECT ISNULL(NULLIF(@maxUserId,'')));
 IF @flag = 1 THEN
   RETURN @userId;
 ELSE
   SET @flag = (SELECT @userId > @maxUserId);
   IF @flag = 1 THEN
     RETURN @userId;
   ELSE
     SET @seq = (SELECT RIGHT(@maxUserId,3)) + 1;
     SET @seq = (SELECT LPAD(@seq, 3, 0));
     SET @dayMonthYear = (SELECT SUBSTRING(@maxUserId, 1, 8));
     RETURN (SELECT CONCAT(@dayMonthYear, @seq));
   END IF;
 END IF;
END$$

DELIMITER ;

DELIMITER $$
CREATE FUNCTION `generateUserName` (`firstName` CHAR(1), `lastName` VARCHAR(255)) RETURNS VARCHAR(255) CHARSET utf8mb4
READS SQL DATA
    DETERMINISTIC
BEGIN
 SET @count = 0;
 SET @userName = (SELECT CONCAT('PHA-US-', firstName, lastName));
 label1: LOOP
 SET @isExitedUserName = (SELECT UserName FROM User WHERE UserName = @userName);
      IF ISNULL(NULLIF(@isExitedUserName,'')) = 0 THEN
      SET @count = @count + 1;
      SET @userName = (SELECT CONCAT('PHA-US-', firstName, lastName, @count));
      SET @isExitedUserName = @userName;
        ITERATE label1;
      END IF;
      LEAVE label1;
    END LOOP label1;
 RETURN (SELECT UPPER(@userName));
 END$$

DELIMITER ;

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `CategoryId` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CategoryName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CategoryId`, `CategoryName`, `Enabled`, `Description`, `CreatedBy`, `CreatedAt`, `UpdatedBy`, `UpdatedAt`) VALUES
('CAT-01', 'Tri gut, xuong khop', 1, 'Tri gut, xuong khop', NULL, '2021-07-06 17:03:50', NULL, NULL),
('CAT-02', 'Tri benh phu khoa', 1, 'Tri benh phu khoa', NULL, '2021-07-06 17:05:11', NULL, '2021-07-06 17:32:33'),
('CAT-03', 'Tri benh da lieu', 1, '', NULL, '2021-07-06 17:11:02', NULL, '2021-07-06 17:30:20'),
('string', 'string', 1, 'string', NULL, '2021-09-07 23:25:47', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `COMMON`
--

CREATE TABLE `COMMON` (
  `COMMON_CODE` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `SEQUENCE_NO` int NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` double NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `COMMON`
--

INSERT INTO `COMMON` (`COMMON_CODE`, `SEQUENCE_NO`, `NAME`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
('1334', 1, 'Pass', 'en', 0, 'MThien', '2021-06-24 10:41:27'),
('1334', 2, 'Fail', 'en', 1, 'MThien', '2021-06-24 10:41:27'),
('ROLE_USER', 1, 'Seasonal Worker', 'en', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 2, 'Accounting Manager', 'en', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 3, 'Official Staff', 'en', 1, 'xphung', '2021-07-14 12:36:15');

-- --------------------------------------------------------

--
-- Table structure for table `COMPANY`
--

CREATE TABLE `COMPANY` (
  `ID_COMPANY` bigint NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `TAX_CODE` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CREATED_BY` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_BY` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `COMPANY`
--

INSERT INTO `COMPANY` (`ID_COMPANY`, `CREATED_AT`, `CREATED_BY`, `DESCRIPTION`, `NAME`, `TAX_CODE`, `UPDATED_AT`, `UPDATED_BY`) VALUES
(14, '2021-10-20 21:13:46', '20210715001', 'COMPANY_1', 'COMPANY_1', 'COMPANY_1_TAX', NULL, NULL),
(15, '2021-10-20 21:20:32', '20210715001', NULL, 'COMPANY_2', 'COMPANY_2_TAX', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `LABEL`
--

CREATE TABLE `LABEL` (
  `LABEL_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` double NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `LABEL`
--

INSERT INTO `LABEL` (`LABEL_CODE`, `NAME`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
('C001', 'Name', 'en', 1, 'MThien', '2021-06-28 01:30:23'),
('C002', 'Description', 'en', 1, 'MThien', '2021-06-28 01:30:23'),
('C003', 'Email', 'en', 1, 'MThien', '2021-06-28 01:31:15'),
('C004', 'Birthday', 'en', 1, 'MThien', '2021-06-28 01:31:15');

-- --------------------------------------------------------

--
-- Table structure for table `MESSAGE`
--

CREATE TABLE `MESSAGE` (
  `MESSAGE_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `TEXT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` tinyint(1) NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `MESSAGE`
--

INSERT INTO `MESSAGE` (`MESSAGE_CODE`, `TEXT`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
('MSG0001', 'Quá trình lưu gặp lỗi.', 'vn', 1, 'MThien', '2021-06-28 02:02:22'),
('MSG0002', 'Lưu thành công', 'vn', 1, 'MThien', '2021-06-28 02:02:22'),
('MSG0003', 'Phone Number entered is invalid.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0004', 'Phone Number is required attribute when adding an User Information.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0005', 'First Name is required attribute when adding/updating an User Information.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0006', 'Last Name is required attribute when adding/updating an User Information.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0007', 'You entered a date which is in the future.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0008', 'User with entered Email Id: {0} already exists.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0009', 'Email entered is invalid.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0010', 'User with entered user name: {0} already exists.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0011', 'No user found with user id: {0}.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0012', 'Category Id is required attribute when adding a Category Information.', 'en', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0013', 'Category with entered Category Id: {0} already exists.', 'en', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0014', 'Category Name is required attribute when adding/updating a Category Information.', 'en', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0015', 'No category found with category id: {0}.', 'en', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0016', 'No Data Found for Role Name : {0}.', 'en', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0017', 'Role Name is required attribute when adding an User Information.', 'en', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0018', 'Date of Birth is required attribute when adding an User Information.', 'en', 1, 'xphung', '2021-07-13 21:02:17'),
('MSG0019', 'No Data Found for Sequence No. of Role: {0}.', 'en', 1, 'xphung', '2021-07-14 17:18:22'),
('MSG0020', '{0} is mandatory.', 'en', 1, 'MThien', '2021-09-08 02:02:22'),
('MSG0021', '{0} must be greater than or equal to {1} and less than or equal to {2} characters.', 'en', 1, 'MThien', '2021-09-08 02:02:22'),
('MSG0022', 'The data does not exist', 'en', 1, 'xphung', '2021-09-12 11:02:37'),
('MSG0023', '{0} must not be more than {1} characters', 'en', 1, 'xphung', '2021-10-20 15:12:23');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `UserId` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `RoleCode` int NOT NULL,
  `FirstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `UserName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `DateOfBirth` date NOT NULL,
  `PhoneNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) DEFAULT '1',
  `CreatedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedAt` datetime NOT NULL,
  `UpdatedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserId`, `RoleCode`, `FirstName`, `LastName`, `UserName`, `Email`, `DateOfBirth`, `PhoneNumber`, `Address`, `Password`, `Enabled`, `CreatedBy`, `CreatedAt`, `UpdatedBy`, `UpdatedAt`) VALUES
('20210715001', 2, 'Bom', 'Park', 'ParkBomBom', 'parkbomnom@gmail.com', '1984-03-24', '0794466213', 'ABC DEF', '$2a$10$cWrlrvHSJsL1EGNBDbS/l.9a/OZLoAr3eZ80jhjuAqiupdem00lK2', 1, NULL, '2021-07-15 20:09:52', NULL, '2021-07-15 20:32:58'),
('20210715002', 1, 'Jennie', 'Kim', 'PHA-US-JKIM1', 'jenniekim@gmail.com', '1996-01-16', '0901671526', 'GHI KLM', '$2a$10$af4KztAZzIabJgZUSctdou.VLFNCf5nllvxkY9U4Sge5ynVnvn6lS', 1, NULL, '2021-07-15 20:10:38', NULL, '2021-07-15 20:40:55'),
('20210715003', 1, 'Chae Young', 'Park', 'PHA-US-YPARK', 'chaeyoungpark@gmail.com', '1997-02-11', '0801457895', 'ABC DEF', '$2a$10$YQlpkA0UnAT2TFOsRR07Oe1hsOmaPoc2L20fRTdAKbOt8XvqdUfHO', 1, NULL, '2021-07-15 20:10:57', NULL, '2021-07-15 20:53:12'),
('20210715004', 3, 'Lalisa', 'Manoban', 'PHA-US-LMANOBAN', 'lalisamanoban@gmail.com', '1997-03-27', '0804577895', 'ABC DEF', '$2a$10$f4qvh15L51S8D.ZFE32KPuNbzE1px6pQWYFJuUT2Ks08IpN3.x8UC', 1, NULL, '2021-07-15 20:11:22', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indexes for table `COMMON`
--
ALTER TABLE `COMMON`
  ADD PRIMARY KEY (`COMMON_CODE`,`SEQUENCE_NO`),
  ADD KEY `CommonCode` (`COMMON_CODE`);

--
-- Indexes for table `COMPANY`
--
ALTER TABLE `COMPANY`
  ADD PRIMARY KEY (`ID_COMPANY`);

--
-- Indexes for table `LABEL`
--
ALTER TABLE `LABEL`
  ADD PRIMARY KEY (`LABEL_CODE`),
  ADD KEY `Name` (`NAME`(191));

--
-- Indexes for table `MESSAGE`
--
ALTER TABLE `MESSAGE`
  ADD PRIMARY KEY (`MESSAGE_CODE`),
  ADD KEY `Text` (`TEXT`(191));

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserId`);

CREATE TABLE `client` (
  `ID_CLIENT` varchar(11) NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `CREATED_AT` date NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(120) DEFAULT NULL,
  `ENABLED` bit(1) DEFAULT NULL,
  `FIRST_NAME` varchar(80) NOT NULL,
  `ID_COMPANY` bigint(20) DEFAULT NULL,
  `LAST_NAME` varchar(80) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PHONE_NUMBER` varchar(12) NOT NULL,
  `UPDATED_AT` date DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_CLIENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
