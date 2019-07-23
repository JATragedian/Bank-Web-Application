#!/usr/bin/env bash

cp devops/docker/Dockerfile target

docker build --no-cache='false' --tag "student-project-1907:$1" target
