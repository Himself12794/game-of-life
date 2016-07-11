package com.himself12794.main

object GameLife {
  
  val empty = ' '
  val full = 'X'
  val h = 9
  val w = 32
  val thing = Array.fill(h,w) { empty }
  
  {
    thing(4)(3) = 'X'
 		thing(4)(4) = 'X'
 		thing(4)(5) = 'X'
  }
  
}

/**
 * Assumes matrix is properly constructed
 */
case class Game(use: Array[Array[Char]], empty: Char = '*', full: Char = 'X') {
  
  private var vals = use
  private val h = vals.length
  private val w = vals(0).length
  
  
  def printArray = vals foreach { x => x.foreach { x => print(x) }; println }
  
  def getSurroundCount(ix: Int, iy: Int, doPrint: Boolean = false) = {

    var am = 0
      
      var row1 = ""
      var row2 = ""
      var row3 = ""
    for {
      x <- (ix - 1) to (ix + 1)
      y <- (iy - 1) to (iy + 1)
    } {
      
      val tmp1 = x > -1
      val tmp2 = y > -1
      val tmp3 = x < w
      val tmp4 = y < h
      lazy val tmp7 = vals(y)(x) == full
      
      if (tmp1 && tmp2 && tmp3 && tmp4) {
        if (y == iy - 1) row1 += vals(y)(x)
        else if (y == iy) row2 += vals(y)(x)
        else if (y == iy + 1) row3 += vals(y)(x)
        if ((x,y) != (ix,iy) && tmp7) am+=1
      }
    }
    if (doPrint) {
      println(row1)
      println(row2)
      println(row3)
    }
    am
  }
  
  def doCycles(x: Int) = {
    
    for (g <- 0 to x) {
      val cp = vals map (_.clone)
      printArray
      for {
        x <- 0 to w - 1
        y <- 0 to h - 1
      } {
        val count = getSurroundCount(x,y)
        val item = vals(y)(x)

        if (item == full) {
          if (count > 3) cp(y)(x) = empty
          else if (count < 2) cp(y)(x) = empty
        } else if (count == 3) cp(y)(x) = full
      }
      vals = cp
      Thread.sleep(100)
      clearConsole
    }
    printArray
  }
  def clearConsole = print("\n\n\n\n\n\n\n\n\n\n\n")
}