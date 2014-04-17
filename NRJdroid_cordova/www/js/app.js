$(document).ready(function () {

    getApi();

});

//Connexion à l'API
/**************************************************************/

function getApi() {
        $.get('apiRoom.json', {
        }, function (json) {
            console.log(json);
        });
    }

