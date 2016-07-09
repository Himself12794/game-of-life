package com.himself12794.main

import scala.collection.mutable.HashMap

object GameLife2 {
  
  def mappify(str: String, pad: Char = ' '): HashMap[(Int,Int), Char] = {
    val its = new HashMap[(Int,Int), Char]
    var x,y = 0
    var items = str.stripMargin split("\n");
    val m = items.reduceLeft((a,b) => if (a.length > b.length) a else b ).length
    items = items map { _ padTo(m, pad) }
    items foreach { z => z foreach { c => its put((x,y),c); x += 1}; y += 1; x = 0 }
    its
  }
  
}

class GameLife2(m: String, living: Char = 'X', dead: Char = ' ') {
  
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
      if (x > -1 && y > -1 && x < width && y < height && (x,y) != (x1,y1) && this(x,y) == living) am += 1            
    }
    am
  }
  
  def doCycles(i: Int) = 0 to i foreach { x => println(this); doCycle; Thread.sleep(500);clearConsole} 
  
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
        
        if (c == living && (am < 2 || am > 3)) cp.put(coords, dead)
        else if (am == 3) cp.put(coords, living)
        
      }
    }; cells = cp
  }
  
  override def toString = {
    val str = Array.ofDim[Char](height,width)
    cells foreach { 
      cell => {
        val dims = cell._1
        str(dims._2)(dims._1) = cell._2
      }
    }
    val b = new StringBuilder
    str foreach {
      st => {
        
        b ++= st.mkString
        if (st != str.last) b ++= "\n"
        
      }
    }
    b.toString
  }
  
  def clearConsole = print("\n" * 9)
  
  
}

