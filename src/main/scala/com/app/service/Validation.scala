package com.app.service

import java.time.LocalDate

import com.app.models
import com.app.models.Constants._
import com.app.models._
import com.app.service.Validations.ValidationError

class Validation extends Validator {

  type ValidationError = models.Error
  type ValidationErrorOr[RawDetails] = Either[ValidationError, RawDetails]

  override def validateEmail(details: RawDetails): ValidationErrorOr[RawDetails] = details.email match {
    case email if email.trim.isEmpty                                   => Left(InvalidEmail)
    case email if EmailRegExPattern.findFirstMatchIn(email).isDefined  => Right(details)
    case _                                                             => Left(InvalidEmail)
  }

  override def validateDate(details: RawDetails): ValidationErrorOr[RawDetails] = {

    def compareDates(booking: String, travel: String): Either[InvalidBookingDate.type, RawDetails] = {
      val bookingDate = LocalDate.parse(booking)
      val travelDate = LocalDate.parse(travel)
      if(bookingDate.isBefore(travelDate)) Right(details) else Left(InvalidBookingDate)
    }

    (details.bookingDate.trim, details.travelDate.trim) match {
      case ("" ,"") | ("", _) | (_, "")         => Left(InvalidBookingDate)
      case (bookingDate, travelDate)            => compareDates(bookingDate, travelDate)
      case _                                    => Left(InvalidBookingDate)
    }

  }

  override def validatePhoneNumber(details: RawDetails): ValidationErrorOr[RawDetails] = {
    def areAllDigits: String => Boolean = (str:String) => str.forall(_.isDigit)
    def isValidLength: String => Boolean = (str:String) => str.length == ValidPhoneNumberLength
    if(isValidLength(details.mobileNumber) && areAllDigits(details.mobileNumber))
      Right(details)
    else
      Left(InvalidMobileNumber)
  }

  override def validatePNR(details: RawDetails): ValidationErrorOr[RawDetails] = details.pnr match {
    case pnr if pnr.trim.isEmpty              => Left(InvalidPNR)
    case pnr if PnrRegExPattern.matches(pnr)  => Right(details)
    case _                                    => Left(InvalidPNR)
  }

  override def validateCabin(details: RawDetails): ValidationErrorOr[RawDetails] = {
    val isCabinValid = (cabin: String) => ValidCabins.exists(str => str.equalsIgnoreCase(cabin))

    if(isCabinValid(details.bookedCabin)) Right(details) else Left(InvalidCabin)
  }

  def validateDetails(details: RawDetails): Either[List[ValidationError], RawDetails] = {
    validate(
      details,
      validateEmail,
      validateDate,
      validatePhoneNumber,
      validatePNR,
      validateCabin
    )
  }
}

object Validations extends Validation