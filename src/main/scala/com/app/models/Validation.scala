package com.app.models

trait Validation {
  def apply(field: String): Either[Error, String]
}
