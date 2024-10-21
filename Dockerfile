#FROM docker:23.0.1
FROM alpine:3.17

# Install necessary packages
RUN apk add --no-cache openjdk17 maven curl bash openssh
# Set working directory
WORKDIR /app

#COPY . .
COPY bootstrap.sh /app/
COPY hosts_ip.sh /app/
COPY hosts_ssh.sh /app/

COPY pom.xml /app/

COPY auth-service/pom.xml /app/auth-service/
COPY auth-service/src /app/auth-service/src

COPY calendar-service/pom.xml /app/calendar-service/
COPY calendar-service/src /app/calendar-service/src

COPY user-service/pom.xml /app/user-service/
COPY user-service/src /app/user-service/src

# Ensure the scripts have executable permissions
RUN chmod +x bootstrap.sh hosts_ip.sh hosts_ssh.sh

# Copy the Maven project files (use .dockerignore to avoid unnecessary files)

# Build the Maven project
RUN mvn clean install -X

# Start the services (update paths to match where the scripts are located)
CMD ["/bin/bash", "-c", "/app/bootstrap.sh && /app/hosts_ip.sh && /app/hosts_ssh.sh && tail -f /dev/null"]
