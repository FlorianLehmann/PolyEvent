#!/usr/bin/env bash

# build external services

# hyperplanning
echo "Build hyperplanning"
cd dotnet/hyperplanning
./compile.sh
echo "Leaving hyperplanning"
cd ../..

# comptabilite
echo "Build comptabilite"
cd dotnet/comptabilite
./compile.sh
echo "Leaving comptabilite"
cd ../..

# build polyevent
cd polyeventbackend
echo "Build polyevent"
mvn clean install
echo "Leaving polyevent"
cd ..

# build client
cd polyeventclients
echo "Build clients"
mvn clean install
echo "Leaving clients"
cd ..

# build docker images

# Build Polyevent image

cd polyeventbackend/polyeventbackendwar
echo "Build polyevent docker image"
docker build . -t polyeventbackend:latest
cd ../..

echo "Build polyevent docker image"
cd dotnet/comptabilite
docker build . --tag=comptabilite:latest
cd ../..

cd dotnet/hyperplanning
echo "Build hyperplanning docker image"
docker build . --tag=hyperplanning:latest
cd ../..

cd jenkins_data
echo "Build hyperplanning docker image"
docker build . --tag=jenkinsci/blueocean_mod
cd ..

docker-compose build