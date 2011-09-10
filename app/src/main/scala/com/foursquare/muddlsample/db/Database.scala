package com.foursquare.muddlsample.db

import com.foursquare.muddl.{Record, MetaRecord}
import com.mongodb.casbah.Imports.{DBObject, MongoCollection}

case class Database[R](collection: MongoCollection, f: DBObject => R) {
  def find(dbo: DBObject, limit: Int): Seq[R] =
    Vector() ++ collection.find(dbo).limit(limit).map(f)
  def findOne(dbo: DBObject): Option[R] = {
    val it = collection.find(dbo).limit(1).map(f)
    if (it.hasNext) Some(it.next) else None
  }
}
