package com.foursquare.muddlsample.serialization

import com.mongodb.DBObject
import com.foursquare.muddlsample.schema.{Checkin, CheckinStrict, Mention, MentionStrict, Venue, VenueStrict}

class StrictMongoDeserializer extends MongoDeserializer {
  override def newCheckin(obj: DBObject): Option[Checkin] =
    Some(new CheckinStrict(obj, this))
  override def newMention(obj: DBObject): Option[Mention] =
    Some(new MentionStrict(obj, this))
  override def newVenue(obj: DBObject): Option[Venue] =
    Some(new VenueStrict(obj, this))
}
