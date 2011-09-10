package com.foursquare.muddlsample.db

import com.mongodb.casbah.Imports.MongoConnection
import com.mongodb.ServerAddress

class DatabaseConnections {
  def serverAddress(hostPort: String): ServerAddress = {
    val parts = hostPort.split(':')
    new ServerAddress(parts(0), parts(1).toInt)
  }

  def serverAddresses(hostPorts: String): List[ServerAddress] = {
    hostPorts.split('|').map(serverAddress).toList
  }

  lazy val coreDb = MongoConnection(serverAddress("dev-4:27017"))("foursquare")
  lazy val usersDb = MongoConnection(serverAddress("dev-4:27023"))("foursquare")
  lazy val usersAuxDb = MongoConnection(serverAddress("dev-4:27023"))("foursquare")
  lazy val checkinsDb = MongoConnection(serverAddress("dev-3:27021"))("foursquare")
  lazy val infraDb = MongoConnection(serverAddress("dev-1:27018"))("foursquare")
  lazy val venuesDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val venuesAuxDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val venuesAuxSlaveDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val venuesSlaveDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val venuesProdSlaveDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val hyperVenuesDb = MongoConnection(serverAddresses("mongotest-shard-0-a:27317|mongotest-shard-0-b:27117|mongotest-shard-0-c:27217"))("foursquare")
  lazy val feedsDb = MongoConnection(serverAddress("dev-4:27023"))("foursquare")
  lazy val trendingDb = MongoConnection(serverAddress("dev-3:27021"))("trending")
}
