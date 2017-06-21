<%--
    View voor aanpassen van de naam van een afspeellijst
    Requirement M1 wordt aangetoond, de JSP bestanden bevatten enkel JSP of JSTL markup
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wijzigen playlist</title>
    </head>
    <body>
        <form action="" method="post">
            <input type="text" name="playlistName" value="${playlist.getName()}"/>
            <input type="text" name="oldPlaylistName" value="${playlist.getName()}" style="display: none;"/>
            <input type="submit" value="Bevestigen"/>
        </form>
    </body>
</html>
