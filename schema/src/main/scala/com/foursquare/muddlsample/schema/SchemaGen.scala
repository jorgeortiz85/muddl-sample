package com.foursquare.muddlsample.schema

import com.foursquare.muddl.compiler.{CodeGen, Reflection, RecordSchema}
import scalax.file.Path
import scalax.io.Codec

object SchemaGen {
  def main(args: Array[String]): Unit = {
    val outputDir = Path(args(0))
    for (schema <- Vector(new Checkin, new Mention, new Venue)) {
      val result = CodeGen.codeGen(schema)
      val annotatedSchema = result.annotatedSchema
      val code = result.code

      val filePath = outputDir / Path(annotatedSchema.packageName.replace('.', '/'))
      val outputFile = filePath / Path(annotatedSchema.className + ".scala")
      implicit val codec = Codec.UTF8
      outputFile.createFile(failIfExists=false)
      outputFile.write(code)
      outputFile.append('\n')
    }
  }
}
