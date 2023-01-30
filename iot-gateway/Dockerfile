FROM eclipse-temurin:17.0.4.1_1-jdk as builder
WORKDIR app

ADD ./target/*.jar app-service.jar
RUN java -Djarmode=layertools -jar app-service.jar extract

FROM eclipse-temurin:17.0.4.1_1-jdk

#RUN addgroup -S ucc && adduser -S ucc -G ucc && mkdir -p /data/logs && chown -R ucc:ucc /data/logs
#USER ucc:ucc


COPY --from=builder app/dependencies/ /data/app
COPY --from=builder app/spring-boot-loader /data/app
COPY --from=builder app/snapshot-dependencies/ /data/app
COPY --from=builder app/application/ /data/app

RUN groupadd -g 1001 ucc && \
    useradd -u 1001 -g 1001 ucc && \
    mkdir /home/ucc && \
    mkdir /data/logs && \
    chown -R ucc:ucc /home/ucc && \
    chown -R ucc:ucc /data

USER ucc
WORKDIR /data/app
EXPOSE 26610

VOLUME ["/data/logs/", "/data/logs"]

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]