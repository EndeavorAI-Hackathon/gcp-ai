# Create a GCS Bucket
#resource "google_storage_bucket" "my_bucket" {
#name     = var.bucket_name
#location = var.region
#}


resource "google_storage_bucket" "function_bucket" {
    name     = "${var.project_id}-function"
    location = var.region
}

resource "google_storage_bucket" "input_bucket" {
    name     = "${var.project_id}-input"
    location = var.region
}