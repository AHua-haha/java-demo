#!/bin/sh

cd /data/7001/
rm -r appendonlydir/ dump.rdb nodes.conf 
nohup redis-server redis.conf 2>&1 &

cd /data/7002/
rm -r appendonlydir/ dump.rdb nodes.conf 
nohup redis-server redis.conf 2>&1 &

cd /data/7003/
rm -r appendonlydir/ dump.rdb nodes.conf 
nohup redis-server redis.conf 2>&1 &

cd /data/7004/
rm -r appendonlydir/ dump.rdb nodes.conf 
nohup redis-server redis.conf 2>&1 &

cd /data/7005/
rm -r appendonlydir/ dump.rdb nodes.conf 
nohup redis-server redis.conf 2>&1 &

redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 \
127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 \
--cluster-replicas 1