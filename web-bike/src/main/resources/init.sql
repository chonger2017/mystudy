create table if not exists t_bikes(
 id bigint auto_increment,
 status int not null,
 longitude double not null,
 latitude double not null,
 primary key (id)
) engine=InnoDB default charset=utf8;