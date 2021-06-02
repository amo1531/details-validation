package com.app.main

import com.app.service.DetailsInterpreter.processDetails

object Main {
  def main(args: Array[String]): Unit = {
    val path = "src/test/resources/input/correct-input.csv"
    println(processDetails(path))
  }
}
