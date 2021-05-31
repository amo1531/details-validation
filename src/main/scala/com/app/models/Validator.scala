package com.app.models

trait Validator {

  def validateEmail(details: RawDetails): Either[Error, RawDetails]
  def validatePhoneNumber(details: RawDetails): Either[Error, RawDetails]
  def validatePNR(details: RawDetails): Either[Error, RawDetails]
  def validateCabin(details: RawDetails): Either[Error, RawDetails]
  def validateDate(details: RawDetails): Either[Error, RawDetails]


  def validate[A, B](obj: B, validations: ((B) => Either[A, B])*): Either[List[A], B] =
    combine(obj, validations.toList.map(_.apply(obj)))

  def combine[A, B](obj: B, validations: List[Either[A, B]]): Either[List[A], B] = {
    val errors = validations collect {  case Left(error) => error }
    if (errors.isEmpty) Right(obj) else Left(errors)
  }
}


