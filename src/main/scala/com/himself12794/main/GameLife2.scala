package com.himself12794.main

import scala.collection.mutable.HashMap

object GameLife2 {
  
  def mappify(str: String, pad: Char = ' ') = {
    val its = new HashMap[(Int,Int), Boolean]
    var x,y = 0
    var items = str.stripMargin split("\n") toBuffer
    val m = items.reduceLeft((a,b) => a max b ).length
    if (items.length < 100) 0 to 100 - items.length foreach { _ => items append ""  }
    items = items map { _ padTo(100, pad) }
    items foreach { z => z foreach { c => its put((x,y),c == 'X'); x += 1}; y += 1; x = 0 }
    its
  }
  
}

class GameLife2(m: String, val living: Char = 'X', val dead: Char = ' ') {
  
  private var cells = GameLife2.mappify(m, dead) 
  private val hw = cells.keySet reduceLeft { (a,b) => (a._1 max b._1, a._2 max b._2) }
  val height = hw._2 + 1
  val width = hw._1 + 1
  
  def getCount(x1: Int, y1: Int) = {
    var am = 0
    for {
      x <- x1 - 1 to x1 + 1
      y <- y1 - 1 to y1 + 1
    } {
      if (x > -1 && y > -1 && x < width && y < height && (x,y) != (x1,y1) && this(x,y)) am += 1            
    }
    am
  }
  
  def apply(x: Int, y: Int) = {
    cells.get((x,y)).get
  }
  
  def doCycle = {
    val cp = cells.clone
    cells foreach {
      cell => {
        val coords = cell._1
        val c = cell._2
        val am = getCount(coords._1, coords._2)
        
        if (c && (am < 2 || am > 3)) cp.put(coords, false)
        else if (am == 3) cp.put(coords, true)
        
      }
    }; cells = cp
  }
  
  def clearConsole = print("\n" * 9)
  
  def getCells = cells
}

