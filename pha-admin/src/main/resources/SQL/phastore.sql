SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Cơ sở dữ liệu: `phastore`
--

CREATE DATABASE IF NOT EXISTS `phastore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci;
USE `phastore`;
-- --------------------------------------------------------

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `generateUserId` () RETURNS VARCHAR(11) CHARSET utf8mb4 BEGIN
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
    SET @seq = (SELECT CAST(RIGHT(@maxUserId,3) AS int)) + 1;
    SET @seq = (SELECT LPAD(@seq, 3, 0));
    SET @dayMonthYear = (SELECT SUBSTRING(@maxUserId, 1, 8));
    RETURN (SELECT CONCAT(@dayMonthYear, @seq));
  END IF;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `generateUserName` (`firstName` CHAR(1), `lastName` VARCHAR(255)) RETURNS VARCHAR(255) CHARSET utf8mb4 BEGIN
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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `common`
--

CREATE TABLE `common` (
  `CommonCode` varchar(9) COLLATE utf8mb4_german2_ci NOT NULL,
  `SequenceNo` int(3) NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` double NOT NULL DEFAULT '1',
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `CreatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Đang đổ dữ liệu cho bảng `common`
--

INSERT INTO `common` (`CommonCode`, `SequenceNo`, `Name`, `Enabled`, `CreatedBy`, `CreatedAt`) VALUES
('1334', 1, 'Pass', 0, 'MThien', '2021-06-24 10:41:27'),
('1334', 2, 'Fail', 1, 'MThien', '2021-06-24 10:41:27'),
('ROLE_USER', 1, 'Seasonal Worker', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 2, 'Accounting Manager', 1, 'xphung', '2021-07-14 12:36:15'),
('ROLE_USER', 3, 'Official Staff', 1, 'xphung', '2021-07-14 12:36:15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `label`
--

CREATE TABLE `label` (
  `LabelCode` varchar(16) COLLATE utf8mb4_german2_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` double NOT NULL DEFAULT '1',
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `CreatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Đang đổ dữ liệu cho bảng `label`
--

INSERT INTO `label` (`LabelCode`, `Name`, `Enabled`, `CreatedBy`, `CreatedAt`) VALUES
('C001', 'Name', 1, 'MThien', '2021-06-28 01:30:23'),
('C002', 'Description', 1, 'MThien', '2021-06-28 01:30:23'),
('C003', 'Email', 1, 'MThien', '2021-06-28 01:31:15'),
('C004', 'Birthday', 1, 'MThien', '2021-06-28 01:31:15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `message`
--

CREATE TABLE `message` (
  `MessageCode` varchar(16) COLLATE utf8mb4_german2_ci NOT NULL,
  `Text` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) NOT NULL DEFAULT '1',
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `CreatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Đang đổ dữ liệu cho bảng `message`
--

INSERT INTO `message` (`MessageCode`, `Text`, `Enabled`, `CreatedBy`, `CreatedAt`) VALUES
('MSG0001', 'Quá trình lưu gặp lỗi.', 1, 'MThien', '2021-06-28 02:02:22'),
('MSG0002', 'Lưu thành công', 1, 'MThien', '2021-06-28 02:02:22'),
('MSG0003', 'Phone Number entered is invalid.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0004', 'Phone Number is required attribute when adding an User Information.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0005', 'First Name is required attribute when adding/updating an User Information.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0006', 'Last Name is required attribute when adding/updating an User Information.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0007', 'You entered a date which is in the future.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0008', 'User with entered Email Id: {0} already exists.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0009', 'Email entered is invalid.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0010', 'User with entered user name: {0} already exists.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0011', 'No user found with user id: {0}.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0012', 'Category Id is required attribute when adding a Category Information.', 1, 'xphung', '2021-07-13 19:50:25'),
('MSG0013', 'Category with entered Category Id: {0} already exists.', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0014', 'Category Name is required attribute when adding/updating a Category Information.', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0015', 'No category found with category id: {0}.', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0016', 'No Data Found for Role Name : {0}.', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0017', 'Role Name is required attribute when adding an User Information.', 1, 'xphung', '2021-07-13 19:53:55'),
('MSG0018', 'Date of Birth is required attribute when adding an User Information.', 1, 'xphung', '2021-07-13 21:02:17'),
('MSG0019', 'No Data Found for Sequence No. of Role: {0}.', 1, 'xphung', '2021-07-14 17:18:22');

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `UserId` varchar(11) COLLATE utf8mb4_german2_ci NOT NULL,
  `RoleCode` int(3) NOT NULL,
  `FirstName` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `LastName` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `UserName` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `DateOfBirth` date NOT NULL,
  `PhoneNumber` varchar(10) COLLATE utf8mb4_german2_ci NOT NULL,
  `Address` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `Password` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) DEFAULT 1,
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedAt` datetime NOT NULL,
  `UpdatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`UserId`, `RoleCode`, `FirstName`, `LastName`, `UserName`, `Email`, `DateOfBirth`, `PhoneNumber`, `Address`, `Password`, `Enabled`, `CreatedBy`, `CreatedAt`, `UpdatedBy`, `UpdatedAt`) VALUES
