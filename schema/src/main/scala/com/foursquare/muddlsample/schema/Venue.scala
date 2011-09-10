package com.foursquare.muddlsample.schema

import com.foursquare.muddl.compiler.RecordSchema
import org.bson.types.ObjectId

class Venue extends RecordSchema {
  val id = field[ObjectId](1, "_id").required_!
  val legacyid = field[Long](2, "legid")
  val oauthConsumer = field[ObjectId](3, "oa")
  val userid = field[Long](4, "userid").required_!
  val venuename = field[String](5, "venuename").required_!
  val keywords = field[String](6, "keywords")
  val address = field[String](7, "address")
  val crossstreet = field[String](8, "crossstreet")
  val city = field[String](9, "city")
  val state = field[String](10, "state")
  val county = field[String](11, "county")
  val zip = field[String](12, "zip")
  val country = field[String](13, "country")
  val countrycode = field[String](14, "cc")
  val url = field[String](15, "url")
  val description = field[String](16, "description")
  val twitterName = field[String](17, "twitter_name")
  val phone = field[String](18, "phone")
  val closed = field[Boolean](19, "closed")
  val locked = field[Boolean](20, "locked")
  val verified = field[Boolean](21, "verified")
  val locationOverride = field[Boolean](22, "location_override")
  val ownLatLng = field[Boolean](23, "oll")
  // TODO(Jorge): DateTime
  // val ownLatLngSince = field[DateTime](24, "oll_d")
  val geomobile = field[Boolean](25, "geomobile")
  val friendsOnly = field[Boolean](26, "friends_only")
  val mayor = field[Long](27, "mayor")
  val mayorCount = field[Int](28, "mayor_count")
  val popularTip = field[ObjectId](29, "pt")
  val totalCheckins = field[Int](30, "tcs")
  val uniqueVisitors = field[Int](31, "uvs")
  val totalTips = field[Int](32, "tts")
  val providerId = field[Int](33, "provider_id")
  val providerVenueId = field[Int](34, "provider_venueid")
  val georadius = field[Int](35, "georadius")
  val shortUrl = field[String](36, "short_url")
  val aliases = field[String](37, "aliases").repeated
  val keywordList = field[String](38, "keywordList").repeated
  // TODO(Jorge): WTF, Any?
  // val tombstones = field[Any](39, "tombstones").repeated
  val decayedPopularity = field[Double](40, "decayedPopularity").repeated
  // TODO(Jorge)): DateTime
  // val popularityUpdated = field[DateTime](41, "popularityUpdated")
  val partitionedPopularity = field[Int](42, "partitionedPopularity").repeated
  val venuetip = field[ObjectId](43, "venuetip")
  val storeid = field[String](44, "storeid")
  val nids = field[Int](45, "nids").repeated
  val participateVenueStats = field[Boolean](46, "pvs")
}
