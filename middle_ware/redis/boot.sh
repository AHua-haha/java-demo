#!/bin/sh

cd /data/7001/
nohup redis-server redis.conf 2>&1 &

cd /data/7002/
nohup redis-server redis.conf 2>&1 &

cd /data/7003/
nohup redis-server redis.conf 2>&1 &

cd /data/7004/
nohup redis-server redis.conf 2>&1 &

cd /data/7005/
nohup redis-server redis.conf 2>&1 &