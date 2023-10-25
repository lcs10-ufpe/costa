#!/usr/bin/env bash

echo "update server up and server down"

cd ~/Desktop/'Curso de AWS'/Nginx/

echo "open container nginx"

docker exec -it nginx sed -i "s/server $1;/server $1 down;/g;s/server $2 down;/server $2;/g" /etc/nginx/conf.d/default.conf

echo "check sintaxe nginx"

docker exec -it nginx nginx -t

echo "reload container nginx"

docker exec -it nginx nginx -s reload


