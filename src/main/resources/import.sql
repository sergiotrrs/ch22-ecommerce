INSERT INTO `customer`(`first_name`, `last_name`, `email`, `password`, `is_active`, `avatar`) VALUES ("Patricio", "Estrella", "estrella@gmail.com","123", 1, "https://randomuser.me/api/portraits/men/78.jpg");
INSERT INTO `customer`(`first_name`, `last_name`, `email`, `password`, `is_active`, `avatar`) VALUES ("Selena","Gómez", "gomez@gmail.com","123", 1, "https://randomuser.me/api/portraits/women/66.jpg");
INSERT INTO `customer`(`first_name`, `last_name`, `email`, `password`, `is_active`, `avatar`) VALUES ("Benito", "Bodoque", "bodoque@gmail.com","123", 0, "https://randomuser.me/api/portraits/men/0.jpg");
INSERT INTO `customer`(`first_name`, `last_name`, `email`, `password`, `is_active`, `avatar`) VALUES ("Edna", "Krabappel", "edna@gmail.com","123", 1, "https://randomuser.me/api/portraits/women/88.jpg");
INSERT INTO `customer`(`first_name`, `last_name`, `email`, `password`, `is_active`, `avatar`) VALUES ("Cristiano", "Ronaldo", "siu@gmail.com","123", 0, "https://randomuser.me/api/portraits/men/3.jpg");

INSERT INTO `address`(`address`,`zip_code`,`city`, `fk_id_customer`) VALUES ("Fondo de Bikini 10","0625","Bikini", 1);
INSERT INTO `address`(`address`,`zip_code`,`city`, `fk_id_customer`) VALUES ("Av. Siempre viva 12","0742","Springfield", 1);
INSERT INTO `address`(`address`,`zip_code`,`city`, `fk_id_customer`) VALUES ("Av. Revolución 234","0742","Springfield", 2);