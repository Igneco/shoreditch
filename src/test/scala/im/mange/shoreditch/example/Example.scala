package im.mange.shoreditch.example

import im.mange.shoreditch._
import im.mange.shoreditch.Shoreditch._
import im.mange.shoreditch.api._

object Example {
  val shoreditch = Shoreditch(
    base = "base",
    version = "10001",
    longName = "Example System",
    alias = "example",
    debug = true,
    routes = Seq(
      "successful/check" check SuccessfulCheck,
      "successful/check/with/arg/@arg" check SuccessfulCheckWithArg,
      "successful/action" action SuccessfulAction,
      "successful/action/with/parameters" action SuccessfulActionWithParameters,
      "successful/action/with/return" action SuccessfulActionWithReturn,
      "failure/check" check FailureCheck,
      "failure/action" action FailureAction
    )
  )
}


case object SuccessfulCheck extends Check {
  override def run = success
}

case object FailureCheck extends Check {
  override def run = failure(Seq("Failed"))
}

case class SuccessfulCheckWithArg(arg: String) extends Check {
  override def run = success
}

case object SuccessfulAction extends Action {
  override def run(in: Seq[In]) = success(None)
}

case object SuccessfulActionWithParameters extends Action {
  override val parameters = Parameters(Seq(In("name", None)), None)
  override def run(in: Seq[In]) = {
    println(in)
    if (in.size == 1) success(None) else failure(Seq("Failed"))
  }
}

case object FailureAction extends Action {
  override def run(in: Seq[In]) = failure(Seq("Failed"))
}

case object SuccessfulActionWithReturn extends Action {
  override def run(in: Seq[In]) = success(Some("returnValue"))
}
