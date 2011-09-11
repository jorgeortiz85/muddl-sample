package com.foursquare.muddlsample.app

import com.foursquare.muddlsample.serialization.{MongoSerializer, StrictMongoDeserializer}
import com.foursquare.muddlsample.db.DatabaseConnections
import com.mongodb.casbah.Imports._

object MuddlApp {
  def main(args: Array[String]): Unit = {
    val deserializer = new StrictMongoDeserializer
    val serializer = new MongoSerializer
    val connections = new DatabaseConnections
    val databases = new MuddlDatabases(connections, deserializer, serializer)

    // There's weird data in the staging venue db with empty venues
    val nonEmptyVenues = MongoDBObject("userid" -> MongoDBObject("$exists" -> true))

    val venues = databases.venues.find(nonEmptyVenues, 10)
    val checkins = databases.checkins.find(MongoDBObject(), 10)

    ()
  }
}
