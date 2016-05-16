<html>
<head>
    <title>Impex Analyzer</title>
</head>
<body>
    <h1>Impex Analyzer</h1>

    <form action="analyze" method="post">
        <label for="impexLine">Enter a single impex error line:</label> <br/>
        <textarea cols="140" rows="10" id="impexLine" name="impexLine">${impexLine}</textarea><br/>
        <input type="submit" value="Analyze"/>
    </form>

    <p>${analysis}</p>

</body>
</html>