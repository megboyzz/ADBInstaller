package ru.megboyzz.data.core.cmd

fun exec(vararg command: String): String{

    val processBuilder = ProcessBuilder(*command)
    processBuilder.redirectErrorStream(true) // объединить stderr с stdout
    val process = processBuilder.start()

    val output = process.inputStream
    return output.bufferedReader().readText()

}