SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phastore`
--

-- --------------------------------------------------------

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
-- Table structure for table `Common`
--

CREATE TABLE `Common` (
  `COMMON_CODE` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `SEQUENCE_NO` int NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` double NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `Common`
--

INSERT INTO `Common` (`COMMON_CODE`, `SEQUENCE_NO`, `NAME`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
('1334', 1, 'Pass', 'en', 0, 'MThien', '2021-06-24 10:41:27'),
('1334', 2, 'Fail', 'en', 1, 'MThien', '2021-06-24 10:41:27'),
('ROLE_USER', 1, 'Seasonal Worker', 'en', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 2, 'Accounting Manager', 'en', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 3, 'Official Staff', 'en', 1, 'xphung', '2021-07-14 12:36:15');

-- --------------------------------------------------------

--
-- Table structure for table `Company`
--

CREATE TABLE `Company` (
  `ID_COMPANY` bigint NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `TAX_CODE` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_BY` varchar(50) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Label`
--

CREATE TABLE `Label` (
  `LABEL_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` double NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `Label`
--

INSERT INTO `Label` (`LABEL_CODE`, `NAME`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
('C001', 'Name', 'en', 1, 'MThien', '2021-06-28 01:30:23'),
('C002', 'Description', 'en', 1, 'MThien', '2021-06-28 01:30:23'),
('C003', 'Email', 'en', 1, 'MThien', '2021-06-28 01:31:15'),
('C004', 'Birthday', 'en', 1, 'MThien', '2021-06-28 01:31:15');

-- --------------------------------------------------------

--
-- Table structure for table `Message`
--

CREATE TABLE `Message` (
  `MESSAGE_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `TEXT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `LANG` varchar(4) COLLATE utf8mb4_german2_ci NOT NULL,
  `ENABLED` tinyint(1) NOT NULL DEFAULT '1',
  `CREATED_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `Message`
--

INSERT INTO `Message` (`MESSAGE_CODE`, `TEXT`, `LANG`, `ENABLED`, `CREATED_BY`, `CREATED_AT`) VALUES
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
('MSG0021', '{0} must be more than {1} characters.', 'en', 1, 'MThien', '2021-09-08 02:02:22');

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
-- Indexes for table `Common`
--
ALTER TABLE `Common`
  ADD PRIMARY KEY (`COMMON_CODE`,`SEQUENCE_NO`),
  ADD KEY `CommonCode` (`COMMON_CODE`);

--
-- Indexes for table `Company`
--
ALTER TABLE `Company`
  ADD PRIMARY KEY (`ID_COMPANY`);

--
-- Indexes for table `Label`
--
ALTER TABLE `Label`
  ADD PRIMARY KEY (`LABEL_CODE`),
  ADD KEY `Name` (`NAME`(191));

--
-- Indexes for table `Message`
--
ALTER TABLE `Message`
  ADD PRIMARY KEY (`MESSAGE_CODE`),
  ADD KEY `Text` (`TEXT`(191));

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
