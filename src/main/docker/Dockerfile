FROM openjdk:20-oraclelinux8
MAINTAINER maxtorm <maxtorm12138@gmail.com>

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone


VOLUME /tmp
ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

