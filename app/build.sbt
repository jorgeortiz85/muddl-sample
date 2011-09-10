sourceGenerators in Compile <+= (fullClasspath in (schema, Runtime), runner in schema, sourceManaged, streams) map { (cp, r, out, s) =>
  r.run("com.foursquare.muddlsample.schema.SchemaGen", cp.files, out.absolutePath :: Nil, s.log) foreach error
  (out ** "*.scala") get
}

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-collection" % "1.1",
  "org.mongodb" % "mongo-java-driver" % "2.5.3",
  "com.mongodb.casbah" %% "casbah" % "2.1.5.0")
