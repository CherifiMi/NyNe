package com.example.nyne.domein.util

import com.starry.myne.repo.models.Author
import java.util.*
import kotlin.collections.ArrayList

object BookUtils {
    fun getAutherAsString(austhers: List<Author>): String{
        return if (austhers.isNotEmpty()){
            var result: String
            if (austhers.size>1){
                result = fixAutherName(austhers.first().name)
                austhers.slice(1 until austhers.size).forEach { author ->
                    if (author.name!= "N/A") result += ", ${fixAutherName(author.name)}"
                }
            }else{
                result = fixAutherName(austhers.first().name)
            }
            result
        }else{
            "Unknown Author"
        }
    }
    fun fixAutherName(name: String): String{
        val reversed = name.split(",").reversed()
        return reversed.joinToString(separator = " "){
            return@joinToString it.trim()
        }
    }

    fun getLanguagesAsString(languages: List<String>): String {
        var result = ""
        languages.forEachIndexed { index, lang ->
            val loc = Locale(lang)
            if (index == 0) {
                result = loc.displayLanguage
            } else {
                result += ", ${loc.displayLanguage}"
            }
        }
        return result
    }

    fun getSubjectsAsString(subjects: List<String>, limit: Int): String {
        val allSubjects = ArrayList<String>()
        // strip "--" from subjects.
        subjects.forEach { subject ->
            if (subject.contains("--")) {
                allSubjects.addAll(subject.split("--"))
            } else {
                allSubjects.add(subject)
            }
        }
        val truncatedSubs: List<String> = if (allSubjects.size > limit) {
            allSubjects.toSet().toList().subList(0, limit)
        } else {
            allSubjects.toSet().toList()
        }
        return truncatedSubs.joinToString(separator = ", ") {
            return@joinToString it.trim()
        }
    }
}