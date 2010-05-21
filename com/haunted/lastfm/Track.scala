package com.haunted.lastfm
import dispatch._
import Http._
import scala.collection.mutable.Map
import scala.xml._

class Track(artist:String="", track:String="", mbid:String="") {
	//mbid = musicbrainz id
	def getInfo(user:User=null): NodeSeq ={
		var paramMap = Map[String, String]()
		if(artist.length > 0){
			paramMap += ("artist" -> artist)
		}
		if(track.length > 0){
			paramMap += ("track" -> track)
		}
		if(mbid.length > 0){
			paramMap += ("mbid" -> mbid)
		}
		if(user != null){
			paramMap += ("user" -> user.userId)
		}
		val getInfoUrl:String = LastFM.makeUrlRequest("track.getInfo", paramMap)
		LastFM.http(getInfoUrl <> { _ \\ "track" })
	}
	
}