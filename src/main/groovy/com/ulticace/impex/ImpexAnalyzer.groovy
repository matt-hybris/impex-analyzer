package com.ulticace.impex

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by Matt Rossner on 5/8/16.
 */
class ImpexAnalyzer {

    static Logger LOG = LoggerFactory.getLogger(ImpexAnalyzer.class)

    Collection<ImpexLine> readLines(String input) {

        Collection<ImpexLine> result



        input.readLines().findAll {
            it.contains('ImpExImportReader') && it.contains('dumped unresolved line')
        }.each { line ->


            def impexLine = new ImpexLine()
            Collection<ValueEntry> entries = []

            impexLine.entries = entries

            def valueEntries = line.findAll(/\d=ValueEntry\([^\)]+\)/)

            if (LOG.isDebugEnabled()) {
                valueEntries.each { println it }
            }

            valueEntries.each {

                def entry = new ValueEntry()
                def fieldNameMatcher = (it =~ /'((\w|\d)+)'=/)

                if (fieldNameMatcher.find()) {
                    entry.fieldName = fieldNameMatcher.group(1)
                }

                entry.ignore = findBooleanFromString(it, 'ignore')
                entry.unresolved = findBooleanFromString(it, 'unresolved')

                if (entry.unresolved) {
                    entry.unresolvedReason = findUnresolvedReason(it)
                }

                entries << entry

                LOG.debug(entry.toString())
            }



            valueEntries.findAll {
                it.contains('unresolved=true')
            }.each {

                println it
                println it.find(/reason:[^,]+/)

            }
        }
    }

    def findUnresolvedReason(String input) {
        def matcher = (input =~ /reason:([^,]+)/)
        if (matcher.find()) {
            matcher.group(1)
        }

    }

    def findBooleanFromString(String input, String searchValue) {
        def matcher = (input =~ /$searchValue=(\w+)/)
        if (matcher.find()) {
            Boolean.parseBoolean(matcher.group(1))
        }
    }

}
