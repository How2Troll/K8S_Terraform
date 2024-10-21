# Use an Ubuntu base image
FROM ubuntu:22.04

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Install MySQL and SSH
RUN apt-get update && \
    apt-get install -y mysql-server openssh-server sudo sshpass && \
    rm -rf /var/lib/apt/lists/*

# Create necessary directories for SSH
RUN mkdir /var/run/sshd

# Set a proper home directory for the mysql user
RUN usermod -d /var/lib/mysql mysql

# Add the vagrant user with sudo privileges
RUN useradd -ms /bin/bash vagrant && \
    echo 'vagrant:vagrant' | chpasswd && \
    usermod -aG sudo vagrant && \
    echo 'vagrant ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

# Enable SSH password authentication
RUN sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config

# Update MySQL bind-address to allow external connections
RUN sed -i 's/bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf

# Start MySQL and create the calendar_db and grant privileges
RUN service mysql start && \
    mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS calendar_db;" && \
    mysql -uroot -proot -e "CREATE USER IF NOT EXISTS 'calendar_user'@'%' IDENTIFIED WITH mysql_native_password BY '4X+8a?qo4uLc'; GRANT ALL PRIVILEGES ON calendar_db.* TO 'calendar_user'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES;"

# Expose MySQL and SSH ports
EXPOSE 3306 22

# Start MySQL and SSH daemon
CMD service mysql start && service ssh start && tail -f /dev/null
