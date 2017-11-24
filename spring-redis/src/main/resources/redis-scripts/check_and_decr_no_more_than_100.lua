--[[

redis-cli eval "$(cat check_and_decr_no_more_than_100.lua)" 1 key amount total
  key: the key
  amount: the increment

]]
if redis.call("EXISTS",KEYS[1]) == 1 then
  local current = tonumber(redis.call("get", KEYS[1]))
  local amount = tonumber(ARGV[1])
  local tostore = current - amount
  if tostore > 0 and tostore < 100 then
    return "left amount less than 100"
  end
  if tostore >= 0 then
    redis.call("set", KEYS[1], tostring(tostore))
    return tostore
  else
    return "less than zero"
  end
else
  return "missvalue"
end
