# 途中です
#FROM eclipse-temurin:17-jdk-focal as builder
#WORKDIR /usr/local/app/
#COPY . .
#RUN ./gradlew build
#RUN $JAVA_HOME/bin/jlink \
#         --add-modules java.base \
#         --strip-debug \
#         --no-man-pages \
#         --no-header-files \
#         --compress=2 \
#         --output /javaruntime
#
#FROM debian:bullseye-slim
#WORKDIR /usr/local/app/
#ENV JAVA_HOME=/usr/local/bin/java
#ENV PATH "${JAVA_HOME}/bin:${PATH}"
#COPY --from=builder /javaruntime/ ${JAVA_HOME}/
#COPY --from=builder /usr/local/app/build/libs/jdbc_template_example-0.0.1-SNAPSHOT* ./
#
#CMD ["java", "-jar", "/usr/local/app/jdbc_template_example-0.0.1-SNAPSHOT.jar"]
