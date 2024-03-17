CREATE TABLE IF NOT EXISTS Categories (
                    CategoryID		INT	auto_increment,
                    CategoryName	varchar(50),
                    CONSTRAINT pk_Categories PRIMARY KEY(CategoryID)
);

CREATE TABLE IF NOT EXISTS Products (
                    ProductID	INT	auto_increment,
                    CategoryID	INT,
                    ModelNumber	varchar(10),
                    ModelName	varchar(120),
                    ProductImage	varchar(30),
                    UnitCost	decimal(15),
                    Description	text,

                    CONSTRAINT pk_Products PRIMARY KEY(ProductID),
                    CONSTRAINT fk_Products_Categories FOREIGN KEY(CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE IF NOT EXISTS Users (
                    `'UserID` varchar(50) NOT NULL COMMENT '아이디',
                    `UserName` varchar(50) NOT NULL COMMENT '이름',
                    `UserPassword` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                    `UserBirth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                    `UserAuth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                    `UserPoint` int NOT NULL COMMENT 'default : 1000000',
                    `CreatedAt` datetime NOT NULL COMMENT '가입일자',
                    `LatestLoginAt` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                     PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE IF NOT EXISTS Reviews (
                         ReviewID	int auto_increment,
                         ProductID	int,
                         UserID	varchar(50),
                         Rating		int,
                         Comments	text,

                         CONSTRAINT pk_ReviewID PRIMARY KEY(ReviewID),
                         CONSTRAINT fk_Review_Products FOREIGN KEY(ProductID) REFERENCES Products(ProductID),
                         CONSTRAINT fk_Review_Customer FOREIGN KEY(UserID) REFERENCES Users(UserID)
);

CREATE TABLE IF NOT EXISTS Orders (
                        OrderID		int auto_increment,
                        UserID	varchar(50),
                        OrderDate	Datetime,
                        ShipDate	Datetime,

                        CONSTRAINT pk_Orders PRIMARY KEY(OrderID),
                        CONSTRAINT fk_Orders_CustomerID FOREIGN KEY(UserID) REFERENCES Users(UserID)
);

CREATE TABLE IF NOT EXISTS OrderDetails (
                              OrderID		int,
                              ProductID	int,
                              Quantity	int,
                              UnitCost	decimal(15),

                              CONSTRAINT pk_OrderDetails PRIMARY KEY(OrderID, ProductID),
                              CONSTRAINT fk_OrderDetails_Orders FOREIGN KEY(OrderID) REFERENCES Orders(OrderID),
                              CONSTRAINT fk_OrderDetails_Products FOREIGN KEY(ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE IF NOT EXISTS ShoppingCart (
                              RecordID	int	auto_increment,
                              CartID	varchar(150),
                              Quantity	int,
                              ProductID	int,
                              DateCreated	Datetime DEFAULT NOW(),

                              CONSTRAINT pk_RecordID PRIMARY KEY(RecordID),
                              CONSTRAINT fk_cart_ProductID FOREIGN KEY(ProductID) REFERENCES Products(ProductID)
);

MERGE INTO Categories KEY (`CategoryID`) VALUES (1,'식품');
MERGE INTO Categories KEY (`CategoryID`) VALUES (2,'의류');
MERGE INTO Categories KEY (`CategoryID`) VALUES (3,'가전제품');
MERGE INTO Categories KEY (`CategoryID`) VALUES (4,'모바일');

MERGE INTO Products KEY(`ProductID`) VALUES (1, 'P001', '과자', 'image1.jpg', 3000, 'Description for Product 1');
MERGE INTO Products KEY(`ProductID`) VALUES (2, 'P002', '후드티', 'image2.jpg', 5000, 'Description for Product 2');
MERGE INTO Products KEY(`ProductID`) VALUES (3, 'P003', '전자레인지', 'image3.jpg', 2300, 'Description for Product 3');
MERGE INTO Products KEY(`ProductID`) VALUES (4, 'P005', '아이폰', 'image5.jpg', 9000, 'Description for Product 5');

MERGE INTO `Users` KEY (  `UserID` ) VALUES ( 'user1','1234','유저1','1999-01-01','유저',100000,'2023-01-01');
MERGE INTO `Users` KEY (  `UserID` ) VALUES ( 'user2','12345','유저2','1999-02-01','유저',100000,'2023-09-01');
MERGE INTO `Users` KEY (  `UserID` ) VALUES ( 'user3','12346','유저3','1999-03-01','유저',100000,'2023-10-01');
MERGE INTO `Users` KEY (  `UserID` ) VALUES ( 'user4','12347','유저4','1999-04-01','유저',100000,'2023-11-01');
MERGE INTO `Users` KEY (  `UserID` ) VALUES ( 'admin','1234','운영자1','1999-05-01','운영자',100000,'2024-01-01');

MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 1,1,'좋아요',1,'user1' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 2,3,'조금좋아요',1,'user2' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 3,2,'좋아요',2,'user1' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 4,4,'좋아요',2,'user3' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 5,3,'좋아요',2,'user4' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 6,3,'좋아요',3,'user1' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 7,2,'조금좋아요',3,'user2' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 8,1,'아쉬워요',3,'user3' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 9,1,'좋아요',4,'user1' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 10,4,'좋아요',4,'user2' );
MERGE INTO `Reviews` KEY ( `ReviewID` ) VALUES ( 11,3,'보통',4,'user4' );

MERGE INTO `Orders` KEY ( `OrderID` ) VALUES ( 1,'2024-01-13','2024-01-20','user1' );
MERGE INTO `Orders` KEY ( `OrderID` ) VALUES ( 2,'2024-01-14','2024-01-21','user2' );
MERGE INTO `Orders` KEY ( `OrderID` ) VALUES ( 3,'2024-01-15','2024-01-23','user3' );
MERGE INTO `Orders` KEY ( `OrderID` ) VALUES ( 4,'2024-01-16','2024-01-24','user4' );

MERGE INTO `OrderDetails` KEY ( `ProductID`, `OrderID` ) VALUES ( 1,1,1,1000 );
MERGE INTO `OrderDetails` KEY ( `ProductID`, `OrderID` ) VALUES ( 2,2,1,13000 );
MERGE INTO `OrderDetails` KEY ( `ProductID`, `OrderID` ) VALUES ( 3,3,1,12000 );
MERGE INTO `OrderDetails` KEY ( `ProductID`, `OrderID` ) VALUES ( 4,4,1,10000 );

MERGE INTO `ShoppingCart` KEY(`RecordID`) VALUES(1,1,1,1,'2024-01-01');
MERGE INTO `ShoppingCart` KEY(`RecordID`) VALUES(2,1,1,2,'2024-01-01');
MERGE INTO `ShoppingCart` KEY(`RecordID`) VALUES(3,2,1,3,'2024-01-03');
