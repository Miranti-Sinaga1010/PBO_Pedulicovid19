-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2020 at 03:10 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pedulicovid_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `cekresiko`
--

CREATE TABLE `cekresiko` (
  `id` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `tingkat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cekresiko`
--

INSERT INTO `cekresiko` (`id`, `judul`, `jenis`, `tingkat`) VALUES
(1, 'Saya pergi keluar rumah', 'Potensi Tertular Didalam Rumah', 'Sedang'),
(2, 'Saya tidak memakai masker pada saat berkumpul dengan orang lain', 'Potensi Tertular Diluar Rumah', 'Tinggi'),
(3, 'Saya dalam sehari tidak kena cahaya matahari minimal 15 menit', 'Pontesi Daya Tahan Tubuh (Imunitas)', 'Rendah');

-- --------------------------------------------------------

--
-- Table structure for table `donasi`
--

CREATE TABLE `donasi` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donasi`
--

INSERT INTO `donasi` (`id`, `nama`, `total`) VALUES
(1, 'valentine', 10000),
(2, 'miranti', 30000),
(3, 'elsa', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `wilayah`
--

CREATE TABLE `wilayah` (
  `id` int(10) NOT NULL,
  `provinsi` varchar(100) NOT NULL,
  `daerah` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wilayah`
--

INSERT INTO `wilayah` (`id`, `provinsi`, `daerah`, `status`) VALUES
(1, 'Riau', 'Pekanbaru', 'Aman'),
(2, 'Aceh', 'Karang baru', 'Aman'),
(4, 'Bengkulu', 'Bintuhan', 'Hati-hati'),
(5, 'Banten', 'serang', 'Zona Merah'),
(6, 'DKI Jakarta', 'Tangerang', 'Zona Merah'),
(7, 'Jawa Barat', 'cikarang', 'Hati-hati');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cekresiko`
--
ALTER TABLE `cekresiko`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donasi`
--
ALTER TABLE `donasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `wilayah`
--
ALTER TABLE `wilayah`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
