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
                window.open("https://github.com/login/oauth/authorize?client_id=b8abe9236e3d41cb22d3&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                window.localStorage.setItem("colsable",true);


            }
            console.log(response)

        },
        dataType:"json"
    });
}