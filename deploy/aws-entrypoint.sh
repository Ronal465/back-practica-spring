echo $EXTERNAL_IP
echo $EXTERNAL_PORT
cleanup() { 
  echo "Caught SIGTERM signal!"
  kill $(lsof -t -i:1675)
  sleep 2
  echo "process ended"
}
trap 'true' SIGTERM
# start telegraf
telegraf &
# java launch app
java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /var/apps/app.jar $SERVICE_OPTIONS & wait $!
cleanup