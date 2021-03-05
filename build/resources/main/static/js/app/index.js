let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        <!--btn-update라는 id를 가진 html엘리먼트에 click이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록.-->
        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

    },
    save : function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data) //json으로 변환.
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function(){
        let data = {
            title:$('#title').val(),
            author:$('#author').val(),
            content:$('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',<!--PostApiController의 API에서 이미 @PutMapping으로 선언했기에 여기도 Put으로 맞춰야함.-->
            url: '/api/v1/posts/'+id,<!--어느 게시글을 수정할 지 URL Path로 구분하기 위해 Path에 id를 추가.-->
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    delete : function(){
        let id = $('#id').val();

        $.ajax({
            type:'DELETE',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
        }).done(function(){
            alert('글을 삭제했습니다.');
            window.location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
};
main.init();