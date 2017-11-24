--[[

redis-cli eval "$(cat setnx_and_expire.lua)" 1 key amount expire
  key: the key
  amount: the increment
  expire: expire time

]]
if redis.call("SETNX", KEYS[1], ARGV[1]) == 1 then
    local expireResult = redis.call("expire", KEYS[1], ARGV[2])
    if expireResult == 1 then
        return "success"
    else
        return "expire failed"
    end
else
  return "setnx not null"
end
