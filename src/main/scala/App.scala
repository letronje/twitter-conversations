object App {
	def disableLogging{
		System.setProperty ("twitter4j.loggerFactory", "twitter4j.internal.logging.NullLoggerFactory"); 
	}

	def saveTokensFromArgs(args: Array[String]){
		if(args.size >= 2){
			Constants.setDefaultTokens(args(0), args(1))	
		}
	}

	def main(args: Array[String]){
		disableLogging
		saveTokensFromArgs(args)
		
		val numStatuses = Constants.maxStatuses
		println("Fetching " + numStatuses + " from your home timeline ...")
		val statuses = Util.getStatuses(numStatuses)
		println("Fetched " + statuses.size + " tweets")

		StatusCache.put(statuses)
		val replies = Util.getReplies(statuses)
		ConversationCache.put(replies)
		ConversationCache.show()
	}
}