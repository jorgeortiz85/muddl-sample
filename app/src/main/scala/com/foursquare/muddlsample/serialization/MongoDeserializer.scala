package com.foursquare.muddlsample.serialization

import com.mongodb.DBObject
import com.foursquare.muddl.Field
import com.foursquare.muddlsample.schema.{CheckinDeserializer, MentionDeserializer, VenueDeserializer}
import org.bson.types.ObjectId
import scalaj.collection.Implicits._

/**
 * This trait mixes in deserializer traits for all of our app's records. It defines deserialization
 * methods for all the types encountered in these records. It is specific to Mongo.
 */
trait MongoDeserializer
    extends CheckinDeserializer
    with MentionDeserializer
    with VenueDeserializer {
  override type Obj = DBObject
  override type Elem = Any
  override type Arr = Vector[Elem]

  override def deserialize$Int(elem: Elem): Option[Int] = elem match {
    case n: Int => Some(n)
    case _ => None
  }

  override def deserialize$Boolean(elem: Elem): Option[Boolean] = elem match {
    case b: Boolean => Some(b)
    case _ => None
  }

  override def deserialize$java$lang$String(elem: Elem): Option[String] = elem match {
    case s: String => Some(s)
    case _ => None
  }

  override def deserialize$org$bson$types$ObjectId(elem: Elem): Option[ObjectId] = elem match {
    case o: ObjectId => Some(o)
    case _ => None
  }

  override def deserialize$Long(elem: Elem): Option[Long] = elem match {
    case l: Long => Some(l)
    case _ => None
  }

  def deserialize$Double(elem: Elem): Option[Double] = elem match {
    case d: Double => Some(d)
    case _ => None
  }

  override def deserializeObj(elem: Elem): Option[Obj] = elem match {
    case o: DBObject => Some(o)
    case _ => None
  }

  override def deserializeArr(elem: Elem): Option[Arr] = elem match {
    case arr: Array[_] => Some(Vector() ++ arr)
    case c: java.util.Collection[_] => Some(Vector() ++ c.asScala)
    case _ => None
  }

  override def getArrElems[T](arr: Arr, f: Elem => T): Seq[T] = {
    arr.map(f)
  }

  override def getObjElem(obj: Obj, field: Field[_, _]): Option[Elem] = {
    Option(obj.get(field.shortName))
  }
}
