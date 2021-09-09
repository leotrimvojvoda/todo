console.log("HELLO");

alert("in SETTINGS, add buttons to the table that show or hide stuff")

$(document).ready(function () {
        $("#add-button").click(function () {
            $("#new-note").slideToggle();
            $("#new-note-textArea").focus();
        });

    $("#cancel").click(function () {
        $("#new-note").slideToggle();
    });

    let tableClass = ".edit-profile-options";

    function hideTable(){$(tableClass).slideUp();}

    function fadeInFlex(className){
        $(className).fadeIn().css("display", "flex");
    }

    /*Show change username*/
    /*
    $(tableClass).click(function (){
        hideTable();
        fadeInFlex('.change-username');
    });
     */


    /*Show change password
      $(tableClass).click(function (){
        hideTable();
        $(".change-password").fadeIn();
    });
    * */

    /*Show Reset tasks

    $(tableClass).click(function (){
        hideTable();
        $(".reset-tasks").fadeIn();
    });
*/

    /*Show Delete Account*/

    $(tableClass).click(function (){
        hideTable();
        $(".delete-account").fadeIn();
    });

});

function editNote(id){
    $(document).ready(function (){
        $(".edit-buttons").slideUp()
        $("#edit-area_"+id).focus()
        $("#"+id).slideToggle();
    });
}

function editNoteFromTextArea(id){
    $(document).ready(function (){
        $(".edit-buttons").slideUp();
        var getId = id.split("_")
        $("#"+getId[1]).slideToggle();
    });
}