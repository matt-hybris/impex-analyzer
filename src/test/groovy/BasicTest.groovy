import com.ulticace.impex.ImpexAnalyzer
import com.ulticace.impex.ImpexLine
import spock.lang.Specification

/**
 * Created by Matt Rossner on 5/8/16.
 */
class BasicTest extends Specification {


    def "run basic" () {

        setup:

//        def input = "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;32mINFO  [hybrisHTTP32] [DefaultImportService] Starting import synchronous using cronjob with code=00000030\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;32mINFO  [hybrisHTTP32] (00000030) [ImpExImportJob] Starting multi-threaded ImpEx cronjob \"ImpEx-Import\" (4 threads)\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;32mINFO  [hybrisHTTP32] (00000030) [Importer] Finished 1 pass in 0d 00h:00m:00s:006ms - processed: 16, dumped: 16 (last pass: 0)\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;32mINFO  [hybrisHTTP32] (00000030) [Importer] Starting pass 2\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 3 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 3 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('category'=category,unresolved=false,ignore=false), 3=ValueEntry('Category'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 4 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 4 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('brand'=brand,unresolved=false,ignore=false), 3=ValueEntry('Brand'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 5 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 5 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('allPromotions'=allPromotions,unresolved=false,ignore=false), 3=ValueEntry('Promotion'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 6 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 6 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('availableInStores'=availableInStores,unresolved=false,ignore=false), 3=ValueEntry('Stores'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 8 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 8 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('description'=description,unresolved=false,ignore=false), 3=ValueEntry('Description'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 9 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 9 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('code'=code,unresolved=false,ignore=false), 3=ValueEntry('Code'=null,unresolved=null,ignore=false)}]\n" +
//                "INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \u001B[m\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 7 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 7 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('manufacturerName'=manufacturerName,unresolved=false,ignore=false), 3=ValueEntry('Manufacturer Name'=null,unresolved=null,ignore=false)}]"

        def input = '\u001B[;0;33mINFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | WARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 8 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column=\'solrIndexedType\' value=\'spProductType\', ,line 8 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry(\'spProductType\'=null,unresolved=true,reason:cannot resolve value \'spProductType\' for attribute \'solrIndexedType\',ignore=false), 2=ValueEntry(\'description\'=description,unresolved=false,ignore=false), 3=ValueEntry(\'Description\'=null,unresolved=null,ignore=false)}]'


        when: "read it"



        Collection<ImpexLine> lines = new ImpexAnalyzer().readLines(input)


//        lines.each { println it }


        then: "Find some stuff"


        lines
        !lines.empty




    }


}
