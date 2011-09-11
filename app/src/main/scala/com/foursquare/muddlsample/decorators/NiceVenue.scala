package com.foursquare.muddlsample.decorators

import com.foursquare.muddlsample.schema.{Venue, VenueDecorator, VenueMutable}

class NiceVenue(decorated: Venue) extends VenueDecorator(decorated) {
  def addressString: String = {
    "%s (@ %s), %s, %s %s, %s".format(
      this.address.getOrElse(""),
      this.crossstreet.getOrElse(""),
      this.city.getOrElse(""),
      this.state.getOrElse(""),
      this.zip.getOrElse(""),
      this.country.getOrElse(""))
  }

  def mutable = new VenueMutable(decorated)
}
