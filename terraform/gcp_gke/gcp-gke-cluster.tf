# Terraform google cloud multi tier Kubernetes deployment
# AGISIT Lab Cloud Native on a Cloud-Hosted Kubernetes

variable "region" {
    type = string
}

variable "project" {
    type = string
}

variable "workers_count" {
    type = number
}

#####################################################################
# GKE cluster Definition
resource "google_container_cluster" "calendar" {
  name     = "calendar"
  project = var.project
  location = var.region
  initial_node_count = var.workers_count
  deletion_protection = false

  timeouts {
    create = "15m"
    delete = "15m"
  }

  addons_config {
    network_policy_config {
      disabled = true
    }
  }

  # Definition of Cluster Nodes
  node_config {
    machine_type = "e2-medium"

    disk_size_gb = 40

    oauth_scopes = [
      "https://www.googleapis.com/auth/devstorage.read_only",
      "https://www.googleapis.com/auth/logging.write",
      "https://www.googleapis.com/auth/monitoring",
      "https://www.googleapis.com/auth/service.management.readonly",
      "https://www.googleapis.com/auth/servicecontrol",
      "https://www.googleapis.com/auth/trace.append",
      "https://www.googleapis.com/auth/compute",
    ]
  }
}


#####################################################################
# Output for K8S
#####################################################################
output "client_certificate" {
  value     = google_container_cluster.calendar.master_auth.0.client_certificate
  sensitive = true
}

output "client_key" {
  value     = google_container_cluster.calendar.master_auth.0.client_key
  sensitive = true
}

output "cluster_ca_certificate" {
  value     = google_container_cluster.calendar.master_auth.0.cluster_ca_certificate
  sensitive = true
}

output "host" {
  value     = google_container_cluster.calendar.endpoint
  sensitive = true
}

