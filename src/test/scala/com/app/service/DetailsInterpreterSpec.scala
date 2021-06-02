package com.app.service

import com.app.models.InvalidInput
import org.specs2.mutable.Specification

class DetailsInterpreterSpec extends Specification {

  "Raw Details InterpreterSpec" should {

    "process the valid data correctly" in {
      val inputFilePath = "src/test/resources/input/correct-input.csv";
      val actual = DetailsInterpreter.processDetails(inputFilePath)

      actual mustEqual "Successfully processed records!"
    }

    "return `InvalidInput` when the data is invalid" in {
      val inputFilePath = "src/test/resources/input/incorrect-input.csv";
      val actual = DetailsInterpreter.processDetails(inputFilePath)

      actual mustEqual InvalidInput.errorMessage
    }
  }

}
//check if the files are written succesfully or not ?