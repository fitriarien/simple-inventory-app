/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 8.0.33 : Database - inventorydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`inventorydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `inventorydb`;

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` varchar(100) NOT NULL,
  `image` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` double DEFAULT NULL,
  `stock` bigint DEFAULT NULL,
  `user_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_user_product` (`user_id`),
  CONSTRAINT `fk_user_product` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `products` */

insert  into `products`(`id`,`image`,`name`,`price`,`stock`,`user_id`) values 
('084091d7-2d8e-4d51-a8f2-3ccf7296db80','www.example83.com','Laptop 84',23400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('0ce39aa2-7d7c-4bd4-9556-d0659767d143','www.example75.com','Laptop 76',22600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('0df69dad-688d-4e73-aca0-e7494cd9e5c6','www.example73.com','Laptop 74',22400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('11b0fc84-5259-48fb-a161-6530fc5f19dd','www.example22.com','Laptop 23',17300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('2085937a-927a-4ab5-9678-462581c9826e','www.example80.com','Laptop 81',23100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('223daae4-7ca8-4c68-b78f-31589b978ea5','www.example21.com','Laptop 22',17200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('2697e1bf-f238-447d-a9b1-d558472e1e89','www.example55.com','Laptop 56',20600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('29cf1ba7-f78e-444a-bfc5-2accf457d381','www.example6.com','Laptop 7',15700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('2d9a6bb3-47c3-40a3-b15a-9e61e72e40e4','www.example48.com','Laptop 49',19900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('2e4bdec4-a015-4fb1-9b18-be04d9a685db','www.example92.com','Laptop 93',24300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('2f6f13ec-dbea-410d-9e90-95e2c0208f4d','www.example99.com','Laptop 100',25000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('342b95fc-4f1b-415c-bbed-aa54ffff2432','www.example11.com','Laptop 12',16200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('345d40dd-39ae-4e0e-8803-34dba521fa71','www.example33.com','Laptop 34',18400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('34a70408-00cb-401f-af8d-409ff0e3917a','www.example67.com','Laptop 68',21800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('35f9fb7b-0da2-4e30-89e4-7010a3d9caa0','www.example15.com','Laptop 16',16600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('3791ff9f-4c9b-4381-9568-b1ea16db6f23','www.example24.com','Laptop 25',17500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('3b5f02ac-11ff-4e2e-8e8d-b8bcfae50dcd','www.example68.com','Laptop 69',21900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('40ffb08f-a9c4-418e-b3e8-0babd1836291','www.example39.com','Laptop 40',19000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('43110a59-42c4-4c71-bc82-a51fa9c4b921','www.example86.com','Laptop 87',23700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('434fa121-3836-4c19-a6ce-a786e39d9884','www.example93.com','Laptop 94',24400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('43ee97ed-1689-4804-808d-8ac3aae0fc87','www.example4.com','Laptop 5',15500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('443b83d3-2bad-495b-b015-ec83c8286c0c','www.example3.com','Laptop 4',15400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('48cbd102-c2cf-470a-b3b0-f2f554a8c25c','www.example54.com','Laptop 55',20500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('4d44ac08-e6df-4d28-a098-6fbcaf7f88e1','www.example2.com','Laptop 3',15300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('4ee18f15-2d5a-4536-903b-8485517eb0bf','www.example19.com','Laptop 20',17000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('4f3d4296-f298-49bc-8bff-efcfb236f12b','www.example36.com','Laptop 37',18700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('4f9a5cc1-efbb-4cca-8fa9-3778504d640c','www.example27.com','Laptop 28',17800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('503504bb-f8ba-445d-af74-89b3d2314ae0','www.example17.com','Laptop 18',16800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('5048b29b-4f3b-41e7-a857-036ab047ef3a','www.example46.com','Laptop 47',19700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('510b0545-9a90-4101-8c6d-017549422033','www.example40.com','Laptop 41',19100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('5427ce7c-a604-43f5-9206-2cc127b5c8b0','www.example41.com','Laptop 42',19200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('54624785-0857-44e9-9ae8-860640df874b','www.example56.com','Laptop 57',20700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('566b02e1-75b9-4a13-aa3c-2942ca89b911','www.example30.com','Laptop 31',18100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('56e35d08-2bfe-402d-89c9-eae472de1718','www.example8.com','Laptop 9',15900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('5928674c-b2b5-4919-8653-e48ab79489d2','www.example26.com','Laptop 27',17700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('5ab9731b-aae4-40ee-a32a-c62e84407732','www.example77.com','Laptop 78',22800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('5b6fad35-58fb-4887-99eb-9f444c4be099','www.example65.com','Laptop 66',21600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('6489f770-ad06-4541-8019-d66bd1dae98d','www.example43.com','Laptop 44',19400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('65f40248-2dd9-41e2-92b6-7cb4dd43b671','www.example96.com','Laptop 97',24700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('6af375d3-40f0-4705-affb-d96a84386930','www.example61.com','Laptop 62',21200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('6c43262a-674a-479d-8289-63432006d1ff','www.example51.com','Laptop 52',20200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('70d92128-5b46-48ad-a96a-9c53307622b3','www.example94.com','Laptop 95',24500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('77b03a77-8b9e-4118-9d35-87303d19ec96','www.example47.com','Laptop 48',19800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('809693cb-af25-47c9-9571-fa72797fec4c','www.example98.com','Laptop 99',24900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('819d18ae-da5c-4458-8078-418d496e7b04','www.example76.com','Laptop 77',22700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('81ac6f7e-8d0c-41f1-9371-1b93f9dc7e45','www.example66.com','Laptop 67',21700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('827c4a75-087f-4e6d-a034-af913f5d41b0','www.example31.com','Laptop 32',18200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('839f2739-8de4-4729-a654-c41aa8d2adf3','www.example32.com','Laptop 33',18300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('86303249-224d-46da-aae1-972cf73a0829','www.example79.com','Laptop 80',23000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('86a82d6d-9be8-47ac-8613-846ebc0d137e','www.example20.com','Laptop 21',17100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('871a6783-3ed5-4140-8ef8-ca280f716918','www.example62.com','Laptop 63',21300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('88b26c25-1896-40ad-ad8d-1223441cdaaa','www.example95.com','Laptop 96',24600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('8bf95783-104a-4b19-994d-6f48d11e1c03','www.example35.com','Laptop 36',18600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('8e3b3574-e978-4615-9149-674a4c9c8709','www.example78.com','Laptop 79',22900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('8f27fbe8-3c11-41c6-a581-1fc4ceb04026','www.example7.com','Laptop 8',15800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('8f955bb3-14a5-49cb-8d47-81ce51c9c7ed','www.example82.com','Laptop 83',23300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('9029d322-4772-4974-b763-176bdc9d24f6','www.example72.com','Laptop 73',22300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('91ed69a6-c608-4f23-aaeb-8ef793cb9cd4','www.example52.com','Laptop 53',20300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('93e12ada-84ba-454a-95e0-e3edec94d2f0','www.example63.com','Laptop 64',21400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('94cb46f1-02f9-4e97-9718-a0365de83760','www.example85.com','Laptop 86',23600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('95e575ef-afd2-4e70-b49e-ccc5360df4de','www.example38.com','Laptop 39',18900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('97f604d3-1779-4e83-bff7-1182bc0335ed','www.example0.com','Laptop 1',15100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('9955a72a-a6e0-484a-8833-cafd89521822','www.example88.com','Laptop 89',23900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('9ee37933-5b94-44dd-ad0c-caa880f741eb','www.example81.com','Laptop 82',23200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('9f38a2b6-58db-42ca-b973-faa8d71ac9fa','www.example37.com','Laptop 38',18800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a12d5c04-f419-49cf-9069-5427206c1b22','www.example12.com','Laptop 13',16300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a1c12a21-9cba-45b3-a59d-c9a12773b0f8','www.example28.com','Laptop 29',17900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a7614a62-5579-4f92-8b27-3ab90ee88fe4','www.example71.com','Laptop 72',22200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a8045a78-cfef-4158-8b3c-f38d41a2f9e0','www.example29.com','Laptop 30',18000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a9b9f128-e6f2-43ab-a458-69a40b94fb74','www.example91.com','Laptop 92',24200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('a9f59fd8-2c29-4796-be5e-b8741258fca9','www.example58.com','Laptop 59',20900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('aaacba20-2880-4704-90a9-ea40dd6742c6','www.example50.com','Laptop 51',20100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('b48345a7-6c59-4cc4-9adf-56ca3e36e19c','www.example90.com','Laptop 91',24100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('b4d37832-b411-498f-9a35-f558f4824ceb','www.example64.com','Laptop 65',21500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('b7db7a6f-5871-4a93-be17-fca4400612e3','www.example34.com','Laptop 35',18500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('ba57f7f9-d5e7-4fef-9de4-03b19cfc6fb5','www.example57.com','Laptop 58',20800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('bb238706-65e3-4e46-8f18-2352dbd9d751','www.example5.com','Laptop 6',15600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('bc95fbd1-1a17-4c01-bff6-d885bd424282','www.example87.com','Laptop 88',23800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('bdb0c5ff-99ce-4be8-8413-88180e1638ee','www.example1.com','Laptop 2',15200000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('c218c968-7596-48ee-a191-a06903c3410c','www.example70.com','Laptop 71',22100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('c43c2f3e-e661-47b7-abf8-2291f79cdef1','www.example53.com','Laptop 54',20400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('c795a1f8-859c-403b-a9a0-74c9ae2b075c','www.example60.com','Laptop 61',21100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('d1350ea6-7dad-4536-a637-411ad29c47ed','www.example89.com','Laptop 90',24000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('d322382c-206e-44d1-9650-b0747a44db47','www.example45.com','Laptop 46',19600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('d95a98be-7dfb-45b1-bcb2-0e11a109d89f','www.example9.com','Laptop 10',16000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('dcbf9b31-0c5d-40bd-8d24-95b2afe56675','www.example18.com','Laptop 19',16900000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('dd06eb6a-c6f7-4598-9777-a487c5f97e3d','www.example69.com','Laptop 70',22000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('e5f9cb91-bcc5-4451-b8e3-bd54a7bce202','www.example74.com','Laptop 75',22500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('e9658f50-a4ac-438e-b51f-672ba477672d','www.example42.com','Laptop 43',19300000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('ea064079-15b4-4081-88a3-d45b987ab064','www.example10.com','Laptop 11',16100000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('eb9321be-6273-4022-bc25-e941667c670c','www.example49.com','Laptop 50',20000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('eeb2b70e-651b-4698-b1f1-52731fc5c724','www.example16.com','Laptop 17',16700000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f03bd5d0-4336-443e-a669-76a108729a6d','www.example14.com','Laptop 15',16500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f47590ff-6668-4b87-92d5-1ef1171a0eea','www.example59.com','Laptop 60',21000000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f5d6d0ab-40ba-4831-a786-ed428c157f50','www.example97.com','Laptop 98',24800000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f82305e3-2517-4fa3-af11-610c7873f18a','www.example84.com','Laptop 85',23500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f84f7d64-2614-435a-8d75-45f09fd044a5','www.example25.com','Laptop 26',17600000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('f8ee4b76-aeb6-40d4-9b66-ecafb2bba33f','www.example44.com','Laptop 45',19500000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('fa163bc2-485c-4993-befc-b6e7397e96f5','www.example23.com','Laptop 24',17400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f'),
('fb051c6b-86c2-458b-bbc1-4f1e16d85d3e','www.example13.com','Laptop 14',16400000,10,'12150c34-4738-4cc4-a654-f5703bb8568f');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`name`) values 
('12150c34-4738-4cc4-a654-f5703bb8568f','admin1','$2a$10$BJ5l/f/B0JIbVh74Jcsio.twgNrVyxeclulQ9.302NoZhtBYHxvAq','Admin 1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
