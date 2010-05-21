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
  val tempNode = recentTracks.headOption
  tempNode match {
    case None => {println("No first element in getNumberOfRecentTracks")}
    case Some(x) => {
      val trackUrlOption = (x \ "url").text
      println(trackUrlOption)
    }
  }
  var tempTrack = new Track(artist="Joanna Newsom", track="Ribbon Bows")
  var trackNode = tempTrack.getInfo(ghostm)
  println(tempTrack.getInfo(ghostm))
  val trackDurationValue = (trackNode \ "duration").text
  println(trackDurationValue)
}