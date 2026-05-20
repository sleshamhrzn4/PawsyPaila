-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2026 at 06:50 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pawsypaila`
--

-- --------------------------------------------------------

--
-- Table structure for table `adoptionrequest`
--

CREATE TABLE `adoptionrequest` (
  `adoptionId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `petId` int(11) DEFAULT NULL,
  `AdoptionStatus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cartId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cartitem`
--

CREATE TABLE `cartitem` (
  `cartItemId` int(100) NOT NULL,
  `productId` int(100) DEFAULT NULL,
  `cartId` int(100) DEFAULT NULL,
  `cartItemQuantity` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `contactId` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`contactId`, `email`, `message`) VALUES
(1, 'gdsfjas@gmail.com', '1234567890'),
(2, 'rakesh@gmail.com', 'sdfghjhkjk;;jksdfghjkl');

-- --------------------------------------------------------

--
-- Table structure for table `donation`
--

CREATE TABLE `donation` (
  `donationId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `donationAmount` decimal(10,2) DEFAULT NULL,
  `donationDate` date DEFAULT NULL,
  `donationPaymentMethod` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donation`
--

INSERT INTO `donation` (`donationId`, `userId`, `donationAmount`, `donationDate`, `donationPaymentMethod`) VALUES
(1, 29, 5.00, '2026-05-19', 'esew'),
(2, 29, 1.00, '2015-05-19', 'esewa'),
(3, 40, 400000.00, '2026-05-20', 'Bank Transfer'),
(4, 43, 4999.00, '2026-05-20', 'Khalti');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `orderId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `paymentId` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `orderStatus` varchar(50) NOT NULL,
  `totalAmount` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `orderItemId` int(11) NOT NULL,
  `orderId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `orderItemPrice` decimal(10,2) DEFAULT NULL,
  `orderItemQuantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentId` int(11) NOT NULL,
  `paymentMethod` varchar(50) DEFAULT NULL,
  `paymentStatus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `petId` int(11) NOT NULL,
  `petName` varchar(100) DEFAULT NULL,
  `petAge` int(20) DEFAULT NULL,
  `petType` varchar(100) DEFAULT NULL,
  `petGender` varchar(200) DEFAULT NULL,
  `petDesc` text NOT NULL,
  `petImage` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`petId`, `petName`, `petAge`, `petType`, `petGender`, `petDesc`, `petImage`) VALUES
(39, 'Kupu', 4, 'Dog', 'Male', 'Hey there, I’m Kupu, playful, gentle, and always on the lookout for the next cozy spot or fun surprise.', '1779295548466Kupu.png'),
(40, 'Bella ', 3, 'Cat', 'Female', 'Hey there, I’m Bella, curious, and perfectly skilled at turning any spot into my own little kingdom.', '1779295594132Bella.png'),
(41, 'Rocky', 5, 'Dog', 'Male', 'Hey there, I’m Rocky, bold, loyal, and always ready to stand guard or chase down a good time.', '1779295636986Rocky.png'),
(42, 'Hachimi', 5, 'Cat', 'Male', 'Hey there, I’m Hachimi, curious, cozy, and always pouncing on little moments of fun.', '1779295676629Hachimi.jpg'),
(43, 'Gheu', 1, 'Dog', 'Female', 'Hey there, I’m Gheu, rich, golden, and always ready to butter up the mood with a little charm.', '1779295747392Gheu.png'),
(44, 'Momo', 5, 'Dog', 'Male', 'Hey there, I’m Momo, fluffy, curious, and always ready for a snack break and a little adventure.', '1779295786188Momo.png');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `productPrice` double(10,2) NOT NULL,
  `productQuantity` int(100) NOT NULL,
  `productDescription` text NOT NULL,
  `productImage` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productId`, `productName`, `productPrice`, `productQuantity`, `productDescription`, `productImage`) VALUES