('20210715001', 2, 'Bom', 'Park', 'ParkBomBom', 'parkbomnom@gmail.com', '1984-03-24', '0794466213', 'ABC DEF', '$2a$10$cWrlrvHSJsL1EGNBDbS/l.9a/OZLoAr3eZ80jhjuAqiupdem00lK2', 1, NULL, '2021-07-15 20:09:52', NULL, '2021-07-15 20:32:58'),
('20210715002', 1, 'Jennie', 'Kim', 'PHA-US-JKIM1', 'jenniekim@gmail.com', '1996-01-16', '0901671526', 'GHI KLM', '$2a$10$af4KztAZzIabJgZUSctdou.VLFNCf5nllvxkY9U4Sge5ynVnvn6lS', 1, NULL, '2021-07-15 20:10:38', NULL, '2021-07-15 20:40:55'),
('20210715003', 1, 'Chae Young', 'Park', 'PHA-US-YPARK', 'chaeyoungpark@gmail.com', '1997-02-11', '0801457895', 'ABC DEF', '$2a$10$YQlpkA0UnAT2TFOsRR07Oe1hsOmaPoc2L20fRTdAKbOt8XvqdUfHO', 1, NULL, '2021-07-15 20:10:57', NULL, '2021-07-15 20:53:12'),
('20210715004', 3, 'Lalisa', 'Manoban', 'PHA-US-LMANOBAN', 'lalisamanoban@gmail.com', '1997-03-27', '0804577895', 'ABC DEF', '$2a$10$f4qvh15L51S8D.ZFE32KPuNbzE1px6pQWYFJuUT2Ks08IpN3.x8UC', 1, NULL, '2021-07-15 20:11:22', NULL, NULL);

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `CategoryId` varchar(20) COLLATE utf8mb4_german2_ci NOT NULL,
  `CategoryName` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `Description` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `UpdatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `Enabled`, `Description`, `CreatedBy`, `CreatedAt`, `UpdatedBy`, `UpdatedAt`) VALUES
('CAT-01', 'Tri gut, xuong khop', 1, 'Tri gut, xuong khop', NULL, '2021-07-06 17:03:50', NULL, NULL),
('CAT-02', 'Tri benh phu khoa', 1, 'Tri benh phu khoa', NULL, '2021-07-06 17:05:11', NULL, '2021-07-06 17:32:33'),
('CAT-03', 'Tri benh da lieu', 1, '', NULL, '2021-07-06 17:11:02', NULL, '2021-07-06 17:30:20');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `common`
--
ALTER TABLE `common`
  ADD PRIMARY KEY (`CommonCode`,`SequenceNo`),
  ADD KEY `CommonCode` (`CommonCode`);

--
-- Chỉ mục cho bảng `label`
--
ALTER TABLE `label`
  ADD PRIMARY KEY (`LabelCode`),
  ADD KEY `Name` (`Name`(191));

--
-- Chỉ mục cho bảng `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`MessageCode`),
  ADD KEY `Text` (`Text`(191));
  
--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

-- --------------------------------------------------------

--
COMMIT;
-- --------------------------------------------------------