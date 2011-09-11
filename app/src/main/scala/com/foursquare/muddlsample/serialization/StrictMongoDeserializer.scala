package com.foursquare.muddlsample.serialization

import com.mongodb.DBObject
import com.foursquare.muddlsample.schema.{Checkin, CheckinStrict, Mention, MentionStrict, Venue, VenueStrict}

/**
 * This trait provides concrete implementations for all of our Records.
 *
 * In order to do this, it needs to be able to deserialize all our records and their types,
 * which it does by mixing in MongoDeserializer.
 */
class StrictMongoDeserializer extends MongoDeserializer {
  override def newCheckin(obj: DBObject): Option[Checkin] =
    Some(new CheckinStrict(obj, this))
  override def newMention(obj: DBObject): Option[Mention] =
    Some(new MentionStrict(obj, this))
  override def newVenue(obj: DBObject): Option[Venue] =
    Some(new VenueStrict(obj, this))
}
