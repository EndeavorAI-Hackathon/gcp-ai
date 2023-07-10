data "archive_file" "hackathon_zip" {
type = "zip"
source_dir ="${path.root}"/hackathon/"
output_path = "${path.root}/hackathon.zip"
}

resource "google_storage_bucket" "hackathon_bucket" {
name = "hackathon_test"

}



resource "google_storage_bucket_object"  "hackathon_zip" {
name = "hackathon_zip"
bucket = "${google_storage_bucket.hackathon_bucket.name}"
source = "${path.root}/hackathon.zip"

}