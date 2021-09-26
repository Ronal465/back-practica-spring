#!/bin/sh
export EXTERNAL_IP=$(hostname -i)
echo $EXTERNAL_IP
# start telegraf
telegraf &
# java launch app
java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /var/apps/app.jar $SERVICE_OPTIONS