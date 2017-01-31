package com.himself12794.main

import scala.collection.mutable.HashMap

object GameLife3 {

  def mappify(str: String, living: Char = 'X', dead: Char = ' ', padded: Int = 100) = {
    val its = new HashMap[(Int, Int), Boolean]
    var x, y = 0
    var items = str.stripMargin split ("\n") toBuffer

    if (items.length < padded) 0 to padded - items.length - 1 foreach { _ => items append "" }
    items = items map { _ padTo (padded, dead) }
    items foreach { z => z foreach { c => its put ((x, y), c == 'X'); x += 1 }; y += 1; x = 0 }
    its
  }

}

class GameLife3(str: String) {

  private val temp = GameLife3.mappify(str)

  val height = temp.map(v => v._1._2).max + 3
  val width = temp.map(v => v._1._1).max + 3

  private var world = Array.ofDim[Int](width, height)

  temp foreach { v => world(v._1._1 + 1)(v._1._2 + 1) = if (v._2) 1 else 0 }

  private val cache = {
    val arr = Array.ofDim[Int](0x200)
    for (i <- 0 to 0x1ff) {
      val cel = ((i >> 4) & 1)
      val sum = ((i >> 8) & 1) + ((i >> 5) & 1) + ((i >> 2) & 1) + ((i >> 7) & 1) + ((i >> 1) & 1) + ((i >> 6) & 1) + ((i >> 3) & 1) + ((i >> 0) & 1)
      arr(i) = criteria(sum, cel)
    }
    arr
  }

  def criteria(sum: Int, cell: Int) = {
    if (cell == 1 && (sum > 3 || sum < 2)) 0
    else if (sum == 3) 1
    else cell
  }

  def apply(x: Int, y: Int) = world(x)(y)

  private def getBytes(x: Int, y: Int) = {
    (256 * this(x - 1, y - 1)) + (32 * this(x, y - 1)) + (4 * this(x + 1, y - 1)) +
      (128 * this(x - 1, y)) + (16 * this(x, y)) + (2 * this(x + 1, y)) +
      (64 * this(x - 1, y + 1)) + (8 * this(x, y + 1)) + (1 * this(x + 1, y + 1))
  }

  def bitMap(i: Int) = {
    val sb = new StringBuilder
    sb append (i >> 8 & 1) append (i >> 5 & 1) append (i >> 2 & 1) append "\n"
    sb append (i >> 7 & 1) append (i >> 4 & 1) append (i >> 1 & 1) append "\n"
    sb append (i >> 6 & 1) append (i >> 3 & 1) append (i >> 0 & 1)
    sb.toString
  }

  def doCycle = {
    val temp = world map { _.clone }
    var x = 0
    var y = 0
    var next = -1
    while (y < height) {

      if (x != 0 && y != 0 && x != width - 1 && y != height - 1) {
        val b = if (next == -1) {
          getBytes(x, y)
        } else {
          next + (4 * this(x + 1, y - 1)) + (2 * this(x + 1, y)) + this(x + 1, y + 1)
        }

        next = (b << 3) & 0x1ff
        if (b == 0) {
          if (width - x > 2) x += 2 else x += 1
        } else {
          val c = cache(b)
          temp(x)(y) = c
          next = if (c == 1) (next | 128) else (next & 383)
          x += 1
        }
        
        
        if ((cache(b) == 1) && (((b >> 4) & 1) == 0) || (cache(b) == 0) && (((b >> 4) & 1) == 1)) {
          val tmp = if (temp(x-1)(y) == 1) next | 0x80 else next & 0x17f
          println("Current: " + b)
          println(bitMap(b))
          println("New: " + temp(x - 1)(y))
          println("Next: " + tmp)
          println(bitMap(tmp) + "\n")
        }
        
      } else x += 1
      if (x == width) {
        x = 0
        y += 1
        next = -1
      }
    }
    world = temp
  }

  override def toString = {
    world.map(_.mkString) mkString "\n"
  }

  def gc = {
    val sb = new StringBuilder
    for (i <- 0 to cache.length - 1) {
      sb ++= s"$i: " + cache(i) + "\n"
    }
    sb.toString
  }
}