package com.app.service.validations

import com.app.models
import com.app.models.Constants.PnrRegExPattern
import com.app.models.{InvalidPNR, Validation}

object PnrValidation extends Validation {

  override def apply(field: String): Either[models.Error, String] = field match {
    case pnr if pnr.trim.isEmpty              => Left(InvalidPNR)
    case pnr if PnrRegExPattern.matches(pnr)  => Right(field)
    case _                                    => Left(InvalidPNR)
  }
}