package com.app.service.validations

import com.app.models
import com.app.models.Constants.EmailRegExPattern
import com.app.models.{InvalidEmail, Validation}

object EmailValidation extends Validation {

  override def apply(field: String): Either[models.Error, String] = field match {
    case email if email.trim.isEmpty                                   => Left(InvalidEmail)
    case email if EmailRegExPattern.findFirstMatchIn(email).isDefined  => Right(field)
    case _                                                             => Left(InvalidEmail)
  }

}
