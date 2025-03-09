

redis-cluster:
	docker run --name redis-container -d -w /data/7000 -v ./redis:/data -p 7000-7010:7000-7010 redis redis-server /data/7000/redis.conf

connect_redis:
	docker exec -it redis-container /bin/bash

xxl_job:
	docker run -d \
	-e PARAMS="--spring.datasource.url=jdbc:mysql://172.17.0.2:3306/xxl_job?Unicode=true&characterEncoding=UTF-8 --spring.datasource.username=root --spring.datasource.password=root" \
	-p 8080:8080 \
	-v /home/ahua/workspace/java/demo/middle_ware/xxl-job/applog:/data/applogs \
	--name xxl-job xuxueli/xxl-job-admin:3.0.0
