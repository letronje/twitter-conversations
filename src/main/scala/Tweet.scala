import java.util.Date
import twitter4j.Status

case class Tweet(
		id: Long,
		text: String,
		userId: Long,
		userName: String,
		parentId: Long,
		parent: Tweet,
		at: Date,
		var children: Set[Tweet],
		level: Int=1,
		var weight: Int = -1) {



	private def truncateText(txt: String) = {
		val maxTextLen = 100
		if (txt.length > maxTextLen){
			txt.substring(0, maxTextLen) + "..."
		}
		else{
			txt
		}
	}


	override def toString={
	   	/*if(parentId == -1){
			""
		}
		else{
			var str = userName + " (sayz) " + truncateText(text)

			if(parent != null){
				str += " ==AS REPLY TO=> " + truncateText(parent.text)
			}
			else{
				println("parentId is " + parentId + " but parent is null")
			}
			str
		}*/

		var s = ("  |" * (level-1)) + "  +" + userName + " => " + truncateText(text)
		/*s += " (l=" + level + ",nc=" + children.size + ",w=" + weight + ",id=" + id + ",pid=" + parentId
		if(children.size > 0){
			s += ",c=" + children(0).id.toString
		}*/
		s
	}

	def calcWeight(): Int = {
		if(weight != -1){
			weight
		}
		else{
			//println("calculating weight for " + id)
			val numChildren = children.size
			//println(children.size + " children")
			val sumChildrenWeight = (if(numChildren == 0){
				0
			}
			else{
				children.foldLeft(0){ (sum, child) =>
					//println("calling calcWeight of child " + child.id)
					val cw = child.calcWeight
					//println("weight of child " + child.id + " is " + cw)
					sum + cw
				}

			})
		   	weight = sumChildrenWeight + 1
			weight
		}
	}


	def printTree(){
		println(this)
		val orderedChildren = children.toList.sortWith( (a,b) =>
			a.at.getTime < b.at.getTime)
		orderedChildren.foreach{ c =>
			c.printTree()
		}
	}
}
