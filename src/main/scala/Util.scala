import collection.mutable.ListBuffer
import java.io._
import java.util.{Date, ArrayList, HashMap}
import twitter4j._
import twitter4j.http._
import scala.collection.JavaConversions._
import xml.{NodeSeq, XML}

object Util{
	var twitter: Twitter = null

	/*private def getDefaultTwitter: Twitter = {
		val twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.consumerKey,Constants.consumerSecret);

		twitter
	}*/

	def getTwitter = {
		if(twitter != null){
			twitter
		}
		else{
			twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(Constants.consumerKey, Constants.consumerSecret);

			if(Constants.defaultTokensAvailable){
				val (defaultToken, defaultTokenSecret) = Constants.getDefaultTokens()
				twitter.setOAuthAccessToken(new AccessToken(defaultToken,defaultTokenSecret))	
			}
			else{
				val requestToken = twitter.getOAuthRequestToken();
				val br = new BufferedReader(new InputStreamReader(System.in));
				val authUrl = requestToken.getAuthorizationURL();
				println("Auth URL ~> " + authUrl);
				println("Goto this url in your browser and give the app some love, thnx :)")
				print("Enter pin: ")
				val pin = br.readLine();
				println(pin)
				val accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				println("Token = " + accessToken.getToken)
				println("Token Secret = " + accessToken.getTokenSecret)
				twitter.setOAuthAccessToken(accessToken)
			}
			twitter
		}
	}
    
	def getStatuses(requestedCount: Int = Constants.numStatusesPerPage) = {
		println("Total requested = " + requestedCount)
		val n = Math.min(requestedCount, Constants.maxStatuses)
		println("Will try to fetch " + n)
		var numFetched = 0
		var pageNum = 1
		val statuses = new ListBuffer[Status]
		val twitter = getTwitter
		while(numFetched < n){
			val numRemaining = n - numFetched
			val numToFetch = Math.min(numRemaining, Constants.numStatusesPerPage)
			print("Asked twitter for " + numToFetch)
			val ss = twitter.getHomeTimeline(new Paging(pageNum, numToFetch)).toList
			ss.foreach(s => statuses.add(s))
			numFetched += ss.size
			pageNum += 1
			print(", Got " + ss.size)
			println(", Total " + numFetched + " so far")
			if(ss.size == 0){
				numFetched = n
			}
		}

		statuses.reverse.toList
	}

	def getReplies(statuses: List[Status]) = {
		statuses.filter(status => (status.getInReplyToStatusId != -1)).toList
	}

}