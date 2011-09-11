package com.foursquare.muddlsample.decorators

import com.foursquare.muddlsample.schema.{Venue, VenueDecorator, VenueMutable}

/**
 * This class decorates a Venue with additional derived data or other helpful methods.
 *
 * It extends VenueDecorator (and thus, Venue too) to get forwarder methods for free.
 */
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
