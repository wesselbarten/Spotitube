<%--
    View voor het bekijken van alle playlists
    Requirement M1 wordt aangetoond, de JSP bestanden bevatten enkel JSP of JSTL markup
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spotitube - Playlist</title>
    </head>

    <body>
    <h1>Playlists</h1>
    <h3>Total duration: ${totalPlaylistDuration}</h3>
    <table style="width: 100%">
        <c:forEach items="${requestScope.allPlaylists}" var="playlist">
        <tr>
            <td><a href="/playlist?name=${playlist.getName()}">${playlist.getName()} </a></td>
            <td><a href="/editplaylistname?name=${playlist.getName()}">Naam wijzigen</a></td>
            <td><a href="/addtracks?id=${playlist.getPlaylistId()}">Track toevoegen</a></td>
            <td>
                <form action="" method="post">
                    <input type="text" value="${playlist.getName()}" name="playlistName" style="display: none"/>
                    <input type="submit" value="Verwijderen" name="deletePlaylistSubmit"/>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>

    <div>
        <form action="" method="post">
            <input type="text" name="newPlaylistName"/>
            <input type="submit" value="Playlist toevoegen" name="addPlaylistSubmit"/>
        </form>
    </div>

    </body>
</html>
