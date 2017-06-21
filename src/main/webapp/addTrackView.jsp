<%--
    View voor het toevoegen van tracks aan de playlist
    Requirement M1 wordt aangetoond, de JSP bestanden bevatten enkel JSP of JSTL markup
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spotitube - Add Tracks</title>
    </head>
    <body>
    <a href="/playlists">Terug</a>
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
                    <td>
                        <form action="" method="post">
                            <input type="text" style="display: none;" value="${track.getTrackId()}" name="trackId">
                            <input type="submit" value="Toevoegen" name="addTrackSubmit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
