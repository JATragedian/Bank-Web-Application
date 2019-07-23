#!/usr/bin/env bash

docker rm -f student-project-1907_$1
docker run -p $2:8080 --restart always --name student-project-1907_$1 -d student-project-1907:$1
