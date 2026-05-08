variable "aws_region" {
  type   = string
  default = "us-east-1"
}
variable "vpc_id" {
  type   = string
  default = "vpc-0cad9f6189361318f"
}

variable "key_name" {
  type   = string
  default = "Damolak"
}



variable "instance_type" {
  type       = string
  default = "t2.micro"
}

variable "ami"{
    type = string
    default = "ami-091138d0f0d41ff90"
}
