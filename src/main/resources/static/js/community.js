function post() {
    var questionId =$("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
            "parent_id":questionId,
            "content":content,
            "type":1
        }),
        success:function (response) {
            if (response.message==="成功")
            {
                $("#comment_section").hide();
            }else {
                alert(response.message);
            }
            console.log(response)

        },
        dataType:"json"

    });


}