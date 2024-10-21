resource "kubernetes_service" "balancer" {
  metadata {
    name = "balancer"
  }

  spec {
    selector = {
      app = "balancer"
    }

    port {
      port        = 8083
      target_port = 80
    }

    type = "LoadBalancer"
  }
}


resource "kubernetes_deployment" "mysql" {
  metadata {
    name = "mysql"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "mysql"
      }
    }

    template {
      metadata {
        labels = {
          app = "mysql"
        }
      }

      spec {
        container {
          image = "mysql:latest"
          name  = "mysql"
          env {
            name  = "MYSQL_ROOT_PASSWORD"
            value = "your-root-password"
          }
          env {
            name  = "MYSQL_DATABASE"
            value = "calendar_db"
          }
          port {
            container_port = 3306
          }
        }
      }
    }
  }
}


resource "kubernetes_deployment" "auth_service" {
  metadata {
    name = "auth-service"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "auth-service"
      }
    }

    template {
      metadata {
        labels = {
          app = "auth-service"
        }
      }

      spec {
        container {
          image = "gcr.io/${var.project}/auth-service:latest"
          name  = "auth-service"
          port {
            container_port = 8081
          }
        }
      }
    }
  }
}

resource "kubernetes_deployment" "user_service" {
  metadata {
    name = "user-service"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "user-service"
      }
    }

    template {
      metadata {
        labels = {
          app = "user-service"
        }
      }

      spec {
        container {
          image = "gcr.io/${var.project}/user-service:latest"
          name  = "user-service"
          port {
            container_port = 8080
          }
        }
      }
    }
  }
}

resource "helm_release" "prometheus" {
  name             = "prometheus"
  repository       = "https://prometheus-community.github.io/helm-charts"
  namespace        = "monitoring"
  chart      = "prometheus" 
  create_namespace = true

  # Optionally, specify custom values for Prometheus
  # values = [
  #   file("path/to/your/custom_values.yaml")
  # ]
}
