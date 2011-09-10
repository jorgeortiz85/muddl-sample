package com.foursquare.muddlsample.schema

import com.foursquare.muddl.compiler.RecordSchema
import org.bson.types.ObjectId

class Checkin extends RecordSchema {
  val id = field[ObjectId](1, "_id").required_!
  val userid = field[Long](2, "uid").required_!
  val venueid = field[ObjectId](3, "venueid")
  val oauthConsumer = field[ObjectId](4, "oa")
  val place = field[String](5, "place")
  val shout = field[String](6, "shout")
  val silent = field[Boolean](7, "silent").required_!
  val send2twitter = field[Boolean](8, "s2tw").required_!
  val send2facebook = field[Boolean](9, "s2fb").required_!
  val send2followers = field[Boolean](10, "s2fol").required_!

  val points = field[Int](11, "points")
  val mentions = field[Mention](12, "m")
  val cheat = field[Boolean](13, "cht")
  val geocheat = field[Boolean](14, "gcht")
  val geocheatDistance = field[Int](15, "gchtd")
  val legacyeventid = field[Long](16, "eid")
  val mayorScore = field[Double](17, "ms")
  val commentCount = field[Int](18, "cc")
  val photoCount = field[Int](19, "pc")
  val verified = field[Boolean](20, "vf")
}

class Mention extends RecordSchema {
  val rangeStart = field[Int](1, "rs").required_!
  val rangeEnd = field[Int](2, "re").required_!
  val userid = field[Long](3, "uid").required_!
}
