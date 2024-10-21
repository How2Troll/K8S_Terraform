variable "host" {}
variable "client_certificate" {}
variable "client_key" {}
variable "cluster_ca_certificate" {}
variable "project" {}


data "google_client_config" "default" {}