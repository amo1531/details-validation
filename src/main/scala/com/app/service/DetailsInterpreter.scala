package com.app.service

import com.app.models.{Details, InvalidDetails, RawDetails, ValidDetails}
import com.app.reader.Reader.ReadOperation
import com.app.reader.syntax.ReaderImplementation.readFile

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
//    validDetails.write(validInputPath)
//    inValidDetails.write(inValidInputPath)
    "Successfully processed records!"
  }


  private def partitionValidatedDetails(validatedDetails: Seq[Details]): (List[ValidDetails], List[InvalidDetails]) = {

    val (validDetailsList, inValidDetailsList) = validatedDetails.partition(_.isInstanceOf[ValidDetails])

    (validDetailsList.asInstanceOf[List[ValidDetails]],
      inValidDetailsList.asInstanceOf[List[InvalidDetails]])
  }
}

object DetailsInterpreter extends DetailsInterpreter
