/**
User Schema
 */
create table users(
  id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) not null,
	password VARCHAR(60) not null,
	enabled boolean not null
);

create unique index ix_users_username on users (username);

create table authorities (
  id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id bigint unsigned NOT NULL,
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) not null,
	constraint fk_authorities_users foreign key(user_id) references users(id)
);
create unique index ix_authorities_authority_username on authorities (authority,username);


/**
Group Authorities
 */
create table groups (
	id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
	group_name VARCHAR(50) not null
);

create unique index ix_groups_group_name on groups (group_name);

create table group_authorities (
  id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
	group_id bigint unsigned not null,
	authority VARCHAR(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id bigint unsigned not null,
	group_id bigint unsigned not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id),
	constraint fk_group_members_user foreign key(user_id) references users(id)
);

/**
Persistent Token Approach(RememberMe)
 */
create table persistent_logins (
	username VARCHAR(64) not null,
	series VARCHAR(64) primary key,
	token VARCHAR(64) not null,
	last_used timestamp not null
);


/*
INSERT INTO users(`username`, `password`, `enabled`)
  VALUES('admin','123456', 1);

INSERT INTO authorities(`user_id`,`username`,`authority`)
  VALUES(1,'admin','ROLE_USER,ROLE_ADMIN');

INSERT INTO authorities(`user_id`,`username`,`authority`)
  VALUES(2,'user','ROLE_USER');*/
