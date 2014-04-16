$(function () {
    /* actions au démarrage */
    createMenu();

    if ($('body>section').hasClass('home')) {
        attachApart('1');
    } else {
        if (getURLParameter('id') != null) {
            var id = getURLParameter('id');
            attachRoom(id);
            attachPlugs(id);
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
                $(ul2).attr('id', room.roomId)
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
                        //Gestion display underMenu
            if (getURLParameter('id') != null) {
                showUnderMenus(id);
            } else {
                hideUnderMenus();
            }
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
            dom.find('.onoff').show();
            dom.find('.alwayson').hide();
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
            dom.find('.onoff').show();
            dom.find('.alwayson').hide();
            $(selector).append(dom)
        })
    }
    //Attache le graph détaillé de la plug
    function attachPlugs(id) {
        var selector = '.room .plug';
        getApi(selector, 'rooms', id);
        $(selector).on('getApi', function (event, json) {
            getTimegraph(selector, json);
        });

        $(selector).on('getGraph', function (event, dom, json) {
            var domString = "";
            var html = dom;
            $.each(json.plugs, function (index, plug) {
                var tempDOM = html.clone();
                tempDOM.attr('id', plug.plugId);
                tempDOM.find('h2').text(plug.name);
                if (plug.alwaysOn) {
                    tempDOM.find('.onoff').hide();
                    tempDOM.find('.alwayson').show();
                } else {
                    tempDOM.find('.onoff').show();
                    tempDOM.find('.alwayson').hide();
                }
                $(selector).append(tempDOM);
            });
        });
    }


        /*-------------------------------------*/
        /*Gestion de l'interaction Menu - Début*/
        /*-------------------------------------*/
        function hideUnderMenus(){
            $('.menu').css("background-color", 'pink');
            $('.menu>ul>li>ul').hide();
        };
        function showUnderMenus(id){
            $("#"+id).css("background-color", 'blue');
            $('.menu>ul>li>ul').hide();
            $("#"+id).show();
        }
        
        //Flèche sur sous menu
        $('.menu').on('click', 'ul > li', function () {
            var liPosition = $(this).position();
            var liHeight = $('h2').height();
            $("#whiteArrow").css("top", liPosition.top + liHeight * 1.2);
   

        });
        //Flèche sur home
        $('.menu').on('click', 'h1', function () {
            $("#whiteArrow").css("top", '95px');
        });


        /*-------------------------------------*/
        /*Gestion de l'interaction Menu - Fin*/
        /*-------------------------------------*/
        /*-------------------------------------*/
        /*Gestion du bouton On/Off - Début*/
        /*-------------------------------------*/
        $('.bouton').click(function () {
            var value = $(this).val();
            if(value =="ON"){
                $(this).css("background", "url('../img/off.png') center center");
            }else{
                $(this).css("background", "url('../img/on.png') center center");
            }
            
        });
        /*-------------------------------------*/
        /*Gestion du bouton On/Off - Fin*/
        /*-------------------------------------*/


})