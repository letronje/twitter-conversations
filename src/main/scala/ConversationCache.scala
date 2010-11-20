import twitter4j.Status

object ConversationCache{
	private var cache = Map.empty[Long, Tweet]

	def get(id: Long): Tweet = {
		if(id <= 0){
			null
		}
		else{
			var tweet = cache.getOrElse(id, null)
			if(tweet == null){
				put(StatusCache.get(id))
				tweet = get(id)
			}
			tweet
		}
	}

	def put(statuses: List[Status]) {
		statuses.foreach(put(_))
	}

	def put(s: Status){
		val (id, text, at) = (s.getId, s.getText, s.getCreatedAt)
		val user = s.getUser
		val (userId, userName) = (user.getId, user.getScreenName)
		val parentId = s.getInReplyToStatusId
		val parent = get(parentId)

		val level = if(parent == null){
			1
		}
		else{
			parent.level + 1
		}
		val tweet = new Tweet(id, text, userId, userName, parentId, parent, at, Set.empty[Tweet], level)
		if(parent != null){
			parent.children = parent.children + tweet
		}
		cache += ((id, tweet))
	}

	def show(){
		//print("Calculating weights ... ")
		//calcWeights()
		//println("Done")

		println("\nPrinting Conversations from " + cache.size + " tweets : \n")

		val sortedConversations = cache.filter(_._2.level == 1).toList.sortWith{ (a, b) =>
			//a._2.weight > b._2.weight
			a._2.at.getTime > b._2.at.getTime
		}

		sortedConversations.foreach{ c =>
			c._2.printTree
			println("\n" + ("=" * 140) + "\n")
		}
    }

	def calcWeights(){
		cache.filter(e => (e._2.level == 1)).foreach(e => e._2.calcWeight)
	}
}