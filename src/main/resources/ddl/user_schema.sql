create table opb_user(
     `username` varchar(100) primary key,
     `name` varchar(255),
     `password` varchar(100),
     `roles` varchar(255),
     `active` int comment '0 inactive | 1 active'
);