package im.mange.shoreditch.api

trait Action extends Service {
  val parameters: Parameters = Parameters(Nil, None)
  def run(in: List[In]): ActionResponse
  def success(returnValue: Option[String]) = ActionResponse(Nil, returnValue)
  def failure(failures: List[String]) = ActionResponse(failures, None)
}

case class Parameters(in: List[In], out: Option[Out])
case class In(name: String, value: Option[String], validValues: List[String])
case class Out(name: String)
