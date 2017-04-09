select
  /*%expand*/*
from
  T_BLOCK_QUEUE
where
  twitter_id = /* twitterId */1
  and
  block_user_twitter_id = /* blockTwitterId */1
limit 1