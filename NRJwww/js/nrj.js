$(function () {
    /* actions au démarrage */
    createMenu();


    if ($('body>section').hasClass('home')) {
        attachApart('1')
    } else {
        if (getURLParameter('id') != null) {
            var id = getURLParameter('id')
            attachRoom(id)
            attachPlugs(id)
        } else {
            $('body > section').append('Veuillez sélectionner un appartement.')
        }
    }



    /* surveillants */


    /************* fonctions générales *******************/
    /*****************************************************/
    // retrieves the json from the API
    //@param selector The jQuery element where the trigger is set
    function getApi(selector, thing, id) {
        $.get('src/proxy.php', {
            thing: thing,
            id: id
        }, function (json) {
            $(selector).trigger('getApi', [json])
        });
    }
    // retrieves the dom of the timegraph.html
    //@param selector The jQuery element where the trigger is set
    function getTimegraph(selector, json) {
        var url = "src/timegraph.html"
        $.get(url, function (html) {
            var dom = $(html);
            $(selector).trigger('getGraph', [dom, json])
        });
    }

    function getURLParameter(name) {
        return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [, ""])[1].replace(/\+/g, '%20')) || null;
    }
    /************** Fonctions de calcul ******************/
    /*****************************************************/

    //Peuple le menu de navigation
    function createMenu() {
        getApi('.menu', 'rooms', '');
        $('.menu').on('getApi', function (event, rooms) {
            var ul = $('<ul>')
            $.each(rooms, function (index, room) {
                var li = $('<li>')
                $('<h2>').append(
                    $('<a>').attr('href', 'room.php?id=' + room.roomId).text(room.name)
                ).appendTo(li)
                var ul2 = $('<ul>')
                $.each(room.plugs, function (i, plug) {
                    ul2.append(
                        $('<li>').append(
                            $('<a>').attr('href', 'room.php?id=' + room.roomId + '#' + plug.plugId).text(plug.name)
                        )
                    )
                })
                ul2.appendTo(li)
                li.appendTo(ul)
            })
            $('.menu').append(ul)
        })

    }

    //Attache le graph général de l'appartement
    function attachApart(id) {
        var selector = '.home .overall'
        getApi(selector, 'apartments', id);
        $(selector).on('getApi', function (event, json) {
            getTimegraph(selector, json)
        })
        $(selector).on('getGraph', function (event, dom, json) {
            dom.find('h2').text(json.name)
            $(selector).append(dom)
        })
    }
    
    //Attache le graph général de la room actuelle
    function attachRoom(id) {
        var selector = '.room .overall'
        getApi(selector, 'rooms', id);
        $(selector).on('getApi', function (event, json) {
            getTimegraph(selector, json)
        })
        $(selector).on('getGraph', function (event, dom, json) {
            dom.find('h2').text(json.name)
            $(selector).append(dom)
        })
    }
    //Attache le graph détaillé de la plug
    function attachPlugs(id) {
        var selector = '.room .plug'
        getApi(selector, 'rooms', id);
        $(selector).on('getApi', function (event, json) {
            getTimegraph(selector, json)
        })
        $(selector).on('getGraph', function (event, dom, json) {
            $.each(json.plugs, function (index, plug) {
                dom.attr('id', plug.plugId)
                dom.find('h2').text(plug.name)
                $(selector).append(dom)
            })

        })
    }
})