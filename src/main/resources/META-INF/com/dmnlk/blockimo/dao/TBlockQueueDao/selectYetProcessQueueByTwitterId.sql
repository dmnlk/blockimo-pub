select
  /*%expand*/*
from
  T_BLOCK_QUEUE
where
  twitter_id = /* twitterId */1
and
  queue_status = 0
limit 100;
