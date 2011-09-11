package com.foursquare.muddlsample.app

import com.foursquare.muddlsample.db.DatabaseConnections
import com.foursquare.muddlsample.decorators.NiceVenue
import com.foursquare.muddlsample.schema.Checkin
import com.foursquare.muddlsample.serialization.{MongoSerializer, StrictMongoDeserializer}
import com.mongodb.casbah.Imports._

/**
 * An example app that initializes serializers, deserialiers, database connections, and databases,
 * and uses them to make some simple queries.
 */
object MuddlApp {
  def main(args: Array[String]): Unit = {
    val deserializer = new StrictMongoDeserializer
    val serializer = new MongoSerializer
    val connections = new DatabaseConnections
    val databases = new MuddlDatabases(connections, deserializer, serializer)

    // There's weird data in the staging venue db with empty venues
    // which blow up on deserialization, so we'll avoid them
    val nonEmptyVenues = MongoDBObject("userid" -> MongoDBObject("$exists" -> true))

    val venues: Seq[NiceVenue] = databases.venues.find(nonEmptyVenues, 10)
    val checkins: Seq[Checkin] = databases.checkins.find(MongoDBObject(), 10)

    ()
  }
}
