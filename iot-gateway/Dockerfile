#基础镜像
FROM eclipse-temurin:17.0.4.1_1-jdk as builder
#工作目录
WORKDIR iot-gateway
#将本地文件添加到容器中
ADD ./target/*.jar iot-gateway.jar
RUN java -Djarmode=layertools -jar app-service.jar extract

FROM eclipse-temurin:17.0.4.1_1-jdk

#RUN addgroup -S ucc && adduser -S ucc -G ucc && mkdir -p /data/logs && chown -R ucc:ucc /data/logs
#USER ucc:ucc


COPY --from=builder iot-gateway/dependencies/ /data/iot-gateway
COPY --from=builder iot-gateway/spring-boot-loader /data/iot-gateway
COPY --from=builder iot-gateway/snapshot-dependencies/ /data/iot-gateway
COPY --from=builder iot-gateway/application/ /data/iot-gateway

RUN groupadd -g 1001 iot && \
    useradd -u 1001 -g 1001 iot && \
    mkdir /home/iot && \
    mkdir /data/logs && \
    chown -R iot:iot /home/iot && \
    chown -R iot:iot /data

USER iot
WORKDIR /data/iot-gateway
EXPOSE 26610

VOLUME ["/data/logs/", "/data/logs"]

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]