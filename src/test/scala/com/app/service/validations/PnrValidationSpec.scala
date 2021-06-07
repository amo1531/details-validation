package com.app.service.validations

import com.app.models.InvalidPNR
import org.specs2.mutable.Specification

class PnrValidationSpec extends Specification {

  "PNR Validation" should {
    "return an error message if pnr is not alphanumeric" in {
      val actual = PnrValidation("AB@123")

      actual mustEqual Left(InvalidPNR)
    }

    "return an error message if pnr is alphanumeric but length is more/less than 6" in {
      val actual = PnrValidation("AB23")

      actual mustEqual Left(InvalidPNR)
    }

    "return an error message if pnr empty" in {
      val actual = PnrValidation("")

      actual mustEqual Left(InvalidPNR)
    }

    "return the pnr if it is valid" in {
      val actual = PnrValidation("ABC234")

      actual mustEqual Right("ABC234")
    }
  }

}
