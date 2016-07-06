package com.himself12794.main

object Test extends App {
  
  println("Hello, World")
  
  Main.main("Do some more stuff", "Hello, World", "Integration of Java into Scala")
  
  doSomeStuff
  println(doRecursiveMath(5))
  
  val l = List(5, 2, 3)
  
  def f(x: Int, y: Int) = x + y
  
  println(performFunction(f))
  
  def doSomeStuff = {

    val x = 2
    println(-x)
    println( "pink" contains 'p' )
    println( 1 to 10 by 3 )
    for (i <- 1 to 10 by 11) {
      println(i)
    }
    
  }
  
  def performFunction(f: ((Int, Int) => Int)) = f(5, 5)
  
  def doMath(x: Int) = x * x
  
  def doRecursiveMath(x: Int): Int = {
    if (x <= 1) 1
    else x * doRecursiveMath(x - 1)
  }
  
}