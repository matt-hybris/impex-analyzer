package com.ulticace.impex

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Created by Matt Rossner on 5/8/16.
 */
@EqualsAndHashCode
@ToString
class ImpexLine {

    int lineNumber
    Collection<ValueEntry> entries
    ImpexType impexType

}
