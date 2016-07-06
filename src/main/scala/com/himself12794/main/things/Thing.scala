package com.himself12794.main.things

import scala.beans._

class Thing(@BeanProperty var name: String, @BeanProperty var value: String) {
  override def toString = name + "=" + value;
}

case class Stuff(name: String, value: String)