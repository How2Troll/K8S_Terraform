resource "kubernetes_service" "auth_service" {
  metadata {
    name = "auth-service"
  }

  spec {
    selector = {
      app = "auth-service"
    }

    port {
      port        = 8081
      target_port = 8081
    }

    type = "LoadBalancer"
  }
}

resource "kubernetes_service" "user_service" {
  metadata {
    name = "user-service"
  }

  spec {
    selector = {
      app = "user-service"
    }

    port {
      port        = 8080
      target_port = 8080
    }

    type = "LoadBalancer"
  }
}
