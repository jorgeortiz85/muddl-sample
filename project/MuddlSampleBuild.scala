import sbt._
import Keys._

object MuddlSampleBuild extends Build {
  val muddlCompiler =
    ProjectRef(uri("../muddl"), "muddl-compiler")
  val muddlLibrary =
    ProjectRef(uri("../muddl"), "muddl-library")

  lazy val root =
    Project("muddl-sample", file(".")) aggregate(schema, app)
  val schema =
    Project("muddl-sample-schema", file("schema/")) dependsOn(muddlCompiler)
  val app =
    Project("muddl-sample-app", file("app/")) dependsOn(muddlLibrary)
}
