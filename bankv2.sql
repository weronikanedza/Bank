-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2017 at 11:20 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bankdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id_account` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `balance` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `pesel` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id_account`, `balance`, `pesel`) VALUES
('111111111', '240.5', '90020197652'),
('222222222', '920.34', '96020598652'),
('309162794', '0.00', '95020658911');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `firstname` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `lastname` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `login` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `firstname`, `lastname`, `login`) VALUES
('1', 'Admin', 'Admin', '1');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `pesel` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `customer_nr` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `firstname` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `lastname` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `idNumber` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `street` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `zipcode` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `phonenumber` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`pesel`, `customer_nr`, `firstname`, `lastname`, `idNumber`, `street`, `email`, `zipcode`, `city`, `phonenumber`) VALUES
('90020197652', '2', 'Anna', 'Dzik', 'AYD234187', 'ul.Nowa 43/23', 'nowy@gmail.com', '23-862', 'Kraków', '987234123'),
('95020658911', '42473', 'Jurek', 'Owsiak', 'AYD231986', '??kowa 4/32', 'kn@gmail.com', '30-022', 'Kraków', '238123675'),
('96020598652', '3', 'Karol', 'Nowak', 'AYD234978', 'ul.Warszawska 12/13', 'weronika439@gmail.com', '12-562', 'Warszawa', '256387654');

-- --------------------------------------------------------

--
-- Table structure for table `newaccountrequest`
--

CREATE TABLE `newaccountrequest` (
  `id_request` varchar(100) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  `firstname` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `lastname` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `street` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `zipcode` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `idNumber` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `phoneNumber` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `pesel` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `id_admin` varchar(100) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `newaccountrequest`
--

INSERT INTO `newaccountrequest` (`id_request`, `firstname`, `lastname`, `street`, `city`, `zipcode`, `idNumber`, `email`, `phoneNumber`, `pesel`, `id_admin`) VALUES
('2', 'Paweł ', 'Pole', 'ul 1/2', 'Wrocłam', '12-987', 'RTZ567123', 'rt@wp.pl', '456789123', '95020198567', '1'),
('3', 'Aleksandra ', 'Kozak', 'ul 1/2', 'Wrocłam', '12-987', 'RTZ567123', 'rt@wp.pl', '456789123', '92020198567', '1'),
('4', 'Liliana ', 'Liść', 'ul 1/2', 'Kamień', '12-987', 'RTZ567123', 'rt@wp.pl', '456789123', '92020191567', '1'),
('5', 'Andrzej ', 'Kajak', 'ul 1/2', 'Kamień', '12-987', 'RTZ567123', 'rt@wp.pl', '345789231', '910101123567', '1'),
('6', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', '1'),
('7', 'Natalia', 'Gawor', 'Wielicka11u/12', 'Kraków', '30-552', 'AZK874512', 'niebieskiePatyki@gmail.com', '123456789', '96121458741', '1'),
('8', 'qw', 'qw', 'sdg1/1', 'hjk', '45-', 'AZK456987', 'sd@.', '2', '96121458747', '1');

-- --------------------------------------------------------

--
-- Table structure for table `transfer`
--

CREATE TABLE `transfer` (
  `id_transfer` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `accFrom` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `accTo` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `amount` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `title` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `date` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `transfer`
--

INSERT INTO `transfer` (`id_transfer`, `accFrom`, `accTo`, `amount`, `title`, `date`) VALUES
('1', '111111111', '222222222', '20', 'tytul', '2017/11/26'),
('2', '111111111', '222222222', '20', 'tytul', '2017/11/26'),
('3', '111111111', '222222222', '20', 'tytul', '2017/11/28');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `login` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login`, `password`, `status`) VALUES
('1', 'Admin1', 'A'),
('2', 'K2', 'C'),
('3', 'k3', 'C'),
('42473', 'HY0XZRJUF', 'C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id_account`),
  ADD KEY `pesel` (`pesel`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `login` (`login`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`pesel`),
  ADD KEY `customer_nr` (`customer_nr`);

--
-- Indexes for table `newaccountrequest`
--
ALTER TABLE `newaccountrequest`
  ADD PRIMARY KEY (`id_request`),
  ADD KEY `FK_admin` (`id_admin`);

--
-- Indexes for table `transfer`
--
ALTER TABLE `transfer`
  ADD PRIMARY KEY (`id_transfer`),
  ADD KEY `accFrom` (`accFrom`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`pesel`) REFERENCES `customers` (`pesel`);

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`login`) REFERENCES `users` (`login`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`customer_nr`) REFERENCES `users` (`login`);

--
-- Constraints for table `newaccountrequest`
--
ALTER TABLE `newaccountrequest`
  ADD CONSTRAINT `FK_admin` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

--
-- Constraints for table `transfer`
--
ALTER TABLE `transfer`
  ADD CONSTRAINT `transfer_ibfk_1` FOREIGN KEY (`accFrom`) REFERENCES `account` (`id_account`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
