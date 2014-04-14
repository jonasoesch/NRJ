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

    graphApartment('.home .overall', 'apartments', '')

    /* surveillants */
    /* $('...').on("click", function () {

    }) */


    /************* fonctions générales *******************/
    // retrieves the json from the API
    //needs the selector where to trigger, the entity to get and his optionnal ID
    function getApi(selector, thing, id) {

        $.get('src/proxy.php', {
            thing: thing,
            id: id
        }, function (json) {
            $(selector).trigger('getApi', [json])
        });

    }
    // retrieves the dom of the timegraph.html
    function getTimegraph(selector, json) {
        var url = "src/timegraph.html"
        $.get(url, function (html) {
            var dom = $(html);
            $(selector).trigger('getGraph', [dom, json])
        });
    }


    /* Fonctions de calcul */
    //Peuple le menu de navigation
    function createMenu() {
        getApi('.menu', 'rooms', '');
        $('.menu').on('getApi', function (event, rooms) {
            var ul = $('<ul>')
            $.each(rooms, function (index, room) {
                var li = $('<li>')
                $('<h2>').text(room.name).appendTo(li)
                var ul2 = $('<ul>')
                $.each(room.plugs, function (i, plug) {
                    ul2.append($('<li>').text(plug.name))
                })
                ul2.appendTo(li)
                li.appendTo(ul)
            })
            $('.menu').append(ul)
        })

    }

    //Attache le graphe de l'appartement
    function graphApartment(selector, thing, id) {
        getApi(selector, thing, id);
        $(selector).on('getApi', function (event, json) {
            getTimegraph(selector, json)
        })
        $(selector).on('getGraph', function (event, dom, json) {
            console.log(dom)
            console.log(json)
        })

    }


})