package com.app.service.validations

import com.app.models.InvalidEmail
import org.specs2.mutable.Specification

class EmailValidationSpec extends Specification {

  "Email Validation" should {

    "return an error if email field is empty" in {
      val actual = EmailValidation("")

      actual mustEqual Left(InvalidEmail)
    }

    "return an error if email field is invalid" in {
      val invalidEmailsList = List(
                                    "abhishek@zzz",
                                    "hello@example.a",
                                    "hello@example..com",
                                    "hello@.com",
                                    "hello@.com.",
                                    "hello@-example.com"
                                  )

      val actual = invalidEmailsList.map(email => EmailValidation(email))

      actual mustEqual List(
                            Left(InvalidEmail),
                            Left(InvalidEmail),
                            Left(InvalidEmail),
                            Left(InvalidEmail),
                            Left(InvalidEmail),
                            Left(InvalidEmail)
                          )
    }

    "return the email if email field is valid" in {
      val validEmailsList = List(
                                  "abhishek@zzz.com",
                                  "hello@example.com",
                                  "hello@example.co.uk",
                                  "hello-2020@example.com",
                                  "hello.2020@example.com",
                                  "hello_2020@example.com",
                                  "h@example.com"
                                )

      val actual = validEmailsList.map(email => EmailValidation(email))

      actual mustEqual List(
                            Right("abhishek@zzz.com"),
                            Right("hello@example.com"),
                            Right("hello@example.co.uk"),
                            Right("hello-2020@example.com"),
                            Right("hello.2020@example.com"),
                            Right("hello_2020@example.com"),
                            Right("h@example.com")
                          )
    }
  }
}
