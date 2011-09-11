package com.foursquare.muddlsample.db

import com.foursquare.muddl.{Record, MetaRecord}
import com.mongodb.casbah.Imports.{DBObject, MongoCollection}

/**
 * A thin wrapper around Casbah's MongoCollection that serializes and deserializes Records
 * into/from DBObjects.
 */
case class Database[T <: Record[T], R <: T](
    collection: MongoCollection,
    deserialize: DBObject => R,
    serialize: T => DBObject) {

  def find(dbo: DBObject, limit: Int): Seq[R] =
    Vector() ++ collection.find(dbo).limit(limit).map(deserialize)

  def findOne(dbo: DBObject): Option[R] = {
    val it = collection.find(dbo).limit(1).map(deserialize)
    if (it.hasNext) Some(it.next) else None
  }

  def save(obj: T): Unit = {
    collection.save(serialize(obj))
  }
}
