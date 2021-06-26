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
  `IdCommon` int(11) NOT NULL,
  `CommonCode` varchar(9) COLLATE utf8mb4_german2_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_german2_ci NOT NULL,
  `Enabled` double NOT NULL,
  `CreatedBy` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CreatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

--
-- Chỉ mục cho bảng `common`
--
ALTER TABLE `common`
  ADD PRIMARY KEY (`IdCommon`);


--
-- AUTO_INCREMENT cho bảng `common`
--
ALTER TABLE `common`
  MODIFY `IdCommon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;