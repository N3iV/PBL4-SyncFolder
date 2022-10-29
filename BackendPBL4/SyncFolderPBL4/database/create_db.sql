USE pbl4;
CREATE TABLE type(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) 
);
CREATE TABLE file(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    node_id INT NOT NULL,
    owner_id INT NOT NULL,
    path LONGTEXT NOT NULL,
    created_date DATE,
    modified_date DATE,
    type_id INT,
    CONSTRAINT FK_File_Type FOREIGN KEY (type_id) REFERENCES type(id)
);
CREATE TABLE user(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) ,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) ,
    lastname VARCHAR(255) ,
    birthday DATE ,
    image TEXT ,
    gender BIT ,
    phonenumber VARCHAR(255) ,
    email VARCHAR(255) NOT NULL,
    created_date DATE ,
    modified_date DATE 
);
CREATE TABLE user_role_file(
	file_id INT,
    user_id INT,
    readPermission BIT NOT NULL,
    updatePermission BIT NOT NULL,
    CONSTRAINT PK_user_file PRIMARY KEY (file_id,user_id),
    CONSTRAINT FK_role_file FOREIGN KEY (file_id) REFERENCES file(id),
    CONSTRAINT FK_role_user FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into type(id,name) values(1,"Directory");
insert into type(id,name) values(2,"File");