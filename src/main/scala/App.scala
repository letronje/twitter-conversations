object App {
	def disableLogging{
		System.setProperty ("twitter4j.loggerFactory", "twitter4j.internal.logging.NullLoggerFactory"); 
	}

	def main(args: Array[String]){
		disableLogging
		val numStatuses = Constants.maxStatuses
		println("Fetching " + numStatuses + " from your home timeline ...")
		val statuses = Util.getStatuses(numStatuses)
		println("Fetched " + statuses.size + " tweets")
		StatusCache.put(statuses)
		//StatusCache.show
		val replies = Util.getReplies(statuses)
		//replies.foreach(r => println(r.getId + " " + r.getText + " " + r.getInReplyToStatusId))
		ConversationCache.put(replies)
		//StatusCache.show
		//ConversationCache.show()

		//println(ConversationCache.get(4246305643700224L).children(0).calcWeight)
		//ConversationCache.calcWeights
		ConversationCache.show()
	}
}