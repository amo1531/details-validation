package com.app.models

import com.app.models.Constants.{offerForRange, rangeA_To_E, rangeF_To_K, rangeL_To_R}


sealed trait Details

//First_name, Last_name, PNR, Fare_class, Travel_date, Pax, Ticketing_date, Email, Mobile_phone, Booked_cabin
final case class RawDetails(firstName: String,
                            lastName: String,
                            pnr: String,
                            fareClass: String,
                            bookingDate: String,
                            pax: String,
                            travelDate: String,
                            email: String,
                            mobileNumber: String,
                            bookedCabin: String) extends Details

final case class ValidDetails(details:RawDetails,
                              disCountCode: String) extends Details

final case class InvalidDetails(details: RawDetails,
                                errorReason: String) extends Details

object ValidDetails {

  def validDetailsToArray(input: ValidDetails): Array[String] = {
    Array(
      input.details.firstName,
      input.details.lastName,
      input.details.pnr,
      input.details.fareClass,
      input.details.bookingDate,
      input.details.pax,
      input.details.travelDate,
      input.details.email,
      input.details.mobileNumber,
      input.details.bookedCabin,
      input.disCountCode
    )
  }

  def getDisCountCode(fareGrade: String): String = {
    val fareClass = fareGrade.toCharArray.head
    val isInRange = (range: Seq[Char]) => range.contains(fareClass)

    fareClass match {
      case _ if isInRange(rangeA_To_E) => offerForRange(rangeA_To_E)
      case _ if isInRange(rangeF_To_K) => offerForRange(rangeF_To_K)
      case _ if isInRange(rangeL_To_R) => offerForRange(rangeL_To_R)
      case _ => ""
    }
  }

  def apply(details: RawDetails): ValidDetails =
    new ValidDetails(details, getDisCountCode(details.fareClass))

}

object InvalidDetails {

  def invalidDetailsToArray(input: InvalidDetails): Array[String] = {
    Array(
      input.details.firstName,
      input.details.lastName,
      input.details.pnr,
      input.details.fareClass,
      input.details.bookingDate,
      input.details.pax,
      input.details.travelDate,
      input.details.email,
      input.details.mobileNumber,
      input.details.bookedCabin,
      input.errorReason
    )
  }

  def apply(details: RawDetails, errorReason: List[Error]): InvalidDetails =
    new InvalidDetails(details, errorReason.map(_.errorMessage).mkString(" & "))

}