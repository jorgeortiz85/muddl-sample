package com.foursquare.muddlsample.serialization

import com.mongodb.{BasicDBObjectBuilder, DBObject}
import com.foursquare.muddl.Field
import com.foursquare.muddlsample.schema.{CheckinSerializer, MentionSerializer, VenueSerializer}
import java.{util => ju}
import org.bson.types.ObjectId
import scalaj.collection.Implicits._

class MongoSerializer
    extends CheckinSerializer
    with MentionSerializer
    with VenueSerializer {
  override type Obj = DBObject
  override type Elem = Any
  override type Arr = ju.Collection[Elem]

  override def serializeObj(fieldValues: Seq[(Field[_, _], Option[Elem])]): Obj = {
    (fieldValues.foldLeft(BasicDBObjectBuilder.start) {
      case (dbo, (field, Some(value))) =>
        dbo.add(field.shortName, value)
        dbo
      case (dbo, (field, _)) =>
        dbo
    }).get
  }

  override def serializeArr[T](arr: Seq[T], f: T => Elem): Option[Arr] = {
    val result = arr.map(f)
    val rv = if (result.isEmpty) None else Some(result.asJava)
    rv
  }

  override def serialize$Double(elem: Double): Elem = elem
  override def serialize$Int(elem: Int): Elem = elem
  override def serialize$Boolean(elem: Boolean): Elem = elem
  override def serialize$java$lang$String(elem: java.lang.String): Elem = elem
  override def serialize$Long(elem: Long): Elem = elem
  override def serialize$org$bson$types$ObjectId(elem: org.bson.types.ObjectId): Elem = elem
}
