#!/usr/bin/env bash
# configure hosts file for the internal network
cat >> /etc/hosts <<EOL
# Vagrant environment nodes
192.168.56.20 mgmt
192.168.56
