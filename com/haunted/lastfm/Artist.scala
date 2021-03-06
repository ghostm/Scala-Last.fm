package com.haunted.lastfm
import scala.xml._

class Artist(var name:String = "", var mbid:String = "", var url:String = "") {
	def setValues(artistNode:Node){
		name = (artistNode \ "name").text
		mbid = (artistNode \ "mbid").text
		url = (artistNode \ "url").text
	}

  override def toString:String = {
    name+" "+mbid+" "+url
  }
}