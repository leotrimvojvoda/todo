console.log("HELLO");

$(document).ready(function () {
        $("#add-button").click(function () {
            $("#new-note").slideToggle();
            $("#new-note-textArea").focus();
        });

    $("#cancel").click(function () {
        $("#new-note").slideToggle();
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