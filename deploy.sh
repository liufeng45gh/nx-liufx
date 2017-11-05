
ps -ef|grep hfc-1.0-SNAPSHOT.jar|awk '{print $2}'|xargs kill -9

mvn package

cp target/hfc-1.0-SNAPSHOT.jar ./hfc-1.0-SNAPSHOT.jar

nohup java -jar ./hfc-1.0-SNAPSHOT.jar > ./nohup.out &


