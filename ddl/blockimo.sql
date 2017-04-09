SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_ACCOUNT;
DROP TABLE IF EXISTS T_ACCOUNT_BLOCK_USER;
DROP TABLE IF EXISTS T_BLOCK_QUEUE;
DROP TABLE IF EXISTS T_USER_QUEUE;




/* Create Tables */

CREATE TABLE T_ACCOUNT
(
	twitter_id bigint NOT NULL,
	screen_id text,
	access_token text,
	access_token_secret text,
	next_batch_execute_date timestamp,
	block_user_cursor bigint,
	friend_block_flg boolean DEFAULT 'false',
	follower_block_flg boolean DEFAULT 'false',
	auto_block_enable_flg boolean DEFAULT 'true',
	add_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	PRIMARY KEY (twitter_id)
);


CREATE TABLE T_ACCOUNT_BLOCK_USER
(
	twitter_id bigint NOT NULL,
	block_user_twitter_id bigint NOT NULL,
	add_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	PRIMARY KEY (twitter_id, block_user_twitter_id)
);


CREATE TABLE T_BLOCK_QUEUE
(
	block_queue_serial_id bigint NOT NULL AUTO_INCREMENT,
	twitter_id bigint NOT NULL,
	block_user_twitter_id bigint NOT NULL,
	queue_status int NOT NULL,
	block_user_screen_name text,
	add_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	PRIMARY KEY (block_queue_serial_id)
);


CREATE TABLE T_USER_QUEUE
(
	block_user_queue_serial_id bigint NOT NULL AUTO_INCREMENT,
	twitter_id bigint NOT NULL,
	register_twitter_id bigint NOT NULL,
	register_screen_name text NOT NULL,
	next_cursor bigint NOT NULL,
	add_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	PRIMARY KEY (block_user_queue_serial_id)
);



