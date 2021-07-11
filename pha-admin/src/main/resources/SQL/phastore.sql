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
('1334', 2, 'Fail', 1, 'MThien', '2021-06-24 10:41:27');

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
('MSG0002', 'Lưu thành công', 1, 'MThien', '2021-06-28 02:02:22');

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
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `common`
--
ALTER TABLE `common`
  MODIFY `IdCommon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryId` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `CategoryName` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `Description` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `UpdatedBy` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `Enabled`, `Description`, `CreatedBy`, `CreatedAt`, `UpdatedBy`, `UpdatedAt`) VALUES
('CAT-01', 'Tri gut, xuong khop', 1, 'Tri gut, xuong khop', NULL, '2021-07-06 17:03:50', NULL, NULL),
('CAT-02', 'Tri benh phu khoa', 1, 'Tri benh phu khoa', NULL, '2021-07-06 17:05:11', NULL, '2021-07-06 17:32:33'),
('CAT-03', 'Tri benh da lieu', 1, '', NULL, '2021-07-06 17:11:02', NULL, '2021-07-06 17:30:20');

-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
COMMIT;
-- --------------------------------------------------------