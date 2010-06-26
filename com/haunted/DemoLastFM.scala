package com.haunted
import com.haunted.lastfm._

object DemoLastFM extends Application {
  val ghostm = new User("ghost-m")
  println(ghostm.getInfo)
  val topArtists = ghostm.getTopArtists()
  val topArtistTracks = ghostm.getArtistTracks(artistName = topArtists(0).name)
  topArtistTracks foreach {t =>
    println(t.artistName+" "+ t.track)
  }
  //val recentTracks = ghostm.getRecentTracks()
  //recentTracks foreach {(track) => println(track.artistName+" "+track.track+" "+track.userplaycount+" "+track.duration)}
  //var tempTrack = new Track(artistName="Joanna Newsom", track="Ribbon Bows", user=ghostm).init
  //println(tempTrack.userplaycount)
  //println(Track.getInfo(artistName="Joanna Newsom", track="Ribbon Bows", user=ghostm))
}