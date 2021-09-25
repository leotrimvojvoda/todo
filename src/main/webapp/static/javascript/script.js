console.log("Todo App - Logging...");

$(document).ready(function () {
/*
    $notes = $('.notes-table');
    $.ajax({type: 'GET',
        url: 'api/getTasks',
        success: function (tasks){
            $.each(tasks, function (i, task){
                $notes.append('<h1 class=\"this-is-awesome\">'+task.text+'</h1>');
            });
        },
        error: function (){
            alert("Error loading tasks");
        }
    });*/


        $("#add-button").click(function () {
            $("#new-note").slideToggle();
            $("#new-note-textArea").focus();
        });

    $("#cancel").click(function () {
        $("#new-note").slideToggle();
    });

    function hideTable(){
        $(".cancel").fadeOut();
        $(".edit-profile-options").slideUp();
    }

    function showTable(){
        $(".edit-profile").fadeOut(function (){
            $(".edit-profile-options").slideDown(function (){
                $(".cancel").fadeIn();
                $(".input").val('');
            });
        });
    }

    function fadeInFlex(className){
        $(className).fadeIn().css("display", "flex");
    }


    /*
    *
    *     SHOW FORMS HIDE TABLE
    *
    * */

    /*Show change username*/
    $(".change-username").click(function (){
        console.log("RAN CHANGE USERNAME");
        hideTable();
        fadeInFlex('.change-username-form');
    });


    /*Show change password*/
      $(".change-password").click(function (){
          console.log("RAN CHANGE PASSWORD");
        hideTable();
        $(".change-password-form").fadeIn();
    });

    /*Show Reset tasks*/
    $(".reset-tasks").click(function (){
        console.log("RAN RESET TASK");
        hideTable();
        $(".reset-tasks-form").fadeIn();
    });

    /*Show Delete Account*/
    $(".delete-account").click(function (){
        console.log("RAN DELETE ACCOUNT");
        hideTable();
        $(".delete-account-form").fadeIn();
    });

    $(".change-cancel").click(function (){
        showTable();
    });
});


/*
*
*FUNCTIONS
*
*/


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

function changeText(){
    $(document).readOnly(function (){
        $("someText").load("https://cros-anywhere.herokuapp.com/http://carnes.cc/code/ajax_example.txt");
    })
}

/*
function back(){
    $(document).ready(function (){
        $(".change-cancel").fadeOut();
        $(".edit-profile").slideUp(
            function (){
                $(".edit-profile-options").slideDown();
            }
        );
    });
}*/