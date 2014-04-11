/* TODO
- Au chargement de la page
-- Menu latéral :
--- Récupération des pièces, et de leur prises
-- Home :
--- Récupération de l'appartment, de ses données totales et append du bloc 'timegraph'
- Fonctions 
-- ???

*/
$(function () {
    /* actions au démarrage */
    createMenu();

    /* surveillants */
    /* $('...').on("click", function () {

    }) */


    /************* fonctions générales *******************/
    // retrieves the json from the API
    function getApi(thing, id) {
        var url = "http://localhost:8080/NRJ/api/" + thing + "/"
        if (id !== "") {
            url += id
        }

        var responseJSON = $.get('src/proxy.php', {
            thing: thing,
            id: id
        }, function (json) {
            return json
        });
        return responseJSON
    }
    // retrieves the dom of the timegraph.html
    function getTimegraph() {
        var url = "src/timegraph.html"
        var responseDOM = $.get(url, function (html) {
            var dom = $(html);
            return dom
        });
        return responseDOM
    }


    /* Foncitons de calcul */
    function createMenu() {
        var rooms = getApi('rooms', '');
        var ul = $('<ul>')
        $.each(rooms, function (index, room) {
            var li = $('<li>')
            $('<h2>').text(room.name).appendTo(li)
            var ul2 = $('<ul>')
            $.each(room.plugs, function (i, plug) {
                ul2 .append($('<li>').text(plug.name))
            })
            ul2.appendTo(li)
            li.appendTo(ul)
        })
        $('.menu').append(ul)
    }


})