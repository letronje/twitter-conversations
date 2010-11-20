import twitter4j.Status

object StatusCache{
	private var cache = Map.empty[Long, Status]

	def get(id: Long): Status = {
		if(id <= 0){
			null
		}
		else{
			var status = cache.getOrElse(id, null)
			if(status == null){
				print("Fetching tweet " + id + " from twitter ... ")
				status = Util.getTwitter.showStatus(id)
				println("Done")
				put(status)
			}
			status
		}
	}

	def put(status: Status){
		cache += ((status.getId, status))	
	}

	def put(statuses: List[Status]) {
		statuses.foreach(s => put(s))
	}

	def show{
		println("\nPRINTING STATUS CACHE(" + cache.size + ") : \n")
		cache.foreach{e =>
			println(e._1 + " => " + e._2.getText + " @ " + e._2.getCreatedAt)			
		}
		println
	}
}