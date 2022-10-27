use pbl4;
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (1,'dangvannhatminh33',0,'StoreLocation\\dangvannhatminh33','2022-10-26','2022-10-26',1);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (2,'a.txt',1,'StoreLocation\\dangvannhatminh33\\a.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (3,'b.txt',1,'StoreLocation\\dangvannhatminh33\\b.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (4,'c.txt',1,'StoreLocation\\dangvannhatminh33\\c.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (5,'d.pdf',1,'StoreLocation\\dangvannhatminh33\\d.pdf','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (6,'e.txt',1,'StoreLocation\\dangvannhatminh33\\e.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (7,'f.txt',1,'StoreLocation\\dangvannhatminh33\\f.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (8,'g.txt',1,'StoreLocation\\dangvannhatminh33\\g.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (9,'h.txt',1,'StoreLocation\\dangvannhatminh33\\h.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (10,'h.txt',1,'StoreLocation\\dangvannhatminh33\\h.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (11,'i.txt',1,'StoreLocation\\dangvannhatminh33\\i.txt','2022-10-26','2022-10-26',2);
INSERT INTO `file` (`id`,`name`,`node_id`,`path`,`created_date`,`modified_date`,`type_id`) VALUES (12,'j.txt',1,'StoreLocation\\dangvannhatminh33\\j.txt','2022-10-26','2022-10-26',2);

INSERT INTO user(id,username,password,firstname,lastname,birthday,image,gender,phonenumber,email,created_date,modified_date) values (1,NULL,'1234567',NULL,NULL,NULL,NULL,0,NULL,'dangvannhatminh33@gmail.com','2022-10-26','2022-10-26');


INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (1,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (2,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (3,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (4,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (5,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (6,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (7,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (8,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (9,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (10,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (11,1,1,1,1);
INSERT INTO `user_role_file` (`file_id`,`user_id`,`owner_id`,`readPermission`,`updatePermission`) VALUES (12,1,1,1,1);