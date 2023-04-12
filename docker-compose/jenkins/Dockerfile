FROM jenkins/jenkins:latest

USER root

# Instala o OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Instala o Maven 3.8.4
RUN apt-get install -y maven

# Configura a variável de ambiente do Maven
ENV MAVEN_HOME /opt/maven
ENV PATH $MAVEN_HOME/bin:$PATH
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
RUN export JAVA_HOME
RUN export MAVEN_HOME
