package com.app.service

import com.app.models
import com.app.models.{RawDetails, Validator}
import com.app.service.validations._

class RawDetailsValidator extends Validator {

  def validateDetails(details: RawDetails): Either[List[models.Error], RawDetails] =  {
    validate(
      details,
      List(
        EmailValidation(details.email),
        PhoneNumberValidation(details.mobileNumber),
        PnrValidation(details.pnr),
        CabinValidation(details.bookedCabin),
        DateValidation(details.bookingDate, details.travelDate)
      )
    )
  }
}
