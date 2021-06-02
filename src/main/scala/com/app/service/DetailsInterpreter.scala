package com.app.service

import com.app.models.Constants.{InvalidDetailsPath, ValidDetailsPath}
import com.app.models.{Details, InvalidDetails, RawDetails, ValidDetails}
import com.app.reader.Reader.ReadOperation
import com.app.reader.syntax.ReaderImplementation.readFile
import com.app.writer.Writer.WriteOperation
import com.app.writer.syntax.WriterImplementation.{writeInValidRecords, writeValidRecords}

class DetailsInterpreter {

  def processDetails(path: String): String = {

    path.read.fold(
      error => error.errorMessage,
      details => validateAndWrite(details)
    )
  }

  private def validateAndWrite(details: Seq[RawDetails]): String = {

    val detailsAfterValidation = ValidateDetails(details)
    val (validDetails, inValidDetails) = partitionValidatedDetails(detailsAfterValidation)
    validDetails.write(ValidDetailsPath)
    inValidDetails.write(InvalidDetailsPath)
    "Successfully processed records!"
  }


  private def partitionValidatedDetails(validatedDetails: Seq[Details]): (List[ValidDetails], List[InvalidDetails]) = {

    val (validDetailsList, inValidDetailsList) = validatedDetails.partition(_.isInstanceOf[ValidDetails])

    (validDetailsList.asInstanceOf[List[ValidDetails]],
      inValidDetailsList.asInstanceOf[List[InvalidDetails]])
  }
}

object DetailsInterpreter extends DetailsInterpreter
