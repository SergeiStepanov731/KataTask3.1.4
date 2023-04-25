$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.myForm #id').val(user.id);
            $('.myForm #name').val(user.name);
            $('.myForm #username').val(user.username);
            $('.myForm #age').val(user.age);
            $('.myForm #email').val(user.email);
            $('.myForm #password').val(user.password);
            $('.myForm #role').val(user.role);
        }) ;$('.myForm #exampleModal').modal();
    });
    $('.table .delBtn').click(function (event) {
        event.preventDefault();

        var href = $(this).attr('href');
        $.get(href,function (user,status){
            $('.DelForm #DelId').val(user.id);
            $('.DelForm #DelName').val(user.name);
            $('.DelForm #DelUsername').val(user.username);
            $('.DelForm #DelAge').val(user.age);
            $('.DelForm #DelEmail').val(user.email);
            $('.DelForm #DelPassword').val(user.password);
            $('.DelForm #DelRoles').val(user.roles);
        });

        $('#DeleteModal').modal();
    });
});