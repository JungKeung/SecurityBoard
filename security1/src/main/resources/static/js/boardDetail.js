$(document).ready(function() {
    $(document).on('click', '#btnDelete', function() {
        var isDelete = confirm("해당 게시글을 삭제하시겠습니까?");
        var boardId = Number($("#boardId").val());

        if (isDelete) {
            $.ajax({
                url:'/board/delete?id='+boardId,
                type:'GET',
                dataType : "json",
                success : function(result) {
                    if (result.errorCode === 0) {
                        alert("정상적으로 삭제되었습니다.");
                        location.href = "/board/list";
                    } else {
                        alert("게시물 삭제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
                    }
                },
                error : function(err) {
                    console.log(err);
                    console.log(err.responseJSON.message);
                    alert("접근이 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
                }
            });
        }
    });
});