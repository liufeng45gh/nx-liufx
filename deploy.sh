
ps -ef|grep nx-liufx-1.0-SNAPSHOT.jar|awk '{print $2}'|xargs kill -9

mvn package

cp target/nx-liufx-1.0-SNAPSHOT.jar ./nx-liufx-1.0-SNAPSHOT.jar

nohup java -jar ./nx-liufx-1.0-SNAPSHOT.jar > ./nohup.out &


