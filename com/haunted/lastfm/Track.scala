package com.haunted.lastfm
import dispatch._
import Http._
import scala.collection.mutable.Map
import scala.xml._

class Track(val artistName:String="", val track:String="", val mbid:String="", user:User=null) {
	//mbid = musicbrainz id
  val MinutesPerHour = 60.0
  val SecondsPerMinute = 60.0
  val MillisecondsPerSecond = 1000.0
	var id = 0
	var url = ""
	var duration = 0
	var listeners = 0
	var playcount = 0
	
	var userplaycount = 0
	var userloved = 0
	
	def init():Track = {
		val tempNodes = Track.getInfo(artistName, track, mbid, user)
		id = (tempNodes \ "id").text.toInt
		url = (tempNodes \ "url").text
		duration = (tempNodes \ "duration").text.toInt
		listeners = (tempNodes \ "listeners").text.toInt
		playcount = (tempNodes \ "playcount").text.toInt
		var userplaycountString = (tempNodes \ "userplaycount").text
		try{
			userplaycount = userplaycountString.toInt
		} catch {
			case e => None
			//leave user playcount at zero
		}
		var userlovedString = (tempNodes \ "userloved").text
		try{
			userloved = userlovedString.toInt
		} catch {
			case e => None
			//leave user loved at false
		}		
		this
	}

  override def toString:String = {
    "Track info: "+artistName+" "+track+" "+userplaycount+" "+duration
  }

  def timeInMinutes:Double = {
    duration / MillisecondsPerSecond / SecondsPerMinute
  }

  def timeInSeconds:Double = {
    duration / MillisecondsPerSecond
  }

  def totalListeningTimeInMinutes:Double = {
    userplaycount * timeInMinutes
  }

  def totalListeningTimeInSeconds:Double = {
    userplaycount * timeInSeconds
  }
}

object Track{

	def getInfo(artistName:String="", track:String="", mbid:String="", user:User=null): NodeSeq ={
		var paramMap = Map[String, String]()
		if(artistName.length > 0){
			paramMap += ("artist" -> artistName)
		}
		if(track.length > 0){
			paramMap += ("track" -> track)
		}
		if(mbid.length > 0){
			paramMap += ("mbid" -> mbid)
		}
		if(user != null){
			paramMap += ("username" -> user.userId)
		}
		val getInfoUrl:String = LastFM.makeUrlRequest("track.getInfo", paramMap)
		//LastFM.http(getInfoUrl >>> System.out)
		LastFM.http(getInfoUrl <> { _ \\ "track" })
	}
}