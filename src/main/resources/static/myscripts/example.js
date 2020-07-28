$('document').ready(function () {
    $('.btn-danger').on('click', function () {
        let id = $(this).attr('data-id');
        let name = $(this).attr('data-name');
        let roles = $('#user' + id).text().trim().split(" ");
        $('#delete #id').val(id);
        $('#delete #name').val(name);
        $('#delete #roles').empty();
        $.each(roles, function(key, value) {
            $('#delete #roles').append('<option value="' + key + '">' + value + '</option>');
        });
        let href = $(this).attr('href');
        $('#delete #hrefDelete').attr('href', href);
    })
    $('.btn-info').on('click', function () {
        let id = $(this).attr('data-id');
        let name = $(this).attr('data-name');
        $('#idEdite').val(id);
        $('#nameEdite').val(name);
    })
})