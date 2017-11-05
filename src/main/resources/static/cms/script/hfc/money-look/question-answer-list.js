function checkFiled(){
    var title=$("#title_input").val();
    if(title.trim()==""){
        $("#title_input_info").css("color","red");
        $("#title_input_info").html("* 答案必须填写");
        return false;
    } else {
        $("#title_input_info").css("color","auto");
         $("#title_input_info").html("* 答案");
    }
    return true;
}
$(document).ready(function () {
    $("#submit-btn").click(function() {
        var allCheck = checkFiled();
        if (allCheck) {
            $("#hfc-form").ajaxSubmit(function(data){
                layer.alert('添加成功!', {
                  closeBtn: 0
                }, function(){
                  window.parent.location.reload();
                  //layer.prompt("dsfssadsd");
                });

            });
        }
    });

    $(".to_delete").click(function() {
        var objectId = $(this).parent().attr("targetId");

        layer.confirm('确定要删除吗？', {
          btn: ['取消','确定'] //按钮
        }, function(){
          layer.closeAll();
        }, function(){
          deleteArtistSubmit(objectId);
        });
    });
});

function deleteArtistSubmit(objectId){
    var url = $("#delete-url").val();
    url = url.replace("{id}",objectId);
    var data_send = {};

    var delete_request =$.ajax({
       type: 'post',
       url: url,
       data: data_send,
       dataType: 'json'
    });

    delete_request.fail(function( jqXHR, textStatus ) {
      if(jqXHR.status==401){
         //openWeiboLogin();

      }
    });

    delete_request.done(function(data) {
            window.location.reload();
    });

}

