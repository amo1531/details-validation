package com.app.models

trait Validator {
  def validate[A, B, C](obj: B, validations: List[Either[A, C]]): Either[List[A], B] = {
    val errors = validations collect {  case Left(error) => error }
    if (errors.isEmpty) Right(obj) else Left(errors)
  }
}


