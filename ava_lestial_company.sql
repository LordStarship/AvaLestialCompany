-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 03:23 AM
-- Server version: 10.11.2-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ava_lestial_company`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(9) NOT NULL,
  `id_game` int(9) NOT NULL,
  `id_user` int(9) NOT NULL,
  `name_barang` varchar(99) NOT NULL,
  `email_barang` varchar(99) NOT NULL,
  `amount_barang` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `id_game`, `id_user`, `name_barang`, `email_barang`, `amount_barang`) VALUES
(1, 2, 2, 'Frieren1', 'mictrq@gmail.com', 'Rp30.000'),
(2, 2, 2, 'Frieren2', 'stanisphia01', 'Rp10.000'),
(3, 2, 2, 'Frieren3', 'stanisphia02', 'Rp10.000'),
(4, 2, 2, 'Frieren4', 'stanisphia03', 'Rp10.000'),
(5, 2, 2, 'Frieren5', 'stanisphia04', 'Rp10.000\r\n'),
(6, 2, 2, 'Frieren6', 'stanisphia05', 'Rp10.000'),
(7, 2, 2, 'Frieren7', 'stanisphia06', 'Rp10.000'),
(8, 2, 2, 'Frieren8', 'stanisphia07', 'Rp10.000'),
(9, 2, 2, 'Frieren9', 'willscanor8@gmail.com', 'Rp10.000'),
(10, 2, 2, 'Frieren10', 'willstaniss@gmail.com', 'Rp10.000'),
(11, 2, 2, 'Frieren11', 'willscanor14@gmail.com', 'Rp10.000'),
(12, 2, 2, 'Frieren12', 'willhoodiex@gmail.com', 'Rp10.000'),
(13, 2, 2, 'Frieren13', 'willscanor7@gmail.com', 'Rp10.000'),
(14, 2, 2, 'Frieren14', 'willscanor13@gmail.com', 'Rp10.000'),
(15, 2, 2, 'Frieren16', 'yoimiyasimp30@gmail.com', 'Rp10.000'),
(16, 2, 2, 'Frieren17', 'willscanor5@gmail.com', 'Rp10.000'),
(17, 2, 2, 'Frieren18', 'dekialina83@gmail.com', 'Rp10.000'),
(18, 2, 2, 'Frieren19', 'febriandik92@gmail.com', 'Rp10.000'),
(19, 2, 2, 'Frieren20', 'denialfika83@gmail.com', 'Rp10.000'),
(20, 2, 2, 'Frieren21', 'fikideka73@gmail.com', 'Rp10.000'),
(21, 2, 2, 'Frieren22', 'nilmuna63@gmail.com', 'Rp10.000'),
(22, 2, 2, 'Frieren23', 'rendiabrian@gmail.com\r\n', 'Rp10.000'),
(23, 2, 2, 'Frieren24', 'gnosisslecter@gmail.com', 'Rp10.000'),
(24, 2, 2, 'Frieren25', 'willscanor9@gmail.com', 'Rp10.000'),
(25, 2, 2, 'Frieren26', 'nonikpitria17@gmail.com', 'Rp10.000'),
(26, 2, 2, 'Frieren27', 'bimapandi00@gmail.com', 'Rp10.000'),
(27, 2, 2, 'Frieren28', 'wiantybila@gmail.com', 'Rp10.000'),
(28, 2, 2, 'Frieren29', 'fitafartdita@gmail.com', 'Rp10.000'),
(29, 2, 2, 'Frieren30', 'aditbowo220@gmail.com', 'Rp135.000'),
(30, 2, 2, 'Frieren31', 'willscanor16@gmail.com', 'Rp40.000'),
(31, 2, 2, 'Frieren32', 'yusaqmusawa@gmail.com', 'Rp50.000'),
(32, 2, 2, 'Frieren33', 'jenijihan7', 'Rp40.000'),
(33, 2, 2, 'Frieren34', 'marcelrino58@gmail.com', 'Rp50.000'),
(34, 2, 2, 'Frieren35', 'narsiriani@gmail.com', 'Rp50.000'),
(35, 2, 2, 'Frieren36', 'mudinkelvin@gmail.com', 'Rp50.000'),
(36, 2, 2, 'Frieren37', 'ucupdian009@gmail.com', 'Rp40.000'),
(37, 2, 2, 'Frieren37', 'piyubaim682@gmail.com', 'Rp50.000'),
(38, 2, 2, 'Frieren39', 'andoabdel7@gmail.com', 'Rp50.000'),
(39, 2, 2, 'Frieren40', 'rakaanika@gmail.com', 'Rp50.000'),
(40, 2, 2, 'Frieren40', 'nikereanis@gmail.com', 'Rp50.000'),
(41, 2, 2, 'Frieren42', 'yadibrodin@gmail.com', 'Rp50.000'),
(42, 2, 2, 'Frieren43', 'brandohito34@gmail.com', 'Rp10.000'),
(43, 2, 2, 'Frieren44', 'farhankardi00@gmail.com', 'Rp50.000'),
(44, 2, 5, 'CuanLiu1', 'oi92434', 'Rp50.000'),
(45, 5, 2, 'CuanLiu2', 'Dy00w15H', 'Rp50.000'),
(46, 5, 2, 'CuanLiu3', 'Vx2xglnh', 'Rp50.000'),
(47, 5, 2, 'CuanLiu10', 'genshin.akun0928@gmail.com', 'Rp210.000'),
(48, 5, 2, 'CuanLiu11', 'genshin.akun1923@gmail.com', 'Rp210.000');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id_game` int(9) NOT NULL,
  `id_user` int(9) NOT NULL,
  `name_game` varchar(99) NOT NULL,
  `variation_game` varchar(99) NOT NULL,
  `type_game` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id_game`, `id_user`, `name_game`, `variation_game`, `type_game`) VALUES
(1, 2, 'Genshin', 'Starter', 'Akun'),
(2, 2, 'Star Rail', 'Starter', 'Akun'),
(3, 2, 'Genshin', 'Endgame', 'Akun'),
(4, 2, 'Star Rail', 'Endgame', 'Akun\r\n'),
(5, 2, 'Star Rail', 'Ex Reroll', 'Akun\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_user` int(9) NOT NULL,
  `name_user` varchar(99) NOT NULL,
  `role_user` int(2) NOT NULL,
  `password_user` varchar(99) NOT NULL,
  `username_user` varchar(99) NOT NULL,
  `email_user` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_user`, `name_user`, `role_user`, `password_user`, `username_user`, `email_user`) VALUES
(2, 'admin', 1, '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'admin', 'avalestialcompany@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(9) NOT NULL,
  `id_barang` int(9) NOT NULL,
  `id_user` int(9) NOT NULL,
  `date_transaksi` date NOT NULL,
  `amount_transaksi` int(9) NOT NULL,
  `note_transaksi` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_barang`, `id_user`, `date_transaksi`, `amount_transaksi`, `note_transaksi`) VALUES
(1, 1, 2, '2024-05-08', 200001, 'Sold in '),
(2, 2, 2, '2024-06-20', 1000000, 'Sold in WA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_user` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
