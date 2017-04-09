select
  /*%expand*/*
from
  T_BLOCK_QUEUE
where
 twitter_id = /* twitterId */1
and
 queue_status = 1
order by block_queue_serial_id
desc