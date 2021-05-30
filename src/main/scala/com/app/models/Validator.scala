package com.app.models

trait Validator {

  def validateEmail(details: Details): Either[Error, Details]
  def validatePhoneNumber(details: Details): Either[Error, Details]
  def validatePNR(details: Details): Either[Error, Details]
  def validateCabin(details: Details): Either[Error, Details]
  def validateDate(details: Details): Either[Error, Details]


  def validate[A, B](obj: B, validations: ((B) => Either[A, B])*): Either[List[A], B] =
    combine(obj, validations.toList.map(_.apply(obj)))

  def combine[A, B](obj: B, validations: List[Either[A, B]]): Either[List[A], B] = {
    val errors = validations collect {  case Left(error) => error }
    if (errors.isEmpty) Right(obj) else Left(errors)
  }
}


