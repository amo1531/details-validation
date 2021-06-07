package com.app.service

import com.app.models.{Details, InvalidDetails, RawDetails, ValidDetails}

object ValidateDetails extends RawDetailsValidator {

  def apply(detailsList: Seq[RawDetails]): Seq[Details] = {
    detailsList map { detail =>
      validateDetails(detail).fold(
        errors => InvalidDetails(detail, errors),
        validDetails => ValidDetails(validDetails)
      )
    }
  }
}