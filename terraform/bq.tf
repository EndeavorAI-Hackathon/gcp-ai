resource "google_bigquery_dataset" "default" {
  dataset_id                  = "endeavor"
  friendly_name               = "endeavor"
  description                 = "Dataset for Analytical purpose"
  location                    = "europe-west3"
  default_table_expiration_ms = 960000000000

  labels = {
    env = "default"
  }
}
