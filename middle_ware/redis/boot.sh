#!/bin/sh

cd /data/clust/7000/
nohup redis-server redis.conf 2>&1 &

cd /data/clsuter/7001/
nohup redis-server redis.conf 2>&1 &

cd /data/clsuter/7002/
nohup redis-server redis.conf 2>&1 &

cd /data/clsuter/7003/
nohup redis-server redis.conf 2>&1 &

cd /data/cluster/7004/
nohup redis-server redis.conf 2>&1 &

cd /data/cluster/7005/
nohup redis-server redis.conf 2>&1 &