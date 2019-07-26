import Meeter.{Meet, WhoToMeet}
import Printer.Meeting
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

class Meeter(message: String, printActor: ActorRef) extends Actor {
  var meeting = ""

  def receive = {
    case WhoToMeet(who) =>
      meeting = message + "," + who
    case Meet =>
      printActor ! Meeting(meeting)
  }
}

class Printer extends Actor with ActorLogging {
  def receive = {
    case Meeting(meeting) =>
      log.info("Meeting recieved(from" + sender() + "):" + meeting)
  }
}

object Meeter {
  def props(message: String, printActor: ActorRef): Props = Props(new Meeter(message, printActor))

  final case class WhoToMeet(who: String)

  case object Meet

}

object Printer {
  def props: Props = Props[Printer]

  final case class Meeting(meeting: String)

}

object QuickStart extends App {
  val system = ActorSystem("hello")
  val printer: ActorRef = system.actorOf(Printer.props)
  val qwerty: ActorRef = system.actorOf(Meeter.props("qwerty", printer))
  val heya: ActorRef = system.actorOf(Meeter.props("heya", printer))
  val coffee: ActorRef = system.actorOf(Meeter.props("coffee", printer))
  qwerty ! WhoToMeet("Akka")
  qwerty ! Meet
  heya ! WhoToMeet("Scala")
  heya ! Meet
  coffee ! WhoToMeet("AI")
  coffee ! Meet
}