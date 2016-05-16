package com.ulticace.impex

import com.google.common.base.Splitter
import org.springframework.stereotype.Component

/**
 * Created by Matt Rossner on 5/16/16.
 */
@Component
class SingleImpexLineAnalyzer {


    def analyzeImpexLine(String line) {

        if (line.find('\r|\n|\r\n')) {
            return "Only one line at a time please... Soon we'll support more, but for now just one OK??"
        }

        if (!line.contains('ImpExImportReader')) {
            return "Not a valid impex line"
        }

        if (line.contains('unresolvable:error finding existing item')) {
            def m = (line =~ /unresolvable:error finding existing item : column='(\w+|\d+)' value='(\w+|\d+)'/)

            m.find()

            def col = m.group(1)
            def val = m.group(2)

            return "Impex failed because of unresolved items. You have a column \"$col\" and with a value \"$val\". " +
                    "Impex is trying to look up an existing $col with the value of $val, but $val doesn't exist!"

        }

        if (line.contains('unresolved=true')) {
            def m = (line =~ /insert_update, (\w|\d)+, \{\}, \[([^\]]+)\]/)
            m.find()
            def allCols = Splitter.on(',').splitToList(m.group(2).replace(' ',''))
            def unresolvedCols = [:]


            line.findAll(/\d+=ValueEntry\([^\)]+\)/).findAll {
                it.contains('unresolved=true')
            }.each {
                def colNum = it.find(/^\d+/).toInteger() - 1
                unresolvedCols.put(allCols.get(colNum), it.find(/reason:[^,]+/))
            }

            def result = 'Found ' + unresolvedCols.size() + ' line(s): \n'
            unresolvedCols.each {k,v ->
                result += "\tColumn \"$k\", reason is: \"$v\"\n"
            }
            return "You've got some unresolved lines there chief...\n" + result
        }
    }
}
