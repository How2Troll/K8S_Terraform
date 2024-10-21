# Load Balancer Dockerfile
FROM ubuntu:22.04

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Install necessary packages including Nginx, SSH, sudo, Python, and Supervisor
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    nginx \
    openssh-server \
    sudo \
    curl \
    ca-certificates \
    python3 \
    python3-pip \
    supervisor && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    rm -rf /var/lib/apt/lists/*

# Create necessary directories for SSH and Supervisor
RUN mkdir -p /var/run/sshd /var/log/supervisor

# Add a user with sudo privileges
RUN useradd -ms /bin/bash vagrant && \
    echo 'vagrant:vagrant' | chpasswd && \
    usermod -aG sudo vagrant && \
    echo 'vagrant ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

# Enable SSH password authentication and restrict root login
RUN sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config && \
    sed -i 's/^\s*#\?\(PermitRootLogin\)\s\+\(yes\|no\|prohibit-password\)\s*$/PermitRootLogin prohibit-password/' /etc/ssh/sshd_config

# Copy Supervisor configuration file directly to the correct location
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Copy the custom nginx configuration to the container
COPY nginx.conf /etc/nginx/nginx.conf

# Expose HTTP and SSH ports
EXPOSE 80 22

# Start Supervisor to manage both services
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]
