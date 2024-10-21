#!/usr/bin/env bash

# Enabling password-based SSH logins for all managed VMs

# Update sshd_config to allow password authentication
if [ -f /etc/ssh/sshd_config ]; then
    sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config
fi

# Apply the same configuration to any files included in /etc/ssh/sshd_config.d/*.conf if they exist
if [ -d /etc/ssh/sshd_config.d ]; then
    sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config.d/*.conf 2>/dev/null
fi

# Ensure PermitRootLogin is set to prohibit-password to prevent root password logins, allowing only key-based root logins
if [ -f /etc/ssh/sshd_config ]; then
    sed -i 's/^\s*#\?\(PermitRootLogin\)\s\+\(yes\|no\|prohibit-password\)\s*$/PermitRootLogin prohibit-password/' /etc/ssh/sshd_config
fi

# Start the SSH service by running the sshd daemon directly
/usr/sbin/sshd

echo "SSH configuration updated."










# # Enabling password-based SSH logins for all managed VMs

# # Update sshd_config to allow password authentication
# sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config

# # Apply the same configuration to any files included in /etc/ssh/sshd_config.d/*.conf if they exist
# sed -i 's/^\s*#\?\(PasswordAuthentication\)\s\+\(yes\|no\)\s*$/PasswordAuthentication yes/' /etc/ssh/sshd_config.d/*.conf 2>/dev/null

# # Ensure PermitRootLogin is set to prohibit-password to prevent root password logins, allowing only key-based root logins
# sed -i 's/^\s*#\?\(PermitRootLogin\)\s\+\(yes\|no\|prohibit-password\)\s*$/PermitRootLogin prohibit-password/' /etc/ssh/sshd_config

# # Restart the SSH service to apply the changes
# service ssh restart

# echo "SSH configuration updated."
