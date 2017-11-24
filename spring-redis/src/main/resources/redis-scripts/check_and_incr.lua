--[[

redis-cli eval "$(cat check_and_incr.lua)" 1 key amount total
  key: the key
  amount: the increment
  total: the total amount

]]
if redis.call("EXISTS",KEYS[1]) == 1 then
  local current = tonumber(redis.call("get", KEYS[1]))
  local amount = tonumber(ARGV[1])
  local tostore = current + amount
  local total = tonumber(ARGV[2])
  if current == total then
    return "sell-out"
  end
  if tostore <= total then
    redis.call("set", KEYS[1], tostring(tostore))
    return tostore
  else
    return "insufficient"
  end
else
  return "missvalue"
end
