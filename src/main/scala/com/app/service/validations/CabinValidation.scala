package com.app.service.validations

import com.app.models
import com.app.models.Constants.ValidCabins
import com.app.models.{InvalidCabin, Validation}

object CabinValidation extends Validation {

  private val isCabinValid = (cabin: String) => ValidCabins.exists(str => str.equalsIgnoreCase(cabin))

  override def apply(field: String): Either[models.Error, String] =
    if(isCabinValid(field)) Right(field) else Left(InvalidCabin)
}