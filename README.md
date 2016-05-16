# impex-analyzer
Utility project to analyze not so user friendly impex errors. 

If you've worked with hybris before you've for sure had some impex errors and you may have noticed the logs can be verbose. 

Most of the time they do tell you what's wrong, but you often have to sift through it. This utility takes impex logs and 
tells what's wrong. For now it's very basic but I plan to make it better as time goes on.

This initial commit only detects unresolved lines.

In the mean time, feel free to send me impex errors that it doesn't recognize and I'll try to add as many cases as possible.

##Usage

I'm using Spring boot, for now you can just run it with the Gradle wrapper.

`gradlew bootRun`

Then [http://localhost:8080/single](http://localhost:8080/single)

If I can actually get this into a nicer state I may host it on AWS or something. 
