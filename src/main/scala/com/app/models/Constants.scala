package com.app.models

object Constants {
  val EmailRegExPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$".r
  val PnrRegExPattern = "^[a-zA-Z0-9]{6}".r
  val ValidCabins = List (
    "Economy",
    "Premium Economy",
    "Business",
    "First"
  )
  val ValidPhoneNumberLength = 10
  val A_to_E = 'A' to 'E'
  val F_to_K = 'F' to 'K'
  val L_to_R = 'L' to 'R'
  val OFFER_20 = "OFFER_20"
  val OFFER_25 = "OFFER_25"
  val OFFER_30 = "OFFER_30"
  val NO_OFFER = "NO_OFFER"

  val OfferForRange = Map(
    A_to_E -> OFFER_20,
    F_to_K -> OFFER_30,
    L_to_R -> OFFER_25
  )
}
