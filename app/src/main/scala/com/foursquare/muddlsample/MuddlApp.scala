package com.foursquare.muddlsample

import com.foursquare.muddlsample.serialization.StrictMongoDeserializer
import com.foursquare.muddlsample.db.{Databases, DatabaseConnections}

object MuddlApp {
  def main(args: Array[String]): Unit = {
    val deserializer = new StrictMongoDeserializer
    val connections = new DatabaseConnections
    val databases = new Databases(connections, deserializer)
    ()
  }
}
