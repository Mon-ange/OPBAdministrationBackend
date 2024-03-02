create table opb_shift_arrangement(
      `id` int auto_increment primary key,
      `username` varchar(100),
      `start` datetime,
      `end` datetime,
      `status` varchar(32) comment 'active | cancelled',
      foreign key(username) references opb_user(username)
);