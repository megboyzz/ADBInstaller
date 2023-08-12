package ru.megboyzz.data.core.parsers

import java.io.InputStream
import java.io.OutputStream

fun copyStreams(
    src: InputStream,
    dest: OutputStream
){
    src.copyTo(dest)
}