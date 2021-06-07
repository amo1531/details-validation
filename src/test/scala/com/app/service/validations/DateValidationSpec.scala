package com.app.service.validations

import com.app.models.{InvalidBookingDate, InvalidDateFormat}
import org.specs2.mutable.Specification

class DateValidationSpec extends Specification {

  "Date Validation" should {

    "return an error if the booking date is after the travel date" in {
      val actual = DateValidation("2019-08-12", "2019-07-31")

      actual mustEqual Left(InvalidBookingDate)
    }

    "return an error if date is empty" in {
      val actual = DateValidation("", "2019-07-31")

      actual mustEqual Left(InvalidDateFormat)
    }

    "return an error if date input is invalid (NOT in `yyyy-MM-dd` format)" in {
      val actual = DateValidation("2018-08", "2019-07-31")

      actual mustEqual Left(InvalidDateFormat)
    }

    "return an error if dates are valid but the format is invalid (NOT in `yyyy-MM-dd` format)" in {
      val actual = DateValidation("2019/07/31", "2019/07/31")

      actual mustEqual Left(InvalidDateFormat)
    }

    "return the date if the booking date is before the travel date" in {
      val actual = DateValidation("2019-05-16", "2019-06-30")

      actual mustEqual Right("2019-05-16")
    }
  }
}
