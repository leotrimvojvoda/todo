console.log("HELLO");

$(document).ready(function () {
        $("#add-button").click(function () {
            $("#new-note").slideToggle();
        });

    $("#discard").click(function () {
        $("#new-note").slideToggle();
    });

    $("#edit-note").click(function (){
        $("#new-note-textArea").text($("#current-note-text").text());
        $("#new-note").slideToggle();
    });

//edit-note
});


