package com.app.service

import com.app.models.InvalidInput
import org.specs2.mutable.Specification

class RawDetailsInterpreterSpec extends Specification {

  "Raw Details InterpreterSpec" should {

    "process the valid data correctly" in {
      val inputFilePath = "src/test/resources/input/valid-input.csv";
      val actual = DetailsInterpreter.processDetails(inputFilePath)

      actual mustEqual "Successfully processed records!"
    }

    "return `InvalidInput` when the data is invalid" in {
      val inputFilePath = "src/test/resources/input/invalid-input.csv";
      val actual = DetailsInterpreter.processDetails(inputFilePath)

      actual mustEqual InvalidInput.errorMessage
    }
  }

}
