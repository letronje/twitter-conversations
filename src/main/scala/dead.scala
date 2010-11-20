




/*def getHttpResponse(url: String): String={
	val start = System.nanoTime
	val client = new HttpClient
	val get = new GetMethod(url)
	val status = client.executeMethod(get)
	val resp = get.getResponseBodyAsString
	val end = System.nanoTime
	println("response to " + url + " in " + (end-start)/1000000000.0 + " secs")
	resp
}*/

/*def getTweetsOfUser(userId: Long)={
    var url = "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=" + userId
    url += "&trim_user=true&count=" + Constants.numStatusesPerPage
    val response = getHttpResponse(url)
    val statuses = (XML.loadString(response) \ "status")
	val tweets = new ArrayList[Tweet]
	//statuses.map(println)
	def statusXmlElemToTweet(xmle: NodeSeq)={
		val id =  (xmle \ "id").text.toLong
		val text = (xmle \ "text").text
		val parentIdStr = (xmle \ "in_reply_to_status_id").text

		//println(xmle)
		//println(" text : " + text)
		//println("pidstr : " + parentIdStr)
		var parentId: Long = 0
		if(parentIdStr != ""){
			parentId = parentIdStr.toLong
		}
		//println(parentId)
		new Tweet(id, text, parentId, null)
	}
    statuses.map(statusXmlElemToTweet).toList
}*/