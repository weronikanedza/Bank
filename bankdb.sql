-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2017 at 06:31 PM
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
('111111111', '400.50', '90020197652'),
('222222222', '760.34', '96020598652');

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
  `phone` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`pesel`, `customer_nr`, `firstname`, `lastname`, `idNumber`, `street`, `email`, `zipcode`, `city`, `phone`) VALUES
('90020197652', '1', 'Anna', 'Dzik', 'AYD234187', 'ul.Nowa 43/23', 'nowy@gmail.com', '23-862', 'Kraków', '987234123'),
('96020598652', '2', 'Karol', 'Nowak', 'AYD234978', 'ul.Warszawska 12/13', 'weronika439@gmail.com', '12-562', 'Warszawa', '256387654');

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
('1', 'Jurek', 'Owsiak', '??kowa 4/32', 'Kraków', '30-022', 'AYD231986', 'kn@gmail.com', '238123675', '95020658911', '1');

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
('2', 'k2', 'C'),
('3', 'k3', 'C');

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
