-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2022 at 06:36 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_elderly`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL,
  `elder_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  `appointment_date` varchar(20) NOT NULL,
  `appointment_details` text NOT NULL,
  `appointment_status` varchar(30) NOT NULL DEFAULT 'Pending approval',
  `appointment_remarks` text DEFAULT NULL,
  `d_status` varchar(22) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `elder_id`, `id`, `staff_id`, `appointment_date`, `appointment_details`, `appointment_status`, `appointment_remarks`, `d_status`) VALUES
(14, 16, 4, 9, '31/10/2022', 'back pain and joint problems', 'Attended', 'will be fine', 'Collected'),
(15, 16, 4, 8, '15/11/2022', 'fever and headache', 'Attended', 'suffering from malaria', 'Pending'),
(16, 20, 4, 8, '25/11/2022', 'monthly appointment', 'Approved', NULL, NULL),
(17, 20, 4, 8, '17/11/2022', 'appointment', 'Sent to lab', NULL, NULL),
(18, 20, 4, 8, '17/11/2022', 'fever', 'Attended', 'get well soon', 'Collected'),
(19, 21, 4, 8, '16/11/2022', 'patient monthly checkup', 'Attended', 'take drugs as recommend by the package', 'Pending'),
(20, 21, 4, 8, '19/11/2022', 'fever', 'Attended', 'normal fever. will subside after a few days', 'Pending'),
(21, 22, 7, 8, '22/11/2022', 'The admissions day checkup', 'Attended', 'hdhdj', 'Collected'),
(22, 22, 7, 8, '22/11/2022', 'monthly appointment', 'Attended', 'The elder was found to have infection and was given medication for one week', 'Collected');

-- --------------------------------------------------------

--
-- Table structure for table `care_giver`
--

