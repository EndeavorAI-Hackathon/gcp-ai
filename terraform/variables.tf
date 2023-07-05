variable "project_id" {
  type = string
  default = "hackathon2023-390809"
}

variable "region" {
  type = string
  default = "us-central1"
}  

variable "bucket_name" {
   description = "GCS Bucket name. Value should be unique ."
   type        = string
}
