-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 16, 2021 at 05:06 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `miagerepertoire`
--

-- --------------------------------------------------------

--
-- Table structure for table `contacts_users`
--

CREATE TABLE `contacts_users` (
  `id` int(11) NOT NULL,
  `telephone` varchar(200) NOT NULL,
  `nom_prenoms` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sexe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contacts_users`
--

INSERT INTO `contacts_users` (`id`, `telephone`, `nom_prenoms`, `email`, `sexe`) VALUES
(1, '170581312', 'Azize soulé', 'Azizesoulé@gmaïl.com', 'Homme'),
(2, '+225 81524789', 'Grace ouraga', 'Graceouraga@gmïl.com', 'Femme'),
(7, '+225 +225 5647632514', 'DATÉ OURAGA', 'Datéouraga@gmaïl.com', 'HOMME'),
(9, '+225 +225 01457896', 'TITI', 'ZOULIN@gmïl.com', 'HOMME'),
(10, '+225 01055569', 'rouga', 'rouga@.com', 'Homme');

-- --------------------------------------------------------

--
-- Table structure for table `registerlogin`
--

CREATE TABLE `registerlogin` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `registerlogin`
--

INSERT INTO `registerlogin` (`id`, `username`, `email`, `password`) VALUES
(1, 'Dieng', 'Dieng@gmail.com', '12345'),
(2, 'qq', 'qq', 'qq'),
(3, 'qq', 'qq', 'qq'),
(4, 'ff', 'ff', 'ff'),
(5, 'ss', 'ss', 'ss'),
(6, 'dd', 'dd', 'dd'),
(7, 'dd', 'dd', 'dd'),
(8, 'dd', 'ddz', 'dd'),
(9, 'dd', 'ddz', 'dd'),
(10, 'f', 'f', 'f'),
(11, 'sss', 'sss', 'sss'),
(12, 'q', 'q', 'q'),
(13, 'q', 's', 's'),
(14, 's', 's', 's'),
(15, 'ss', 'ss', 'ss'),
(16, 'qq', 'qqq', 'qqq'),
(17, 's', 's', 's'),
(18, 's', 's', 'ss'),
(19, 'qq', 'qq', 'qq'),
(20, '<<', '<<', 'vv'),
(21, 'c', 'c', 'c'),
(22, 'Dieng', 'Dieng@gmail.com', '12345'),
(23, 'aa', 'aa', 'aaa'),
(24, 'q', 'q', 'q'),
(25, 'a', 'q@gmail.com', 'q'),
(26, 'd', 'd', 'd'),
(27, 's', 's', 's');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contacts_users`
--
ALTER TABLE `contacts_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `registerlogin`
--
ALTER TABLE `registerlogin`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contacts_users`
--
ALTER TABLE `contacts_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `registerlogin`
--
ALTER TABLE `registerlogin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
