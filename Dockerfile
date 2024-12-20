FROM debian:bullseye-slim

ENV DEBIAN_FRONTEND=noninteractive
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV SCALA_HOME=/opt/scala
ENV SPARK_HOME=/opt/spark
ENV PATH=$PATH:$JAVA_HOME/bin:$SCALA_HOME/bin:$SPARK_HOME/bin

# Install dependencies
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    git \
    openjdk-11-jdk \
    ca-certificates \
    procps \
    gnupg \
    vim \
    make \
    && rm -rf /var/lib/apt/lists/*

# Download and install Spark 3.5.3
RUN wget https://archive.apache.org/dist/spark/spark-3.5.3/spark-3.5.3-bin-hadoop3.tgz \
    && tar -xzf spark-3.5.3-bin-hadoop3.tgz \
    && mv spark-3.5.3-bin-hadoop3 /opt/spark \
    && rm spark-3.5.3-bin-hadoop3.tgz

# Download and install Scala 2.12.18
RUN wget https://downloads.lightbend.com/scala/2.12.18/scala-2.12.18.tgz \
    && tar -xzf scala-2.12.18.tgz \
    && mv scala-2.12.18 /opt/scala \
    && rm scala-2.12.18.tgz

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list \
    && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add \
    && apt-get update \
    && apt-get install -y sbt

# Set working directory

WORKDIR /app

# Copy the current directory contents into the container at /app
COPY ./sparky_project /app

CMD ["bash"]