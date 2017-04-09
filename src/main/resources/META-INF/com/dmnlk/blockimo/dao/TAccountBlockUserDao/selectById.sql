select
  /*%expand*/*
from
  T_ACCOUNT_BLOCK_USER
where
  twitter_id = /* twitterId */1
  and
  block_user_twitter_id = /* blockUserTwitterId */1
