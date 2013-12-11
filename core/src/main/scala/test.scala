import scala.language.experimental.macros
import scala.reflect.api.Liftable
import scala.reflect.runtime.{universe ⇒ u}
import u._


case class Ok(s: String, i: Int)
case class Test(d: Double, s: String, ok: Ok)
case class `Ok.Test`(d: Double)

object Test {
  import liftableMacro._

  val s = Ok("s", 1)
  val tst = Test(1.0, "test", s)
  val dot = `Ok.Test`(1.0)

  def ok = {
    implicit val ev = liftableMacro.liftableCaseClass[Ok]
    println(showRaw(q"""$s"""))
    println(showRaw(q"""$tst"""))
    println(showRaw(q"""$dot"""))
  }
}
