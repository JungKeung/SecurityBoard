$(document).ready(function() {
    $(document).on('click', '#keywordSearchBtn', function() {
        var type = $('select[name=type]').val();
        var keyword = $('input[name=keyword]').val();
        var data = {
                  'type' : type,
                  'keyword' : keyword
                }
        $.ajax({
            url:'/board/list',
            type:'GET',
            data: data,
            dataType : "json",
            success : function(result) {
                if (result.errorCode === 0) {
                    location.href = "/board/list";
                } else {
                    alert("검색에 실패하였습니다. 잠시 후 다시 시도해주세요.");
                }
            },
            error : function(err) {
                console.log(err);
                console.log(err.responseJSON.message);
                alert("접근이 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
            }
        });
    });
});