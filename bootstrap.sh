#!/usr/bin/env bash

# Set timezone to Europe/Lisbon without interactive prompt
ln -fs /usr/share/zoneinfo/Europe/Lisbon /etc/localtime
apk add --no-cache tzdata
cp /etc/localtime /etc/timezone

# Update and upgrade packages
apk update
apk upgrade

# Install basic tools
apk add --no-cache iputils
apk add --no-cache tzdata
apk add --no-cache unzip
apk add --no-cache build-base  # Equivalent to build-essential
apk add --no-cache openssl-dev  # Equivalent to libssl-dev
apk add --no-cache libffi-dev
apk add --no-cache gnupg
apk add --no-cache python3-dev
apk add --no-cache py3-pip  # Equivalent to python3-pip
apk add --no-cache ufw
apk add --no-cache nmap
apk add --no-cache net-tools

# Install Ansible (Alpine repositories do not have ansible PPA)
apk add --no-cache ansible

# Clean up cached packages
rm -rf /var/cache/apk/*
