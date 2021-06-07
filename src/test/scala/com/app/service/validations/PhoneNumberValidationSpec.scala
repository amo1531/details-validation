package com.app.service.validations

import com.app.models.InvalidMobileNumber
import org.specs2.mutable.Specification

class PhoneNumberValidationSpec extends Specification {

  "Phone number validation" should {

    "return an error if the phone number is empty" in {
      val actual = PhoneNumberValidation("")

      actual mustEqual Left(InvalidMobileNumber)
    }

    "return an error if the phone number is less/more than 10 in length" in {
      val invalidPhoneNumbersList = List("987654", "98765498586398")

      val actual = invalidPhoneNumbersList.map(number => PhoneNumberValidation(number))

      actual mustEqual List(Left(InvalidMobileNumber), Left(InvalidMobileNumber))
    }

    "return an error message if the phone number contains any other character than digits" in {
      val invalidPhoneNumbersList = List("987fds8654", "9&7@@$#549")

      val actual  = invalidPhoneNumbersList.map(number => PhoneNumberValidation(number))

      actual mustEqual List(Left(InvalidMobileNumber), Left(InvalidMobileNumber))
    }

    "return the mobile number if it is valid" in {
      val actual = PhoneNumberValidation("9876543210")

      actual mustEqual Right("9876543210")
    }
  }

}
