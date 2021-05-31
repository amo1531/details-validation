package com.app.models

import com.app.models.Constants.{A_to_E, F_to_K, L_to_R, OfferForRange}


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
      case _ if isInRange(A_to_E) => OfferForRange(A_to_E)
      case _ if isInRange(F_to_K) => OfferForRange(F_to_K)
      case _ if isInRange(L_to_R) => OfferForRange(L_to_R)
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