package com.foursquare.muddlsample.db

import com.foursquare.muddlsample.serialization.MongoDeserializer
import com.mongodb.casbah.Imports.DBObject

class Databases(cxs: DatabaseConnections, deser: MongoDeserializer) {
  def deserF[A](f: MongoDeserializer => DBObject => Option[A]): DBObject => A =
    dbo => f(deser)(dbo).getOrElse(throw new RuntimeException("Failed to deserialize: " + dbo))

  val checkins = Database(cxs.checkinsDb("checkins"), deserF(_.newCheckin))
  // val venues = Database(cxs.venuesDb("venues"), deserF(_.newVenue))
}
