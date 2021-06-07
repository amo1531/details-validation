package com.app.models

sealed trait Error {
  def errorMessage: String
}

case object InvalidInput extends Error {
  override def errorMessage: String = "Invalid Input"
}

case object InvalidEmail extends Error {
  override def errorMessage: String = "Invalid Email ID"
}

case object InvalidMobileNumber extends Error {
  override def errorMessage: String = "Invalid Mobile Number"
}

case object InvalidPNR extends Error {
  override def errorMessage: String = "Invalid PNR"
}

case object InvalidCabin extends Error {
  override def errorMessage: String = "Invalid Cabin"
}

case object InvalidDateFormat extends Error {
  override def errorMessage: String = "Invalid Date Format"
}

case object InvalidBookingDate extends Error {
  override def errorMessage: String = "Invalid Booking Date"
}
