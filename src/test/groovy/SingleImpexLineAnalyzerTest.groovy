import com.ulticace.impex.SingleImpexLineAnalyzer
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Matt Rossner on 5/16/16.
 */
class SingleImpexLineAnalyzerTest extends Specification {

    @Shared SingleImpexLineAnalyzer singleImpexLineAnalyzer = new SingleImpexLineAnalyzer()

    def "Not an impex line"() {
        when: "Using non impex line"

        def res = singleImpexLineAnalyzer.analyzeImpexLine("not an impex line")

        println res

        then: "Result should tell me it's not an impex line"

        res.contains('Not a valid impex line')
    }

    def "Multiple lines fail"() {
        when: "Trying multiple lines"

        def res = singleImpexLineAnalyzer.analyzeImpexLine("//                \"INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \\u001B[m\\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 3 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 3 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('category'=category,unresolved=false,ignore=false), 3=ValueEntry('Category'=null,unresolved=null,ignore=false)}]\\n\" +\n" +
                "//                \"INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \\u001B[m\\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 4 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 4 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('brand'=brand,unresolved=false,ignore=false), 3=ValueEntry('Brand'=null,unresolved=null,ignore=false)}]\\n\" +\n" +
                "//                \"INFO   | jvm 1    | main    | 2016/04/26 09:12:00.727 | \\u001B[m\\u001B[0;33mWARN  [ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 5 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 5 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('allPromotions'=allPromotions,unresolved=false,ignore=false), 3=ValueEntry('Promotion'=null,unresolved=null,ignore=false)}]\\n\" +")

        println res

        then: "It should fail"

        res.contains('Only one line at a time')
    }

    def "Unresolved item in log line"() {
        when: "Input has clear unresovled description"


        def res = singleImpexLineAnalyzer.analyzeImpexLine("[ImpExResultProcessWorker] (0000002Z) [ImpExImportReader] line 3 at main script: dumped unresolved line ValueLine[unresolvable:error finding existing item : column='solrIndexedType' value='spProductType', ,line 3 at main script,null,HeaderDescriptor[line 2 at main script, update, SolrIndexedProperty, {}, [solrIndexedType, name, displayName] ],{1=ValueEntry('spProductType'=null,unresolved=true,reason:cannot resolve value 'spProductType' for attribute 'solrIndexedType',ignore=false), 2=ValueEntry('category'=category,unresolved=false,ignore=false), 3=ValueEntry('Category'=null,unresolved=null,ignore=false)}]")

        println res

        then: "Utilty should tell me so"

        res.contains('solrIndexedType')
        res.contains('spProductType')
    }

    def "Unresolved error buried in log"() {
        when: "There is an unresolved item, but it's a bit buried in the message"

        def res = singleImpexLineAnalyzer.analyzeImpexLine("[ImpExImportReader] line 25 at main script: dumped unresolved line ValueLine[,line 25 at main script,Order,HeaderDescriptor[line 15 at main script, insert_update, Order, {}, [code, user, date, currency, net, deliveryMode, paymentMode, Discounts, calculated, site, store, status] ],{1=ValueEntry('<ignore>acceptanceTestOrder0'=null,unresolved=null,ignore=true), 2=ValueEntry('<ignore>acceptanceuser@test.com'=null,unresolved=null,ignore=true), 3=ValueEntry('<ignore>17.04.2011 15:28'=null,unresolved=null,ignore=true), 4=ValueEntry('<ignore>USD'=null,unresolved=null,ignore=true), 5=ValueEntry('<ignore>false'=null,unresolved=null,ignore=true), 6=ValueEntry('<ignore>'=null,unresolved=null,ignore=true), 7=ValueEntry('<ignore>advance'=null,unresolved=null,ignore=true), 8=ValueEntry('<ignore>'=null,unresolved=null,ignore=true), 9=ValueEntry('<ignore>false'=null,unresolved=null,ignore=true), 10=ValueEntry('electronics'=null,unresolved=true,reason:could not resolve item for electronics,ignore=false), 11=ValueEntry('electronics'=null,unresolved=true,reason:could not resolve item for electronics,ignore=false), 12=ValueEntry('<ignore>CREATED'=null,unresolved=null,ignore=true)}]")

        println res

        then: "Utilty should find those pesky unresolved items"

        res.contains('solrIndexedType')
        res.contains('spProductType')
    }
}
