<%--
    View voor bekijken van de playlist met de daar bijhorende tracks
    Requirement M1 wordt aangetoond, de JSP bestanden bevatten enkel JSP of JSTL markup
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Playlist ${playlist.getName()} | Spotitube</title>
    </head>
    <body>
        <h2>Playlist ${playlist.getName()}</h2>
        <table>
            <tr>
                <td>${playlist.getName()}</td>
                <td>${playlist.getOwner()}</td>
                <td>${playlist.getCreationDate()}</td>
                <td>${playlist.isOfflineAvailable()}</td>
            </tr>
        </table>
        <h3>Tracks</h3>
        <table>
            <tr>
                <th>Name</th>
                <th>Performer</th>
                <th>Type</th>
            </tr>
        <c:forEach items="${requestScope.tracks}" var="track">
            <tr>
                <td>${track.getName()}</td>
                <td>${track.getPerformer()}</td>
                <td>${track.getContentType()}</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
