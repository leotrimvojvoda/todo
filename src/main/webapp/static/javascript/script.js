console.log("HELLO");

$(document).ready(function () {
        $("#add-button").click(function () {
            $("#new-note").slideToggle();
        });

    $("#cancel").click(function () {
        $("#new-note").slideToggle();
    });

  /*  $(".edit-note").click(function (){
        $("#new-note").slideToggle();
        $("#new-note-textArea").text($("#current-note1").text());
    });
*/


    //specificSpan
});


function editNote(id){
$(document).ready(function (){

     $("#new-note").slideToggle();
     $("#new-note-textArea").text($("#current-note"+id).text());
});
}