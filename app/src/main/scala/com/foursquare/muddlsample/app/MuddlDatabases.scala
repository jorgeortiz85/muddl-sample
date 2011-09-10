package com.foursquare.muddlsample.app

import com.foursquare.muddl.DeserializationException
import com.foursquare.muddlsample.db.{Database, DatabaseConnections}
import com.foursquare.muddlsample.serialization.MongoDeserializer
import com.mongodb.casbah.Imports.DBObject

class MuddlDatabases(cxs: DatabaseConnections, deser: MongoDeserializer) {
  def deserF[A](f: MongoDeserializer => DBObject => Option[A])(dbo: DBObject): A = {
    var caughtException: DeserializationException = null
    val opt = try {
      f(deser)(dbo)
    } catch {
      case e: DeserializationException =>
        caughtException = e
        None
    }
    opt.getOrElse(throw new RuntimeException("Failed to deserialize: " + dbo, caughtException))
  }

  val checkins = Database(cxs.checkinsDb("checkins"), deserF(_.newCheckin))
  val venues = Database(cxs.venuesDb("venues"), deserF(_.newVenue))
}
