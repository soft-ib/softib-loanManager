FROM openjdk:8-jdk-alpine
EXPOSE 8093
COPY target/soft-ib-loan-manager-0.0.1-SNAPSHOT.war soft-ib-loan-manager-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/soft-ib-loan-manager-0.0.1-SNAPSHOT.war"]