CREATE TABLE `care_giver` (
  `id` int(11) NOT NULL,
  `elder_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `care_giver`
--

INSERT INTO `care_giver` (`id`, `elder_id`, `staff_id`) VALUES
(4, 16, 7),
(5, 20, 7),
(6, 21, 7),
(7, 22, 6);

-- --------------------------------------------------------

--
-- Table structure for table `donated_items`
--

CREATE TABLE `donated_items` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `donation_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` varchar(14) NOT NULL DEFAULT 'Cart',
  `item_details` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donated_items`
--

INSERT INTO `donated_items` (`id`, `item_id`, `donation_id`, `quantity`, `status`, `item_details`) VALUES
(8, 3, 24, 4, 'Pending approv', NULL),
(9, 4, 24, 23, 'Pending approv', NULL),
(10, 3, 25, 12, 'Pending approv', NULL),
(13, 3, 28, 12, 'Pending approv', NULL),
(14, 3, 29, 12, 'Pending approv', NULL),
(15, 4, 29, 10, 'Pending approv', NULL),
(16, 3, 30, 2, 'Pending approv', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `donations`
--

CREATE TABLE `donations` (
  `donation_id` int(11) NOT NULL,
  `donor_id` int(11) NOT NULL,
  `donation_date` timestamp NULL DEFAULT current_timestamp(),
  `donation_status` varchar(30) NOT NULL DEFAULT 'Cart',
  `donation_remarks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donations`
--

INSERT INTO `donations` (`donation_id`, `donor_id`, `donation_date`, `donation_status`, `donation_remarks`) VALUES
(24, 5, '2022-11-15 07:27:07', 'Confirmed delivery', NULL),
(25, 6, '2022-11-15 09:48:06', 'Approved', NULL),
(28, 6, '2022-11-15 09:52:12', 'Approved', NULL),
(29, 7, '2022-11-21 08:36:37', 'Confirmed delivery', NULL),
(30, 7, '2022-11-21 08:52:26', 'Confirmed delivery', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `donors`
--

CREATE TABLE `donors` (
  `donor_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_no` varchar(14) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp(),
  `password` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Pending approval',
  `remarks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donors`
--

INSERT INTO `donors` (`donor_id`, `first_name`, `last_name`, `username`, `email`, `phone_no`, `date_created`, `password`, `status`, `remarks`) VALUES
(5, 'Kiki', 'James', 'Kiki', 'kiki@gmail.com', '0875422948', '2022-11-14 23:00:08', '1234', 'Approved', NULL),
(6, 'Edwin', 'Ndungu', 'Edwin', 'edwin@gmail.com', '0712293279', '2022-11-15 09:46:53', '1234', 'Approved', NULL),
(7, 'John', 'Kihara', 'Kihara', 'kihara@gmail.com', '0745454545', '2022-11-21 08:35:46', '1234', 'Approved', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `drugs`
--

CREATE TABLE `drugs` (
  `drug_id` int(11) NOT NULL,
  `drug_name` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drugs`
--

INSERT INTO `drugs` (`drug_id`, `drug_name`, `stock`, `date_added`) VALUES
(1, 'Contus', 525, '2022-11-14 08:28:48'),
(2, 'Azithromycin', 120, '2022-11-14 08:28:48'),
(3, 'Panadol', 262, '2022-11-14 08:28:48'),
(4, 'Brufen', 140, '2022-11-14 08:28:48');

-- --------------------------------------------------------

--
-- Table structure for table `elder`
--

CREATE TABLE `elder` (
  `elder_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `gender` varchar(11) DEFAULT NULL,
  `dob` varchar(20) DEFAULT NULL,
  `elder_status` varchar(30) NOT NULL DEFAULT 'Pending approval',
  `date_added` timestamp NOT NULL DEFAULT current_timestamp(),
  `elder_remarks` text NOT NULL,
  `relation` varchar(30) NOT NULL DEFAULT 'Father'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `elder`
--

INSERT INTO `elder` (`elder_id`, `member_id`, `firstname`, `lastname`, `gender`, `dob`, `elder_status`, `date_added`, `elder_remarks`, `relation`) VALUES
(16, 15, 'Njambi', 'Wanja', 'Female', '1955', 'Admitted', '2022-10-18 21:32:02', '', 'Father'),
(18, 15, 'Muiruri', 'Chega', 'Male', '8/10/1957', 'Pending approval', '2022-10-19 07:25:18', '', 'Father'),
(19, 18, 'Abdi', 'Omondi', 'Male', '10/11/1954', 'Pending approval', '2022-11-01 11:03:45', '', 'Father'),
(20, 19, 'Anna', 'Nganga', 'Female', '7/11/1957', 'Admitted', '2022-11-15 10:04:12', '', 'Mother'),
(21, 20, 'David', 'Kaje', 'Male', '8/11/1957', 'Admitted', '2022-11-15 12:23:14', '', 'Brother'),
(22, 21, 'Elizabeth', 'Wambui', 'Female', '20/11/1957', 'Admitted', '2022-11-21 08:09:48', '', 'Mother');

-- --------------------------------------------------------

--
-- Table structure for table `family_member`
--

CREATE TABLE `family_member` (
  `member_id` int(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `phone_no` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Pending',
  `remarks` varchar(200) NOT NULL,
  `create_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `family_member`
--

INSERT INTO `family_member` (`member_id`, `first_name`, `last_name`, `username`, `phone_no`, `email`, `password`, `status`, `remarks`, `create_date`) VALUES
(15, 'Njonjo', 'Njenga', 'Njenga', '0751513838', 'njonjo@gmail.com', '12345', 'Approved', '', '2022-10-18 06:00:43'),
(16, 'Kimani', 'Wangari', 'Kimani', '0751513838', 'njonjo@gmail.com', '1234', 'Approved', '', '2022-10-18 06:01:19'),
(17, 'Mumbi', 'Wangari', 'Wangari', '0750078580', 'wangari@gmail.com', '1234', 'Rejected', 'You are an able family', '2022-10-18 06:02:52'),
(18, 'Otieno', 'Omondi', 'Omondi', '0781525840', 'atieno@gmail.com', '1234', 'Approved', '', '2022-11-01 10:58:22'),
(19, 'Denis', 'Nganga', 'Denis', '0715138444', 'denis@gmail.com', '1234', 'Approved', '', '2022-11-15 10:02:51'),
(20, 'Peter', 'Waweru', 'Waweru', '0712364567', 'waweru@gmail.com', '1234', 'Approved', '', '2022-11-15 12:19:59'),
(21, 'Mr', 'Riungu', 'Riungu', '0789696963', 'riungu@gmail.com', '1234', 'Approved', '', '2022-11-21 08:05:54');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `fb_id` int(40) NOT NULL,
  `member_id` int(40) NOT NULL,
  `comment` varchar(200) NOT NULL,
  `reply` varchar(200) NOT NULL DEFAULT 'Pending',
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `staff_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`fb_id`, `member_id`, `comment`, `reply`, `date`, `staff_id`) VALUES
(3, 15, 'hey', 'Pending', '2022-11-18 17:29:56', 6),
(4, 15, 'hey', 'Pending', '2022-11-18 17:32:12', 7),
(5, 20, 'thanks for the good care', 'Pending', '2022-11-19 00:16:01', 7),
(6, 19, 'hey muthoni', 'Pending', '2022-11-19 00:18:36', 7),
(8, 19, '0', 'hey', '2022-11-20 17:46:12', 7),
(9, 19, '0', 'hey too', '2022-11-20 17:47:23', 7),
(10, 21, 'hello', 'Pending', '2022-11-21 12:07:56', 6),
(11, 21, 'how is my elder doing', 'Pending', '2022-11-21 12:08:20', 6),
(12, 21, '0', 'everything is okay', '2022-11-21 12:12:05', 6);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(170) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `item_name`, `stock`) VALUES
(3, 'Cloths', 758),
(4, 'Shoes', 83),
(5, 'Bed sheets', 100),
(6, 'Mattress', 50),
(7, 'bed', 60);

-- --------------------------------------------------------

--
-- Table structure for table `item_requested`
--

CREATE TABLE `item_requested` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `issue_status` varchar(20) NOT NULL DEFAULT 'Cart',
  `request_details` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item_requested`
--

INSERT INTO `item_requested` (`id`, `item_id`, `request_id`, `quantity`, `issue_status`, `request_details`) VALUES
(13, 4, 18, 2, 'Cart', 'open shoes size 10'),
(14, 3, 18, 1, 'Cart', 'fgb'),
(15, 3, 19, 2, 'Cart', 'The elder needs clothes'),
(16, 3, 20, 12, 'Cart', 'cloths'),
(17, 4, 20, 16, 'Cart', 'shoes');

-- --------------------------------------------------------

--
-- Table structure for table `lab_test`
--

CREATE TABLE `lab_test` (
  `test_id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `appointment_id` int(11) DEFAULT NULL,
  `test_result` varchar(50) DEFAULT 'Pending',
  `test_details` text DEFAULT NULL,
  `test_remarks` text DEFAULT NULL,
  `test_status` varchar(20) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lab_test`
--

INSERT INTO `lab_test` (`test_id`, `staff_id`, `appointment_id`, `test_result`, `test_details`, `test_remarks`, `test_status`) VALUES
(2, 0, 14, 'trace of blood found', 'test urine and blood test', '', 'Test done'),
(4, NULL, 18, 'positive', 'blood test', NULL, 'Test done'),
(5, NULL, 19, 'the patient has bacterial infection', 'blood test', NULL, 'Test done'),
(6, NULL, 15, 'yellow urine with a lot of glucose', 'urine test', NULL, 'Test done'),
(7, NULL, 17, 'Pending', 'check blood sugar', NULL, 'Pending'),
(8, NULL, 22, 'The patient has infection', 'The elder to have a blood test', NULL, 'Test done');

-- --------------------------------------------------------

--
-- Table structure for table `medication`
--

CREATE TABLE `medication` (
  `med_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `drug_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `med_status` varchar(40) NOT NULL DEFAULT 'Cart',
  `med_detail` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medication`
--

INSERT INTO `medication` (`med_id`, `appointment_id`, `drug_id`, `quantity`, `med_status`, `med_detail`) VALUES
(5, 14, 1, 2, 'Collected', ''),
(6, 14, 2, 1, 'Collected', ''),
(7, 14, 4, 1, 'Collected', ''),
(8, 14, 3, 10, 'Collected', ''),
(9, 18, 4, 5, 'Collected', ''),
(10, 18, 3, 1, 'Collected', ''),
(11, 19, 4, 3, 'Pending collection', ''),
(12, 19, 1, 5, 'Pending collection', ''),
(13, 15, 1, 2, 'Pending collection', ''),
(14, 15, 2, 2, 'Pending collection', ''),
(15, 15, 4, 10, 'Pending collection', ''),
(16, 15, 3, 2, 'Pending collection', ''),
(17, 22, 1, 1, 'Collected', ''),
(18, 22, 2, 13, 'Collected', '');

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `request_id` int(11) NOT NULL,
  `elder_id` int(11) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `request_status` varchar(20) NOT NULL DEFAULT 'Cart',
  `request_remarks` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`request_id`, `elder_id`, `request_date`, `request_status`, `request_remarks`) VALUES
(18, 16, '2022-11-17 15:10:36', 'Issued', ''),
(19, 22, '2022-11-21 08:56:21', 'Issued', ''),
(20, 22, '2022-11-21 09:00:47', 'Issued', '');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(40) NOT NULL,
  `f_name` varchar(40) NOT NULL,
  `l_name` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Active',
  `email` varchar(40) NOT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `userlevel` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT current_timestamp(),
  `role` varchar(20) NOT NULL DEFAULT 'Staff'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `f_name`, `l_name`, `username`, `password`, `status`, `email`, `phone_no`, `userlevel`, `create_date`, `role`) VALUES
(4, 'Admin', 'Admin', 'Admin', '1234', 'Active', 'adminuser@gmail.com', '0785421458', 'Admin', '2022-10-18 00:59:41', 'Staff'),
(5, 'Kamau', 'Mwangi', 'Kamau', '1234', 'Active', 'mwang@gmail.com', '0712457845', 'Supervisor', '2022-10-18 01:04:50', 'Staff'),
(6, 'Kendi', 'wambui', 'kendi', '1234', 'Active', 'ked@gmail.com', '0784515210', 'Care giver', '2022-10-28 17:09:03', 'Staff'),
(7, 'Muthoni', 'Chege', 'Muthoni', '1234', 'Active', 'chege@gmail.com', '0781115210', 'Care giver', '2022-10-28 17:09:59', 'Staff'),
(8, 'Mwenda', 'Kilonzo', 'Mwenda', '1234', 'Active', 'Mwenda@gmaii.com', '0784515201', 'General doctor', '2022-10-31 13:53:06', 'Doctor'),
(9, 'Mwende', 'Juma', 'Juma', '1234', 'Active', 'juma@gmail.com', '0784510102', 'Othopedic surgeon', '2022-10-31 13:54:59', 'Doctor'),
(10, 'Ben', 'Otieno', 'ben', '1234', 'Active', 'ben@gmail.com', '0784515260', 'Lab technician', '2022-11-14 07:58:21', 'Staff'),
(11, 'Kamau', 'Mwaniki', 'Mwaniki', '1234', 'Active', 'mwaniki@gmail.com', '0784845152', 'Pharmacist', '2022-11-14 17:21:38', 'Staff'),
(12, 'Nancy', 'Wanjiku', 'wanjiku', '1234', 'Active', 'nancy@gmail.com', '0784512014', 'Store manager', '2022-11-15 12:51:17', 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `stock_in`
--

CREATE TABLE `stock_in` (
  `id` int(11) NOT NULL,
  `drug_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `stock_in_status` varchar(20) NOT NULL DEFAULT 'Cart',
  `request_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock_in`
--

INSERT INTO `stock_in` (`id`, `drug_id`, `quantity`, `stock_in_status`, `request_id`) VALUES
(3, 1, 25, 'Pending approval', 1),
(5, 2, 20, 'Pending approval', 1),
(6, 3, 100, 'Pending approval', 1),
(7, 3, 12, 'Pending approval', 3),
(8, 4, 70, 'Pending approval', 4);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplier_id` int(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `phone_no` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Pending',
  `remarks` varchar(200) NOT NULL,
  `create_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `first_name`, `last_name`, `username`, `phone_no`, `email`, `password`, `status`, `remarks`, `create_date`) VALUES
(1, 'Erick', 'Mwangi', 'Erick', '0785425180', 'erico@gmail.com', '1234', 'Approved', '', '2022-11-18 08:21:12'),
(2, 'Otieno', 'Mwangi', 'Kim', '0754287515', 'oti@gmail.com', '1234', 'Approved', '', '2022-11-18 08:22:09'),
(3, 'Njoro', 'Mwafrica', 'Njoro', '0756454645', 'njoro@gmail.com', '1234', 'Approved', '', '2022-11-21 09:03:42');

-- --------------------------------------------------------

--
-- Table structure for table `s_request`
--

CREATE TABLE `s_request` (
  `request_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `request_status` varchar(20) NOT NULL DEFAULT 'Cart'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `s_request`
--

INSERT INTO `s_request` (`request_id`, `supplier_id`, `request_date`, `request_status`) VALUES
(1, 1, '2022-11-18 09:38:13', 'Confirmed delivery'),
(3, 3, '2022-11-21 09:27:59', 'Confirmed delivery'),
(4, 3, '2022-11-21 09:31:35', 'Confirmed delivery');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `elder_id` (`elder_id`),
  ADD KEY `staff_id` (`staff_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `care_giver`
--
ALTER TABLE `care_giver`
  ADD PRIMARY KEY (`id`),
  ADD KEY `elder_id` (`elder_id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Indexes for table `donated_items`
--
ALTER TABLE `donated_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `donation_id` (`donation_id`);

--
-- Indexes for table `donations`
--
ALTER TABLE `donations`
  ADD PRIMARY KEY (`donation_id`),
  ADD KEY `donor_id` (`donor_id`);

--
-- Indexes for table `donors`
--
ALTER TABLE `donors`
  ADD PRIMARY KEY (`donor_id`);

--
-- Indexes for table `drugs`
--
ALTER TABLE `drugs`
  ADD PRIMARY KEY (`drug_id`);

--
-- Indexes for table `elder`
--
ALTER TABLE `elder`
  ADD PRIMARY KEY (`elder_id`),
  ADD KEY `patient_id` (`member_id`);

--
-- Indexes for table `family_member`
--
ALTER TABLE `family_member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`fb_id`),
  ADD KEY `patient_id` (`member_id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `item_requested`
--
ALTER TABLE `item_requested`
  ADD PRIMARY KEY (`id`),
  ADD KEY `request_id` (`request_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `lab_test`
--
ALTER TABLE `lab_test`
  ADD PRIMARY KEY (`test_id`),
  ADD KEY `staff_id` (`staff_id`),
  ADD KEY `appointment_id` (`appointment_id`);

--
-- Indexes for table `medication`
--
ALTER TABLE `medication`
  ADD PRIMARY KEY (`med_id`),
  ADD KEY `appointment_id` (`appointment_id`),
  ADD KEY `drug_id` (`drug_id`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `child_id` (`elder_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `branch` (`email`),
  ADD KEY `type_id` (`userlevel`);

--
-- Indexes for table `stock_in`
--
ALTER TABLE `stock_in`
  ADD PRIMARY KEY (`id`),
  ADD KEY `stock_id` (`drug_id`),
  ADD KEY `request_id` (`request_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `s_request`
--
ALTER TABLE `s_request`
  ADD PRIMARY KEY (`request_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `care_giver`
--
ALTER TABLE `care_giver`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `donated_items`
--
ALTER TABLE `donated_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `donations`
--
ALTER TABLE `donations`
  MODIFY `donation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `donors`
--
ALTER TABLE `donors`
  MODIFY `donor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `drugs`
--
ALTER TABLE `drugs`
  MODIFY `drug_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `elder`
--
ALTER TABLE `elder`
  MODIFY `elder_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `family_member`
--
ALTER TABLE `family_member`
  MODIFY `member_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `fb_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `item_requested`
--
ALTER TABLE `item_requested`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `lab_test`
--
ALTER TABLE `lab_test`
  MODIFY `test_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `medication`
--
ALTER TABLE `medication`
  MODIFY `med_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `stock_in`
--
ALTER TABLE `stock_in`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `s_request`
--
ALTER TABLE `s_request`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`elder_id`) REFERENCES `elder` (`elder_id`),
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`id`) REFERENCES `care_giver` (`id`);

--
-- Constraints for table `care_giver`
--
ALTER TABLE `care_giver`
  ADD CONSTRAINT `care_giver_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`),
  ADD CONSTRAINT `care_giver_ibfk_2` FOREIGN KEY (`elder_id`) REFERENCES `elder` (`elder_id`);

--
-- Constraints for table `donated_items`
--
ALTER TABLE `donated_items`
  ADD CONSTRAINT `donated_items_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  ADD CONSTRAINT `donated_items_ibfk_2` FOREIGN KEY (`donation_id`) REFERENCES `donations` (`donation_id`);

--
-- Constraints for table `donations`
--
ALTER TABLE `donations`
  ADD CONSTRAINT `donations_ibfk_2` FOREIGN KEY (`donor_id`) REFERENCES `donors` (`donor_id`);

--
-- Constraints for table `elder`
--
ALTER TABLE `elder`
  ADD CONSTRAINT `elder_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `family_member` (`member_id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `family_member` (`member_id`);

--
-- Constraints for table `item_requested`
--
ALTER TABLE `item_requested`
  ADD CONSTRAINT `item_requested_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `requests` (`request_id`),
  ADD CONSTRAINT `item_requested_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

--
-- Constraints for table `lab_test`
--
ALTER TABLE `lab_test`
  ADD CONSTRAINT `lab_test_ibfk_2` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`);

--
-- Constraints for table `medication`
--
ALTER TABLE `medication`
  ADD CONSTRAINT `medication_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`),
  ADD CONSTRAINT `medication_ibfk_2` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`drug_id`);

--
-- Constraints for table `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`elder_id`) REFERENCES `elder` (`elder_id`);

--
-- Constraints for table `stock_in`
--
ALTER TABLE `stock_in`
  ADD CONSTRAINT `stock_in_ibfk_1` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`drug_id`),
  ADD CONSTRAINT `stock_in_ibfk_2` FOREIGN KEY (`request_id`) REFERENCES `s_request` (`request_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
