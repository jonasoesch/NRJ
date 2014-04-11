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
    getTimegraph();

    getApi('rooms', '');
    /* surveillants */
    /* $('...').on("click", function () {

    }) */


    /* fonctions générales */
    /* function getAllPlugs() {
    } */

    // retrieves the json from the API
    function getApi(thing, id) {
        var url = "http://localhost:8080/NRJ/api/" + thing + "/"
        if (id !== "") {
            url += id
        }

        $.get('src/proxy.php', {
            thing:thing,
            id:id
        }, function (json) {
            console.log(json)
        });
    }
    // retrieves the dom of the timegraph.html
    function getTimegraph() {
        var url = "src/timegraph.html"
        $.get(url, function (html) {
            var dom = $(html)
            dom.find('h2').text('TestH2')
            $('.overall').append(dom)
        });

    }


})