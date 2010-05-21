package com.haunted.lastfm
import dispatch._
import Http._
import scala.xml._
import scala.collection.mutable.Map
import scala.runtime

class User(userId:String){
	
  def userId(): String = {
	  userId
  }
  
  def getInfo(): NodeSeq = {
    val paramMap = Map[String, String]()
    paramMap += ("user" -> userId)
    val getInfoUrl:String = LastFM.makeUrlRequest("user.getInfo", paramMap)
    LastFM.http(getInfoUrl <> { _ \\ "user" })
  }
  
  def getRecentTracks(limit:Int = -1, page:Int = -1): NodeSeq = {
    val paramMap = Map[String, String]()
    paramMap += ("user" -> userId)
    if(limit >= 0){
      paramMap += ("limit"->limit.toString)
    }
    if(page >= 0){
      paramMap += ("page"->page.toString)
    }
    val getRecentTracksUrl:String = LastFM.makeUrlRequest("user.getRecentTracks", paramMap)
    LastFM.http(getRecentTracksUrl <> { _ \\ "track" })
  }
  
  def getNumberOfRecentTracksPages(): Int = {
    val paramMap = Map[String, String]()
    paramMap += ("user" -> userId)
    val getRecentTracksUrl:String = LastFM.makeUrlRequest("user.getRecentTracks", paramMap)
    var responseSeq = LastFM.http(getRecentTracksUrl <> { _ \\ "recenttracks" })
    var totalPagesInt = 0
    val tempNode = responseSeq.headOption
    tempNode match {
      case None => {println("No first element in getNumberOfRecentTracks")}
      case Some(x) => {
        val totalPagesOption = x.attribute("totalPages")
        var totalPagesInt = 0
        totalPagesOption match{
          case None => {println("No total pages attribute")}
          case Some(tp) => {
            //println(tp)
            totalPagesInt = Integer.parseInt(tp.toString)
          }
        }
      }
    }
    return totalPagesInt
  }
}