FROM tomee:8-jre-7.0.2-webprofile
RUN apt-get update && apt-get install -y nano
ADD tomcat-users.xml /usr/local/tomee/conf/
ADD target/polyeventbackend-war.war /usr/local/tomee/webapps
