package com.app.service.validations


import java.time.LocalDate
import java.time.format.DateTimeFormatter

import com.app.models
import com.app.models.{InvalidBookingDate, InvalidDateFormat, Validation}

import scala.util.{Failure, Success, Try}

object DateValidation extends Validation {

  private def compareDates(booking: String, travel: String): Either[models.Error , String] = {
    val bookingDate = LocalDate.parse(booking)
    val travelDate = LocalDate.parse(travel)
    if(bookingDate.isBefore(travelDate)) Right(booking) else Left(InvalidBookingDate)
  }

  val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  private def parseDate(input: String): Either[models.Error , String] =
    Try {LocalDate.parse(input, format)} match {
    case Failure(_) => Left(InvalidBookingDate)
    case Success(_) => Right(input)
  }

  override def apply(field: String): Either[models.Error, String] = parseDate(field)

  def apply(booking: String, travel: String): Either[models.Error, String] = {
    apply(booking).fold(
      err => Left(InvalidDateFormat),
      booking => apply(travel)
    ).fold(
      err => Left(InvalidDateFormat),
      travel => compareDates(booking, travel)
    )
  }
}

