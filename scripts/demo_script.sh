#!/bin/bash

echo "Running Terraform apply..."

../terraform/terraform init
../terraform/terraform apply -auto-approve

gcloud container clusters get-credentials calendar --zone europe-west6-a --project agisit-2425-website-111295

IP_ADDRESS=$(kubectl get service balancer -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

kubectl delete all --all
../k8s/kubectl apply -f .

kubectl delete pods --all

sleep 2m

# Function to log and execute curls

log_and_execute() {
    echo "Executing: $1" | tee -a $LOG_FILE
    eval "$2" 2>&1 | tee -a $LOG_FILE
    echo "\n--------------------------------------\n"
}

# Curl commands with descriptions
log_and_execute "Create user Szymon" \
    "curl --silent --show-error --location '$IP_ADDRESS/user-service/user' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        \"name\": \"Szymon\",
        \"email\": \"szymon@tecnico.ulisboa.pt\"
    }'"

log_and_execute "Create user Igor" \
    "curl --silent --show-error --location '$IP_ADDRESS/user-service/user' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        \"name\": \"Igor\",
        \"email\": \"Igor@tecnico.ulisboa.pt\"
    }'"

log_and_execute "Create user JeremiasF" \
    "curl --silent --show-error --location '$IP_ADDRESS/user-service/user' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        \"name\": \"JeremiasF\",
        \"email\": \"jeremias@tecnico.ulisboa.pt\"
    }'"

log_and_execute "Register user JeremiasF" \
    "curl --silent --show-error --location '$IP_ADDRESS/auth-service/auth' \
    --header 'Content-Type: application/json' \
    --data '{
        \"username\": \"JeremiasF\",
        \"password\": \"mypassword\"
    }'"

log_and_execute "Login with correct credentials for JeremiasF" \
    "curl --silent --show-error --location '$IP_ADDRESS/auth-service/auth/login' \
    --header 'Content-Type: application/json' \
    --data '{
        \"username\": \"JeremiasF\",
        \"password\": \"mypassword\"
    }'"

log_and_execute "Login with wrong credentials for JeremiasF" \
    "curl --silent --show-error --location '$IP_ADDRESS/auth-service/auth/login' \
    --header 'Content-Type: application/json' \
    --data '{
        \"username\": \"JeremiasF\",
        \"password\": \"wrongpassword\"
    }'"

log_and_execute "Create a calendar event for My Birthday" \
    "curl --silent --show-error --location '$IP_ADDRESS/calendar-service/calendar' \
    --header 'Content-Type: application/json' \
    --data '{
        \"title\": \"My Birthday \",
        \"description\": \"Come have fun!\",
        \"startTime\": \"2024-11-11T20:00:00\",
        \"endTime\": \"2024-11-11T22:00:00\",
        \"userId\": 3
    }'"

log_and_execute "Add participants to calendar event 1" \
    "curl --silent --show-error --location '$IP_ADDRESS/calendar-service/calendar/1/participants' \
    --header 'Content-Type: application/json' \
    --data '{
        \"userIds\": [1,2]
    }'"

log_and_execute "Get all calendar events for user JeremiasF" \
    "curl --silent --show-error --location --request GET '$IP_ADDRESS/calendar-service/calendar/3' \
    --header 'Content-Type: application/json'"

log_and_execute "Get participants for calendar event 1" \
    "curl --silent --show-error --location --request GET '$IP_ADDRESS/calendar-service/calendar/1/participants' \
    --header 'Content-Type: application/json'"


explorer.exe "http://$IP_ADDRESS/prometheus/service-discovery"
