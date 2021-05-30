package com.app.reader.syntax

import com.app.models.{RawDetails, Error, InvalidInput}
import com.app.reader.Reader

import scala.io.Source
import scala.util.Try

object ReaderImplementation {

  implicit def readFile: Reader[String, Seq[RawDetails]] = new Reader[String, Seq[RawDetails]] {
    override def read(path: String): Either[Error, Seq[RawDetails]] = {
      Try(
        readCSVFrom(path)
      ).fold(
        _ => Left(InvalidInput),
        details => Right(details)
      )
    }
  }

  private def readCSVFrom(path: String): Seq[RawDetails] = {
    for {
      line <-  Source.fromFile(path).getLines().drop(1)
     // First_name, Last_name, PNR, Fare_class, Travel_date, Pax, Ticketing_date, Email, Mobile_phone, Booked_cabin
      Array(firstName, lastName, pnr, fareClass, travelDate, pax, bookingDate, email, mobileNumber, bookedCabin) =
      line.split(",").map(_.trim)
    }yield RawDetails(firstName, lastName, pnr, fareClass, bookingDate, pax, travelDate, email, mobileNumber, bookedCabin)
  }.toList
}

