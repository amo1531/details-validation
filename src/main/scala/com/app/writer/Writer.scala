package com.app.writer

trait Writer[A, B] {
  def write(path: A, data: B): Unit
}

object Writer {
  implicit class WriteOperation[B](input: B) {
    def write[A](path: A)(implicit writer: Writer[A, B]): Unit = writer.write(path, input)
  }
}
