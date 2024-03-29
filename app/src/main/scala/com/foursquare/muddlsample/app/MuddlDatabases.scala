package com.foursquare.muddlsample.app

import com.foursquare.muddl.DeserializationException
import com.foursquare.muddlsample.db.{Database, DatabaseConnections}
import com.foursquare.muddlsample.decorators.NiceVenue
import com.foursquare.muddlsample.serialization.{MongoDeserializer, MongoSerializer}
import com.mongodb.casbah.Imports.DBObject

/**
 * This class brings together database connections, serializers, deserializers, and decorators.
 */
class MuddlDatabases(
    connections: DatabaseConnections,
    deserializer: MongoDeserializer,
    serializer: MongoSerializer) {

  def deserialize[A](f: MongoDeserializer => DBObject => Option[A])(dbo: DBObject): A = {
    try {
      f(deserializer)(dbo).getOrElse(throw new RuntimeException("Failed to deserialize: " + dbo))
    } catch {
      case e: DeserializationException =>
        throw new RuntimeException("Failed to deserialize: " + dbo, e)
    }
  }

  val checkins = Database(connections.checkinsDb("checkins"), deserialize(_.newCheckin), serializer.serializeCheckin _)
  val venues = Database(connections.venuesDb("venues"), dbo => new NiceVenue(deserialize(_.newVenue)(dbo)), serializer.serializeVenue _)
}
