package com.app.service.validations

import com.app.models
import com.app.models.Constants.ValidPhoneNumberLength
import com.app.models.{InvalidMobileNumber, Validation}

object PhoneNumberValidation extends Validation {

  private val areAllDigits: String => Boolean = (str:String) => str.forall(_.isDigit)
  private val isValidLength: String => Boolean = (str:String) => str.length == ValidPhoneNumberLength

  override def apply(field: String): Either[models.Error, String] =
    if(isValidLength(field) && areAllDigits(field)) Right(field) else Left(InvalidMobileNumber)

}