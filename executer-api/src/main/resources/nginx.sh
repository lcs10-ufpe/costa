#!/usr/bin/env bash

echo "update server up and server down"

cd ~/Desktop/'Curso de AWS'/Nginx/

echo "open container nginx"

echo "var1=$1 var2=$2"

docker exec -i nginx sed -i "s/server $1;/server $1 down;/g;s/server $2 down;/server $2;/g" /etc/nginx/conf.d/default.conf

echo "check sintaxe nginx"

docker exec -i nginx nginx -t

echo "reload container nginx"

docker exec -i nginx nginx -s reload


