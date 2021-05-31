package com.app.service

import com.app.models.{RawDetails, InvalidBookingDate, InvalidCabin, InvalidEmail, InvalidMobileNumber, InvalidPNR}
import org.specs2.mutable.Specification

class ValidationsSpec extends Specification {
  "Validations" >> {

    "validate email" should {
      "return an error message if email in details is empty" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31"," ","9876543210","Economy")

        val actual  = Validations.validateEmail(details)

        actual mustEqual Left(InvalidEmail)
      }

      "return an error message if email in details is invalid" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validateEmail(details)

        actual mustEqual Left(InvalidEmail)
      }

      "return the details if email in details is valid" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz.com","9876543210","Economy")

        val actual  = Validations.validateEmail(details)

        actual mustEqual Right(details)
      }
    }

    "validate mobile number" should {
      "return an error message if the mobile number in details is less/more than 10 in length" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz","987654","Economy")

        val actual  = Validations.validatePhoneNumber(details)

        actual mustEqual Left(InvalidMobileNumber)
      }

      "return an error message if the mobile number in details does not contain only digits" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz","987fds8654","Economy")

        val actual  = Validations.validatePhoneNumber(details)

        actual mustEqual Left(InvalidMobileNumber)
      }

      "return the details if mobile number in details is valid" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz.com","9876543210","Economy")

        val actual  = Validations.validatePhoneNumber(details)

        actual mustEqual Right(details)
      }
    }

    "validate booking date" should {
      "return an error message if the bookingDate in details is after the travelDate" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-08-21","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validateDate(details)

        actual mustEqual Left(InvalidBookingDate)
      }

      "return an error message if the bookingDate in details is empty" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F"," ","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validateDate(details)

        actual mustEqual Left(InvalidBookingDate)
      }

      "return an error message if the travelDate in details is empty" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-07-31","2"," ","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validateDate(details)

        actual mustEqual Left(InvalidBookingDate)
      }

      "return details message if the bookingDate in details is before the travelDate" in {
        val details =  RawDetails("Kalyani","Ben","A1B2C3","M","2019-05-21","1","2019-06-30","kben@zzz.com","9876543213","Premium Economy")

        val actual  = Validations.validateDate(details)

        actual mustEqual Right(details)
      }
    }

    "validate pnr" should {
      "return an error message if pnr is not alphanumeric" in {
        val details = RawDetails("Abhishek","Kumar","AB@123","F","2019-08-21","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validatePNR(details)

        actual mustEqual Left(InvalidPNR)
      }

      "return an error message if pnr is alphanumeric but length is more/less than 6" in {
        val details = RawDetails("Abhishek","Kumar","AB23","F","2019-08-21","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validatePNR(details)

        actual mustEqual Left(InvalidPNR)
      }

      "return details if the pnr is valid alphanumeric code" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-08-21","2","2019-07-31","abhishek@zzz","9876543210","Economy")

        val actual  = Validations.validatePNR(details)

        actual mustEqual Right(details)
      }
    }

    "validate cabin" should {
      "return an error message if cabin is not valid" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-08-21","2","2019-07-31","abhishek@zzz","9876543210","Supreme")

        val actual  = Validations.validateCabin(details)

        actual mustEqual Left(InvalidCabin)
      }

      "return details if the cabin is valid" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-08-21","2","2019-07-31",
          "abhishek@zzz","9876543210","premium economy")

        val actual  = Validations.validatePNR(details)

        actual mustEqual Right(details)
      }
    }

    "validate details" should {

      "apply all the validations on details and return error messages in case of error" in {
        val details = RawDetails("Abhishek","Kumar","AB@123","F","2019-08-21","2","2019-07-31",
          "abhishek@zzz","9876543210","premium economy")

        val actual = Validations.validateDetails(details)

        actual mustEqual Left(List(InvalidEmail, InvalidBookingDate, InvalidPNR))

      }

      "apply all the validations on details and return details if no error occurred" in {
        val details = RawDetails("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz.co.in","9876543210","Economy")

        val actual = Validations.validateDetails(details)

        actual mustEqual Right(details)

      }
    }
  }
}
