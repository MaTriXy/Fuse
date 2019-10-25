package com.github.kittinunf.fuse.core

import java.nio.charset.Charset
import org.json.JSONObject

class ByteArrayDataConvertible : Fuse.DataConvertible<ByteArray> {
    override fun convertFromData(bytes: ByteArray): ByteArray = bytes
    override fun convertToData(value: ByteArray): ByteArray = value
}

class StringDataConvertible(private val charset: Charset = Charset.defaultCharset()) : Fuse.DataConvertible<String> {
    override fun convertFromData(bytes: ByteArray): String = bytes.toString(charset)
    override fun convertToData(value: String): ByteArray = value.toByteArray(charset)
}

class JsonDataConvertible(private val charset: Charset = Charset.defaultCharset()) : Fuse.DataConvertible<JSONObject> {
    override fun convertFromData(bytes: ByteArray): JSONObject = JSONObject(bytes.toString(charset))
    override fun convertToData(value: JSONObject): ByteArray = value.toString().toByteArray(charset)
}