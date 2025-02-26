

redis-cluster:
	docker run --name redis-container -d -v ./redis:/data -p 7000-7010:7000-7010 redis redis-server /data/7000/redis.conf

connect_redis:
	docker exec -it redis-container /bin/bash