(3, 'Summer Pink Hawaiian T-Shirt', 150000.00, 30, 'Keep your pet looking cute and comfortable with this vibrant Summer Pink Hawaiian T-Shirt. Designed with a tropical-inspired pattern and lightweight fabric, it’s perfect for sunny walks, playtime, and everyday wear. Soft, breathable, and stylish—your furry friend stays cool while standing out in style.', '1779211633859.png'),
(4, 'Black on White Pinstripe Shirt', 1500.00, 50, 'Give your pet a smart and timeless look with this Black on White Pinstripe Shirt. Featuring a classic pinstripe design and comfortable fit, this stylish shirt is perfect for casual outings, special occasions, or everyday wear. Made with soft, breathable fabric to keep your furry companion comfortable and fashionable.', '1779211735969.png'),
(5, 'Traditional Bhakhu Inspired T-Shirt', 1800.00, 55, 'A stylish pet T-shirt inspired by traditional Bhakhu design, combining cultural charm with soft, comfortable wear for everyday use.', '1779246970490.png'),
(6, 'Blue Denim Jacket', 1800.00, 66, 'A classic blue denim jacket for pets, designed for a stylish look with a comfortable fit for everyday adventures.', '1779247039164.png'),
(7, 'Happy Paws Dog Food', 3000.00, 146, 'A nutritious and tasty dog food made to support your pet’s overall health, energy, and daily well-being while keeping mealtime enjoyable.', '1779248960530.png'),
(8, 'Happy Paws Cat Food', 3000.00, 150, 'A nutritious and flavorful cat food designed to support your cat’s health, energy, and overall well-being while making every meal enjoyable', '1779249013406.png'),
(9, 'Green Collar with Golden Tag', 600.00, 100, 'A stylish green pet collar featuring an elegant golden tag, designed for comfort, durability, and a charming everyday look', '1779249092115.png'),
(10, 'Dog Blue Harness Leash', 1000.00, 600, 'A comfortable and durable blue harness leash designed to provide better control, secure fitting, and enjoyable walks for your dog.', '1779249164460.png'),
(11, 'Ultra-Soft Donut Cat Bed for Dogs', 6000.00, 20, 'An ultra-soft donut-shaped pet bed designed to provide warmth, comfort, and cozy support for dogs during naps and restful sleep.', '1779249243361.png'),
(12, 'Ultra-Soft Donut Cat Bed for  Cats', 6000.00, 20, 'A plush and ultra-soft donut bed designed to give your cat cozy comfort, warmth, and a relaxing space to rest and sleep.', '1779249334729.png'),
(13, 'jack&pup YakChew', 1500.00, 29, 'A long-lasting and natural dog chew made to support dental health while keeping your dog entertained and satisfied.', '1779249412829.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'user',
  `profileImg` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `fullName`, `phone`, `email`, `password`, `address`, `age`, `gender`, `active`, `role`, `profileImg`) VALUES
(29, 'slesha', '23456789', 'slesha@gmail.com', '$2a$10$4S2S/OTCXiG340MEjbrNFOP9CBGqfBes1.7SZCHx6bHuLpJGOP7.W', 'ktm', 19, 'female', 0, '', ''),
(40, 'Siya', '9866337738', 'siya@gmail.com', '$2a$10$9uFW7CS8H5esBnSvVSY5JuH5lAQpuGztXHeW1aBjgQywPrOuYaFl2', 'ktm', 30, 'female', 1, '', '1779244429198Siya.png'),
(41, 'Admin', '9866337738', 'admin@gmail.com', '$2a$10$CW4cQu0yBlQLfsZs9eapd.5QM388IGwDKdvgjBSyGCTvxDVdpJ7b.', 'ktm', 30, 'female', 1, 'admin', '1779244671277Saiyra.png'),
(42, 'ram', '9866337738', 'ram@gmail.com', '$2a$10$JBDXYItrG6BZ1uPpeCSmROxvR4MneQFDcnZKsAdRFOgmecQ8suyE.', 'ktm', 44, 'male', 1, 'user', 'default.png'),
(43, 'Rakesh ', '1234567890', 'rakesh@gmail.com', '$2a$10$Hez.AlB/uvu5Hq8j2ZefXemSWwLApZrpLVp7gOIdUar.5I390CxTm', 'ktm', 33, 'male', 1, 'user', '1779286998702Rakesh.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adoptionrequest`
--
ALTER TABLE `adoptionrequest`
  ADD PRIMARY KEY (`adoptionId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `petId` (`petId`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `cartitem`
--
ALTER TABLE `cartitem`
  ADD PRIMARY KEY (`cartItemId`),
  ADD KEY `productId` (`productId`),
  ADD KEY `cartId` (`cartId`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`contactId`);

--
-- Indexes for table `donation`
--
ALTER TABLE `donation`
  ADD PRIMARY KEY (`donationId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `paymentId` (`paymentId`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD PRIMARY KEY (`orderItemId`),
  ADD KEY `orderId` (`orderId`),
  ADD KEY `productId` (`productId`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentId`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`petId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `unique_email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adoptionrequest`
--
ALTER TABLE `adoptionrequest`
  MODIFY `adoptionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cartId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cartitem`
--
ALTER TABLE `cartitem`
  MODIFY `cartItemId` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `contactId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donation`
--
ALTER TABLE `donation`
  MODIFY `donationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderitem`
--
ALTER TABLE `orderitem`
  MODIFY `orderItemId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pet`
--
ALTER TABLE `pet`
  MODIFY `petId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `adoptionrequest`
--
ALTER TABLE `adoptionrequest`
  ADD CONSTRAINT `adoptionrequest_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `adoptionrequest_ibfk_2` FOREIGN KEY (`petId`) REFERENCES `pet` (`petId`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `cartitem`
--
ALTER TABLE `cartitem`
  ADD CONSTRAINT `cartitem_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`),
  ADD CONSTRAINT `cartitem_ibfk_2` FOREIGN KEY (`cartId`) REFERENCES `cart` (`CartId`);

--
-- Constraints for table `donation`
--
ALTER TABLE `donation`
  ADD CONSTRAINT `donation_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`paymentId`) REFERENCES `payment` (`paymentId`);

--
-- Constraints for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`),
  ADD CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
