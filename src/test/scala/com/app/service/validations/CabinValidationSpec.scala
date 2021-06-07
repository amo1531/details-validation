package com.app.service.validations

import com.app.models.Constants.ValidCabins
import com.app.models.InvalidCabin
import org.specs2.mutable.Specification

class CabinValidationSpec extends Specification {
  "Cabin Validation" should {

    "return an error if cabin is invalid" in {
      val invalidCabin = "Supreme Deluxe"

      val actual = CabinValidation(invalidCabin)

      actual mustEqual Left(InvalidCabin)
    }

    "return an error if cabin is empty" in {
      val actual = CabinValidation("")

      actual mustEqual Left(InvalidCabin)
    }

    "return cabin name if the cabin field is valid" in {

      val results = ValidCabins.map(cabin => CabinValidation(cabin))

      results mustEqual List(
                              Right("Economy"),
                              Right("Premium Economy"),
                              Right("Business"),
                              Right("First")
                            )

    }
  }
}
