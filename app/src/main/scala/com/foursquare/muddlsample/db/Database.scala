package com.foursquare.muddlsample.db

import com.foursquare.muddl.{Record, MetaRecord}
import com.mongodb.casbah.Imports.{DBObject, MongoCollection}

case class Database[R](
    collection: MongoCollection,
    deserialize: DBObject => R,
    serialize: R => DBObject) {

  def find(dbo: DBObject, limit: Int): Seq[R] =
    Vector() ++ collection.find(dbo).limit(limit).map(deserialize)

  def findOne(dbo: DBObject): Option[R] = {
    val it = collection.find(dbo).limit(1).map(deserialize)
    if (it.hasNext) Some(it.next) else None
  }

  def save(obj: R): Unit = {
    collection.save(serialize(obj))
  }
}
