create table tbl_all_users
(
username varchar(100) not null primary key,
password varchar(100) not null,
otp varchar(100) DEFAULT NULL,
otpvalidity timestamp NULL, 
roles varchar(100) not null,
firstname varchar(100) not null,
lastname varchar(100) not null,
emailid varchar(100) not null,
department varchar(100) not null,
ssn varchar(10) null,
createdby varchar(100) not null,
createddate timestamp not null,
updatedby varchar(100) not null,
updateddate timestamp not null
);

delete from tbl_all_users;
#password is admin
insert into tbl_all_users 
values("admin","21232f297a57a5a743894a0e4a801fc3", null, null, "ROLE_VALID_USER","dexter","morgan","ramkumar007@gmail.com","customer",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());

#password is euser
insert into tbl_all_users 
values("euser","3960edfad5d748670763a3ded95af414", null, null, "ROLE_VALID_USER","debra","morgan","ramkumar007@gmail.com","customer",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());

#password is sysad
insert into tbl_all_users 
values("sysad","a3fe41c36274fa31157d64bd152c8eeb", null, null, "ROLE_VALID_USER","Balin","Fundin","shardul27@gmail.com","customer",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());

#password is ceo
insert into tbl_all_users 
values("corporate","55161575f3e05dfb61145c5d63d67d29", null, null, "ROLE_VALID_USER","David","Gin","ramkumar007@gmail.com","corporate",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());
#password is ceo
insert into tbl_all_users 
values("corporate2","55161575f3e05dfb61145c5d63d67d29", null, null, "ROLE_VALID_USER","David","Gin","ramkumar007@gmail.com","corporate",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());
#password is ceo
insert into tbl_all_users 
values("corporate3","55161575f3e05dfb61145c5d63d67d29", null, null, "ROLE_VALID_USER","David","Gin","ramkumar007@gmail.com","corporate",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());


#password is moheetB
insert into tbl_all_users 
values("moheetB","9e9f217121645aedc2c4cf6996705671", null, null, "ROLE_VALID_USER","Sheldon","Cooper","moheet.bhute@gmail.com","transaction",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());

#password is gborse
insert into tbl_all_users 
values("gborse","b13ea122e813b9e13fc4f5eba9f7975f", null, null, "ROLE_VALID_USER","Howard","Wolowitz","gborse@asu.edu","transaction",null,substring_index(user(),'@',1), curdate(),substring_index(user(),'@',1), curdate());

