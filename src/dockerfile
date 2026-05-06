FROM maven:4.0.0-openjdk-17 AS build
WORKDIR /app/Studentportal
COPY . /app/Studentportal/
RUN mvn -b clean package

FROM openjdk:8-slim
COPY --from=builder /app/Studentportal/target/Studentportal.jar /
CMD ["java", "-jar", "/Studentportal.jar"]
