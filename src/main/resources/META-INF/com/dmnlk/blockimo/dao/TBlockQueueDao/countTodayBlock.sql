SELECT
  count(1)
FROM T_BLOCK_QUEUE
WHERE
  twitter_id  = /* twitterId */1
and
  queue_status = 1
and
  update_date >= date(current_timestamp - interval 1 day)
and
  update_date < date(now());