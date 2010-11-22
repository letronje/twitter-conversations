object Constants{
	/*private val defaultTokenAndTokenSecrets = Map(
		"paramatendi1" -> ("215628581-IKbLScxQx2qJchW9SwDH3CvD8TcOo8kIP6HsJ9s0", "Gov6z3l9pmJM967FhaygBD1vesUY8AAlPdgnpDE5k"),
		"invalid" -> ("", ""))

	private val defaultUsernames = ("paramatendi1", "invalid")
	private val defaultUsername = defaultUsernames._1

	def getDefaultToken = defaultTokenAndTokenSecrets(defaultUsername)._1
	def getDefaultTokenSecret = defaultTokenAndTokenSecrets(defaultUsername)._2*/

	private var defaultToken = ""
	private var defaultTokenSecret = ""
	private var defaultTokensPresent = false

	def getDefaultTokens() = ((defaultToken, defaultTokenSecret))
	def defaultTokensAvailable = defaultTokensPresent

	def setDefaultTokens(token: String, tokenSecret: String){
		defaultToken = token
		defaultTokenSecret = tokenSecret
		defaultTokensPresent = true
	}

	val numStatusesPerPage = 200
	val maxStatuses = 800
	val consumerKey = "x3iLiSLZJs6qbVGjxBPQ"
	val consumerSecret = "saOjKeNHTX6NQnXSrZ5qiscb9zo5RID1t1FTDWVzgs"
}