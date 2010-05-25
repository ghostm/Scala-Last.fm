package com.haunted

import dispatch._
import Http._

import com.haunted.lastfm._
import tagsoup._
import java.net._
import java.io._
import _root_.java.io.Reader
import org.xml.sax.InputSource

import scala.xml._

object DemoLastFM extends Application {
  val ghostm = new User("ghost-m")
  println(ghostm.getInfo)
  val recentTracks = ghostm.getRecentTracks()
  recentTracks foreach {(track) => println(track.artistName+" "+track.track+" "+track.userplaycount+" "+track.duration)}
  var tempTrack = new Track(artistName="Joanna Newsom", track="Ribbon Bows", user=ghostm).init
  println(tempTrack.userloved)
  println(tempTrack.userplaycount)
  println(Track.getInfo(artistName="Joanna Newsom", track="Ribbon Bows", user=ghostm))

}