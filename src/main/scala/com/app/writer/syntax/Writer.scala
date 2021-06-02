package com.app.writer.syntax

import java.io.FileWriter

import com.app.models.InvalidDetails.invalidDetailsToArray
import com.app.models.ValidDetails.validDetailsToArray
import com.app.models.{InvalidDetails, ValidDetails}
import com.app.writer.Writer
import com.opencsv.CSVWriter

import scala.jdk.CollectionConverters._

object WriterImplementation {

  implicit def writeValidRecords:Writer[String, List[ValidDetails]] = new Writer[String, List[ValidDetails]] {

    override def write(path:String , details: List[ValidDetails]): Unit = {
      val output = new FileWriter(path)
      val writer = new CSVWriter(output)
      val validDetailsArray = details.map(validDetailsToArray(_)).asJava
      writer.writeAll(validDetailsArray)
      output.close()
    }

  }


  implicit def writeInValidRecords:Writer[String, List[InvalidDetails]] = new Writer[String, List[InvalidDetails]] {

    override def write(path:String , details: List[InvalidDetails]): Unit = {
      val output = new FileWriter(path)
      val writer = new CSVWriter(output)
      val inValidDetailsArray = details.map(invalidDetailsToArray(_)).asJava
      writer.writeAll(inValidDetailsArray)
      output.close()
    }

  }
  
}








