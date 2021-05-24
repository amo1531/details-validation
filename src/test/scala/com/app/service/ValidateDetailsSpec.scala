package com.app.service

import com.app.models.{Details, InvalidDetails, ValidDetails}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class ValidateDetailsSpec extends Specification {
  "Validate Details" should {
    "categorize details in valid and invalid details" in new Fixture {
      val expected = Seq(
        ValidDetails(Details("Abhishek", "Kumar", "ABC123", "F", "2019-05-21", "2", "2019-07-31", "abhishek@zzz.com", "9876543210", "Economy"), "OFFER_30"),
        ValidDetails(Details("Kalyani", "Ben", "A1B2C3", "M", "2019-05-21", "1", "2019-06-30", "kben@zzz.com", "9876543213", "Premium Economy"), "OFFER_25"),
        InvalidDetails(Details("Radhika", "Suresh", "ZZZ345", "T", "2019-05-21", "4", "2019-05-31", "radhika@zzz", "9876543212", "Business"), "Invalid Email ID"),
        ValidDetails(Details("Somnath", "Batra", "X1Y2Z4", "Z", "2019-05-23", "3", "2019-07-25", "sbatra@zzz.com", "9876543214", "Economy"), ""),
        InvalidDetails(Details("Monin", "Sankar", "PQ234", "C", "2019-05-22", "2", "2019-08-30", "monin@zzz.com", "9876543211", "Economy"), "Invalid PNR")
      )

      val actual = ValidateDetails.apply(details)

      actual mustEqual expected
    }
  }

  trait Fixture extends Scope {

    val details = Seq(
      Details("Abhishek","Kumar","ABC123","F","2019-05-21","2","2019-07-31","abhishek@zzz.com","9876543210","Economy"),
      Details("Kalyani","Ben","A1B2C3","M","2019-05-21","1","2019-06-30","kben@zzz.com","9876543213","Premium Economy"),
      Details("Radhika","Suresh","ZZZ345","T","2019-05-21","4","2019-05-31","radhika@zzz","9876543212","Business"),
      Details("Somnath","Batra","X1Y2Z4","Z","2019-05-23","3","2019-07-25","sbatra@zzz.com","9876543214","Economy"),
      Details("Monin","Sankar","PQ234","C","2019-05-22","2","2019-08-30","monin@zzz.com","9876543211","Economy")
    )
  }
}