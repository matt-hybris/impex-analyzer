package com.ulticace.impex

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Created by Matt Rossner on 5/8/16.
 */
@EqualsAndHashCode
@ToString
class ValueEntry {

    String fieldName
    boolean unresolved
    boolean ignore
    String unresolvedReason

}
