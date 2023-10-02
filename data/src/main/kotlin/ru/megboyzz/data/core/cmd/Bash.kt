package ru.megboyzz.data.core.cmd

fun exec(vararg command: String): String{
/*
    println("Command: ")
    print("\t")
    command.forEach {
        print("$it ")
    }
    println()*/

    val processBuilder = ProcessBuilder(*command)
    processBuilder.redirectErrorStream(true) // объединить stderr с stdout
    val process = processBuilder.start()

    //println("Command out: ")
    //print("\n")
    //val output = process.inputStream.bufferedReader().readText()
   //println(output)
    return process.inputStream.bufferedReader().readText()

}