#!/usr/bin/env bash


echo "change the two url in polyeventbackend/pom.xml to your own artifactory url"
echo "------> <url>http://artifactory:8081/artifactory/polyeventbackend</url>"
echo "change the in polyeventbackend/pom.xml to your own artifactory name"
echo "------> <name>5c3c6f24472b-snapshots</name>"

echo "User: florian"
echo "Password: d62317724245475eb12d64a776810935"

sleep 2

docker run --user root -p 8080:8080 -p 50000:50000 -v "$PWD"/jenkins_data/jenkins_blue_ocean:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkinsci/blueocean_mod

