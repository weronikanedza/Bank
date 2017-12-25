-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2017 at 12:34 AM
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
('111111111', '10380.0', '90020197652'),
('222222222', '1720', '96020598652'),
('619335823', '0.00', '96121458741');

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
('90020197652', '2', 'Anna', 'Dzik', 'AYD234187', 'ul.Nowa 43/23', 'nowy@gmail.com', '23-862', NULL, '987234123'),
('96020598652', '3', 'Alan', 'Kubik', 'ASC546123', 'ul.?wietlista 3', 'kubik@gmail.com', '12-345', 'Warszawa', '789456123'),
('96121458741', '98667', 'Natalia', 'Gawor', 'AZK874512', 'Wielicka11u/12', 'niebieskiePatyki@gmail.com', '30-552', 'Wrocłam', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id_loan` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `amount` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `bankRate` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `instalment` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `numberOfMonths` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `customer_nr` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `salary` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `date` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id_loan`, `amount`, `bankRate`, `instalment`, `numberOfMonths`, `customer_nr`, `salary`, `date`, `status`) VALUES
('1', '2500', '5', '250', '12', '2', '3500', '2017-12-26', '1');

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
  `id_admin` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `reqstatus` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `newaccountrequest`
--

INSERT INTO `newaccountrequest` (`id_request`, `firstname`, `lastname`, `street`, `city`, `zipcode`, `idNumber`, `email`, `phoneNumber`, `pesel`, `id_admin`, `reqstatus`) VALUES
('5', 'Aleksandra ', 'Bożek', 'ul 1/2', 'Wrocłam', '12-987', 'RTZ567123', 'rt@wp.pl', '456789123', '96121458741', '1', '0');

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
('10', '111111111', '222222222', '200.00', 'ble', '2017-12-25'),
('2', '111111111', '222222222', '20', 'tytul', '2017/11/26'),
('3', '111111111', '222222222', '20', 'tytul', '2017/11/28'),
('6', '111111111', '222222222', '12.98', '\"HALO\"', '2017/04/12'),
('7', '111111111', '222222222', '12.98', '\"bufalo\"', '2017/12/12'),
('8', '111111111', '222222222', '200', 'ble', '2017-12-25'),
('9', '111111111', '222222222', '200.00', 'ble', '2017-12-25');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `login` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `counter` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login`, `password`, `status`, `counter`) VALUES
('1', 'Admin1', 'A', 0),
('2', 'Admin1', 'C', 0),
('3', 'k3', 'C', 0),
('98667', 'ZMULA1', 'C', 0);

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
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id_loan`),
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
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`customer_nr`) REFERENCES `customers` (`customer_nr`);

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
