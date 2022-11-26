use pbl4;

-- Delete old data
DELETE FROM user_role_file;
DELETE FROM file;
DELETE FROM user;

-- Insert new Data
-- insert user
INSERT INTO `pbl4`.`user` (`id`, `username`, `password`, `firstname`, `lastname`, `birthday`, `gender`, `phonenumber`, `email`, `created_date`, `modified_date`) VALUES ('1', 'dangvannhatminh', '123456', 'Đặng Văn Nhật', 'Minh', '2002-05-28', 1, '0905050501', 'dangvannhatminh@gmail.com', '2022-11-23', '2022-11-23');
INSERT INTO `pbl4`.`user` (`id`, `username`, `password`, `firstname`, `lastname`, `birthday`, `gender`, `phonenumber`, `email`, `created_date`, `modified_date`) VALUES ('2', 'huynhdinhhoangvien', '123456', 'Huỳnh Đinh Hoàng Viên', 'Hưng', '2002-03-13', 1, '0905050502', 'huynhdinhhoangvien@gmail.com', '2022-11-23', '2022-11-23');
INSERT INTO `pbl4`.`user` (`id`, `username`, `password`, `firstname`, `lastname`, `birthday`, `gender`, `phonenumber`, `email`, `created_date`, `modified_date`) VALUES ('3', 'trancongnguyenhai', '123456', 'Trần Công Nguyên', 'Hải', '2002-09-20', 1, '0905050503', 'trancongnguyenhai@gmail.com', '2022-11-23', '2022-11-23');
INSERT INTO `pbl4`.`user` (`id`, `username`, `password`, `firstname`, `lastname`, `birthday`, `gender`, `phonenumber`, `email`, `created_date`, `modified_date`) VALUES ('4', 'nguyenthexuanly', '123456', 'Nguyễn Thế Xuân', 'Ly', '1980-04-16', 1, '0905050504', 'nguyenthexuanly@gmail.com', '2022-11-23', '2022-11-23');

-- insert file
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('1', 'dangvannhatminh', '0', '1', 'StoreLocation\\dangvannhatminh', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('2', 'huynhdinhhoangvien', '0', '2', 'StoreLocation\\huynhdinhhoangvien', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('3', 'trancongnguyenhai', '0', '3', 'StoreLocation\\trancongnguyenhai', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('4', 'nguyenthexuanly', '0', '4', 'StoreLocation\\nguyenthexuanly', '2022-11-23', '2022-11-23', '1');

INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('5', 'Chuong trinh dich', '1', '1', 'StoreLocation\\dangvannhatminh\\Chuong trinh dich', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('6', 'CNXH Khoa hoc', '1', '2', 'StoreLocation\\huynhdinhhoangvien\\CNXH Khoa hoc', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('7', 'Lap trinh linux', '1', '3', 'StoreLocation\\trancongnguyenhai\\Lap trinh linux', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('8', 'Dien toan dam may', '1', '4', 'StoreLocation\\nguyenthexuanly\\Dien toan dam may', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('9', 'Lap trinh mang', '1', '1', 'StoreLocation\\dangvannhatminh\\Lap trinh mang', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('10', 'Cong nghe Web', '1', '2', 'StoreLocation\\huynhdinhhoangvien\\Cong nghe Web', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('11', 'Thi nghiem vat ly', '1', '3', 'StoreLocation\\trancongnguyenhai\\Thi nghiem vat ly', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('12', 'Mang may tinh', '1', '4', 'StoreLocation\\nguyenthexuanly\\Mang may tinh', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('13', 'Toan ung dung CNTT', '1', '1', 'StoreLocation\\dangvannhatminh\\Toan ung dung CNTT', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('14', 'Phap luat dai cuong', '1', '2', 'StoreLocation\\huynhdinhhoangvien\\Phap luat dai cuong', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('15', 'Xu ly tin hieu so', '1', '3', 'StoreLocation\\trancongnguyenhai\\Xu ly tin hieu so', '2022-11-23', '2022-11-23', '1');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('16', 'PBL4', '1', '4', 'StoreLocation\\nguyenthexuanly\\PBL4', '2022-11-23', '2022-11-23', '1');

INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('17', 'code1.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Chuong trinh dich\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('18', 'code2.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Chuong trinh dich\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('19', 'code3.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Chuong trinh dich\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('20', 'code1.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Lap trinh mang\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('21', 'code2.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Lap trinh mang\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('22', 'code3.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Lap trinh mang\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('23', 'code1.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Toan ung dung CNTT\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('24', 'code2.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Toan ung dung CNTT\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('25', 'code3.txt', '2', '1', 'StoreLocation\\dangvannhatminh\\Toan ung dung CNTT\\code3.txt', '2022-11-23', '2022-11-23', '2');

INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('26', 'code1.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\CNXH Khoa hoc\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('27', 'code2.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\CNXH Khoa hoc\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('28', 'code3.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\CNXH Khoa hoc\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('29', 'code1.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Cong nghe Web\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('30', 'code2.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Cong nghe Web\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('31', 'code3.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Cong nghe Web\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('32', 'code1.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Phap luat dai cuong\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('33', 'code2.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Phap luat dai cuong\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('34', 'code3.txt', '2', '2', 'StoreLocation\\huynhdinhhoangvien\\Phap luat dai cuong\\code3.txt', '2022-11-23', '2022-11-23', '2');


INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('35', 'code1.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Lap trinh linux\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('36', 'code2.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Lap trinh linux\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('37', 'code3.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Lap trinh linux\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('38', 'code1.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Thi nghiem vat ly\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('39', 'code2.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Thi nghiem vat ly\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('40', 'code3.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Thi nghiem vat ly\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('41', 'code1.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Xu ly tin hieu so\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('42', 'code2.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Xu ly tin hieu so\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('43', 'code3.txt', '2', '3', 'StoreLocation\\trancongnguyenhai\\Xu ly tin hieu so\\code3.txt', '2022-11-23', '2022-11-23', '2');


INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('44', 'code1.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Dien toan dam may\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('45', 'code2.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Dien toan dam may\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('46', 'code3.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Dien toan dam may\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('47', 'code1.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Mang may tinh\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('48', 'code2.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Mang may tinh\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('49', 'code3.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\Mang may tinh\\code3.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('50', 'code1.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\PBL4\\code1.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('51', 'code2.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\PBL4\\code2.txt', '2022-11-23', '2022-11-23', '2');
INSERT INTO `pbl4`.`file` (`id`, `name`, `node_id`, `owner_id`, `path`, `created_date`, `modified_date`, `type_id`) VALUES ('52', 'code3.txt', '2', '4', 'StoreLocation\\nguyenthexuanly\\PBL4\\code3.txt', '2022-11-23', '2022-11-23', '2');


-- Insert role
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (1, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (2, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (3, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (4, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (5, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (6, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (7, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (8, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (9, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (10, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (11, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (12, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (13, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (14, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (15, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (16, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (17, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (18, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (19, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (20, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (21, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (22, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (23, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (24, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (25, 1, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (26, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (27, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (28, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (29, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (30, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (31, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (32, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (33, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (34, 2, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (35, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (36, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (37, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (38, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (39, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (40, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (41, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (42, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (43, 3, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (44, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (45, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (46, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (47, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (48, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (49, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (50, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (51, 4, 1, 1);
INSERT INTO `pbl4`.`user_role_file` (`file_id`, `user_id`, `readPermission`, `updatePermission`) VALUES (52, 4, 1, 1);

